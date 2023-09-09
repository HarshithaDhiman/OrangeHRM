package PageObjects_OrangeHRM;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AttachmentsPage {
	public WebDriver driver;
	WebDriverWait wait;
	public AttachmentsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	/*Attachments Table elements*/
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div")
	public List <WebElement> AllElementsOfAttachment;
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[1]/child::div[1]/child::h6[1]")
	public WebElement AttachmentsText;
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[2]/descendant::span[1]")
	public WebElement AttachmentsRecordsText;
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div")
	public List<WebElement> AttachmentsNoOfRecords;	
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[1]/child::div[1]/child::button[1]")
	public WebElement AttachmentsAdd_Btn;
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[1]/child::h6[1]")
	public WebElement AddAttachmentText;
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[1]/child::form[1]/child::div")
	public List<WebElement> AllElementsOfAddAttachments;
	//div[@class='orangehrm-attachment']/child::div[1]/child::form[1]/descendant::div[4]/child::div[2]/child::div[1]/child::div[1]
	@FindBy(xpath = "//div[@class='oxd-file-button']")
	public WebElement Browse_Btn;
	@FindBy(xpath = "//form[@class='oxd-form']/descendant::div[4]/child::div[2]/following-sibling::span[1]")
	public WebElement BrowseBtn_Error_Msg;
	@FindBy(xpath = "//div[@class='oxd-file-input-div']")
	public WebElement Browse_BtnTextField;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[2]/descendant::div[5]/child::textarea[1]")
	public WebElement AddAttachmentCommentTextArea;
	@FindBy (xpath = "//div[@class='oxd-form-actions']/child::button[1]")
	public WebElement AddAttachments_CancelBtn;
	@FindBy (xpath = "//div[@class='oxd-form-actions']/child::button[2]")
	public WebElement AddAttachments_SaveBtn;
	
	/*Delete record elements under 'Attachments' Table. */
	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']/child::div[5]/child::div/child::h6[1]")	
	public WebElement EditAttachmentText;
	@FindBy(xpath = "//form[@class='oxd-form']/child::div[3]/descendant::div[5]/child::textarea[1]")					
	public WebElement EditCommentTextArea;
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/descendant::input")
	public List<WebElement> multipleCheckBox_Attachments_Table;
	@FindBy(xpath = "//div[@class='orangehrm-attachment']/child::div[2]/descendant::div[2]/child::button[1]")
	public WebElement CommomDelSymbOfAttachments;
	
	/*Delete record elements under 'Attachments' Table. */
	@FindBy(xpath = "//div[@class='oxd-dialog-container-default--inner']/child::div[1]/child::div[1]/child::p[1]")
	public WebElement ConfirmationPopUp;
	@FindBy(xpath = "//div[@class='orangehrm-modal-footer']/child::button[2]")
	public WebElement ConfirmationPopUpDelete;
	@FindBy(xpath = "//div[@class='orangehrm-modal-footer']/child::button[1]")
	public WebElement ConfirmationPopUpCancel;
	
	/*Common Methods*/	
	public void waitElement() {
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAttachment));
	}
	public String AttachmentsRecordsText() {
		wait.until(ExpectedConditions.visibilityOf(AttachmentsRecordsText));
		return AttachmentsRecordsText.getText();		
	}
	public int NoOfAttachmentsRecords() {
		wait.until(ExpectedConditions.visibilityOfAllElements(AttachmentsNoOfRecords));
		return AttachmentsNoOfRecords.size();		
	}
	public String BrowseTextFieldText() {
		wait.until(ExpectedConditions.visibilityOf(Browse_BtnTextField));
		return Browse_BtnTextField.getText();
	}
	/*Specific Methods*/
	public WebElement attachementsTableVerification_22() {
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAttachment));
		return AttachmentsText;
	}
	public WebElement clickOnAttachmentsAddBtn_TC23() {
		AttachmentsAdd_Btn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAddAttachments));
		return AddAttachmentText;
	}
	public WebElement browseBtnAddAttachmentVerification_TC24() {
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAddAttachments));
		return Browse_Btn;
	}	
	public String clickOnBrowseBtnAddAttachment_TC25() throws AWTException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAddAttachments));
		Browse_Btn.click();
		Thread.sleep(1000);
		Robot robo = new Robot();
		String FilePath = "D:\\SELENIUM\\Data.xlsx";
		StringSelection str = new StringSelection(FilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		robo.keyRelease(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		File file = new File(FilePath);
		String FileName = file.getName();
		return FileName;
	}
	public void addAttachementsSaveAndCancelBtn_TC26(){
		Assert.assertTrue(AddAttachments_SaveBtn.isDisplayed());
		Assert.assertTrue(AddAttachments_SaveBtn.isEnabled());
		assertEquals(AddAttachments_SaveBtn.getText(), "Save");
		Assert.assertTrue(AddAttachments_CancelBtn.isDisplayed());
		Assert.assertTrue(AddAttachments_CancelBtn.isDisplayed());
		assertEquals(AddAttachments_CancelBtn.getText(), "Cancel");
		System.out.println("'Save' and 'Cancel' buttons on 'Add Attachment' section are available and enabled");
	}
	public String clickOnCancelBtnOfAddAttachmentsPage_TC27() throws InterruptedException, AWTException {
		Browse_Btn.click();
		Thread.sleep(1000);
		Robot robo = new Robot();
		String filePath = "D:\\SELENIUM\\Data.xlsx";
		StringSelection str = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyPress(KeyEvent.VK_ENTER);
		wait.withTimeout(Duration.ofMillis(3000));
		robo.keyRelease(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		File file = new File(filePath);
		AddAttachments_CancelBtn.click();
		wait.until(ExpectedConditions.visibilityOf(AttachmentsRecordsText));
		return AttachmentsRecordsText.getText();
	}
	public String clickOnSaveBtnOfAddAttachmentsPage_TC28() throws InterruptedException, AWTException {
		Browse_Btn.click();
		Thread.sleep(1000);
		Robot robo = new Robot();
		String filePath = "D:\\SELENIUM\\Data.xlsx";
		StringSelection str = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		robo.keyRelease(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		File file = new File(filePath);
		wait.until(ExpectedConditions.visibilityOf(AddAttachments_SaveBtn));
		AddAttachments_SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(AttachmentsNoOfRecords));
		return AttachmentsRecordsText.getText();
	}
	public String clickOnCancelBtn_WithoutAttachment_TC29() {
		AddAttachments_CancelBtn.click();
		wait.until(ExpectedConditions.visibilityOf(AttachmentsRecordsText));
		return AttachmentsRecordsText.getText();
	}
	public String clickOnSaveBtn_WithoutAttachment_TC30() {
		AddAttachments_SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(BrowseBtn_Error_Msg));
		return BrowseBtn_Error_Msg.getText();
	}
	public String browseBtnFileSizeMoreThan1Mb_TC31() throws InterruptedException, AWTException{
		Browse_Btn.click();
		Thread.sleep(1000);
		Robot robo = new Robot();
		String filePath = "D:\\Selenium Material\\Project\\Exp.pptx";
		StringSelection str=new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyPress(KeyEvent.VK_ENTER);
		wait.withTimeout(Duration.ofMillis(6000));
		robo.keyRelease(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		wait.until(ExpectedConditions.visibilityOf(BrowseBtn_Error_Msg));
		return BrowseBtn_Error_Msg.getText();
	}
	public WebElement addAttachements_CommentTextArea_TC32() {
		wait.until(ExpectedConditions.visibilityOf(AddAttachmentCommentTextArea));
		return AddAttachmentCommentTextArea;
	}
	public String selectAttachRecordToEdit_TC33(int i){
		wait.until(ExpectedConditions.visibilityOf(AttachmentsRecordsText));
		WebElement selectedRecordToEdit = driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[8]/descendant::button[1]"));
		Assert.assertTrue(selectedRecordToEdit.isDisplayed());
		System.out.println("Edit symbol for the selected record is displayed");
		selectedRecordToEdit.click();
		wait.until(ExpectedConditions.visibilityOf(EditAttachmentText));
		return EditAttachmentText.getText();
	}	
	public String attachmentsPreviousFileName(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(AttachmentsNoOfRecords));
		return driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[2]")).getText();
	}
	public String editedAttachRecordValidation_TC34(int i) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfAllElements(AttachmentsNoOfRecords));
		driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[8]/descendant::button[1]")).click();		
		System.out.println("Edit symbol for the selected record is displayed");
		wait.until(ExpectedConditions.visibilityOf(Browse_Btn));		
		Browse_Btn.click();
		Thread.sleep(1000);
		Robot robo = new Robot();
		String filePath = "D:\\Selenium Material\\Project\\OrangeHRM\\PageObject.rtf";
		StringSelection str = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyPress(KeyEvent.VK_ENTER);
		wait.withTimeout(Duration.ofMillis(3000));
		robo.keyRelease(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		AddAttachments_SaveBtn.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(AttachmentsNoOfRecords));
		return driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[2]")).getText();
	}
	public WebElement deleteAttachments_PopUp_TC35(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAttachment));
		driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[8]/descendant::button[2]")).click();
		System.out.println("Selected Record's delete symbol is available");
		wait.until(ExpectedConditions.visibilityOf(ConfirmationPopUp));
		return ConfirmationPopUp;
	}
	public String ClickOnCancelOnConfirmationPopUp_TC37(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAttachment));
		driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[8]/descendant::button[2]")).click();
		wait.until(ExpectedConditions.visibilityOf(ConfirmationPopUp));
		ConfirmationPopUpCancel.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAttachment));
		return AttachmentsRecordsText.getText();
	}
	public String ClickOnDelteOnConfirmationPopUp_TC38(int i) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[8]/descendant::button[2]")).click();
		wait.until(ExpectedConditions.visibilityOf(ConfirmationPopUpDelete));
		ConfirmationPopUpDelete.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(AttachmentsRecordsText));
		return AttachmentsRecordsText.getText();
	}
	public WebElement clickOnDownloadSymb_TC39(int i) {
		wait.until(ExpectedConditions.visibilityOfAllElements(AllElementsOfAttachment));
		return driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/child::div["+i+"]/child::div[1]/child::div[8]/descendant::button[3]"));
	}
	public String selectMultipleCheckBoxes_TC40(int i) {		
		driver.findElement(By.xpath("//div[@class='orangehrm-attachment']/child::div[3]/child::div[1]/child::div[2]/descendant::span["+i+"]")).click();
		CommomDelSymbOfAttachments.click();
		wait.until(ExpectedConditions.visibilityOf(ConfirmationPopUpDelete));
		ConfirmationPopUpDelete.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(AttachmentsRecordsText));
		return AttachmentsRecordsText.getText();
	}
}
