package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Hashtable;
import java.util.Map;

public class BrowserFactory {

    private static WebDriver driver;
    private final static String BROWSER = PropertyReader.getProperty("Browser");
    private final static String OPERATING_SYSTEM = "Windows";
    private final static String CHROME_PROPERTY = "webdriver.chrome.driver";
    private final static String FIREFOX_PROPERTY = "webdriver.gecko.driver";
    private final static String CHROME_DRIVER_PATH = "src/test/java/resources/drivers/%s/chromedriver%s";
    private final static String FIREFOX_DRIVER_PATH = "src/test/java/resources/drivers/%s/geckodriver%s";
    private final static String WINDOWS_DRIVER_EXT = ".exe";
    private final static String LINUX_DRIVER_EXT = "";
    private static String folderName;
    private static String formatDriverPath;
    private static String absoluteDriverPath;
    private final static String FILE_PATH = "src/test/java/resources/downloads";
    private static String absolutePath;

    private BrowserFactory() {

    }

    /**
     * The driver is selected, based on the operating system type.
     *
     */
    public static WebDriver getDriver() {

        folderName = OPERATING_SYSTEM.toLowerCase();

        if (driver == null) {
            switch (OPERATING_SYSTEM) {
                case "Windows":
                    switchBrowser(WINDOWS_DRIVER_EXT);
                    break;
                case "Linux":
                    switchBrowser(LINUX_DRIVER_EXT);
                    break;
            }
        }
        return driver;
    }

    /**
     * The driver is selected, based on the browser type.
     *
     */
    private static WebDriver switchBrowser(String extension){

        switch (BROWSER) {
            case "Chrome":
                getSystemProperty(CHROME_DRIVER_PATH,extension,CHROME_PROPERTY);
                driver = new ChromeDriver(getChromeCapabilities());
                break;
            case "Firefox":
                getSystemProperty(FIREFOX_DRIVER_PATH,extension,FIREFOX_PROPERTY);
                driver = new FirefoxDriver(getFirefoxCapabilities());
                break;
        }
        return driver;
    }

    /**
     * path formatting
     * get absolute path
     * return system property for browser
     */
    private static String getSystemProperty(String driverPath, String driverExtension,
                                            String property){

        formatDriverPath = String.format(driverPath, folderName,
                driverExtension);
        absoluteDriverPath = FileSystems.getDefault().getPath(formatDriverPath)
                .normalize().toAbsolutePath().toString();
        return System.setProperty(property, absoluteDriverPath);
    }

    private static FirefoxOptions getFirefoxCapabilities() {

        getAbsolutePath();

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-gzip, " +
                              "application/octet-stream, application/zip");
        profile.setPreference("browser.download.dir", absolutePath);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return options;

    }

    private static ChromeOptions getChromeCapabilities() {

        getAbsolutePath();

        Map<String, Object> preferences = new Hashtable<String, Object>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.default_directory", absolutePath);
        preferences.put("safebrowsing.enabled", "true");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        return options;

    }

    public static void getAbsolutePath(){
        File file = new File(FILE_PATH);
        try {
            absolutePath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }



