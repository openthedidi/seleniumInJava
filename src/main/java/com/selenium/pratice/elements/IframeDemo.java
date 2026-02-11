package com.selenium.pratice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class IframeDemo {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ilp.nanshanlife.com.tw/main.html?sUrl=$W$ANNOUNCE$ANNOUNCELIST]DJHTM{TYPEID}TDF");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);

        WebElement iframeObj = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("mainframe"))
        );
        driver.switchTo().frame(iframeObj);

        WebElement target = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='【復華投信】目標到期債券基金策略說明202512']")));
        System.out.println(target.getText());



        driver.get("https://docs.oracle.com/javase/8/docs/api/");
        //如果frame有Name or Id 可以直接switch
        driver.switchTo().frame("packageListFrame");
        WebElement targetInPackageList = driver.findElement(By.linkText("java.awt.dnd"));
        targetInPackageList.click();
        //多frame，必須切回default才能切另一個
        driver.switchTo().defaultContent();
        driver.switchTo().frame("packageFrame");
        WebElement targetInAnt = driver.findElement(By.linkText("Autoscroll"));
        targetInAnt.click();






    }
}
