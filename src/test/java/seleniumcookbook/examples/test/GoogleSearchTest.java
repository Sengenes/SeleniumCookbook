package seleniumcookbook.examples.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import static org.junit.Assert.*;


public class GoogleSearchTest {
	protected WebDriver driver;
	private StringBuffer verificationErrors=new StringBuffer();
	
@Before
public void setUp() {
	System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://www.google.com");
}	
@Test
public void testGoogleSearch() {
	try {
		WebElement element=driver.findElement(By.xpath("//input[@id='lst-ib']"));
		element.sendKeys("Selenium testing tools cookbook");
		element.submit();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith(("selenium testing tools cookbook"));
			}
		});
		assertEquals("Selenium testing tools cookbook - Buscar con Google", driver.getTitle());
		}catch (Error e) {
			verificationErrors.append(e.toString());
			
		}
}

@After
public void terDown() throws Exception{
	driver.quit();
	String verificationErrorString=verificationErrors.toString();
	if(!"".equals(verificationErrorString)) {
		fail(verificationErrorString);
			}
	}

}
