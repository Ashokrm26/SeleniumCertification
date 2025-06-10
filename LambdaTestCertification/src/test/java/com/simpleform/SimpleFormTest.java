package com.simpleform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;

public class SimpleFormTest {
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
            capabilities.setCapability("name", "Simple Form Test");
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

    @Test(timeOut = 20000) // 20 seconds timeout
    public void testSimpleFormDemo() {
        try {
            // Step 2: Click "Simple Form Demo" (using linkText locator)
            WebElement simpleFormLink = driver.findElement(By.linkText("Simple Form Demo"));
            simpleFormLink.click();

            // Step 3: Validate the URL contains "simple-form-demo"
            String currentUrl = driver.getCurrentUrl();
            assert currentUrl.contains("simple-form-demo") : "URL validation failed";

            // Step 4: Create a variable for the message
            String message = "Welcome to LambdaTest";

            // Step 5: Enter message in the input box (using id locator)
            WebElement messageInput = driver.findElement(By.id("user-message"));
            messageInput.sendKeys(message);
            
            // Step 6: Click "Get Checked Value" button (using cssSelector locator)
            WebElement showMessageButton = driver.findElement(By.cssSelector("button#showInput"));
            showMessageButton.click();

            // Step 7: Validate that message is displayed correctly (using xpath locator)
            WebElement displayedMessage = driver.findElement(By.xpath("//p[@id='message']"));

            String output = displayedMessage.getText();
            assert output.equals(message) : "Message validation failed. Output: " + output;

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}