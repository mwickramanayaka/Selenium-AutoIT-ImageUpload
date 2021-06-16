package demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoITDemo {

	public static void main(String[] args) throws IOException, InterruptedException {
		test();
	}

	public static void test() throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://image.online-convert.com/convert-to-gif");
		driver.findElement(By.id("fileUploadButton")).click();


		//calling AutoIT script
		String projectPath = System.getProperty("user.dir");
		Runtime.getRuntime().exec(projectPath+"\\src\\test\\resources\\FileUploadScript.exe"); 

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		//Fluent wait till the file is upload
		WebElement element  = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement linkElement =  driver.findElement(By.xpath("//span[@title='image.jpg']"));

				if (linkElement.isEnabled()) {
					System.out.println("Element Found");

				}
				return linkElement;
			}
		});

		//Thread.sleep(10000);

		//driver.findElement(By.xpath("//span[@title='image.jpg']"));
		driver.close();
		driver.quit();
	}

}
