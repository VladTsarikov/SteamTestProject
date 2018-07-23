package steam.pageObject.menu;

import framework.BaseEntity;
import framework.PropertyReader;
import framework.elements.Label;
import org.openqa.selenium.By;

public class NavigateBarMenu extends BaseEntity {

    private static By lblXpath;
    private static String btnGamesPath = "//a[@class='pulldown_desktop'][text()='%s']";
    private static String btnActionPath = "//div[@id='genre_flyout']//a[@class='popup_menu_item'][contains(text(),'%s')]";
    private static Label lblMenu = new Label(By.xpath(btnGamesPath));
    private static Label lblMenuCameGenre = new Label(By.xpath(btnActionPath));

    public enum LabelName {

        YOU_STORE("YouStore"), GAMES("Games"), SOFTWARE("Software"), HARDWARE("Hardware");

        String category;

        LabelName(String category){
            this.category = category;
        }
    }

    public enum GameGenre{

        ACTION("Action"), ADVENTURE("Adventure"), CASUAL("Casual"), INDIE("Indie");

        String genre;

        GameGenre(String genre){
            this.genre = genre;
        }
    }


    public void navigateMenu(LabelName labelName, GameGenre gameGenre){

        String category = labelName.category;
        formatPath(category,lblMenu,btnGamesPath);
        lblMenu.moveToElement();

        String genre = gameGenre.genre;
        formatPath(genre,lblMenuCameGenre,btnActionPath);
        lblMenuCameGenre.click();

    }

    public static void formatPath(String additInfo, Label label, String path ){
        String langCategory = PropertyReader.getProperty(additInfo);
        String btnGamesFormat = String.format(path,langCategory);
        lblXpath = By.xpath(btnGamesFormat);
        label.setLocator(lblXpath);

    }


}
