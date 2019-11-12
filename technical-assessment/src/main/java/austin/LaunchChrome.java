package austin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaunchChrome {

	public static void main(String[] args) throws InterruptedException {
		
		// Declare All variables used.
		System.setProperty("webdriver.chrome.driver", "/Users/AustinHua/Documents/DXworkspace/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		// navigates and Validates Website
		implicitWait(driver, 10);
		String url = "http://the-internet.herokuapp.com/";
		String titlePage = "The Internet";
		System.out.println("------ Opening the website ------");
		driver.get(url);
		if (driver.getTitle() != null && driver.getTitle().contains(titlePage)){
			System.out.println("Website: Passed! ");
		}
		else {
			System.out.println("Website: Failed! ");
			driver.quit();
		}
		
		// Initialise an explicitWait for the buttons
		WebDriverWait explicitWait = new WebDriverWait(driver, 10);
		
		// Click Javascript Alerts Button
		System.out.println("------ Click on Javascript Alerts ------");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"content\"]/ul/li[29]/a")));
		
		Boolean jsAlertButton = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li[29]/a")).size() > 0;
		if (jsAlertButton) {
			System.out.println("Javascript Alert Button pressed: Passed! ");
			driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[29]/a")).click();
		}
		else {
			System.out.println("Javascript Alert Button pressed: Failed! ");
			driver.quit();
		}
		
		// Click Javascript Confirm Button
		System.out.println("------ Click on JS Confirm ------");
		explicitWait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button")));
		
		Boolean jsConfirmButton = driver.findElements(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button")).size() > 0;
		if (jsConfirmButton) {
			System.out.println("Javascript Confirm Button: Passed! ");
			driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button")).click();
		}
		else {
			System.out.println("Javascript Confirm Button: Failed! ");
			driver.quit();
		}
			
		
		// Check to make sure no Alert box has appeared before starting.
		new WebDriverWait(driver, 10)
				.ignoring(NoAlertPresentException.class)
		        .until(ExpectedConditions.alertIsPresent());
		
		// Accept the Alert Box
		System.out.println("------ Accept the Alert Box ------");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		// check to make sure green results are returned
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"result\"]")));
		String result = driver.findElement(By.xpath("//*[@id=\"result\"]")).getText();
		if (result.equals("You clicked: Ok")) {
			System.out.println("Results returned: Passed! ");
		}
		else {
			System.out.println("Results returned: Failed! ");
		}
		
		// Closing Screen 
		System.out.println("------ Closing the browser ------");
		driver.quit();
	
	}
	
	public static void implicitWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
}

/*
List<WebElement> jsAlertButton = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li[29]/a"));
//driver.findElement(By.xpath("//a[starts-with(@href, '/javascript')]")).click();
if(jsAlertButton.isEmpty()) {
	System.out.println("Button_1: Failed! ");
	driver.quit();
}
else {
	System.out.println("Button_1: Passed! ");
	driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[29]/a")).click();
}
*/
