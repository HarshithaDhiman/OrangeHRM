package PageObjects_OrangeHRM;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

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

public class AssignedImmigrationRecordsPage {
	public WebDriver driver;
	WebDriverWait wait;
	public AssignedImmigrationRecordsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	/* Immigration related elements*/
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-navigation']/child::div[2]/child::div[5]/child::a[1]")
	public WebElement Immigration;
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div")
	public List <WebElement> ImmigrationPage;
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div[1]/child::div[1]/child::h6[1]")
	public WebElement ImmigrationText;
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div[1]/child::div[1]/child::button[1]")
	public WebElement ImmigrationAdd_Btn;
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div[2]/child::div[1]/child::span[1]")
	public WebElement ImmigrationRecordsText;
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div")
	public WebElement AllElementsOfAddImmigration;
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div[1]/child::h6[1]")
	public WebElement AddImmigrationText;
	@FindBy(xpath = "//div[@class='oxd-input-group']/child::div[2]/child::div[1]/child::div[2]/child::div[1]/child::label[1]")
	public WebElement PassportText;
	@FindBy(xpath = "//div[@class='oxd-input-group']/child::div[2]/child::div[2]/child::div[2]/child::div[1]/child::label[1]")
	public WebElement VisaText;
	@FindBy(xpath = "//div[@class='oxd-input-group']/child::div[2]/child::div[1]/child::div[2]/child::div[1]/child::label[1]/child::span[1]")
	public WebElement Passport_Radio_Btn;
	@FindBy(xpath = "//div[@class='oxd-input-group']/child::div[2]/child::div[2]/child::div[2]/child::div[1]/child::label[1]/child::span[1]")
	public WebElement Visa_Radio_Btn;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[1]/child::div[1]/child::div[2]/child::input[1]")
	public WebElement PpVisaNum;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[1]/child::div[1]/child::div[2]/following-sibling::span[1]")
	public WebElement ErrorOnSave_WithNoImmiData;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[3]/child::div[1]/child::div[2]/following-sibling::span[1]")
	public WebElement ExpiryDateValidation;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[2]/child::div[1]/child::div[2]/descendant::input[1]")
	public WebElement IssuedDate;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[3]/child::div[1]/child::div[2]/descendant::input[1]")
	public WebElement ExpiryDate;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[4]/child::div[1]/child::div[2]/descendant::input[1]")
	public WebElement EligibleStatus;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[5]/child::div[1]/child::div[2]/descendant::div[3]")
	public WebElement IssuedBy;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[6]/child::div[1]/child::div[2]/descendant::input[1]")
	public WebElement EligibleReviewDate;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/child::div[1]/child::div[7]/child::div[1]/child::div[2]/child::textarea[1]")
	public WebElement CommentTextArea;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SaveBtn;
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-form-hint']/following-sibling::button[1]")
	public WebElement CancelBtn;
	
	/*Edit records under 'Assigned Immigration Records' Table records*/
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div")
	public List<WebElement> NoOfRecords_Immi;
	@FindBy (xpath = "//div[@class='orangehrm-edit-employee-content']/child::div[1]/child::h6[1]")
	public WebElement EditImmigrationText;
	
	/*Delete record elements under 'Assigned Immigration Records'. */
	@FindBy(xpath = "//div[@class='oxd-dialog-container-default--inner']/child::div[1]/child::div[1]/child::p[1]")
	public WebElement ConfirmationPopUp;
	@FindBy(xpath = "//div[@class='orangehrm-modal-footer']/child::button[2]")
	public WebElement ConfirmationPopUpDelete;
	@FindBy(xpath = "//div[@class='orangehrm-modal-footer']/child::button[1]")
	public WebElement ConfirmationPopUpCancel;
	
