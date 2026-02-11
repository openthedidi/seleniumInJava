package com.selenium.pratice.action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MouseAndKeyboardActionDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.eztravel.com.tw/");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement findHotelBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("瘋台灣"))
        );

        Actions action = new Actions(driver);
        action.moveToElement(findHotelBtn).perform();
        action.doubleClick(findHotelBtn).perform();


        WebElement searchInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("trip-search-dest"))
        );
        action.keyDown(searchInput, Keys.SHIFT).sendKeys("abc").perform();
        action.keyDown(searchInput,Keys.CONTROL).keyDown("a").keyUp(Keys.CONTROL).perform();
        action.sendKeys(Keys.DELETE).perform();
        Thread.sleep(3000);
        action.sendKeys(searchInput,"台北").perform();

        /// Drag And Drop
        driver.get("https://www.globalsqa.com/demo-site/draganddrop/");



        Wait<WebDriver> waitFlunt = new FluentWait<>(driver)
                .pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofSeconds(10));
        WebElement iframe =waitFlunt.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@class='demo-frame']"))
        );
        driver.switchTo().frame(iframe);


        WebElement dragTarget = waitFlunt.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='High Tatras']"))
        );
        WebElement dropTarget = waitFlunt.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='trash']")));

        action.dragAndDrop(dragTarget,dropTarget).perform();

    }
}
