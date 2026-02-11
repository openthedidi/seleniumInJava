package com.selenium.pratice.browser;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ScrollerWindowAndTableDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.misin.msu.edu/0/js/DataTablesP/DataTables-1.10.18/examples/api/tabs_and_scrolling.html");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");


        WebElement scrollTableDiv = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(""))
        );

        // 向下捲動指定距離
        js.executeScript("arguments[0].scrollBy(0, 1000);", scrollTableDiv);

        sleep(1000);

        // 捲動到最底部
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollTableDiv);

        sleep(1000);

        // 捲動回最頂部
        js.executeScript("arguments[0].scrollTop = 0;", scrollTableDiv);

        sleep(1000);

        // 捲動到中間位置
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight / 2;", scrollTableDiv);

        sleep(1000);

        // 向上捲動指定距離
        js.executeScript("arguments[0].scrollBy(0, -300);", scrollTableDiv);

        sleep(1000);

        // 水平捲動向右
        js.executeScript("arguments[0].scrollLeft = 200;", scrollTableDiv);

        sleep(1000);

        // 水平捲動回最左邊
        js.executeScript("arguments[0].scrollLeft = 0;", scrollTableDiv);

        sleep(1000);

        // 捲動到表格內某個特定元素
        WebElement targetRow = driver.findElement(By.xpath("//table[@id='myTable1']//td[text()='Dai Rios']"));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", targetRow);

        sleep(5000);


        // 平滑捲動到最底部 (smooth scroll)
        js.executeScript("arguments[0].scrollTo({top: arguments[0].scrollHeight, behavior: 'smooth'});", scrollTableDiv);

        sleep(2000);

        // 平滑捲動回頂部
        js.executeScript("arguments[0].scrollTo({top: 0, behavior: 'smooth'});", scrollTableDiv);

        sleep(2000);

        driver.quit();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
