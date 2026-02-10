package com.selenium.pratice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSettingDemo {
    public static void main(String[] args) {

        /**
         *可以指定drive的路徑
         */
        System.setProperty("webdriver.chrome.driver","D:\\selenium-driver\\driver\\chromedriver.exe");
        WebDriver chromeDriver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver","D:\\selenium-driver\\driver\\geckodriver.exe");
        WebDriver firefoxDriver = new FirefoxDriver();

        System.setProperty("webdriver.edge.driver","D:\\selenium-driver\\driver\\msedgedriver.exe");
        WebDriver edgeDriver = new EdgeDriver();

        edgeDriver.get("https://www.google.com");
        edgeDriver.quit();
    }

}
