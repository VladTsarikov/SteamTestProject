package steam.pageObject.forms;
import framework.BaseForm;
import framework.PropertyReader;
import framework.elements.Button;
import org.openqa.selenium.By;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import static org.awaitility.Awaitility.await;
import static org.testng.Assert.assertEquals;

public class DownloadPage extends BaseForm {

    private static By btnWindowsDownloadPath = By.xpath("//div[@id='about_greeting_ctn']//a[@id='about_install_steam_link']");
    private static By btnLinuxDownloadPath = By.xpath("//div[@class='installerlist']//a[contains(text(),'Linux')]");
    private static String btnDownloadText = "//h2[contains(text(),'%s')]";
    private static String btnDownloadTextFormat = String.format(btnDownloadText, PropertyReader.getProperty("PageDownloadText"));
    private static By locator = By.xpath(btnDownloadTextFormat);
    private final static String FILE_PATH  = "src/test/java/resources/downloads/";
    private static String filePath = "src/test/java/resources/downloads/%s";
    private static String fullFilePath;
    private final static String WINDOWS_FILE_NAME = "SteamSetup.exe";
    private final static String LINUX_FILE_NAME = "steam_latest.deb";
    private static String absolutePath;
    private static File file;
    private static int originalFileSize;
    private static int tomeOut = Integer.parseInt(PropertyReader.getProperty("TimeOut"));
    private static String split = " ";
    private static int index = 0;

    public DownloadPage(By locator) {
        super(locator);
    }

    private static Button btnWindowsDownload = new Button(btnWindowsDownloadPath);
    private static Button btnLinuxDownload = new Button(btnLinuxDownloadPath);


    public static By getLocator() {
        return locator;
    }

    public void clickDownloadButton(){

        downloadFile();
        getFilePath();
        waitingUntilFileDownload();
        assertEquals(getLocaleFileSize(),getOriginalFileSize());

    }

    /**
     * getting file path depending on the operating system
     */
    private void getFilePath(){

        if (getOperatingSystemName().equals("Windows")){
            fullFilePath = String.format(filePath, WINDOWS_FILE_NAME);
        }else{
            fullFilePath = String.format(filePath, LINUX_FILE_NAME);
        }

        absolutePath = FileSystems.getDefault().getPath(fullFilePath).normalize()
                .toAbsolutePath().toString();

        file = new File(absolutePath);

    }


    private static long getLocaleFileSize(){

        long fileSize = file.length();
        return fileSize;

    }

    private void waitingUntilFileDownload(){

        Path filePath = Paths.get(absolutePath);
        await().atMost(tomeOut, TimeUnit.SECONDS)
                .ignoreExceptions()
                .until(() -> filePath.toFile().exists());

    }


    private static int getOriginalFileSize(){

        String ButtonUrl = btnWindowsDownload.getUrl();
        URL steam = null;
        try {
            steam = new URL(ButtonUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection)steam.openConnection();
            httpConn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return originalFileSize = httpConn.getContentLength();
    }

    public static void isExistFiles(){

        for (File files : new File(FILE_PATH).listFiles())
            if (files.isFile()) {
                files.delete();
            }
    }

    private String getOperatingSystemName(){

        String[] operatiтпSystems = System.getProperty("os.name").split(split);
        String operatiтпSystem = operatiтпSystems[index];
        return operatiтпSystem;
    }

    private void downloadFile(){

        if (getOperatingSystemName().equals("Windows")){
            btnWindowsDownload.click();
        }else{
            btnLinuxDownload.click();
        }
    }

}




