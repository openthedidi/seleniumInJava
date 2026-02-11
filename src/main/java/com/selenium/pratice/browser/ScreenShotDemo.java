package com.selenium.pratice.browser;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotDemo {
    public static void main(String[] args) throws IOException {

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.google.com");

        String resourceDir = "src/main/resources/screenshots";
        Files.createDirectories(Paths.get(resourceDir));

        // ===TakesScreenshot、FileUtils ===
        File googleSH = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(googleSH, new File(resourceDir + "/google_method1.png"));
        System.out.println("方法一完成：使用 FileUtils.copyFile");

        // ===TakesScreenshot、JavaIO ===
        byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path targetPath = Paths.get(resourceDir, "google_method2_" + timestamp + ".png");
        Files.write(targetPath, screenshotBytes);
        System.out.println("方法二完成：使用 Files.write，儲存至 " + targetPath);

        // ===取特定元素的截圖 ===
        WebElement searchBox = webDriver.findElement(By.name("q"));
        File elementShot = searchBox.getScreenshotAs(OutputType.FILE);
        Files.copy(elementShot.toPath(), Paths.get(resourceDir, "searchBox_element.png"));
        System.out.println("方法三完成：單一元素截圖");

        // === 換成Base64 ===
        String base64Screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        System.out.println("方法四完成：Base64 字串長度 = " + base64Screenshot.length());

        webDriver.quit();
    }
}
