package com.fca.calidad.funcionales;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runners.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MernCrudTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	JavascriptExecutor js;
	
	@Before
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		js = (JavascriptExecutor) driver;
	}
  
	@Test
	public void aagregarUsuarioTest() throws Exception {
		driver.get("https://mern-crud.herokuapp.com/");
		driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
		pause(2000);
		driver.findElement(By.name("name")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Walter White");
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("walterwhite@meta.com");
		driver.findElement(By.name("age")).click();
		driver.findElement(By.name("age")).clear();
		driver.findElement(By.name("age")).sendKeys("52");
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
		pause(2000);
		driver.findElement(By.xpath("//i")).click();
		pause(2000);
		System.out.println("Usuario agregado con ??xito"+"\n");
		assertTrue(driver.findElement(By.xpath("//*[text()='Walter White']")).isDisplayed());
	}
	  
	@Test
	public void bbuscarUsuarioTest() throws Exception {
		driver.get("https://mern-crud.herokuapp.com/");
		pause(2000);
		System.out.println("Usuario encontrado con ??xito"+"\n");
		assertTrue(driver.findElement(By.xpath("//*[text()='Walter White']")).isDisplayed());
	}
	 
	@Test
	public void ceditarUsuarioTest() throws Exception {
		driver.get("https://mern-crud.herokuapp.com/");
		driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
		pause(2000);
		driver.findElement(By.name("name")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Heisenberg");
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("heisenberg@meta.com");
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
		pause(2000);
		driver.findElement(By.xpath("//i")).click();
		pause(2000);
		System.out.println("Usuario editado con ??xito"+"\n");
		assertTrue(driver.findElement(By.xpath("//*[text()='Heisenberg']")).isDisplayed());
	}
	
	@Test
	public void deliminarUsuarioTest() throws Exception {
		driver.get("https://mern-crud.herokuapp.com/");
		driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
		pause(2000);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Heisenberg'])[2]/following::button[1]")).click();
		pause(2000);
		System.out.println("Usuario eliminado con ??xito"+"\n");
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Heisenberg[\\s\\S]*$"));
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	    	fail(verificationErrorString);
	    }
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	private boolean isAlertPresent() {
	    try {
	    	driver.switchTo().alert();
	    	return true;
	    } catch (NoAlertPresentException e) {
	    	return false;
	    }
	}
	
	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
	    }
	}
	  
	private void pause(long mils) {
		try {
			Thread.sleep(mils);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
