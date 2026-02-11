package com.selenium.pratice.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class TabSwitchDemo {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get("https://www.skbank.com.tw/search");
        WindowType newTab = WindowType.TAB;
        WindowType newWindow = WindowType.WINDOW;

        String currentWindowHandle = driver.getWindowHandle();
        System.out.println("第一個視窗的 Handle: " + currentWindowHandle);


        driver.switchTo().newWindow(newTab);
        driver.get("https://www.google.com");
        String newTabWindowHandle = driver.getWindowHandle();
        System.out.println("新分頁的 Handle: " + newTabWindowHandle);


        driver.switchTo().newWindow(newWindow);
        driver.get("https://vacation.eztravel.com.tw/");
        String newWindowWindowHandle = driver.getWindowHandle();
        System.out.println("新視窗的 Handle: " + newWindowWindowHandle);



        driver.quit();

    }
}
