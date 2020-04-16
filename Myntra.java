package testCase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
// Selenium Test Case Without Framework to Shop on Myntra
public class Myntra {


	public static void main(String[] args) throws InterruptedException {

		// To Disable Browser Notification.

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Launching chrome browser.
		//Set the chromedriver.exe file to the java class.
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver(options);
		// 1) To load the url(Open https://www.myntra.com/).
		driver.get("https://www.myntra.com/");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// 2)  Mouse over on WOMEN (Actions -> moveToElement.
		WebElement ele1 = driver.findElementByXPath("//a [text()=\"Women\"]");
		Actions builder = new Actions(driver);
		builder.moveToElement(ele1).perform();

		// 3) Click Jackets & Coats.
		driver.findElementByLinkText("Jackets & Coats").click();
		// 4) Find the total count of item (top) -> getText -> String.
		String text = driver.findElementByClassName("title-count").getText();
		String text2 = text.replaceAll("\\D", "");
		int totalitem = Integer.parseInt(text2);
		System.out.println("The Count of Jackets&Coat For Women is "+totalitem);
		// 5)Validate the sum of categories count matches.
		// 5a)find Jacket count. 
		String text3 = driver.findElementByClassName("categories-num").getText();
		String text4 = text3.replaceAll("\\D", "");
		int Jacketscount = Integer.parseInt(text4);
		System.out.println("The Count of Jacket is "+Jacketscount);

		// 5b)find Coats count.
		String text5 = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String text6 = text5.replaceAll("\\D", "");
		int Coatscount = Integer.parseInt(text6);
		System.out.println("The Count of Coat is "+ Coatscount);
		int categoriescount = Jacketscount + Coatscount;
		System.out.println("The Count of Category is "+categoriescount);
		if (categoriescount== totalitem) {

			System.out.println("Count Matches");

		}else {
			System.out.println("Count Fails");
		}
		// 6) Check Coats.
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		// 7) Click + More option under BRAND.
		driver.findElementByXPath("//div[@class='brand-more']").click();
		// 8) Type MANGO and click checkbox.
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("mango");
		driver.findElementByXPath("//span[@class='FilterDirectory-count']/following-sibling::div").click();
		// 9) Close the pop-up x
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		// Sleep execution for 5 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(5000);
		/*
		 * 10) Confirm all the Coats are of brand MANGO 
		 * findElements (brand) ->
		 * List<WebElement> foreach -> getText of each brand 
		 * compare > if(condition)
		 */
		List<WebElement> CoatsBName = driver.findElementsByXPath("//h3");
		int size = CoatsBName.size();
		int CountMatches =0;
		int CountNotMatches =0;
		for (WebElement ele : CoatsBName) {
			String coatB = ele.getText();
			if (coatB.equalsIgnoreCase("MANGO")) {
				CountMatches= CountMatches+1;
			}else {
				CountNotMatches= CountNotMatches+1;
			}
		}
		System.out.println("The Count of Sort result is " + size);
		System.out.println("The count of Count Matches is "+CountMatches);
		System.out.println("The count of Count does not Match is "+CountNotMatches);
		if (size==CountMatches) {
			System.out.println("all the Coats are of brand MANGO");
		}else {
			System.out.println("all the Coats are not of brand MANGO, May be some of different brand");
		}
		// 11) Sort by Better Discount
		WebElement sort = driver.findElementByXPath("//div[@class='sort-sortBy']"); 
		Actions builder1 = new  Actions(driver);
		builder1.moveToElement(sort).perform();
		driver.findElementByXPath("//label[text()='Better Discount']").click();
		//Sleep execution for 3 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(3000);
		
		/*
		 * //12) Find the price of first displayed item 
		 * findElements (price) ->List<WebElement>
		 * get(0) -> WebElement -> getText -> String -> int
		 */
		
		  List<WebElement> Pricelist =
		  driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		  WebElement bestprice = Pricelist.get(0); String BPrice = bestprice.getText();
		  String BP = BPrice.replaceAll("\\D", ""); int SortedBDP =
		  Integer.parseInt(BP); System.out.println(SortedBDP); List<WebElement>
		  
		  // 12a) Find the Product Name of first displayed item
		  ProductName = driver.findElementsByXPath("//h4[@class='product-product']");
		  WebElement PN1 = ProductName.get(0); String PN = PN1.getText();
		  System.out.println(PN);
		  
		  //13) Mouse over on size of the first item
		  
		  WebElement mouseoverPN =
		  driver.findElementByXPath("//h4[@class='product-product']"); Actions builder2
		  = new Actions(driver); builder2.moveToElement(mouseoverPN).perform();
		  
		  //14) Click on WishList Now
		  driver.findElementByXPath("//span[@class='product-launchDate']").click();
		  //15) Close Browser 
		  driver.close();
	}
}

//Some learning 

// 1. About Webdriver wait
/*
 * WebDriverWait wait = new WebDriverWait(driver, 30);
 * 
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
 * "//input[@value='discount']")));
 */

// 2. When Visibility :Hidden of element , When Element not interactable use JS execute method to interact.

//WebElement ele = driver.findElementByXPath("//input[@value='discount']").click();

//driver.executeScript("arguments[0].click();", ele);
