package steam.pageObject.forms;

import framework.BaseForm;
import framework.PropertyReader;
import org.openqa.selenium.By;
import steam.pageObject.menu.LanguageMenu;
import steam.pageObject.menu.NavigateBarMenu;


public class MainPage extends BaseForm {

    private static String locatorLbl = "//span[contains(text(), '%s')]";
    private static By lbllanguage = By.xpath(String.format(locatorLbl,PropertyReader.getProperty("Language")));
    public static NavigateBarMenu navigateBarMenu = new NavigateBarMenu();
    public static LanguageMenu languageMenu = new LanguageMenu();


    protected MainPage(By locator) {
        super(locator);
    }

    public static By getLbllanguage() {
        return lbllanguage;
    }

    }

