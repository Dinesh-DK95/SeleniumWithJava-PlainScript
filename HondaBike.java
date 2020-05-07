package testCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HondaBike {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Launching chrome browser.
		//Set the chromedriver.exe file to the java class.
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentoutput", "true");
		ChromeDriver driver= new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//1) Go to https://www.honda2wheelersindia.com/
		driver.get("https://www.honda2wheelersindia.com/");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//close Pop Up Message 
		
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='close']")));
			driver.findElementByXPath("//button[@class='close']").click();

		} catch (Exception e) {
			System.out.println("Pop Up Not displayed");
		}
		
		//2) Click on scooters and click dio
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Scooter']")));
		driver.findElementByXPath("//a[text()='Scooter']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/dio-BS-VI/']")));
		driver.findElementByXPath("//a[@href='/dio-BS-VI/']").click();
		
		//3) Click on Specifications and mouseover on ENGINE
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Specifications']")));
		driver.findElementByXPath("//a[text()='Specifications']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='ENGINE']")));
		WebElement engine = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions build = new Actions(driver);
		build.moveToElement(engine).perform();
		//4) Get Displacement value
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Displacement']/following-sibling::span")));
		System.out.println("The Displacement Value of Dio is "+driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText());
		String text2 = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
		String DioD = text2.replaceAll("[^.0-9]", "");
		double DioDisp = Double.parseDouble(DioD);
		//5) Go to Scooters and click Activa 125
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Scooter']")));
		driver.findElementByXPath("//a[text()='Scooter']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/assets/images/thumb/activa-125new-icon.png']")));
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
		
		//6) Click on Specifications and mouseover on ENGINE
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Specifications']")));
		driver.findElementByXPath("//a[text()='Specifications']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[text()='ENGINE']")));
		WebElement ENGINE = driver.findElementByXPath("//li/a[text()='ENGINE']");
		build.moveToElement(ENGINE).perform();
		
		//7) Get Displacement value
		System.out.println("The Displacement Value of Activa 125 is "+driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText());
		String text = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
		String ActDis = text.replaceAll("[^.0-9]", "");
		double ActivaDis = Double.parseDouble(ActDis);
		
		//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if (DioDisp>ActivaDis) {
			System.out.println("Dio Displacement is better");
			
		}else {
			System.out.println("Activa Displacement is better");
		}
		//9) Click FAQ from Menu 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='FAQ']")));
		driver.findElementByXPath("//a[text()='FAQ']").click();
		//10) Click Activa 125 BS-VI under Browse By Product
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Activa 125 BS-VI']")));
		driver.findElementByXPath("//a[text()='Activa 125 BS-VI']").click();
		//11) Click  Vehicle Price 
		driver.findElementByXPath("//a[text()=' Vehicle Price']").click();
		//12) Make sure Activa 125 BS-VI selected and click submit
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ModelID6")));
		// Use Select class to get text . Since it is DD
		WebElement selscooter = driver.findElementById("ModelID6");
		Select DD = new Select(selscooter);
		String selected = DD.getFirstSelectedOption().getText();
		System.out.println("The Selected option is "+selected);
		if (selected.equalsIgnoreCase("Activa 125 BS-VI")) {
			driver.findElementByXPath("//button[@id='submit6']").click();
		}else {
			System.out.println("The Selected item is not Selected");
		}
		//13) click the price link
		driver.findElementByXPath("//a[text()='Click here to know the price of Activa 125 BS-VI.']").click();
		//14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> windows = driver.getWindowHandles();
		List<String> Winlis = new ArrayList<String>(windows);
		driver.switchTo().window(Winlis.get(1));
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='StateID']")));
		WebElement State = driver.findElementByXPath("//select[@id='StateID']");
		Select DD2 = new Select(State);
		DD2.selectByVisibleText("Tamil Nadu");
		WebElement City = driver.findElementByXPath("//select[@id='CityID']");
		Select DD3 = new Select(City);
		DD3.selectByVisibleText("Chennai");
		//15) Click Search
		driver.findElementByXPath("//button[text()='Search']").click();
		//16) Print all the 3 models and their prices
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));
		List<WebElement> rows = driver.findElementsByXPath("//tbody/tr");
		
		int size = rows.size();
		for (int i = 0; i < size; i++) {
			WebElement nrow = rows.get(i);
			List<WebElement> nrowcols = nrow.findElements(By.tagName("td"));
			int size2 = nrowcols.size();
			for (int j = 0; j<size2; j++) {
				if (!nrowcols.get(j).getText().equalsIgnoreCase("chennai")) {
					System.out.println(nrowcols.get(j).getText());

				}

			}
		}
		
		//17) Close the Browser
		driver.quit();
	}

}
