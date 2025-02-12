package jasmin.akter.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import jasmin.akter.basedrivers.PageDriver;
import jasmin.akter.utilities.CommonMethods;
import jasmin.akter.utilities.Screenshots;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;

public class CartPage extends CommonMethods {
    ExtentTest test;
    public CartPage(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    //Xpath

    @FindBys({@FindBy(xpath = "//a[contains(text(),'কালবেলা')]")
    })
    WebElement bookName;

    @FindBy(xpath = "//a[@id='viewBuyURL']")
    WebElement addToCart;

    @FindBy(xpath = "(//img[@src='https://www.prothoma.com/images/frontend/cart_bag.png'])[1]")
    WebElement cartIcon;

    @FindBy(xpath = "(//a[@class='btn btn-primary bag']")
    WebElement cart;


    // Report + Screenshot
    public void passCase(String message) {
        test.pass("<p style='color:#85BC63; font-size:14px'><b>" + message + "</b></p>");
    }

    public void passCaseWithSC(String message, String screenshotname) throws IOException {
        test.pass("<p style='color:#22dd3e; font-size:14px'><b>" + message + "</b></p>");
        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(), "" + screenshotname + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.info(dest);
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void failCase(String message, String screenshotname) throws IOException {
        test.fail("<p style='color:Red; font-size:14px'><b>" + message + "</b></p>");
        Throwable t = new InterruptedException("Exception");
        test.fail(t);

        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(), "" + screenshotname + "");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" + screenshotname + ".png";
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());


        PageDriver.getCurrentDriver().quit();
    }


    public void cart() throws IOException {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            test.info("Please click your book name options.");
            bookName.click();
            passCaseWithSC("Click on the book name","book_name_click_success");

            try {
                test.info("Please click add to Cart button.");
                addToCart.click();
                passCaseWithSC("Click on the add to cart button","addToCart_success");


                try{
                    test.info("Please show Add to Cart Icon");
                    new Actions(driver).moveToElement(cartIcon).build().perform();
                    passCaseWithSC("Show  the Cart Icon","cartIcon_show_success");

                    try {
                        test.info("Please Click  Cart Options");
                        cart.click();
                        passCaseWithSC("Click on the Cart Options","cart_success");

                    }catch (Exception e){
                        failCase("Cart Options was not Locate ","cart_success_fail");
                    }


                }catch (Exception e) {
                    failCase("Cart Icon was not Locate ","cartIcon_show_fail");
                }


            } catch (Exception e) {
                failCase("Add to Cart was not Locate ","addToCart_fail");
            }

        }catch (Exception e){
            failCase("Details was not Locate ","book_name_click_fail");

        }

    }
}

