package framework;

import org.openqa.selenium.By;
import java.util.NoSuchElementException;
import static org.testng.Assert.*;

public abstract class BaseForm extends BaseEntity {

    protected By locator;

    protected BaseForm(By locator) {

        this.locator = locator;
        assertTrue(isOpen());

    }


    public Boolean isOpen(){

        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Page has not opened");
            return false;
        }
        return true;

    }






}
