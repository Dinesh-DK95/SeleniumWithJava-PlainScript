package testCase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {

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
		//1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//2) Mouseover on Furniture and click Office Chairs under Chairs
		WebElement Furniture = driver.findElementByXPath("//a[text()='Furniture']");
		Actions build = new Actions(driver);
		build.moveToElement(Furniture).perform();
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		//3) click Executive Chairs
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Executive Chairs']")));
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		// Close Pop ups
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='popup-close'])[4]")));
			driver.findElementByXPath("(//a[@class='popup-close'])[4]").click();
		} catch (Exception e) {
			System.out.println("Pop was not handled");
		}
		//4) Change the minimum Height as 50 in under Dimensions
		WebElement height = driver.findElementByClassName("clipFilterDimensionHeightValue");
		height.clear();
		height.sendKeys("50",Keys.ENTER);
		// To Close 50 % discount Pop Up
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElementByXPath("//div[@class='close']").click();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//li[text()='Height(50-55)']"), "Height(50-55)"));
		//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-productname='Poise Executive Chair in Black Colour']")));
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		//6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		WebElement Homeware = driver.findElementByXPath("//a[text()='Homeware']");
		build.moveToElement(Homeware).perform();
		WebElement Cooker = driver.findElementByXPath("//a[text()='Pressure Cookers']");
		Cooker.click();
		//7) Select Prestige as Brand
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Prestige']")));
		driver.findElementByXPath("//label[text()='Prestige']").click();
		//8) Select Capacity as 1-3 Ltr
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='1 Ltr - 3 Ltr']")));
		Thread.sleep(2000);
		driver.findElementByXPath("//label[text()='1 Ltr - 3 Ltr']").click();
		//9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")));
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		//10) Verify the number of items in Wishlist
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='item_count'])[2]")));
		//Thread.sleep(3000);
		String count = driver.findElementByXPath("(//span[@class='item_count'])[2]").getText();
		System.out.println(count);
		//11) Navigate to Wishlist
		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
		//12) Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("(//a[text()='Add to Cart'])[2]").click();
		//13) Check for the availability for Pincode 600128
		driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600128",Keys.ENTER);
		//14) Click Proceed to Pay Securely
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		//15 Click Proceed to Pay
		driver.findElementByXPath("//a[text()='PLACE ORDER']").click();
		//16) Capture the screenshot of the item under Order Item
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='ORDER SUMMARY']")));
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/pepperfry.jpeg");
		FileUtils.copyFile(src, dst);
		//17) Close the browser
		//driver.quit();
	}

}
