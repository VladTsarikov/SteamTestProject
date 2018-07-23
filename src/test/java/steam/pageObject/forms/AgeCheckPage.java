package steam.pageObject.forms;

import framework.BaseForm;
import framework.PropertyReader;
import framework.elements.Button;
import org.openqa.selenium.By;

public class AgeCheckPage extends BaseForm {

    private static String btnViewPage = "//span[contains(text(),'Открыть страницу')]";
    private static String btnViewPageFormat = String.format(btnViewPage, PropertyReader.getProperty("ViewPage"));
    private static By locator = By.xpath(btnViewPageFormat);

    public AgeCheckPage() {
        super(locator);
    }

    public static By getLocator() {
        return locator;
    }

    private static Button btnConfirm = new Button(locator);


    public static void clickConfirmButton(){
        btnConfirm.click();
    }

    public static boolean isPresented(){
       return btnConfirm.isPresent(locator);
    }
}
