package jasmin.akter.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import jasmin.akter.basedrivers.PageDriver;
import jasmin.akter.pages.HomePage;
import jasmin.akter.utilities.CommonMethods;
import jasmin.akter.utilities.ExtentFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

public class HomePage_Test extends CommonMethods {
    ExtentReports extent;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void open_url() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        sleep();

        extent = ExtentFactory.getInstance();
        parentTest = extent.createTest("<p style=\"color:blue; font-size:14px\"><b>Author</b></p>").assignAuthor("Jasmin Akter").assignDevice("Windows");
    }

    @Test
    public void prothoma() throws IOException, InterruptedException {
        childTest = parentTest.createNode("<p style=\"color:blue; font-size:14px\"><b>Home Page Click</b></p>");
        HomePage homePage = new HomePage(childTest);
        homePage.selectAuthor();
    }

    @AfterClass
    public void report(){

        extent.flush();
    }
}