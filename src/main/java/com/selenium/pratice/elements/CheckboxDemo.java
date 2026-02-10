package com.selenium.pratice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckboxDemo {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.isunfar.com.tw/product/prodlist.aspx?mpt=47#/bcNo=12&ept=47");

        Thread.sleep(4000);

        List<WebElement> checkBoxLabelList = driver.findElements(By.xpath("//div[@class='bc_level0_first']//div[@class='pt_level0_first_12 ptf_mclist_first']//label[@for='sc_item_W51_1'][contains(text(),'Logitech 羅技')]/parent::div/following-sibling::*"));

        checkBoxLabelList.forEach(
                checkBoxLabel -> {
                    checkBoxLabel.click();
                    System.out.println(checkBoxLabel.getText());
                    System.out.println(checkBoxLabel.isSelected());
                }
        );



    }

}
