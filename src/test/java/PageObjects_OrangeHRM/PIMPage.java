package PageObjects_OrangeHRM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {
	public WebDriver driver;
	WebDriverWait wait;
	public PIMPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	/*MyInfo and Immigration related elements*/
	@FindBy(xpath = "//div[@class='oxd-sidepanel-body']/child::ul/child::li[6]/child::a[1]")
	public WebElement MyInfo;
	
	@FindBy(xpath = "//div[@id='app']/descendant::div[2]/child::header[1]/descendant::h6")
	public WebElement PIMPage;
	
	public void clickOnMyInfo() {
		MyInfo.click();
		wait.until(ExpectedConditions.visibilityOf(PIMPage));
	}
}
