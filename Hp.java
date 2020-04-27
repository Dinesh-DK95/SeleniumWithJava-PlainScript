package testCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hp {

	public static void main(String[] args) throws InterruptedException {
		//17/04/2020

		// To Disable Browser Notification.

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Launching chrome browser.
		//Set the chromedriver.exe file to the java class.
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		//1) Go to https://store.hp.com/in-en/
		driver.get("https://store.hp.com/in-en/");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// close cookies 
		try {
			//Sleep execution for .5 seconds , while DOM is getting loaded after previous action.
			Thread.sleep(500);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Close']")));
			driver.findElementByXPath("//button[@title='Close']").click();
		} catch (Exception e) {
			System.out.println("Cookies are not closed");
			System.out.println(e);
		}
		// Close Sign Up Mesaage

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='optly-modal-close close-icon']")));
			driver.findElementByXPath("//span[@class='optly-modal-close close-icon']").click();
		} catch (Exception e) {
			System.out.println("We did not get the Sign Up Message ");
			System.out.println(e);
		}


		//2) Mouse over on Laptops menu and click on Pavilion

		WebElement Laptops = driver.findElementByXPath("//span[text()='Laptops']");
		wait.until(ExpectedConditions.elementToBeClickable(Laptops));
		Actions Build = new Actions(driver);
		Build.moveToElement(Laptops).perform();
		//Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Pavilion']").click();

		// close cookies 
		try {
			//Sleep execution for .5 seconds , while DOM is getting loaded after previous action.
			Thread.sleep(500);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Close']")));
			driver.findElementByXPath("//button[@title='Close']").click();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Cookies Pop Up is not closed");
		}

		// Close the Message window


		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='inside_windowBlack']")));
			WebElement ele = driver.findElementByXPath("//div[@class='inside_windowBlack']");
			driver.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			System.out.println("Not Able to click the Grey Screen");
		}
		//driver.findElementByXPath("//body[@data-container='body']").click();


		//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		//Sleep execution for .5 seconds , while DOM is getting loaded after previous action.

		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Processor'])[2]")));
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		WebElement i7 = driver.findElementByXPath("(//span[text()='Intel Core i7'])");
		wait.until(ExpectedConditions.elementToBeClickable(i7));
		i7.click(); 

		//4)Hard Drive Capacity -->More than 1TB
		//Sleep execution for 3 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='More than 1 TB']").click();



		//5) Select Sort By: Price: Low to High
		//Sleep execution for 3 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(3000);
		WebElement sort = driver.findElementByXPath("//select[@id='sorter']");
		Select DD = new Select(sort);
		DD.selectByIndex(1);

		//6) Print the First resulting Product Name and Price
		System.out.println(driver.findElementByXPath("//div[@class='product details product-item-details']//a[@class='product-item-link']").getText());
		String PP = driver.findElementByXPath("//span[@data-price-type='finalPrice']/span[@class='price']").getText();
		String ProductPrice = PP.replaceAll("[^0-9]", "");
		System.out.println("Product Price is "+ProductPrice);
		//7) Click on Add to Cart
		Thread.sleep(2000);
		driver.findElementByXPath("//button[@title='Add To Cart']").click();
		//8) Click on Shopping Cart icon --> Click on View and Edit Cart

		Thread.sleep(2000);
		driver.findElementByXPath("//a[@title='Shopping Cart']").click();
		driver.findElementByXPath("(//a[@href='https://store.hp.com/in-en/default/checkout/cart/'])[2]").click();
		//9) Check the Shipping Option --> Check availability at Pincode
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600015");
		driver.findElementByXPath("//button[text()='check']").click();
		//10) Verify the order Total against the product price

		String TP = driver.findElementByXPath("//td[@data-th='Order Total']//span").getText();
		String TotalPrice = TP.replaceAll("[^0-9]", "");
		System.out.println("Total Price is "+TotalPrice);
		Thread.sleep(2000);
		//11) Proceed to Checkout if Order Total and Product Price matches
		//Use if and compare 
		if (TotalPrice.contentEquals(ProductPrice)) {
			System.out.println("Total Price and Product Price are  Matched");
			driver.findElementByXPath("//button[@title='Proceed to Checkout']").click();
			//12) Click on Place Order
			driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();
			//13) Capture the Error message and Print
			System.out.println("Error Message will be Printed ,Since Required details is not Filled at check out before place Order");
			List<WebElement> errormsg = driver.findElementsByXPath("//div[@generated='true']");
			System.out.println("Number of field were not filled are "+errormsg.size());

			for (WebElement ele : errormsg) {
				System.out.println(ele.getText());

			}
		}else {
			System.out.println("Total Price and Product Price Does not Match");
		}
		//14) Close Browser
		driver.close();
	}

}

//New Learning 
// Add below properties to handle unexpected pop up / alerts
//ChromeOptions options = new ChromeOptions();
//options.addArguments("--disable-notifications");

//DesiredCapabilities cap = new DesiredCapabilities();
//cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);


//options.merge(cap);

//ChromeDriver driver = new ChromeDriver(options);

