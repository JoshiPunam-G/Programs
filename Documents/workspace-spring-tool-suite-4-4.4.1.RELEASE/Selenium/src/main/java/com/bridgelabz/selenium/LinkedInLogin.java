package com.bridgelabz.selenium;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LinkedInLogin {
	 @Test
     public void loginLinkedIn()
     {
		 System.setProperty("webdriver.chrome.driver", "/home/admin106/Downloads/chromedriver_linux64/chromedriver");
    	 WebDriver driver=new ChromeDriver();
 		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 		 driver.manage().window().maximize();
 		 driver.navigate().to("https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
 		 driver.findElement(By.id("username")).sendKeys("joshipunam207@gmail.com");
 		 driver.findElement(By.xpath("//input[@name='session_password']")).sendKeys("7218374140");
 		 driver.findElement(By.xpath("//button[@class='btn__primary--large from__button--floating']")).click();
 		// driver.findElement(By.xpath("//button[@name='submit']")).click();
       //  Thread.sleep(10000);
     }
		
    

	
}
