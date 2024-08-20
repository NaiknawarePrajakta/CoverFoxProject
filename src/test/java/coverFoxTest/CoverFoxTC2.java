package coverFoxTest;


import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxPOM.CoverFoxResultPage;
import coverFoxUtility.Utility;




public class CoverFoxTC2 extends Base
{
	
	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxAddressDetailsPage addressDertailsPage;
	CoverFoxMemberDetailsPage memberDetailsPage;
	CoverFoxResultPage resultPage;
	String filePath;
	public static Logger logger;
	
	
	
	@BeforeTest
	public void launchBrowser()
	{
		logger=Logger.getLogger("CoverFoxTest");
		PropertyConfigurator.configure("Log4j.properties");
        logger.info("opening browser");
		openBrowser();
		homePage=new CoverFoxHomePage(driver);
		healthPlanPage = new CoverFoxHealthPlanPage(driver);
		addressDertailsPage = new CoverFoxAddressDetailsPage(driver);
		memberDetailsPage = new CoverFoxMemberDetailsPage(driver);
		resultPage = new CoverFoxResultPage(driver);
		filePath = "D:\\ExcelTest.xlsx";

		}
	
	@BeforeClass
	public void preConditions() throws InterruptedException, EncryptedDocumentException, IOException
	{
		homePage.clickOnGenderButton();
		logger.info("clicking on gender button");
		Thread.sleep(1000);
		healthPlanPage.clickOnNextButton();
		
		logger.info("clicking on next button");
		Thread.sleep(1000);
		memberDetailsPage.handleAgeDropDown(Utility.readDataFromExcel(filePath, "Sheet5", 0, 0));
		logger.warn("enter age between 18-90 years");
		logger.info("handling age dropdown");
		memberDetailsPage.clickOnNextButton();
		logger.info("clicking on next button");
		Thread.sleep(1000);
		addressDertailsPage.enterPincode(Utility.readDataFromExcel(filePath, "Sheet5", 0, 1));
		logger.warn("please enter valid pincode");
		logger.info("entering pincode");
		addressDertailsPage.enterMobileNumber(Utility.readDataFromExcel(filePath, "Sheet5", 0, 2));
		logger.warn("please enter valid mobile number");
		logger.info("entering mobile number");
		addressDertailsPage.clickOnContinueButton();
		logger.info("clicking on continue button");
	}
		
		
		
	
	
	
	
  @Test
  public void validateBanners() throws InterruptedException 
  {
	  Thread.sleep(4000);
	  //Assert.fail();
	  int bannerPlanNumbers = resultPage.getPlanNumbersFromBanners();
	  int StringPlanNumbers = resultPage.getPlanNumbersFromString();
	  logger.info("validating banners");
	  Assert.assertEquals(StringPlanNumbers, bannerPlanNumbers,"Plans on banners not matching with results ,TC failed");
  }
  
  @Test
  public void validatePresenceOfSortButton() throws InterruptedException, IOException
  {
	  Thread.sleep(4000);
	 // Assert.fail();
	  logger.info("validating presence of sort button");
	  Assert.assertTrue(resultPage.sortPlanFilterIsDisplayed(), "Sort Plan filter is not displayed,TC is failed");
	 
  }
  @AfterClass
  public void closeBrowser() throws InterruptedException
  {
	  logger.info("closing browser");
	  browserClose();
  }
}
