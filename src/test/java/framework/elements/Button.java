package framework.elements;

import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;

public class Button extends BaseElement {

    private final static String HREF = "href";

    public Button(By xpath){
        super(xpath);
    }

    public String getUrl(){
        assertTrue(getElement().isDisplayed());
        return getElement().getAttribute(HREF);
    }

}
