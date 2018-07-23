package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import framework.BaseEntity;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertTrue;

public abstract class BaseElement extends BaseEntity {

    private By xpath;
    private final static int LIST_SIZE = 0;

    public BaseElement(By xpath){
        this.xpath = xpath;
    }


    public WebElement getElement(){
        return driver.findElement(xpath);
    }

    public List<WebElement> getElements(){
        return driver.findElements(getLocator());
    }

    public  void clickAndWait(By xpath){
        click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, wait);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(xpath));
    }


    public By getLocator(){
        return xpath;
    }

    public void setLocator(By xpath){
        this.xpath = xpath;
    }

   public void clickViaAction(){

       Actions action = new Actions(driver);
       action.moveToElement(getElement()).click().build().perform();

   }

   public void moveToElement(){

       Actions action = new Actions(driver);
       action.moveToElement(getElement()).build().perform();

   }

   public void click(){
        assertTrue(getElement().isEnabled());
        getElement().click();
   }

   public boolean isPresent(By locator){
       Boolean isPresent = driver.findElements(locator).size() > LIST_SIZE;
       return isPresent;
   }








}
