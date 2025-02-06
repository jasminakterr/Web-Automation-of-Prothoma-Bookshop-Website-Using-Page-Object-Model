package jasmin.akter.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import jasmin.akter.pages.CartPage;
import jasmin.akter.utilities.CommonMethods;
import jasmin.akter.utilities.ExtentFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CartPage_Test extends CommonMethods {
    ExtentReports extent;
    ExtentTest parentTest;
    ExtentTest childTest;

    @BeforeClass
    public void open_url() throws InterruptedException {
        //PageDriver.getCurrentDriver().get(url);
        sleep();
        extent = ExtentFactory.getInstance();
        parentTest = extent.createTest("<p style=\"color:Green; font-size:14px\"><b>Details</b></p>").assignAuthor("Esrat").assignDevice("Windows");
    }

    @Test
    public void prothoma_Cartpage() throws IOException, InterruptedException {
        childTest = parentTest.createNode("<p style=\"color:Green; font-size:14px\"><b>Details Page Click</b></p>");
        CartPage cartPage = new CartPage(childTest);
        cartPage.cart();

    }

    @AfterClass
    public void report(){

        extent.flush();
    }
}
