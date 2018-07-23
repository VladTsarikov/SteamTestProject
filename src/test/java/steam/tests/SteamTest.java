package steam.tests;

import framework.BaseEntity;
import org.testng.annotations.Test;
import steam.pageObject.forms.*;
import static steam.pageObject.menu.NavigateBarMenu.GameGenre.ACTION;
import static steam.pageObject.menu.NavigateBarMenu.LabelName.GAMES;


public class SteamTest extends BaseEntity {


   @Test
    public void steamTest(){

       MainPage.languageMenu.checkLanguage();
       MainPage.navigateBarMenu.navigateMenu(GAMES,ACTION);

       ActionGamePage actionGamePage = new ActionGamePage(ActionGamePage.getLocator());
       actionGamePage.clickActionsButton();

       SpecialPage specialPage = new SpecialPage(SpecialPage.getLocator());
       specialPage.getSpecialsGame();

       DiscountGamePage specialGamePage = new DiscountGamePage(DiscountGamePage.getLocator());
       specialGamePage.verifyCurrentSpecial();
       specialGamePage.clickDownloadButton();

       DownloadPage downloadPage = new DownloadPage(DownloadPage.getLocator());
       downloadPage.clickDownloadButton();

       }
   }


