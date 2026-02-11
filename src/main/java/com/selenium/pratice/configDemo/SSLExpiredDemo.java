package com.selenium.pratice.configDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SSLExpiredDemo {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
//      以下兩種都可以解決憑證問題
        options.setAcceptInsecureCerts(true);
        options.addArguments("--ignore-certificate-errors");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");
        System.out.println("Page title: " + driver.getTitle());

    }

}
