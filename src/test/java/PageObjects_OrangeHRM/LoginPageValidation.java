package PageObjects_OrangeHRM;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class LoginPageValidation {
	public WebDriver driver;
	WebDriverWait wait;
	public LoginPageValidation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	/*Login related elements*/
	@FindBy(xpath = "//div[@class='orangehrm-login-slot-wrapper']/child::div[2]/child::div[1]/following-sibling::h5")
	public WebElement LoginHeading;
	@FindBy(name = "username")
	public WebElement username;
	@FindBy(name = "password")
	public WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement login_btn;
	@FindBy(xpath = "//div[@class='oxd-alert oxd-alert--error']/descendant::p[1]")
	public WebElement loginErrorText;
	@FindBy(xpath = "//div[@class='oxd-layout-navigation']/child::header/descendant::span/child::h6")
	public WebElement dashText;
	
	public void launchUrl() {		
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		wait.until(ExpectedConditions.visibilityOf(LoginHeading));
	}
	
	public void loginValidation() throws IOException {
		Assert.assertTrue(username.isDisplayed());
		Assert.assertTrue(password.isDisplayed());
		File NewFile = new File(".\\Data\\OrangeHRM_Immigration.xlsx");
		FileInputStream fis = new FileInputStream(NewFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Login_Validation");
		int rows = sheet.getLastRowNum();
		for(int r=1;r<=rows;r++) {
			XSSFRow row = sheet.getRow(r);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			XSSFCell validationExcel = row.getCell(2);
			this.username.sendKeys(username.toString());
			this.password.sendKeys(password.toString());
			System.out.println(this.username.getText()+" "+this.password.getText());
			login_btn.click();
			if(username.toString().equals("Admin") && password.toString().equals("admin123")){
				wait.until(ExpectedConditions.visibilityOf(dashText));
				assertEquals(dashText.getText(), validationExcel.toString());
				System.out.println("Landed On Dashboard Page and title 'Dashboard' is seen.");
				break;
			}
			else {
				wait.until(ExpectedConditions.visibilityOf(loginErrorText));
				assertEquals(loginErrorText.getText(), validationExcel.toString());
				System.out.println("Invalid credentials,Didn't Land on Home Page");
			}						
		}
		fis.close();
		workbook.close();
	}
	
	public void validLogin() {
		this.username.sendKeys("Admin");
		this.password.sendKeys("admin123");
		login_btn.click();
		wait.until(ExpectedConditions.visibilityOf(dashText));
	}
		
	/*Methods*/
	public WebElement selectImmiRecordToEdit(int i){
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[7]/descendant::button[2]"));	
	}
	public WebElement editedImmiRecordValidation(int i) {
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[3]"));
	}
	public WebElement selectRecordToDelete(int i) throws InterruptedException {
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[7]/descendant::button[1]"));
	}
		
}