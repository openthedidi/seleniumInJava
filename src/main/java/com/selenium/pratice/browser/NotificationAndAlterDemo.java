package com.selenium.pratice.browser;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NotificationAndAlterDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();


        Alert alertObj = driver.switchTo().alert();
        System.out.println(alertObj.getText());
        alertObj.sendKeys("Test");
        alertObj.accept();

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        alertObj.dismiss();

        WebElement resultP = driver.findElement(By.id("result"));
        System.out.println(resultP.getText());
        driver.quit();


        //handle notification
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");


        WebDriver driver2 = new ChromeDriver(options);
        driver2.get("https://www.eztravel.com.tw/");
        Thread.sleep(3000);
        driver2.quit();


    }
}
