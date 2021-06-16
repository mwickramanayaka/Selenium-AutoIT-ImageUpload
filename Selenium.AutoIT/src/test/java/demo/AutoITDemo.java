package demo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
		Runtime.getRuntime().exec("D:\\seleniumTests\\inprogress\\AutoIT\\FileUploadScript.exe"); 
		
		Thread.sleep(3000);
		//driver.close();
	}

}
