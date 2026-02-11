package com.selenium.pratice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.nanshanlife.com.tw/nanshanlife/products-health");

        Duration implicitDuration = Duration.ofSeconds(1);
        Duration explicitDuration = Duration.ofSeconds(3);
        /**
         * implicit wait：全域設定，等待時間最長為設定值
         * 缺點：有時候某些頁面會超過捨定值
         */
        driver.manage().timeouts().implicitlyWait(implicitDuration);
        try {
            WebElement enBtn = driver.findElement(By.xpath("//a[text()='EN']"));
            System.out.println("implicit wait:" + enBtn.getText());
            enBtn.click();
        } catch (Exception e) {
            System.out.println("implicit wait failure");
            /**
             * Explicit wait:個別設定，等待時間最長為設定值
             */
            WebDriverWait wait = new WebDriverWait(driver, explicitDuration);
            WebElement enBtn = wait.until(
                    driverObj -> driverObj.findElement(By.xpath("//a[text()='EN']"))
            );
            System.out.println("Explicit wait:" + enBtn.getText());


            WebElement publicInfoBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='資訊公開']"))
            );
            System.out.println("Explicit wait:" + publicInfoBtn.getText());

        }


        /**
         * fluent wait：10s,polling 3s > 代表每三秒檢查一次，因為設定總時間是10s，所以會檢查4次，
         * 如果第 9 秒的檢查仍然失敗，等待時間耗盡，wait.until() 會拋出 TimeoutException。
         */

        driver.get("https://www.nanshanlife.com.tw/nanshanlife/");

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        WebElement incidentBtn = fluentWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='意外傷害' and @class='h07 insurance-glance__title']"))
        );
        System.out.println("fluent wait:" + incidentBtn.getText());
        incidentBtn.click();

        Thread.sleep(3000);

        driver.quit();


    }
}
