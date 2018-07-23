package steam.pageObject.forms;

import framework.BaseForm;
import framework.PropertyReader;
import framework.elements.Button;
import org.openqa.selenium.By;

public class ActionGamePage extends BaseForm {

    private static String txtTitle ="//h2[contains(text(), '%s')]";
    private static String txtTitleFormat = String.format(txtTitle, PropertyReader.getProperty("ActionsTitle"));
    private static By locator = By.xpath(txtTitleFormat);

    private final static By btnAllSpecials = By.xpath("//div[@class='contenthub_specials_see_more']//span");

    private Button btnSpecials = new Button(btnAllSpecials);

    public ActionGamePage(By locator) {
        super(locator);
    }


    public void clickActionsButton(){

        btnSpecials.click();

    }

    public static By getLocator() {
        return locator;
    }
}
