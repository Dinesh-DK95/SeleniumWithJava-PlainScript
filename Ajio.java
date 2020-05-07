package testCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajio {

	public static void main(String[] args) throws InterruptedException {

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
		Ajio obj =new Ajio();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//1)Load Url https://www.ajio.com/shop/sale
		driver.get("https://www.ajio.com/shop/sale");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//2) Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByName("searchVal").sendKeys("Bags");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Bags in ']")));
		driver.findElementByXPath("//span[text()='Bags in ']").click();
		//3) Click on five grid and Select SORT BY as "What's New"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("five-grid")));
		driver.findElementByClassName("five-grid").click();
		WebElement sort = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select DD = new Select(sort);
		DD.selectByVisibleText("What's New");
		
		//4) Enter Price Range Min as 2500 and Max as 5000
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("minPrice")));
		driver.findElementByXPath("//span[text()='price']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("minPrice")));

		driver.findElementById("minPrice").sendKeys("2500");
		driver.findElementById("maxPrice").sendKeys("5000",Keys.ENTER);
		//Thread.sleep(2000);

		//5) Click on the product "Puma Ferrari LS Shoulder Bag"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='https://assets.ajio.com/medias/sys_master/root/h90/h5e/16036830052382/-286Wx359H-460571597-red-MODEL.jpg']")));
		//img[@alt='Puma Red Shoulder Ferrari LS Shoulder Bag']
		//div[text()='Ferrari LS Shoulder Bag']
		driver.findElementByXPath("//img[@src='https://assets.ajio.com/medias/sys_master/root/h90/h5e/16036830052382/-286Wx359H-460571597-red-MODEL.jpg']").click();
		//6) Verify the Coupon code for the price above 2690 is applicable for your product, if applicable then get the Coupon Code and Calculate the discount price for the coupon
		Set<String> winset = driver.getWindowHandles();
		List<String> winlis = new ArrayList<String>(winset);
		driver.switchTo().window(winlis.get(1));
		
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='prod-sp']")));

		String PPrice = driver.findElementByClassName("prod-sp").getText();
		System.out.println(PPrice);
		System.out.println(obj.Getnumbers(PPrice));
		int ProductP= obj.Getnumbers(PPrice);
		int AppPrice =2690;
		if (ProductP>AppPrice) {
			String DPrice = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();
			System.out.println(driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText());
			System.out.println(obj.Getnumbers(DPrice));
			int OfferP= obj.Getnumbers(DPrice);
			int DiscountPrice = ProductP-OfferP;
			System.out.println(DiscountPrice);
			String Coupon = driver.findElementByXPath("//div[@class='promo-title']").getText();
			System.out.println(Coupon);	
		}
		//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		driver.findElementByXPath("//span[text()='Enter pin-code to know estimated delivery date.']").click();
		driver.findElementByClassName("edd-pincode-modal-text").sendKeys("682001",Keys.ENTER);
		String DDate = driver.findElementByClassName("edd-message-success-details-highlighted").getText();
		System.out.println(DDate);
		//8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		driver.findElementByXPath("//div[text()='Other information']").click();
		String Details = driver.findElementByXPath("(//span[text()='Customer Care Address']/following-sibling::span)[2]").getText();
		System.out.println(Details);
		//9) Click on ADD TO BAG and then GO TO BAG
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='GO TO BAG']")));

		driver.findElementByXPath("//span[text()='GO TO BAG']").click();
		//10) Check the Order Total before apply coupon
		String BCoupon = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		System.out.println(BCoupon);
		//11) Enter Coupon Code and Click Apply
		driver.findElementById("couponCodeInput").sendKeys("EPIC33");
		driver.findElementByXPath("//button[text()='Apply']").click();
		//12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details
		String CP = driver.findElementByXPath("//span[text()='Coupon savings']/following::span").getText();
		System.out.println(obj.Getnumbers(CP));
		String ACoupon = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		System.out.println(ACoupon);
		//13) Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[text()='Delete']").click();
		driver.findElementByXPath("//div[text()='DELETE']").click();
		//14) Close all the browsers
		driver.quit();
	}
	public int  Getnumbers(String text) {
		String text2 = text.replaceAll("[^0-9]", "");
		int Int = Integer.parseInt(text2);
		return Int;
	} 

}
