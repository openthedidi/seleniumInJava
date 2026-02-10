package com.selenium.pratice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorAdvancedDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.nanshanlife.com.tw/nanshanlife/products-health");

        WebElement self = driver.findElement(
                By.xpath("//li[contains(text(),'安寧療護')]/self::li"));

        WebElement totalItemsUl = driver.findElement(
                By.xpath("//li[contains(text(),'安寧療護')]/parent::ul"));

//      ----------ancestor-----------
//      ancestor後面的html tag樣式決定相同的祖先數量

        WebElement totalItemsDiv = driver.findElement(
                By.xpath("//li[contains(text(),'安寧療護')]/ancestor::nanshan-product-card"));
//      ----------descendant-----------
//       self以下的全部層級
        List<WebElement> totalItemsLi = driver.findElements(By.xpath("//li[contains(text(),'安寧療護')]/ancestor::div/child::ul/descendant::li"));

        WebElement medicalItem = driver.findElement(By.xpath("//li[contains(text(),'安寧療護')]/ancestor::div/child::ul/descendant::li[contains(.,'醫材補助')]"));
        System.out.println(medicalItem.getText());
//      ----------preceding-sibling-----------
//       與self平行及以前的層級
        List<WebElement> medicalItemsOnePart = driver.findElements(By.xpath("//li[contains(text(),'安寧療護')]/ancestor::div/child::ul/descendant::li[contains(.,'醫材補助')]//preceding-sibling::*"));
//      ----------preceding------------
//       不含parent，只有與parent的平行以前的層級與self平行且以前的層級
        List<WebElement> medicalItemsAnotherPart3 = driver.findElements(By.xpath("//li[contains(text(),'安寧療護')]/ancestor::div/child::ul/descendant::li[contains(.,'醫材補助')]//preceding::li[@class='ng-star-inserted']"));
// -------following-sibling-------
// 與self平行及之後的層級
        List<WebElement> medicalItemsAnotherPart = driver.findElements(By.xpath("//li[contains(text(),'安寧療護')]/ancestor::div/child::ul/descendant::li[contains(.,'醫材補助')]//following-sibling::*"));
// -------following-------
//  不含parent，只有與parent的平行之後的層級與self平行且之後的層級
        List<WebElement> medicalItemsAnotherPart2 = driver.findElements(By.xpath("//li[contains(text(),'安寧療護')]/ancestor::div/child::ul/descendant::li[contains(.,'醫材補助')]//following::li[@class='ng-star-inserted']"));


        driver.quit();

    }

}
