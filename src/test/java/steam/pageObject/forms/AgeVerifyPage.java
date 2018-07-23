package steam.pageObject.forms;

import framework.BaseForm;
import framework.PropertyReader;
import framework.elements.Button;
import framework.elements.ComboBox;
import framework.elements.Label;
import org.openqa.selenium.By;

public class AgeVerifyPage extends BaseForm {

    private static By cmbYear = By.xpath("//select[@id='ageYear']");
    private static By lblYearValuePath = By.xpath("//select[@id='ageYear']//option[@value='2000']");
    private static String btnEnter = "//span[contains(text(),'%s')]";
    private static String btnEnterFormat = String.format(btnEnter, PropertyReader.getProperty("Enter"));
    private static By btnSentPath = By.xpath(btnEnterFormat);

    public AgeVerifyPage() {
        super(btnSentPath);
    }

    public static By getBtnSentPath() {
        return btnSentPath;
    }

    private static ComboBox lblYear = new ComboBox(cmbYear);
    private static Label lblYearValue = new Label(lblYearValuePath);
    private static Button btnSent = new Button(btnSentPath);

    public static void confirmYear(){
        lblYear.click();
        lblYearValue.click();
        btnSent.click();
    }

    public static boolean isPresented(){
        return btnSent.isPresent(btnSentPath);
    }



}
