package steam.pageObject.forms;
import framework.BaseForm;
import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import static org.testng.Assert.assertEquals;

public class DiscountGamePage extends BaseForm {

    private static By currentSpecial = By.xpath("//div[@class='discount_pct']");
    private static By actualPrice = By.xpath("//div[@class='game_area_purchase_game_wrapper']//div[@class='discount_final_price']");
    private static By originalPrice = By.xpath("//div[@class='game_area_purchase_game_wrapper']//div[@class='discount_original_price']");
    private static String discountGamedecription = "//h2[contains(text(),'%s')]";
    private static String discountGamedecriptionFormat = String.format(discountGamedecription, PropertyReader.getProperty("GameDescription"));
    private static By locator = By.xpath(discountGamedecriptionFormat);
    private static By btnDownloadPath = By.xpath("//a[@class='header_installsteam_btn_content']");
    private final static int BEGIN_INDEX = 1;
    private final static int ENDIN_PERSENT_INDEX = 3;
    private final static int ENDIN_ACTUAL_PRICET_INDEX = 5;

    private Label lblCurкentDiscount = new Label(currentSpecial);
    private Label lblActualPrice = new Label(actualPrice);
    private Label lblOriginalPrice = new Label(originalPrice);
    private Button btnDownload = new Button(btnDownloadPath);

    public DiscountGamePage(By locator) {
        super(locator);
    }

    public static By getBtnDownloadPath() {
        return btnDownloadPath;
    }

    public static By getLocator() {
        return locator;
    }

    public void verifyCurrentSpecial(){

        int persent = Integer.parseInt(lblCurкentDiscount.getElement().getText().substring(BEGIN_INDEX,
                                                                                            ENDIN_PERSENT_INDEX));
        double actualPrice = Double.parseDouble(lblActualPrice.getElement().getText().substring(BEGIN_INDEX,
                                                                                                ENDIN_ACTUAL_PRICET_INDEX));
        double originalPrice = Double.parseDouble(lblOriginalPrice.getElement().getText().substring(BEGIN_INDEX));

        assertEquals(persent ,SpecialPage.getDiscount());
        assertEquals(actualPrice ,SpecialPage.getActualPrice());
        assertEquals(originalPrice ,SpecialPage.getOldPrice());

    }

    public void clickDownloadButton(){
        btnDownload.click();
    }

}
