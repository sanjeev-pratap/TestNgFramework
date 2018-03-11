package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class PageTest {
    ExtentHtmlReporter extentHtmlReporter;
    ExtentReports extentReports;
    public ExtentTest logger;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Inside before suite");
    }

    @BeforeTest
    public void beforeTest(){
        try{
            extentHtmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/extent.html");
            extentReports=new ExtentReports();
            extentReports.attachReporter(extentHtmlReporter);

        }
        catch(Exception e){

            Assert.fail("Exception in before test "+e.toString());
        }


    }

    @BeforeMethod
    public void beforeMethod(ITestNGMethod testNGMethod){
        logger=extentReports.createTest(testNGMethod.getMethodName());
    }

    @AfterMethod
    public void afterMethod(ITestResult iTestResult){
        try{
            System.out.println("Inside after method");
            if(iTestResult.getStatus()==ITestResult.FAILURE){
                logger.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " - Test Case Failed", ExtentColor.RED));
                logger.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            }
            else if(iTestResult.getStatus()==ITestResult.SKIP){
                logger.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
            }
        }
        catch(Exception e){

        }
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Inside After Test");
        extentReports.flush();
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Inside After Suite");
    }

}
