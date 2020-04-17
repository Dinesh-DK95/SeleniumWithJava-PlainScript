package testCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
//  shop on Nykaa 
public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//15/04/2020

		// To Disable Browser Notification.

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Launching chrome browser.
		//Set the chromedriver.exe file to the java class.
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver(options);
		

		// 1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//2) Mouseover on Brands and Mouseover on Popular
		WebElement Bele = driver.findElementByXPath("//a[text()='brands']");
		Actions build = new Actions(driver);
		build.moveToElement(Bele).perform();
		WebElement Pele = driver.findElementByXPath("//a[text()='Popular']");
		Actions build1 = new Actions(driver);
		build1.moveToElement(Pele).perform();
		//3) Click L'Oreal Paris
		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']").click();
		
		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windows = driver.getWindowHandles();
		List<String> Winlist = new ArrayList<String>(windows);
		driver.switchTo().window(Winlist.get(1));
		System.out.println(driver.getTitle());
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("title matches");
		}else {
			System.out.println("Title not matched");
		}
        //5) Click sort By and select customer top rated
		driver.findElementByXPath("//div[@class='sort-btn clearfix']").click();
		
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		//Sleep execution for 3 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(3000);
        //6) Click Category and click Shampoo
		driver.findElementByXPath("(//div[@class='clearfix'])[2]").click();
		driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined'])").click();
		driver.findElementByXPath("((//label[@for='chk_Shampoo_undefined']))[2]").click();
		//Sleep execution for 3 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(3000);
        //7) check whether the Filter is applied with Shampoo
		String filter1 = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']/li").getText();
		System.out.println(filter1);
		
		String filter2 = driver.findElementByXPath("(//ul[@class='pull-left applied-filter-lists'])/li[2]").getText();
		System.out.println(filter2);
        if (filter1.contains("Shampoo")) {
        	if (filter2.contains("Shampoo")) {
				System.out.println("The filter Shampoo is applied ");	
			}else {
				System.out.println("The filter Shampoo1 is applied and Shampoo2 is not applied");
			}
			
		}else {
				System.out.println("The filter Shampoo1 is not applied");
		}
        
        //8) Click on L'Oreal Paris Colour Protect Shampoo
        driver.findElementByXPath("//img[@src='https://images-static.nykaa.com/media/catalog/product/tr:w-276,h-276,cm-pad_resize/8/9/8901526102518_color_protect_shampoo_75ml_82.5ml__i1_1.png']").click();
        //9) GO to the new window and select size as 175ml
        Set<String> windows2 = driver.getWindowHandles();
        List<String> winlist2 = new ArrayList<String>(windows2);  
        driver.switchTo().window(winlist2.get(2));
        driver.findElementByXPath("(//span[@class='size-pallets'])[2]").click();
        
        //10) Print the MRP of the product
        System.out.println(driver.findElementByXPath("//span[@class='post-card__content-price-offer']").getText());
        //11) Click on ADD to BAG
        driver.findElementByXPath("(//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  '])").click();
      //Sleep execution for 2 seconds , while DOM is getting loaded after previous action.
        Thread.sleep(2000);
        //12) Go to Shopping Bag 
        driver.findElementByXPath("//div[@class='AddToBagbox']").click();
      //Sleep execution for 2 seconds , while DOM is getting loaded after previous action.
        Thread.sleep(2000);
        //13) Print the Grand Total amount
        System.out.println(driver.findElementByXPath("//div[@class='value medium-strong']").getText());
        //14) Click Proceed
        driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']").click();
        //15) Click on Continue as Guest
        driver.findElementByXPath("//button[@class='btn full big']").click();
        //16) Print the warning message (delay in shipment)
        System.out.println(driver.findElementByXPath("//div[@class='layout horizontal p10 communication-msg mtb10']/div").getText());
        //17) Close all windows
        driver.quit();
	}

}

// Some new learning
// To Scroll down using JS executeScript by visibility of the element
//driver.executeScript("arguments[0].scrollIntoView();", sortele);
//sortele.click();
//JavascriptExecutor js = (JavascriptExecutor) driver;