	public String clickOnImmigration_TC3() throws InterruptedException {
		Immigration.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		return ImmigrationText.getText();
	}
	public void waitElement() {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
	}
	public String ImmigrationRecordsText() {
		wait.until(ExpectedConditions.visibilityOf(ImmigrationRecordsText));
		return ImmigrationRecordsText.getText();
	}
	public int NoOfImmigrationRecords() {
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfRecords_Immi));
		return NoOfRecords_Immi.size();
	}
	public String clickOnAdd_TC4() {		
		ImmigrationAdd_Btn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAddImmigration));
		return AddImmigrationText.getText();
	}
	public String clickOnPassport_TC5() {
		Passport_Radio_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(AllElementsOfAddImmigration));
		return PassportText.getText();
	}
	public String clickOnVisa_TC5() {
		Visa_Radio_Btn.click();
		wait.until(ExpectedConditions.visibilityOf(AllElementsOfAddImmigration));
		return VisaText.getText();
	}
	public void saveCancelBtnVerification_TC6_13() {
		Assert.assertTrue(SaveBtn.isDisplayed());
		Assert.assertTrue(SaveBtn.isEnabled());
		assertEquals(SaveBtn.getText(), "Save");		
		Assert.assertTrue(CancelBtn.isDisplayed());
		Assert.assertTrue(CancelBtn.isEnabled());
		assertEquals(CancelBtn.getText(), "Cancel");
		System.out.println("Save and Cancel Buttons are available and enabled");
	}
	public void ClickOncancelBtn_WithoutData_TC7() {
		CancelBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
	}
	public void clickOnCanceBtn_WithData_TC8() throws IOException {
		File NewFile = new File(".\\Data\\OrangeHRM_Immigration.xlsx");
		FileInputStream fis = new FileInputStream(NewFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("ImmigrationDetails");
		String PPOrVisaNumber = sheet.getRow(1).getCell(0).toString();
		PpVisaNum.sendKeys(PPOrVisaNumber);
		workbook.close();
		CancelBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
	}
	public void clickOnSaveBtn_WithoutData_TC9() {
		SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(ErrorOnSave_WithNoImmiData));
		assertEquals(ErrorOnSave_WithNoImmiData.getText(), "Required");
	}
	public void clickOnSaveBtn_WithData_TC10() throws IOException {
		File NewFile = new File(".\\Data\\OrangeHRM_Immigration.xlsx");
		FileInputStream fis = new FileInputStream(NewFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("ImmigrationDetails");
		String PPOrVisaNumber = sheet.getRow(1).getCell(0).toString();
		Date IssuedDate = sheet.getRow(1).getCell(1).getDateCellValue();
		Date ExpiryDate = sheet.getRow(1).getCell(2).getDateCellValue();
		String EligibleStaus = sheet.getRow(1).getCell(3).toString();
		String IssuedBy = sheet.getRow(1).getCell(4).toString();
		String EligibleReviewDate = sheet.getRow(1).getCell(5).toString();
		String Comments = sheet.getRow(1).getCell(6).toString();
		
		PpVisaNum.sendKeys(PPOrVisaNumber);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");			
		this.IssuedDate.sendKeys(df.format(IssuedDate).toString());
			
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		this.ExpiryDate.sendKeys(df1.format(ExpiryDate));
			
		this.EligibleStatus.sendKeys(EligibleStaus);			
		this.IssuedBy.sendKeys(IssuedBy);
		this.EligibleReviewDate.sendKeys(EligibleReviewDate);
		this.CommentTextArea.sendKeys(Comments);
		workbook.close();
		SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(ImmigrationRecordsText));
	}
	public String expiryBeforeIssue_TC11() throws IOException {
		File NewFile = new File(".\\Data\\OrangeHRM_Immigration.xlsx");
		FileInputStream fis = new FileInputStream(NewFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("ExpiryDateValidation");
		String PPOrVisaNumber = sheet.getRow(1).getCell(0).toString();
		Date IssuedDate = sheet.getRow(1).getCell(1).getDateCellValue();
		Date ExpiryDate = sheet.getRow(1).getCell(2).getDateCellValue();
		
		this.PpVisaNum.sendKeys(PPOrVisaNumber);	
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
		this.IssuedDate.sendKeys(df.format(IssuedDate).toString());
		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		this.ExpiryDate.sendKeys(df1.format(ExpiryDate));
		
		fis.close();
		workbook.close();
		SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(ExpiryDateValidation));
		return ExpiryDateValidation.getText();		
	}
	public String clickOnEditSymbol_TC12(int i) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry! No records exist to edit");
			System.exit(0);
		}
		WebElement ImmigrationRecordToEdit = driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[7]/child::div[1]/child::button[2]"));
		Assert.assertTrue(ImmigrationRecordToEdit.isDisplayed());
		System.out.println("Edit symbol of the selected record is displayed and clickable");
		ImmigrationRecordToEdit.click();
		wait.until(ExpectedConditions.visibilityOf(EditImmigrationText));
		return EditImmigrationText.getText();
	}
	public String saveEditImmigrationPage_WithEdit_TC14(String EditValue, int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry! No records exist to edit");
			System.exit(0);
		}
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[7]/child::div[1]/child::button[2]")).click();
		wait.until(ExpectedConditions.visibilityOf(IssuedBy));
		//String BeforeColValue = driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[3]")).getText();		
		PpVisaNum.clear();
		PpVisaNum.sendKeys(EditValue);
		SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfRecords_Immi));
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[3]")).getText();
	}
	public String beforeEditValue(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfRecords_Immi));
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[3]")).getText();
	}
	public String cancelEditImmigrationPage_WithEdit_TC15(String EditValue, int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry! No records exist to edit");
			System.exit(0);
		}		
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[7]/child::div[1]/child::button[2]")).click();
		wait.until(ExpectedConditions.visibilityOf(IssuedBy));
		PpVisaNum.clear();
		PpVisaNum.sendKeys(EditValue);
		CancelBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfRecords_Immi));
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[3]")).getText();	
	}
	public String saveEditImmigration_WithoutEdit_TC16(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry! No records exist to edit");
			System.exit(0);
		}
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[7]/child::div[1]/child::button[2]")).click();
		wait.until(ExpectedConditions.visibilityOf(IssuedBy));
		SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfRecords_Immi));
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[3]")).getText();		
	}
	public String cancelEditImmigration_WithoutEdit_TC17(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry! No records exist to edit");
			System.exit(0);
		}
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfRecords_Immi));
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[7]/child::div[1]/child::button[2]")).click();
		wait.until(ExpectedConditions.visibilityOf(IssuedBy));
		CancelBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(NoOfRecords_Immi));
		return driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/div["+i+"]/child::div[1]/child::div[3]")).getText();
	}
	public WebElement deleteImmigration_PopUp_TC18(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[7]/descendant::button[1]")).click();
		wait.until(ExpectedConditions.visibilityOf(ConfirmationPopUp));
		return ConfirmationPopUp;
	}
	
	public int ClickOnCancelOnConfirmationPopUp_TC20(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[7]/descendant::button[1]")).click();
		wait.until(ExpectedConditions.visibilityOf(ConfirmationPopUp));
		ConfirmationPopUpCancel.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		return NoOfRecords_Immi.size();
	}
	public int ClickOnDeleteOnConfirmationPopUp_TC21(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		if(ImmigrationRecordsText.getText().equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-content']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[7]/descendant::button[1]")).click();
		wait.until(ExpectedConditions.visibilityOf(ConfirmationPopUp));
		ConfirmationPopUpDelete.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(ImmigrationPage));
		System.out.println(NoOfRecords_Immi.size());
		return NoOfRecords_Immi.size();
	}
}
