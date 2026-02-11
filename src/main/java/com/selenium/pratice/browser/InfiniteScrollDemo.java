package com.selenium.pratice.browser;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class InfiniteScrollDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/Scrollable/");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // === 整個頁面的無限捲動 ===
        System.out.println("=== 開始：整個頁面的無限捲動 ===");

        int maxScrollCount = 10; // 設定最大捲動次數，避免無限迴圈
        int scrollCount = 0;
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

        while (scrollCount < maxScrollCount) {
            // 捲到頁面底部
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            sleep(2000); // 等待新內容載入

            // 取得新的頁面高度
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");

            // 高度沒變 = 已經到底了
            if (newHeight == lastHeight) {
                System.out.println("已捲動到底部，沒有更多內容了");
                break;
            }

            lastHeight = newHeight;
            scrollCount++;
            System.out.println("第 " + scrollCount + " 次捲動，目前頁面高度: " + newHeight);
        }

        System.out.println("總共捲動了 " + scrollCount + " 次");

        // === table內的無限捲動（到底部後載入更多資料）  ===
        //        todo 尚未完成
        js.executeScript("window.scrollTo(0, 0)");
        sleep(1000);

        System.out.println("\n=== 開始：容器內的無限捲動 ===");

        // 找到可捲動的容器
        WebElement scrollableContainer = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='table-container']"))
        );

        int containerScrollCount = 0;
        int maxContainerScroll = 20;

        // 用「資料行數」判斷是否有新內容載入，而非只看高度
        int lastRowCount = driver.findElements(By.cssSelector("table tbody tr")).size();
        System.out.println("初始資料筆數: " + lastRowCount);

        while (containerScrollCount < maxContainerScroll) {
            // 捲動容器到底部，觸發載入更多
            js.executeScript(
                    "arguments[0].scrollTop = arguments[0].scrollHeight", scrollableContainer);
            sleep(5000); // 等待新資料載入（AJAX 需要時間）

            // 重新抓取行數，判斷是否有新資料載入
            int newRowCount = driver.findElements(By.cssSelector("table tbody tr")).size();
            System.out.println("目前的總共資料筆數: " + newRowCount);

            if (newRowCount == lastRowCount) {
                System.out.println("沒有更多新資料了，停止捲動");
                break;
            }

            containerScrollCount++;
            System.out.println("第 " + containerScrollCount + " 次捲動，資料從 "
                    + lastRowCount + " 筆增加到 " + newRowCount + " 筆");
            lastRowCount = newRowCount;
        }

        System.out.println("最終共載入 " + lastRowCount + " 筆資料，捲動了 " + containerScrollCount + " 次");

        // === 3. 捲動完成後，收集所有已載入的資料 ===
        System.out.println("\n=== 開始：收集所有已載入的資料 ===");

        List<WebElement> allRows = driver.findElements(By.cssSelector("table tbody tr"));
        for (int i = 0; i < allRows.size(); i++) {
            List<WebElement> cells = allRows.get(i).findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                StringBuilder rowData = new StringBuilder("第 " + (i + 1) + " 行: ");
                for (WebElement cell : cells) {
                    rowData.append(cell.getText()).append(" | ");
                }
                System.out.println(rowData);
            }
        }

        sleep(4000);
//        driver.quit();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
