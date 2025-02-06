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

public class HomePage extends CommonMethods {
    ExtentTest test;
    public HomePage(ExtentTest test) {
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
        this.test = test;
    }

    @FindBys({@FindBy(xpath = "//a[text()='লেখক']")

    })
    WebElement authorMenu;

    @FindBys({@FindBy(xpath = "//a[text()='সমরেশ মজুমদার']")

    })
    WebElement authorName;

    @FindBys({@FindBy(xpath = "//label[@for='category653']")

    })
    WebElement categorySelect;


    // Report and Screenshot
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


    public void selectAuthor() throws IOException {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            test.info("Please click your author menu option");
            new Actions(driver).moveToElement(authorMenu).build().perform();
            passCaseWithSC("You have successfully click your  Writer from the Menu","author_menu_success");

            try {
                test.info("Please select the author name");
                authorName.click();
                passCaseWithSC("You have successfully select the author name","author_name_success");


                try{
                    test.info("Please click the category list");
                    new Actions(driver).moveToElement(categorySelect).build().perform();
                    categorySelect.click();
                    passCaseWithSC("You Successfully click the category list","category_select_success");

                }catch (Exception e) {
                    failCase("Failed to select category list.","category_select_fail");
                }


            } catch (Exception e) {
                failCase("Failed to select author name.","author_name_fail");
            }

        }catch (Exception e){
            failCase("Failed to click author menu.","author_menu_fail");

        }

    }

}

