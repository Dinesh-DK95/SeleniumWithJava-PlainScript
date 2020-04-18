package testCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

// Book hotel in Make MyTrip
public class MakeMyTrip {

	public static void main(String[] args ) throws InterruptedException {

		//16/04/2020

		// To Disable Browser Notification.

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Launching chrome browser.
		//Set the chromedriver.exe file to the java class.
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver(options);
		//1) Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		// To Maximize the browser.
		driver.manage().window().maximize();
		// IMplicit Wait  Until url is loaded.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//close ad
		//driver.findElementById("webklipper-publisher-widget-container-notification-close-div").click();

		//2) Click Hotels
		driver.findElementByXPath("//a[@href='https://www.makemytrip.com/hotels/']").click();
		//Sleep execution for 2 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(2000);
		//3) Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//input[@class='hsw_inputField font30 lineHeight36 latoBlack']").click();
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
		driver.findElementByXPath("//p[text()='Goa, India']").click();
		//4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElementByXPath("//div[@aria-label='Wed May 20 2020']").click();
		driver.findElementByXPath("//div[@aria-label='Mon May 25 2020']").click();
		//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementByXPath("//input[@class='hsw_inputField guests font20']").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		WebElement age = driver.findElementByXPath("//select[@data-cy='childAge-0']");
		Select drp = new Select(age);
		drp.selectByIndex(11);
		driver.findElementByXPath("//button[@data-cy='submitGuest']").click();
		//6) Click Search button
		driver.findElementByXPath("//button[text()='Search']").click();
		//click to background visible 
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		//7) Select locality as Baga
		driver.findElementByXPath("//label[text()='Baga']").click();
		//Sleep execution for 2 seconds , while DOM is getting loaded after previous action.
		//Thread.sleep(2000);
		//8) Select 5 start in Star Category under Select Filters
		driver.findElementByXPath("//label[text()='5 Star']").click();
		//Sleep execution for 3 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(3000);
		//9) Click on the first resulting hotel and go to the new window
		driver.findElementByXPath("(//div[@class='makeFlex spaceBetween'])[2]").click();
		Set<String> windows = driver.getWindowHandles();
		List<String> WinLis = new ArrayList<String>(windows);
		driver.switchTo().window(WinLis.get(1));
		//Sleep execution for 3 seconds , while DOM is getting loaded after previous action.
		Thread.sleep(3000);
		//10) Print the Hotel Name 
		System.out.println(driver.findElementByXPath("//h1").getText());
		//11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		driver.findElementByXPath("//span[@class='latoBold font12 pointer makeFlex hrtlCenter right blueText']").click();
		driver.findElementByXPath("//span[@class='close']").click();
		//12) Click on BOOK THIS NOW
		driver.findElementByXPath("//a[@class='primaryBtn ']").click();
		//To close warning message
		driver.findElementByXPath("//span[@class='close']").click(); 
		//13) Print theTotal Payable amount 
		System.out.println(driver.findElementByXPath("//span[@class='latoBlack font16']").getText()); 
		//14)Close the browser 
		driver.quit();

	}

}
