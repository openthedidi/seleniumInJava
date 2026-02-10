package com.selenium.pratice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserNavigationDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to a URL
        driver.get("https://www.google.com");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        Thread.sleep(2000); // Wait for 2 seconds

        // Navigate to another URL
        driver.navigate().to("https://www.bing.com");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        Thread.sleep(2000);

        // Go back to the previous page (Google)
        driver.navigate().back();
        System.out.println("Current URL after back: " + driver.getCurrentUrl());
        Thread.sleep(2000);

        // Go forward to the next page (Bing)
        driver.navigate().forward();
        System.out.println("Current URL after forward: " + driver.getCurrentUrl());
        Thread.sleep(2000);

        // Refresh the current page
        driver.navigate().refresh();
        System.out.println("Current URL after refresh: " + driver.getCurrentUrl());
        Thread.sleep(2000);

        driver.quit();
    }

}
