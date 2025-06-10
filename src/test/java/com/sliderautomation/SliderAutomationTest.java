package com.sliderautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.URL;
import static org.testng.Assert.assertEquals;

public class SliderAutomationTest {
    private RemoteWebDriver driver;
    private static final String USERNAME = "rashokise2023";
    private static final String ACCESS_KEY = "xly78Z1YyZQ3vwVB0G0HGNonQQpVdoSBakJuiuZYMgGbBqvwTT";
    private static final String GRID_URL = "@hub.lambdatest.com/wd/hub";

    
    @Parameters({"browser", "version", "os"})
    @BeforeClass
    public void setup(String browser, String version, String os) throws Exception {
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
        capabilities.setCapability("name", "Slider Automation Test");
        capabilities.setCapability("build", "Selenium Playground Tests");
        capabilities.setCapability("timezone", "UTC+05:30");
        
        driver = new RemoteWebDriver(
            new URL("https://" + USERNAME + ":" + ACCESS_KEY + GRID_URL), 
            capabilities
        );
        
        driver.manage().window().maximize();
    }

    @Test(timeOut = 20000)
    public void testSliderFunctionality() throws InterruptedException {
        // 1. Open the Selenium Playground
        driver.get("https://www.lambdatest.com/selenium-playground");

        // 2. Click "Drag & Drop Sliders" under "Progress Bars & Sliders"
        driver.findElement(By.linkText("Drag & Drop Sliders")).click();

        // 3. Locate the slider with default value 15
        WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));

        // 4. Drag the slider to 95 (EXACTLY AS YOU REQUESTED)
        Actions actions = new Actions(driver);
        // Move slider by calculated offset — you may need to fine-tune based on actual browser/device
        actions.clickAndHold(slider).moveByOffset(212, 0).release().perform();  // 550 is approx for 95 value

        Thread.sleep(1000);
        
        String ele = driver.findElement(By.xpath("//*[@id=\"rangeSuccess\"]")).getText();

        // 5. Validate the slider value
        assertEquals(ele, "95", "Slider value validation failed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
