package steam.pageObject.menu;

import framework.BaseEntity;
import framework.PropertyReader;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LanguageMenu  extends BaseEntity{


    private static String actualLanguage;
    private static String lang = PropertyReader.getProperty("PagesLanguage");
    private static By cmbLanguages = By.xpath("//span[@id='language_pulldown']");
    private static By languagesList = By.xpath("//a[@class='popup_menu_item tight']");
    protected static Label lblLanguage = new Label(cmbLanguages);
    protected static Label lblLanguageName = new Label(languagesList);
    private List<WebElement> webList;

    public enum Language  {

        RU("Русский"), EN("English");

        private String additLanguage;

        Language(String additLanguage) {
            this.additLanguage = additLanguage;
        }

        public String getAdditLanguage() {
            return additLanguage;
        }

    }

    public void checkLanguage(){
        if(!lblLanguage.getElement().getText().equals(PropertyReader.getProperty("Language"))) {
            lblLanguage.getElement().click();
            webList = lblLanguageName.getElements();
            setLanguage();
        }
    }

    public static String getLanguage() {

        Language[] languages = Language.values();

        for (Language language : languages) {
            if (language.toString().equals(lang)) {
                actualLanguage = language.getAdditLanguage();
            }
        }
        return actualLanguage;
    }

    private void setLanguage(){
        for (WebElement language : webList) {
            String[] formatlanguage = language.getText().split(" ");
            String formLang = formatlanguage[0];
            if (formLang.equals(LanguageMenu.getLanguage())) {
                language.click();
                break;
            }
        }
    }

}
