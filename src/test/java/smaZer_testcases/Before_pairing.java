package smaZer_testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.AddDevicePage;
import pages.Analytics;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.SmaZer_info_Page;
import utils.logReadandWrite;
import pages.OtpPage;
import pages.Reportpage;
import pages.Schedularpage;
import wrappers.MobileAppWrappers;

public class Before_pairing extends MobileAppWrappers {

	public String userName = loadProp("USERNAME");
	public String emaId = loadProp("EMAILID");
	
	LandingPage landingpage;
	SignInPage loginpage;
	HomePage homepage;
	OtpPage otppage;
	AddDevicePage adddevicepage;
	AccountsInfoPage accountinfopage;
	DeviceMenuPage devicesettingpage;
	SignUpPage signuppage;
	Reportpage reportpage;
	DeviceMenuPage devicemenupage;
	SmaZer_info_Page sZephyrinfopage;
	Analytics analyticspage;
	Schedularpage schedularpage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "Default contents check of before Login_and_after login check";
		//check login username and Accounts info username are same or not 
		//check for language selection 
		//pair with device try to del account and check for popup and try to remove device and try to del acnt and check add device page .

		testDescription = "Before_pairing";
	}

//Before starting delete your account "testuser007"
	@Test(priority = 0)
	public void homePageUICheck() throws Exception {
		initAndriodDriver();
		adddevicepage= new AddDevicePage(driver);
		homepage = new HomePage(driver);
		accountinfopage= new AccountsInfoPage(driver);
		devicesettingpage= new DeviceMenuPage(driver);
		signuppage = new SignUpPage(driver);
		reportpage= new Reportpage(driver);
		loginpage = new SignInPage(driver);
		landingpage = new LandingPage(driver);
		otppage = new OtpPage(driver);
		devicemenupage= new DeviceMenuPage(driver);
		sZephyrinfopage = new SmaZer_info_Page(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
			readwrite.openPort();
			readwrite.write("reboot\r");
			Thread.sleep(3000);

			//home page ui check
			loginpage.verifysignInPage();
			homepage.register_Page_ScreenShot_Check();
			homepage.SignIn_Page_ScreenShot_Check();
			homepage.SignUp_Page_ScreenShot_Check();
			landingpage.clickSignUpLink();
			signuppage.enterUserName(userName);
			signuppage.enterEmailId(emaId);
			signuppage.clickSignUpTCCheckBox();
			signuppage.clickSignUpButton();
			otppage.verifyOTPVerificationTitle("OTP Verification");
			otppage.enterOTPField1("1");
			otppage.enterOTPField2("2");
			otppage.enterOTPField3("3");
			otppage.enterOTPField4("4");
			otppage.submitButton();
			adddevicepage.verifyAddDevicePage("Add Device");
			homepage.Adddevice_Page_ScreenShot_Check();
			//menubar navigation check
			homepage.clickMenuBarButton();
			homepage.clickAccountinfobutton();
			homepage.checkAccountsinfoDefaultValues();
			homepage.backnavigation();
			
			homepage.clickMenuBarButton();
			homepage.clickKnownDevicepairingButton();
			homepage.checkAdddevicePageDefaultvalues();
			homepage.backnavigation();
			
			homepage.clickMenuBarButton();
			homepage.clickLogoutButton();
			homepage.checkLogoutcontents();
			
			
			homepage.clickMenuBarButton();
			homepage.clickReportButton();
			reportpage.Reportanissuepagecontents();
			homepage.backnavigation();
			
			
			//delete account
			homepage.clickMenuBarButton();
			homepage.clickAccountinfobutton();
			accountinfopage.deleteaccount_toregisterpage();
			
			
			//resignin
			landingpage.clickSignInButton();
			loginpage.enterUserName(userName);
			loginpage.clickSignInButton();
			loginpage.checkUserNameNotFoundToast("User Not Found");
			driver.navigate().back();
			
			//user creation
			landingpage.clickSignUpLink();
			signuppage.enterUserName(userName);
			signuppage.enterEmailId(emaId);
			signuppage.clickSignUpTCCheckBox();
			signuppage.clickSignUpButton();
			otppage.verifyOTPVerificationTitle("OTP Verification");
			otppage.enterOTPField1("1");
			otppage.enterOTPField2("2");
			otppage.enterOTPField3("3");
			otppage.enterOTPField4("4");
			otppage.submitButton();
			adddevicepage.verifyAddDevicePage("Add Device");
			
			//logout 
			homepage.clickMenuBarButton();
			homepage.clickLogoutButton();
			homepage.clickokonpopup();
			
			
			//login
			landingpage.clickSignInButton();
			loginpage.enterUserName(userName);
			loginpage.clickSignInButton();
			otppage.verifyOTPVerificationTitle("OTP Verification");
			otppage.enterOTPField1("1");
			otppage.enterOTPField2("2");
			otppage.enterOTPField3("3");
			otppage.enterOTPField4("4");
			otppage.submitButton();
			adddevicepage.verifyAddDevicePage("Add Device");
			
			
			//menubar navigation check
			homepage.clickMenuBarButton();
			homepage.clickAccountinfobutton();
			homepage.checkAccountsinfoDefaultValues();
			homepage.backnavigation();
			
			homepage.clickMenuBarButton();
			homepage.clickKnownDevicepairingButton();
			homepage.checkAdddevicePageDefaultvalues();
			homepage.backnavigation();
			
			homepage.clickMenuBarButton();
			homepage.clickLogoutButton();
			homepage.checkLogoutcontents();
			
			
			homepage.clickMenuBarButton();
			homepage.clickReportButton();
			reportpage.Reportanissuepagecontents();
			homepage.backnavigation();
			
			
			readwrite.closePort();
		}
		catch (Exception e) {
			readwrite.closePort();
			fail(e);
		}
	}

	


}	

