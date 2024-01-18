package coverFoxTest;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHealthPlanResultsPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxUtility.Utility;

public class CF_TC555_Validate_search_results_for_healthcare_policies extends Base {
	
	public static Logger logger;                             //Logger=Class,logger=object
	//WebDriver driver;
	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDetailsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxHealthPlanResultsPage result;
@BeforeClass
	public void launchBrowser() throws InterruptedException
	{
	
	logger= logger.getLogger("COVERFOXINSURANCE");
	PropertyConfigurator.configure("log4j.properties");
	logger.info("Launching coverfox");
	    launchCoverFox();
		// driver=new ChromeDriver();
		 home=new CoverFoxHomePage(driver);
		 healthPlan=new CoverFoxHealthPlanPage(driver);
		 memberDetails=new CoverFoxMemberDetailsPage(driver);
		 addressDetails=new CoverFoxAddressDetailsPage(driver);
		 result=new CoverFoxHealthPlanResultsPage(driver);
		 
//		 Reporter.log("Opening browser",true);
//		 driver.get("https://www.coverfox.com/");
//		 driver.manage().window().maximize();
//		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		 Thread.sleep(1000);
	}
	@BeforeMethod
	
	public void enterMemberDetails() throws InterruptedException, EncryptedDocumentException, IOException
	{
		
		Reporter.log("clicking on gender button", true);
		home.clickonMaleButton();
		logger.info("clicking on male button");
		Thread.sleep(1000);
		
		logger.info("Launching coverfox");
		Reporter.log("clicking on next button", true);
		healthPlan.clickOnNextButton();
		logger.info("clicking on next button");
		Thread.sleep(1000);
		
		Reporter.log("Handling age drop down", true);
		logger.info("Handling age drop down");
		memberDetails.handleAgeDropDown(Utility.readDataFromExcel(1, 0));
		Thread.sleep(1000);
		Reporter.log("clicking on next button", true);
		logger.info("clicking on next button");
		memberDetails.clickonNextButton();
		Thread.sleep(1000);
		 
		Reporter.log("Entering pin code", true);
		logger.info("Entering pin code");
		addressDetails.enterPinCode(Utility.readDataFromExcel(1, 1));
		Thread.sleep(1000);
		Reporter.log("Entering mobile num", true);
		addressDetails.enterMobNum(Utility.readDataFromExcel(1, 2)); 
		Thread.sleep(1000);
		Reporter.log("clicking on continue button", true);
		addressDetails.clickOnContinueButton();
		Thread.sleep(1000);
		
	}
	private void info(String string) {
		// TODO Auto-generated method stub
		
	}
	@Test
	public void validateTestPlanFromTextAndBanners() throws InterruptedException, IOException
	
	{
		Thread.sleep(5000);
		Reporter.log("fetching number of result from text", true);
		int textResult=result.availablePlanNumberFromText();
		Thread.sleep(7000);
		Reporter.log("fetching number of result from Banners", true);
		int bannerResult=result.availablePlanNumberFromBanners(); 
		Thread.sleep(1000);
		Assert.assertEquals(textResult, bannerResult, "Text result are not matching with banner result, TC is failed");
		
		Thread.sleep(2000);
		Reporter.log("TC is passed", true);
		Utility.takeScreenShot(driver, "CF_TC555");
		
	}
	
	@AfterMethod
	public void closeBrowser() throws InterruptedException
	{
		
		Thread.sleep(7000);
	    closeCoverFox();
	}
 
}

//public static Logger logger;
//logger= logger.getLogger("COVERFOXINSURANCE");
//PropertyConfigurator.configure("log4j.properties");
//logger.info("Launching coverfox");
