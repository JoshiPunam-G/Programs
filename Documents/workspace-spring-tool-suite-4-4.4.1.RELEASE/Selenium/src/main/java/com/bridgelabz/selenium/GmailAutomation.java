package com.bridgelabz.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailAutomation {

	public static void main(String[] args) throws InterruptedException {
		 
		System.setProperty("webdriver.chrome.driver", "/home/admin106/Downloads/chromedriver_linux64/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		/**Open gmail login page*/ 
		driver.navigate().to("http://mail.google.com");
		
		/** Enter Username */
		driver.findElement(By.name("identifier")).sendKeys("toolselenium07@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		
        /** Enter password */
		WebElement passwordButton = driver.findElement(By.xpath("//input[@name='password']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(passwordButton));
		passwordButton.sendKeys("tool@123");
		
		/**Submit */
		driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
		
		/**Compose Mail */
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();
		
		/**Sent Mail  */
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("punamsj20@gmail.com");
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Automated Mail");
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
		
//		/**Attach File */
//		driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']")).click();
//		
       
	}

}
