package com.inputform;

import java.net.URL;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InputFormAutomation {
	
	WebDriver driver;
    String username = "rashokise2023";
    String accessKey = "xly78Z1YyZQ3vwVB0G0HGNonQQpVdoSBakJuiuZYMgGbBqvwTT";
    String gridURL = "@hub.lambdatest.com/wd/hub";

    @Parameters({"browser", "version", "os"})
    @BeforeClass
    public void setup(String browser, String version, String os) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", browser);
            capabilities.setCapability("version", version);
            capabilities.setCapability("platform", os);
            
            // Enable logging and recording
            capabilities.setCapability("visual", true);
            capabilities.setCapability("video", true);
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", true);
            
            // Additional capabilities
            capabilities.setCapability("name", "Input Form Automation");
            capabilities.setCapability("build", "Selenium Playground Tests");
            capabilities.setCapability("timezone", "UTC+05:30");
            
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
            
            // Step 1: Open Selenium Playground
            driver.get("https://www.lambdatest.com/selenium-playground");
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("Error setting up driver: " + e.getMessage());
        }
    }
	
    @Test(timeOut = 20000)
    public void InputForm() throws InterruptedException {

        // 1. Open the playground
        driver.get("https://www.lambdatest.com/selenium-playground");

        // 2. Click "Input Form Submit"
        driver.findElement(By.linkText("Input Form Submit")).click();
        
        Thread.sleep(2000);

       // 3. Fill in the form fields
       driver.findElement(By.id("name")).sendKeys("Ashok R");
       driver.findElement(By.id("inputEmail4")).sendKeys("rashokise2023@gmail.com");
       driver.findElement(By.id("inputPassword4")).sendKeys("A1s2h3o4k5_r@123");
       driver.findElement(By.id("company")).sendKeys("Kodnest");
       driver.findElement(By.id("websitename")).sendKeys("https://www.linkedin/in/ashok-ramamoorthy-/");

        // 4. Select "India" from the Country dropdown by visible text
        WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText("India");

        driver.findElement(By.id("inputCity")).sendKeys("Bengaluru");
        driver.findElement(By.id("inputAddress1")).sendKeys("#10A, 5th main, 7th cross");
        driver.findElement(By.id("inputAddress2")).sendKeys("2/352B, tms, krishnagiri");
        driver.findElement(By.id("inputState")).sendKeys("Karnataka");
        driver.findElement(By.id("inputZip")).sendKeys("560068");
        
        
        driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div/div/div/form/div[6]/button")).click();

        // 5. Validate success message
        Thread.sleep(2000);
        WebElement successMsg = driver.findElement(By.xpath("/html/body/div[1]/div/section[2]/div/div/div/div/p"));

        String expected = "Thanks for contacting us, we will get back to you shortly.";
        if (successMsg.getText().contains(expected)) {
            System.out.println("Test Passed: Success message displayed correctly.");
        } else {
            System.out.println("Test Failed: Message = " + successMsg.getText());
        }

        driver.quit();
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

