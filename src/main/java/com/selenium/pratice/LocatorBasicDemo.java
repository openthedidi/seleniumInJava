package com.selenium.pratice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorBasicDemo {
    public static void main(String[] args) {


        WebDriver chromeDrive = new ChromeDriver();
        chromeDrive.manage().window().maximize();

        chromeDrive.get("https://demo.nopcommerce.com");


        WebElement input=chromeDrive.findElement(By.xpath("//input[@id='small-searchterms']"));

        input.sendKeys("iphone");


        WebElement loginBtn = chromeDrive.findElement(By.className("ico-login"));
        System.out.println(loginBtn.getText());

        WebElement topCartLink = chromeDrive.findElement(By.cssSelector("a.ico-cart"));
        System.out.println(topCartLink.getText());

        WebElement pollAnswer1Label = chromeDrive.findElement(By.cssSelector("label[for='pollanswers-1']"));
        System.out.println(pollAnswer1Label.getText());


        List<WebElement> aElementList = chromeDrive.findElements(By.tagName("a"));
        System.out.println(aElementList.size());


        chromeDrive.quit();




    }

}
