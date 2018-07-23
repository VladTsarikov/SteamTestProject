package steam.pageObject.forms;

import framework.BaseForm;
import framework.PropertyReader;
import framework.RegularExpFinder;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class SpecialPage extends BaseForm {

    private static List<String> discountlPersent;
    private final static String DISCOUNT_REGULAR_EXP = "<span>-(.*)%<\\/span>";
    private static By persentFormatPath = By.xpath("//div[@class='col search_discount responsive_secondrow']");
    private static By oldPriceFormatPath = By.xpath("//div[@class='col search_price discounted responsive_secondrow']/span/strike");
    private static By actualPriceFormatPrice = By.xpath("//div[@class='col search_price discounted responsive_secondrow']");
    private static String specialsTitle = "//h2[contains(text(),'%s')]";
    private static String specialsTitleFormat = String.format(specialsTitle, PropertyReader.getProperty("SpecialsTitle"));
    private static By locator = By.xpath(specialsTitleFormat);
    private static int iterator;
    private final static String SPLIT_EXPRESSION = "\\$";
    private static int discount = 0;
    private static int index = 1;
    private final static int FULL_PERSENT = 100;
    private static double oldPrice;
    private static double actualPrice;
    private static Label lblPersent = new Label(persentFormatPath);
    private static Label lblOldPrice = new Label(oldPriceFormatPath);
    private static Label lblActualPrice = new Label(actualPriceFormatPrice);


    public static By getLocator() {
        return locator;
    }

    public static double getOldPrice() {
        return oldPrice;
    }

    public static double getActualPrice() {
        return actualPrice;
    }

    public SpecialPage(By locator) {
        super(locator);
    }

    public static int getDiscount() {
        return discount;
    }

    public static void getSpecialsGame(){

        discountlPersent = RegularExpFinder.findByRegularExp(DISCOUNT_REGULAR_EXP);
        getMaxPersent();

        oldPrice = Double.parseDouble(getListValue(lblOldPrice));
        actualPrice = Double.parseDouble(getListValue(lblActualPrice));

        assertTrue(checkDiscount());

        clickMaxDiscountGame();
        verifyRightPage();

    }

    private static void clickMaxDiscountGame(){

        List<WebElement> webElements = lblPersent.getElements();
        webElements.get(iterator).click();

    }

    /**
     *
     * @param label
     * @return price value for the maximum discount
     */
    private static String getListValue(Label label){
        List<WebElement> webLs = label.getElements();
        String value = webLs.get(iterator).getText();
        String[] splitValue = value.split(SPLIT_EXPRESSION);
        index++;
        return  splitValue[index-1];

    }

    private static void getMaxPersent(){
        for(String persent: discountlPersent) {
            if (Integer.parseInt(persent) >= discount) {
                discount = Integer.parseInt(persent);
                iterator = discountlPersent.indexOf(persent);
            }
        }
    }

    private static void verifyRightPage() {

        if(AgeCheckPage.isPresented()){
            AgeCheckPage.clickConfirmButton();
        }else if(AgeVerifyPage.isPresented()){
            AgeVerifyPage.confirmYear();
        }

    }

    private static boolean checkDiscount(){

        int actualPersent = FULL_PERSENT - (int) Math.round( actualPrice*FULL_PERSENT/oldPrice);
        return (actualPersent == discount);

    }


}

