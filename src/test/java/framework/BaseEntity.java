package framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import steam.pageObject.forms.DownloadPage;

import java.util.concurrent.TimeUnit;

public class BaseEntity {

    private static String url;
    protected static int wait;
    protected static WebDriver driver;

    @BeforeClass
    public void before(){

        PropertyReader.initProperty();
        PropertyReader.changePath();
        PropertyReader.initProperty();

        url = PropertyReader.getProperty("URL");
        wait = Integer.parseInt(PropertyReader.getProperty("Wait"));
        driver = BrowserFactory.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        driver.get(url);

        DownloadPage.isExistFiles();

}

    @AfterClass
    public void after(){
            driver.quit();
    }

    }
