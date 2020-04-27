package testCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasket {

	
	public static void main(String[] args) throws InterruptedException {
		//21/04/2020
		BigBasket obj = new BigBasket();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Launching chrome browser.
		//Set the chromedriver.exe file to the java class.
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentoutput", "true");
		ChromeDriver driver= new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//1) Go to https://www.bigbasket.com/
		driver.get("https://www.bigbasket.com/");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		///2) mouse over on  Shop by Category 
		WebElement ShopBY = driver.findElementByXPath("//a[@qa='categoryDD']");
		Actions build = new Actions(driver);
		build.moveToElement(ShopBY).perform();

		//3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS 
		WebElement fom = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		build.moveToElement(fom).perform();
		WebElement rice = driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]");
		build.moveToElement(rice).perform();


		//4) Click on Boiled & Steam Rice
		WebElement BSrice = driver.findElementByXPath("(//a[text()='Boiled & Steam Rice'])[2]");
		build.moveToElement(BSrice).perform();
		BSrice.click();
		Thread.sleep(2000);
		//5) Choose the Brand as bb Royal
		driver.findElementByXPath("//span[text()='bb Royal']").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//6) Go to Ponni Boiled Rice - Super Premium (Ponni Boiled Rice/Puzhungal Arisi)and select 5kg bag from Dropdown
		driver.findElementByXPath("//a[text()='Ponni Boiled Rice/Puzhungal Arisi']").click();
		driver.findElementByXPath("//div[text()='5 kg']").click();

		//7) print the price of Rice
		String RP = driver.findElementByXPath("//td[@data-qa='productPrice']").getText();
		System.out.println(obj.Getnumbers(RP));


		//8) Click Add button
		driver.findElementByXPath("//div[@data-qa='addToBasket']").click();
		//9) Verify the success message displayed 

		System.out.println(driver.findElementByXPath("//div[@class='_3pK87']").getText());
		//<div class="_3pK87">Successfully added Ponni Boiled Rice to the basket</div>
		//div[text()='Successfully added Ponni Boiled Rice to the basket']

		//10) Type Dal in Search field and enter
		driver.findElementByXPath("//input[@aria-label='productSearch']").sendKeys("dal",Keys.ENTER);
		//12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']").click();
		driver.findElementByXPath("//div[text()='2 kg']").click();

		WebElement qty = driver.findElementByXPath("//input[@name='qty']");
		qty.clear();
		qty.sendKeys("2");
		//13) Print the price of Dal
		String DP = driver.findElementByXPath("//td[@data-qa='productPrice']").getText();
		System.out.println(obj.Getnumbers(DP));


		//14) Click Add button
		driver.findElementByXPath("//div[@data-qa='addToBasket']").click();
		Thread.sleep(2000);
		//15) Mouse hover on My Basket 
		WebElement Basket = driver.findElementByXPath("//span[text()='My Basket']");
		build.moveToElement(Basket).perform();
		//Thread.sleep(2000);-> This does not read the element in given time.

		//16) Validate the Sub Total displayed for the selected items

		String ele1txt = driver.findElementByXPath("//div[@class='wOyS7']").getText();
		int Item1 = obj.GetItemPrice(ele1txt);
		System.out.println("The Price of first item is "+Item1);
		String ele2txt = driver.findElementByXPath("(//div[@class='wOyS7'])[2]").getText();
		int Item2 = obj.GetItemPrice(ele2txt);
		System.out.println("The Price of Second item is "+Item2);
		String Stotal = driver.findElementByXPath("(//div[@class='Mbz4m']/span)[2]").getText();
		int Subtotal = obj.Getnumbers(Stotal);
		System.out.println("The Subtotal is "+obj.Getnumbers(Stotal));
		int total= Item1 + Item2;
		System.out.println("The Total Price is "+total);
		if (total==Subtotal) {
			System.out.println("The Subtotal displayed is Valid");
		}else {
			System.out.println("The Subtotal displayed is not Valid ");
		}
		//17) Reduce the Quantity of Dal as 1 

		build.moveToElement(Basket).perform();
		driver.findElementByXPath("(//div[@class='_2bvd7'])[2]").click();
		WebElement itemname=driver.findElementByXPath("//h2[text()='bb Popular Toor/Arhar Dal']");
		build.moveToElement(itemname).perform(); itemname.click();
		Thread.sleep(3000);
		build.moveToElement(Basket).perform();
		driver.findElementByXPath("//div[text()='2 kg']").click();
		Thread.sleep(3000);
		//18) Validate the Sub Total for the current items
		build.moveToElement(Basket).perform();
		String ele1atxt = driver.findElementByXPath("//div[@class='wOyS7']").getText();
		int Item1a = obj.GetItemPrice(ele1atxt);
		System.out.println("The Price of first item is "+Item1a);
		String ele2atxt = driver.findElementByXPath("(//div[@class='wOyS7'])[2]").getText();
		int Item2a = obj.GetItemPrice(ele2atxt);
		System.out.println("The Price of Second item is "+Item2a);
		String Stotal2 = driver.findElementByXPath("(//div[@class='Mbz4m']/span)[2]").getText();
		int Subtotal2 = obj.Getnumbers(Stotal2);
		System.out.println("The Subtotal is "+obj.Getnumbers(Stotal2));

		int total2= Item1a + Item2a;
		System.out.println("The Total Price is "+total2);
		if (total2==Subtotal2) {
			System.out.println("The Subtotal displayed is Valid");
		}else {
			System.out.println("The Subtotal displayed is not Valid ");
		}
		//19) Close the Browser
		driver.quit();
	}
	
	public  int GetItemPrice(String text) {
		String[] split = text.split("x");
		String itqty = split[0].replaceAll("\\s", "");
		int ItemQty = Integer.parseInt(itqty);
		String it = split[1].replaceAll("\\s", "");
		String[] split2 = it.split("[.]");
		int ItemP = Integer.parseInt(split2[0]);
		int ItemPrice = ItemQty * ItemP;
		//System.out.println(ItemPrice);
		return ItemPrice;

	} 
	public int  Getnumbers(String text) {
		String text2 = text.replaceAll("[^0-9]", "");
		int Int = Integer.parseInt(text2);
		return Int;
	} 
}

//New Learning
/*
*  PAUSE SCRIPT EXECUTION
* 
* For this example, there's a site with a drop-down menu that immediately
* disappears when you try to inspect it.
* 
* To resolve this you can delay JavaScript execution and view the source code
* by doing the following:
* 
* Open your page Open the dev console (F12 on Windows/Linux or option + ⌘ + J
* on OSX) Select the Console tab in Chrome Developer Tools Enter this into the
* Console and press return:
* 
* setTimeout(function(){debugger;}, 5000)
* 
* In the web browser window, click or hover over the desired element to
* initiate the drop-down action Wait 5 seconds without doing anything and the
* script will automatically pause. Go to the Elements tab in Developer Tools
* Look for your element there or click the inspect elements icon to directly
* access your drop-down menu elements within the HTML
*/