package com.selenium.pratice.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class WindowsSwitchDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.skbank.com.tw/search");

        //getWindowHandle，代表driver關注的視窗，不會因為另開分頁而改變關注
        String currentWindowHandle = driver.getWindowHandle();
        System.out.println(currentWindowHandle);

        Thread.sleep(3000);
        WebElement enBtn = driver.findElement(By.xpath("//span[@class=\'header__nav-text ng-tns-c120-0\'][normalize-space()=\'EN\']"));
        enBtn.click();
        Thread.sleep(3000);

        Set<String> windowHandles = driver.getWindowHandles();
        String enWindowHandle = "";
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                enWindowHandle = windowHandle;
                //切換driver關注的視窗
                driver.switchTo().window(enWindowHandle);
                break;
            }
        }

        System.out.println(enWindowHandle);
        Thread.sleep(3000);
        driver.switchTo().window(currentWindowHandle);
        Thread.sleep(3000);


        driver.quit();


    }
}
