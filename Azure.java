package testCase;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Azure {
	
public static void main(String[] args) throws InterruptedException {
	
	//07/05/2020

	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
	options.merge(cap);
	//Launching chrome browser.
	//Set the chromedriver.exe file to the java class.
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	System.setProperty("webdriver.chrome.silentoutput", "true");
	ChromeDriver driver= new ChromeDriver(options);
	WebDriverWait wait = new WebDriverWait(driver, 30);
	//1) Go to https://azure.microsoft.com/en-in/
	driver.get("https://azure.microsoft.com/en-in/");
	// To Maximize the browser.
	driver.manage().window().maximize();
	// IMplicit Wait  Until url is loaded.
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//2) Click on Pricing
	driver.findElementByXPath("//a[text()='Pricing']").click();
	//3) Click on Pricing Calculator
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/en-in/pricing/calculator/']")));
	driver.findElementByXPath("//a[@href='/en-in/pricing/calculator/']").click();
	//4) Click on Containers
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Containers']")));
	driver.findElementByXPath("//button[text()='Containers']").click();
	//5) Select Container Instances
	driver.findElementByXPath("(//span[text()='Easily run containers on Azure without managing servers'])[3]").click();
	//6) Click on Container Instance Added View
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='View']")));
	driver.findElementByXPath("//a[text()='View']").click();
	//7) Select Region as "South India"
	WebElement region = driver.findElementByXPath("//select[@name='region']");
	Select DD = new Select(region);
	DD.selectByVisibleText("South India");
	//8) Set the Duration as 180000 seconds
	WebElement duration = driver.findElementByXPath("//input[@aria-label='Seconds']");
	duration.clear();
	duration.sendKeys(Keys.BACK_SPACE,"180000");
	//9) Select the Memory as 4GB
	WebElement Memory = driver.findElementByXPath("//select[@aria-label='Memory']");
	Select DD1 = new Select(Memory);
	DD1.selectByVisibleText("4 GB");
	//10) Enable SHOW DEV/TEST PRICING
	driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
	//11) Select Indian Rupee  as currency
	WebElement Currency = driver.findElementByXPath("//select[@aria-label='Currency']");
	Select DD2 = new Select(Currency);
	DD2.selectByVisibleText("Indian Rupee (₹)");
	//12) Print the Estimated monthly price
	System.out.println("The Estimated Price for Monthly is "+driver.findElementByXPath("(//span[@class='numeric'])[4]/span").getText());
	//13) Click on Export to download the estimate as excel
	driver.findElementByXPath("//button[text()='Export']").click();
	//14) Verify the downloded file in the local folder
	Thread.sleep(3000);
	File file = new File("C:\\Users\\dines\\Downloads\\ExportedEstimate.xslx");
	if (file.exists()) {
		System.out.println("Export Successful");
	}else {
		System.out.println("Export is not Successful");

	}
	//15) Navigate to Example Scenarios and Select CI/CD for Containers
	WebElement Example = driver.findElementByXPath("//a[text()='Example Scenarios']");
	Actions build = new Actions(driver);
	build.moveToElement(Example).click().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='CI/CD for Azure Web Apps']")));
	driver.findElementByXPath("//button[@title='CI/CD for Azure Web Apps']").click();
	//16) Click Add to Estimate
	driver.findElementByXPath("//button[text()='Add to estimate']").click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Estimate added!']")));
	//17) Change the Currency as Indian Rupee
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@aria-label='Currency']")));
	WebElement Currency2 = driver.findElementByXPath("//select[@aria-label='Currency']");
	Select DD3 = new Select(Currency2);
	DD3.selectByVisibleText("Indian Rupee (₹)");
	//18) Enable SHOW DEV/TEST PRICING
	driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
	//19) Export the Estimate
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dev/Test pricing has been applied']")));
	driver.findElementByXPath("//button[text()='Export']").click();
	Thread.sleep(3000);
	//20) Verify the downloded file in the local folder
	File file1 = new File("C:\\Users\\dines\\Downloads\\ExportedEstimate (1).xslx");
	if (file1.exists()) {
		System.out.println("Export Successful");
	}else {
		System.out.println("Export is not Successful");

	}
}
}
