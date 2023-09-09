package com.OrangeHRM.MyInfo_Immigration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import PageObjects_OrangeHRM.AssignedImmigrationRecordsPage;
import PageObjects_OrangeHRM.AttachmentsPage;
import PageObjects_OrangeHRM.LoginPageValidation;
import PageObjects_OrangeHRM.PIMPage;

public class TestCases {
	WebDriver driver;
	String BeforeRecords;
	public LoginPageValidation loginrepo ;
	public PIMPage PIMrepo ;
	public AssignedImmigrationRecordsPage immigrationrepo;
	public AttachmentsPage attachmentrepo;
	String AttachmentsBeforeRecords;
	String AttachmentsAfterRecords;
	String ImmigrationBeforeRecords;
	String ImmigrationAfterRecords;
	
	@BeforeMethod
	public void launchUrl() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		loginrepo = new LoginPageValidation(driver);
		PIMrepo = new PIMPage(driver);
		immigrationrepo = new AssignedImmigrationRecordsPage(driver);
		attachmentrepo = new AttachmentsPage(driver);
	}
	
	/* Validation of Login Page*/
	@Test(priority = 1) 
	public void loginValidation() throws IOException{		
		loginrepo.launchUrl();
		loginrepo.loginValidation();
	}
	
	/*Validation of MyInfo Link, On clicking My Info Should land on PIM Page*/	
	@Test(priority = 2) 
	public void myInfo_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		assertEquals(PIMrepo.PIMPage.getText(),"PIM");
		System.out.println("Landed on 'PIM' page on clicking 'MyInfo'");
	}
	
	/*Validation of Immigration Link under personal Details, On clicking immigration Should land on 'Assigned Immigration Records' Page*/
	@Test(priority = 3)
	public void Immigration_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		String Immigration = immigrationrepo.clickOnImmigration_TC3();		
		assertEquals(Immigration, "Assigned Immigration Records");
		System.out.println("Immigration is available and on clicking Lands on to 'Assigned Immigration Records' section");
	}
	
	/* Validate navigation to 'Add Immigration' page on clicking 'Add' button beside 'Assigned 'Immigration'.*/
	@Test(priority = 4)
	public void immigration_AddBtn_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		String AddImmigrationText = immigrationrepo.clickOnAdd_TC4();
		assertEquals(AddImmigrationText, "Add Immigration");
		System.out.println("Add button is available and Landed on to 'Add Immigration' section on clicking 'Add' button");
	}
	
	/* Validating "Passport" and "Visa" radio buttons are available and Functional */
	@Test(priority = 5)
	public void addImmigration_Page_PassVisa_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		immigrationrepo.clickOnAdd_TC4();
		String Passport = immigrationrepo.clickOnPassport_TC5();
		assertEquals(Passport, "Passport");
		System.out.println("Passport radio button is available and on clicking it gets selected");
		immigrationrepo.clickOnVisa_TC5();
		System.out.println("Visa radio button is available and on clicking it gets selected");
	}
	
	/* Validation of presence of 'Save' and 'Cancel' buttons on 'Add Immigration' Page*/
	@Test(priority = 6)
	public void immigration_SaveCancel_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		immigrationrepo.clickOnAdd_TC4();
		immigrationrepo.saveCancelBtnVerification_TC6_13();
	}
	
	/*clicking on "Cancel" button at the end of "Add Immigration" page before entering data */
	@Test(priority = 7)
	public void AddimmiPage_CancelBtn_WithNoData_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		ImmigrationBeforeRecords = immigrationrepo.ImmigrationRecordsText();
		immigrationrepo.clickOnAdd_TC4();		
		immigrationrepo.clickOnVisa_TC5();
		immigrationrepo.ClickOncancelBtn_WithoutData_TC7();
		ImmigrationAfterRecords = immigrationrepo.ImmigrationRecordsText();
		assertEquals(ImmigrationAfterRecords,ImmigrationBeforeRecords);
		System.out.println("Landed back on to 'Assigned Immigration Records' section with no change in records when clicked on 'Cancel' button");
		System.out.print("of 'Add Immigration' page");
	}
	
	/*clicking on "Cancel" button at the end of "Add Immigration" page after entering data */
	@Test(priority = 8)
	public void AddimmiPage_CancelBtn_WithData_Validation() throws InterruptedException, IOException{
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		ImmigrationBeforeRecords = immigrationrepo.ImmigrationRecordsText();
		immigrationrepo.clickOnAdd_TC4();
		immigrationrepo.clickOnPassport_TC5();
		immigrationrepo.clickOnCanceBtn_WithData_TC8();
		ImmigrationAfterRecords = immigrationrepo.ImmigrationRecordsText();
		assertEquals(ImmigrationAfterRecords,ImmigrationBeforeRecords);
		System.out.println("Number of records after entering data is same as previous, when clicked on 'Cancel' button of 'Add Immigration' Page");
	}
	
	/* click on "Save" button at the end of "Add Immigration" page without entering any data */
	@Test(priority = 9)
	public void AddImmiPage_ClickOnSave_WithNoData_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		ImmigrationBeforeRecords = immigrationrepo.ImmigrationRecordsText();
		immigrationrepo.clickOnAdd_TC4();
		immigrationrepo.clickOnVisa_TC5();
		immigrationrepo.clickOnSaveBtn_WithoutData_TC9();
		System.out.println("Gives an error msg 'Required' under Number field when clicked on 'Save' without entering Immigration details");
	}
	
	/* click on "Save" button at the end of "Add Immigration" page after entering any data */
	@Test(priority = 10)
	public void immigrationDetails_SaveBtn_Validation() throws InterruptedException, IOException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		ImmigrationBeforeRecords = immigrationrepo.ImmigrationRecordsText();
		immigrationrepo.clickOnAdd_TC4();
		immigrationrepo.clickOnVisa_TC5();
		immigrationrepo.clickOnSaveBtn_WithData_TC10();
		immigrationrepo.waitElement();
		ImmigrationAfterRecords = immigrationrepo.ImmigrationRecordsText();
		assertNotEquals(ImmigrationAfterRecords,ImmigrationBeforeRecords);		
	}
	
	/*Validae by entering Immigration details with 'Expiry Date' date as before the 'Issued Date' date.*/
	@Test(priority = 11)
	public void addImmi_ExpiryDate_Validation() throws IOException, InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		immigrationrepo.clickOnAdd_TC4();
		immigrationrepo.clickOnPassport_TC5();
		String ExpiryIssueDate = immigrationrepo.expiryBeforeIssue_TC11();
		assertEquals(ExpiryIssueDate, "Expiry date should be after issued date");
		System.out.println("Throws an error 'Expiry date should be after issued date' under 'Expiry Date' field of 'Add Immigration' page ");
	}
	
	/* Validate presence of Edit symbol of selected record and validate navigation to 'Edit Immigration' page on clicking it*/
	@Test(priority = 12)
	public void editImmigration_Page_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		System.out.println("Select the Record number to be edited of total records "+TotalRecords);
		Scanner sc  = new Scanner(System.in);		
		int i = sc.nextInt();
		String EditImmigrationText = immigrationrepo.clickOnEditSymbol_TC12(i);
		assertEquals(EditImmigrationText,"Edit Immigration");	
		System.out.println("Landed on to 'Edit Immigration' page on clicking edit symbol of the selected record under 'Assigned Immigration Records' Table");		
	}
	
	/* Validate presence of 'Save' and 'Cancel' button of "Edit Immigration" page.*/
	@Test(priority = 13)
	public void editImmiPage_CancelSaveBtn_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc  = new Scanner(System.in);
		System.out.println("Select the Record number to be edited of total records "+TotalRecords);
		int i = sc.nextInt();
		immigrationrepo.clickOnEditSymbol_TC12(i);
		immigrationrepo.saveCancelBtnVerification_TC6_13();
		System.out.println("On Edit Immigration Page");
	}
	
	/* Click on 'Save' button of "Edit Immigration" page after editing.*/
	@Test(priority = 14)
	public void editImmiPage_ClickOnSave_WithEdit_Validation() throws IOException, InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the Record number to be edited of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		FileInputStream file = new FileInputStream(".\\Data\\OrangeHRM_Immigration.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("EditData");
		String EditValue = sheet.getRow(1).getCell(0).toString();
		String AfterEditValue = immigrationrepo.saveEditImmigrationPage_WithEdit_TC14(EditValue,recordNum);
		assertNotEquals(AfterEditValue, EditValue);
		System.out.println("Change in the records under 'Assigned Immigration Records as 'Clicked on 'Save' button of 'Edit Immigration' page after editing .");
	}

	/* Click on 'Cancel' button of "Edit Immigration" page after editing.*/
	@Test(priority = 15)
	public void editImmiPage_ClickOnCancel_WithEdit_Validation() throws IOException, InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the Record number to be edited of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		FileInputStream file = new FileInputStream(".\\Data\\OrangeHRM_Immigration.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("EditData");
		String EditValue = sheet.getRow(1).getCell(1).toString();
		String BeforeEditValue = immigrationrepo.beforeEditValue(recordNum);
		String AfterEditValue = immigrationrepo.cancelEditImmigrationPage_WithEdit_TC15(EditValue, recordNum);
		assertEquals(AfterEditValue, BeforeEditValue);
		System.out.println("Clicked on 'Cancel' button of 'Edit Immigration' page without editing hence, didnot reflect any changes in the records values under 'Assigned Immigration Records'.");
	}
	
	/* Click on 'Save' button of "Edit Immigration" page without editing.*/
	@Test(priority = 16)
	public void editImmiPage_ClickOnSave_WithNoEdit_Validation() throws IOException, InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the Record number to be edited of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		String BeforeEditValue = immigrationrepo.beforeEditValue(recordNum);
		String AfterEditValue = immigrationrepo.saveEditImmigration_WithoutEdit_TC16(recordNum);
		assertEquals(AfterEditValue, BeforeEditValue);
		System.out.println("Clicked on 'Save' button of 'Edit Immigration' page without editing hence, No changes in the record values under 'Assigned Immigration Records'.");
	}
	
	/* Click on 'Cancel' button of "Edit Immigration" page without editing.*/
	@Test(priority = 17)
	public void editImmiPage_ClickOnCancel_WithNoEdit_Validation() throws IOException, InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the Record number to be edited of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		String BeforeEditValue = immigrationrepo.beforeEditValue(recordNum);
		String AfterEditValue = immigrationrepo.cancelEditImmigration_WithoutEdit_TC17(recordNum);
		assertEquals(AfterEditValue, BeforeEditValue);
		System.out.println("Clicked on 'Cancel' button of 'Edit Immigration' page after editing hence, No changes in the record value under 'Assigned Immigration Records'.");
	}
	
	/* Validate the presence of delete symbol of a particular record and Validate confirmation popup display on clicking it*/
	@Test(priority = 18)
	public void deleteImmi_PopUp_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc= new Scanner(System.in);
		System.out.println("Select the Record number to be deleted of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		WebElement ConfirmationPopUp = immigrationrepo.deleteImmigration_PopUp_TC18(recordNum);
		Assert.assertTrue(ConfirmationPopUp.isDisplayed());
		assertEquals(ConfirmationPopUp.getText(), "Are you Sure?");
		System.out.println("Delete symbol for selected record is available and on clicking ");
		System.out.print(ConfirmationPopUp.getText()+" Confirmation pop up is displayed.");
	}
	
	/*Validate the presence of 'No, Cancel' and 'Yes, Delete' buttons on 'Are you Sure' pop up*/
	@Test(priority = 19)
	public void popUp_CancelDeleteBtn_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc= new Scanner(System.in);
		System.out.println("Select the Record number to be deleted of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		WebElement ConfirmationPopUp = immigrationrepo.deleteImmigration_PopUp_TC18(recordNum);
		Assert.assertTrue(immigrationrepo.ConfirmationPopUpCancel.isDisplayed());
		Assert.assertTrue(immigrationrepo.ConfirmationPopUpCancel.isEnabled());
		assertEquals(immigrationrepo.ConfirmationPopUpCancel.getText(),"No, Cancel");
		System.out.println("'No, Cancel' Button on Confirmation popup displayed and enabled");
		Assert.assertTrue(immigrationrepo.ConfirmationPopUpDelete.isDisplayed());
		Assert.assertTrue(immigrationrepo.ConfirmationPopUpDelete.isEnabled());
		assertEquals(immigrationrepo.ConfirmationPopUpDelete.getText(), "Yes, Delete");
		System.out.println("'Yes, Delete' Button on Confirmation popup displayed and enabled");	
	}
	
	/* Validate on clicking 'No, Cancel' button of 'confirmation popup'.*/
	@Test(priority = 20)
	public void confirmationPopUp_Cancel_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc= new Scanner(System.in);
		System.out.println("Select the Record number to be deleted of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		int AfterRecords = immigrationrepo.ClickOnCancelOnConfirmationPopUp_TC20(recordNum);
		assertEquals(AfterRecords, TotalRecords);
		System.out.println("Selected record under 'Assigned Immigration Records' did not delete as clicked on 'No, Cancel' Button of confirmation pop-up");
		System.out.println("No. Of Before Records: "+TotalRecords+" No.of After Records: "+AfterRecords);
	}
	
	/*Validate on clicking 'Yes, Delete' button of confirmation popup.*/
	@Test(priority = 21)
	public void ConfirmationpopUp_Delete_Validation() throws InterruptedException{
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		int TotalRecords = immigrationrepo.NoOfImmigrationRecords();
		Scanner sc= new Scanner(System.in);
		System.out.println("Select the Record number to be deleted of total records "+TotalRecords);
		int recordNum = sc.nextInt();
		int AfterRecords = immigrationrepo.ClickOnDeleteOnConfirmationPopUp_TC21(recordNum);
		assertNotEquals(AfterRecords, TotalRecords);
		System.out.println("Selected record deleted sucessfully under 'Assigned Immigration Records'");
	}
	
	/*Validation of 'Attachments' Table under 'Assigned Immigration Records' Table on the same Page */
	@Test(priority = 22)
	public void attachments_Table_Validation() throws InterruptedException{
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		WebElement AttachmentsText = attachmentrepo.attachementsTableVerification_22();
		Assert.assertTrue(AttachmentsText.isDisplayed());
		assertEquals(AttachmentsText.getText(), "Attachments");
		System.out.println("Landed on to Attachments Page when clicked on 'Immigration'");
	}
	
	/*Validation of 'Add' button besides 'Attachements' Table and land on 'Add Attachment' on clicking*/
	@Test(priority = 23)
	public void addBtn_Attachments_Validation() throws InterruptedException{
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		WebElement AddAttachmentsText = attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		assertEquals(AddAttachmentsText.getText(),"Add Attachment");
		System.out.println("'Add' button besides 'Attachments' is available and on clicking landed on to 'Add Attachement' Section");		
	}
	
	/* Validate presence of 'Browse' button under 'Add Attachment' section.*/
	@Test(priority = 24)
	public void addAttachPage_BrowseBtn_Validation() throws InterruptedException{
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		WebElement BrowseBtn = attachmentrepo.browseBtnAddAttachmentVerification_TC24();
		Assert.assertTrue(BrowseBtn.isDisplayed());
		Assert.assertTrue(BrowseBtn.isEnabled());
		assertEquals(BrowseBtn.getText(), "Browse");
		System.out.println(BrowseBtn.getText()+" button under 'Add Attachment' page is displayed and enabled");
	}
	
	/* Validate that the selected file name is reflected in the 'Browse' button field on 'Add Attachment' page on clicking.*/
	@Test(priority = 25)
	public void addAttachPage_browseBtn_DisplayFileName_Validation() throws InterruptedException, AWTException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		String FileName = attachmentrepo.clickOnBrowseBtnAddAttachment_TC25();
		String TextFieldFileName = attachmentrepo.BrowseTextFieldText();
		assertEquals(TextFieldFileName, FileName);
		System.out.println("The selected file name reflected in the browse button field");
	 }
	 
	 /* Validate the presence of 'Save' and 'Cancel' buttons on 'Add Attachment' page. */
	 @Test(priority = 26)
	 public void addAttachments_SaveCancelBtns_Validation() throws InterruptedException {
		 loginrepo.launchUrl();
		 loginrepo.validLogin();
		 PIMrepo.clickOnMyInfo();
		 immigrationrepo.clickOnImmigration_TC3();
		 attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		 attachmentrepo.addAttachementsSaveAndCancelBtn_TC26();
	 }
	 
	 /* Validate 'Cancel' button on 'Add Attachment' page after attaching a document.*/
	 @Test(priority = 27)
	 public void addAttachPage_CancelBtn_Validation() throws InterruptedException, AWTException {
		 loginrepo.launchUrl();
		 loginrepo.validLogin();
		 PIMrepo.clickOnMyInfo();
		 immigrationrepo.clickOnImmigration_TC3();
		 //attachmentrepo.waitElement();
		 AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		 attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		 AttachmentsAfterRecords = attachmentrepo.clickOnCancelBtnOfAddAttachmentsPage_TC27();
		 //attachmentrepo.waitElement();
		 assertEquals(AttachmentsAfterRecords, AttachmentsBeforeRecords);
		 System.out.println("No. of records before attaching was: "+AttachmentsBeforeRecords);
		 System.out.println("No. of records after attaching are: "+AttachmentsAfterRecords);		 
		 System.out.println("Number of records before and after attaching are same as clicked on 'Cancel' button and landed back on 'Attachments'");
	 }
	 
	 /* Validate 'Save' button on 'Add Attachment' page after attaching a document.*/
	 @Test(priority = 28)
	 public void addAttachPage_SaveBtn_Validation() throws InterruptedException, AWTException {
		 loginrepo.launchUrl();
		 loginrepo.validLogin();
		 PIMrepo.clickOnMyInfo();
		 immigrationrepo.clickOnImmigration_TC3();
		 AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		 attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		 AttachmentsAfterRecords = attachmentrepo.clickOnSaveBtnOfAddAttachmentsPage_TC28();
		 assertNotEquals(AttachmentsAfterRecords, AttachmentsBeforeRecords);
		 System.out.println("Number of records before and after attaching are not same as clicked on 'Save' button and landed back on 'Attachments'");
		 System.out.println("Number of records before attaching was: "+AttachmentsBeforeRecords);
		 System.out.println("Number of records after attaching are: "+AttachmentsAfterRecords);
		 System.out.println("Successfully Attached");
	 }
	
	 /*Validation of 'Cancel' Button on 'Add Attachment' Page without attaching a file*/
	 @Test(priority = 29)
	 public void addAttachPage_CancelBtn_WithoutFile_Validation() throws InterruptedException {
		 loginrepo.launchUrl();
		 loginrepo.validLogin();
		 PIMrepo.clickOnMyInfo();
		 immigrationrepo.clickOnImmigration_TC3();
		 attachmentrepo.waitElement();
		 AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		 attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		 attachmentrepo.waitElement();
		 AttachmentsAfterRecords = attachmentrepo.clickOnCancelBtn_WithoutAttachment_TC29();
		 assertEquals(AttachmentsAfterRecords, AttachmentsBeforeRecords);
		 System.out.println("Number of records before attaching was: "+AttachmentsBeforeRecords);
		 System.out.println("Number of records after clicking on cancel without attaching is: "+AttachmentsAfterRecords);		 
		 System.out.println("Lands on 'Attachments' Page With no change in records when clicked 'Cancel' button of 'Add Attachment' Page");
	}
	 
	/*Validation of 'Save' Button on 'Add Attachment' Page without attaching a file*/
	@Test(priority = 30)
	public void addAttachPage_SaveBtn_WithoutFile_Validation() throws InterruptedException, AWTException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		attachmentrepo.waitElement();
		String Error = attachmentrepo.clickOnSaveBtn_WithoutAttachment_TC30();
		assertEquals(AttachmentsAfterRecords, AttachmentsBeforeRecords);
		System.out.println("Number of records before attaching was: "+AttachmentsBeforeRecords);
		System.out.println("Number of records after clicking on cancel without attaching is: "+AttachmentsAfterRecords);
		assertEquals(Error, "Required");
		System.out.println("An error message 'Required' is seen under 'Browse' field as clicked on 'Save' without attaching a file.");
	}
	
	/* Validation of attaching a file on 'Add Attachment' page of size more than 1MB. */
	@Test(priority = 31)
	public void addAttach_BrowseBtn_FileSize_Validation() throws InterruptedException, AWTException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		attachmentrepo.waitElement();
		String ErrorMsg = attachmentrepo.browseBtnFileSizeMoreThan1Mb_TC31();	
		assertEquals(ErrorMsg, "Attachment Size Exceeded");
		System.out.println("An error msg 'Attachment Size Exceeded' is displayed under 'Browse' button on 'Add Attachment' page");
	}
	
	/* Validate the presence of 'Comment' text area on 'Add Attachment' section  under 'Browse' button*/
	@Test(priority = 32)
	public void addAttachPage_CommentTextArea_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		attachmentrepo.clickOnAttachmentsAddBtn_TC23();
		attachmentrepo.waitElement();
		WebElement CommentTextArea = attachmentrepo.addAttachements_CommentTextArea_TC32();		
		Assert.assertTrue(CommentTextArea.isDisplayed());
		System.out.println("Comment Text Area under 'Browse' button is displayed and accepts Data");
		CommentTextArea.sendKeys("Hey there!");
	}
	
	/* Validating whether records exist and validate the presence of edit symbol of a selected record and lands on 
	 * 'Edit Attachment' page on clicking. */
	@Test(priority = 33)
	public void edit_Symbol_Validation() throws InterruptedException, AWTException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		int TotalRecords = attachmentrepo.NoOfAttachmentsRecords();
		System.out.println("Enter the Record number to be edited of total: "+TotalRecords);
		Scanner sc = new Scanner(System.in);
		int RecordNum = sc.nextInt();
		String EditAttachment = attachmentrepo.selectAttachRecordToEdit_TC33(RecordNum);		
		assertEquals(EditAttachment, "Edit Attachment");
		System.out.println("Lands on 'Edit Attachment' Page on clicking edit symbol of selected record");		
	}
	
	/* Validate editing/reattaching another file under 'Browse' button of 'Edit Attachment' page of a selected record*/
	@Test(priority = 34)
	public void edit_File_Attachments_Validation() throws InterruptedException, AWTException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}		
		int TotalRecords = attachmentrepo.NoOfAttachmentsRecords();
		System.out.println("Enter the Record number to be edited of total: "+TotalRecords);
		Scanner sc = new Scanner(System.in);
		int RecordNum = sc.nextInt();
		String PreviousFileName = attachmentrepo.attachmentsPreviousFileName(RecordNum);
		attachmentrepo.selectAttachRecordToEdit_TC33(RecordNum);
		String LaterFileName = attachmentrepo.editedAttachRecordValidation_TC34(RecordNum);
		assertNotEquals(LaterFileName, PreviousFileName);
		System.out.println("Before File Name "+PreviousFileName);
		System.out.println("After File Name "+LaterFileName);
		System.out.println("The file name is replaced in the browse button field"); 
		System.out.println("Successfully replaced old attachment with new");
	}
	
	/* Validate the delete symbol presence of a particular record under 'Attachments' Table 
	 * and validate confirmation popup */
	@Test(priority = 35)
	public void deleteSymbol_Attachments_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		int TotalRecords = attachmentrepo.NoOfAttachmentsRecords();
		System.out.println("Enter the Record number to be deleted of total: "+TotalRecords);
		Scanner sc = new Scanner(System.in);
		int RecordNum = sc.nextInt();
		WebElement ConfirmationPopUp = attachmentrepo.deleteAttachments_PopUp_TC35(RecordNum);
		Assert.assertTrue(ConfirmationPopUp.isDisplayed());
		assertEquals(ConfirmationPopUp.getText(), "Are you Sure?");
		System.out.println("Delete Symbol of a selected record and Confirmation Pop Up On clicking are displayed ");
	}
	/*Validate the presence of 'Yes,Delete' and 'No,Cancel' buttons on confirmation popup*/
	@Test(priority = 36)
	public void cancelDeleteOfConfirmationPopUp_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		int TotalRecords = attachmentrepo.NoOfAttachmentsRecords();
		System.out.println("Enter the Record number to be deleted of total: "+TotalRecords);
		Scanner sc = new Scanner(System.in);
		int RecordNum = sc.nextInt();
		attachmentrepo.deleteAttachments_PopUp_TC35(RecordNum);
		Assert.assertTrue(attachmentrepo.ConfirmationPopUpCancel.isDisplayed());
		Assert.assertTrue(attachmentrepo.ConfirmationPopUpCancel.isEnabled());
		assertEquals(attachmentrepo.ConfirmationPopUpCancel.getText(), "No, Cancel");
		Assert.assertTrue(attachmentrepo.ConfirmationPopUpDelete.isDisplayed());
		Assert.assertTrue(attachmentrepo.ConfirmationPopUpDelete.isEnabled());
		assertEquals(attachmentrepo.ConfirmationPopUpDelete.getText(), "Yes, Delete");
	}
	/* Click on 'No, Cancel' button of confirmation popup. */
	@Test(priority = 37)
	public void confirmationPopUp_ClickOnDel_Attachments_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}		
		int TotalRecords = attachmentrepo.NoOfAttachmentsRecords();
		System.out.println("Enter the Record number to be deleted of total: "+TotalRecords);
		Scanner sc = new Scanner(System.in);
		int RecordNum = sc.nextInt();
		AttachmentsAfterRecords = attachmentrepo.ClickOnCancelOnConfirmationPopUp_TC37(RecordNum);
		assertEquals(AttachmentsAfterRecords, AttachmentsBeforeRecords);
		System.out.println("No. Of Records Before "+AttachmentsBeforeRecords);
		System.out.println("No. Of Records After "+AttachmentsAfterRecords);
		System.out.println("No. of records before and after are same as clicked on 'No, Cancel' btn of confirmation popup");
	}
	
	/* Click on 'Yes, Delete' button of confirmation popup/ validation of deletion of record under 'Attachments' Table*/
	@Test(priority = 38)
	public void delete_Attachments_Validation() throws InterruptedException, AWTException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		int TotalRecords = attachmentrepo.NoOfAttachmentsRecords();
		System.out.println("Enter the Record number to be deleted of total: "+TotalRecords);
		Scanner sc = new Scanner(System.in);
		int RecordNum = sc.nextInt();
		AttachmentsAfterRecords = attachmentrepo.ClickOnDelteOnConfirmationPopUp_TC38(RecordNum);
		assertNotEquals(AttachmentsAfterRecords, AttachmentsBeforeRecords);
		System.out.println("No. of records bedfore and after are same as clicked on 'Yes, Delete' btn of confirmation popup");
	}
	
	/* Validate click on download symbol of a particular record under 'Attachments' Table */
	@Test(priority = 39)
	public void clickOnDownload_Attachments_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		int TotalRecords = attachmentrepo.NoOfAttachmentsRecords();
		System.out.println("Enter the Record number to be deleted of total: "+TotalRecords);
		Scanner sc = new Scanner(System.in);
		int RecordNum = sc.nextInt();
		WebElement DownloadSymb = attachmentrepo.clickOnDownloadSymb_TC39(RecordNum);
		Assert.assertTrue(DownloadSymb.isDisplayed());
		System.out.println("Download symbol for the selected record is available");
	}
	
	/* Validate deletion of single or multiple records randomly */
	@Test(priority = 40)
	public void clickOnCheckBoxes_Attachments_Table_Validation() throws InterruptedException {
		loginrepo.launchUrl();
		loginrepo.validLogin();
		PIMrepo.clickOnMyInfo();
		immigrationrepo.clickOnImmigration_TC3();
		attachmentrepo.waitElement();
		AttachmentsBeforeRecords = attachmentrepo.AttachmentsRecordsText();
		if(AttachmentsBeforeRecords.equals("No Records Found")) {
			System.out.println("Sorry!,No records exist to delete");
			System.exit(0);
		}
		int numberOfCheckBoxes = attachmentrepo.NoOfAttachmentsRecords();
		int min = 1;		
		System.out.println("Enter the record number to delete of total records "+numberOfCheckBoxes);
		int rand = (int)(Math.random()* numberOfCheckBoxes) + min;
		AttachmentsAfterRecords = attachmentrepo.selectMultipleCheckBoxes_TC40(rand);
		assertNotEquals(AttachmentsAfterRecords, AttachmentsBeforeRecords);
		System.out.println("Successfully deleted selected records");
	}	
}
