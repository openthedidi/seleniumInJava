package com.selenium.pratice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectionDemo {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://tiponet.tipo.gov.tw/020_OUT_V1/registry.do");
//
//        /**
//         * 方法一：使用selenium.support.ui.Select
//         */
//        WebElement cityDropDown = driver.findElement(By.xpath("//select[@name='stLocalAddr_1']"));
//        Select citySelectObj = new Select(cityDropDown);
//
//        /**
//         * 方法二：直接選option
//         */
//        citySelectObj.selectByVisibleText("新北市");
//        WebElement districtOption = driver.findElement(By.xpath("//option[text()='金山區']"));
//        districtOption.click();
//
//        Thread.sleep(2000);
//        driver.quit();


        /**
         * autoSuggestSelection
         */


        //<input type="text" id="autosuggest" class="inputs ui-autocomplete-input valid" placeholder="Type to Select" autocomplete="off">
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        WebElement autoSuggest = driver.findElement(By.id("autosuggest"));
        autoSuggest.sendKeys("Ar");
        Thread.sleep(3000);

        WebElement arubaLi = driver.findElement(By.xpath("//a[text()='Aruba']"));
        arubaLi.click();













    }

}
