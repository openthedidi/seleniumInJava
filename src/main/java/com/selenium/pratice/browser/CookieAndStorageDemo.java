package com.selenium.pratice.browser;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Set;

public class CookieAndStorageDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://n:n@n-proj.walkflow.com.tw/cms/login");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);


        WebElement loginInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("mingZi"))
        );
        loginInput.sendKeys("a");

        WebElement passwordInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("password"))
        );
        passwordInput.sendKeys("a4");

        WebElement loginBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@value='Login']"))
        );
        wait.until(
                ExpectedConditions.not(ExpectedConditions.attributeToBe(loginBtn, "disabled", "true"))
        );
        loginBtn.click();
        Thread.sleep(5000);

        //logout cookie delete

        Set<Cookie> cookies =driver.manage().getCookies();
        System.out.println(cookies.size());
        driver.manage().deleteAllCookies();

        //delete local storage 全部刪除
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.localStorage.clear();");

        //delete local storage 只刪除特定的key
        js.executeScript("window.localStorage.removeItem('loginInput')");
        driver.navigate().refresh();

        js.executeScript("window.sessionStorage.clear();");

        /**
         * local storage、session storage、cookie 差異：
         *
         * 1. 儲存大小：
         *    - Cookie：約 4KB
         *    - Local Storage：約 5~10MB
         *    - Session Storage：約 5~10MB
         *
         * 2. 生命週期：
         *    - Cookie：可設定過期時間（Expires / Max-Age），沒設定則關閉瀏覽器時清除
         *    - Local Storage：永久保存，除非手動刪除
         *    - Session Storage：關閉分頁（Tab）時自動清除
         *
         * 3. 與伺服器的關係：
         *    - Cookie：每次 HTTP 請求都會自動帶到 Server（放在 Request Header）
         *    - Local Storage：不會自動送到 Server，純前端使用
         *    - Session Storage：不會自動送到 Server，純前端使用
         *
         * 4. 作用範圍：
         *    - Cookie：同源（Domain + Path），可跨分頁共享
         *    - Local Storage：同源（Protocol + Domain + Port），可跨分頁共享
         *    - Session Storage：同源，但僅限同一分頁，不跨分頁共享
         *
         * 5. 常見用途：
         *    - Cookie：登入狀態（Session ID）、追蹤、伺服器端需要讀取的資訊
         *    - Local Storage：使用者偏好設定、快取資料（長期保存）
         *    - Session Storage：表單暫存、單次瀏覽流程的暫時資料
         *
         * 6. 儲存位置：
         *    - Cookie：硬碟（SQLite 資料庫）
         *      Chrome 路徑：%LocalAppData%\Google\Chrome\User Data\Default\Network\Cookies
         *      每次 HTTP 請求會自動透過 Request Header 送到 Server，同時存在 Client 和 Server
         *    - Local Storage：硬碟（LevelDB）
         *      Chrome 路徑：%LocalAppData%\Google\Chrome\User Data\Default\Local Storage\leveldb\
         *      關閉瀏覽器後資料還在，不會送到 Server
         *    - Session Storage：記憶體（Memory），不寫入硬碟
         *      關閉分頁後記憶體釋放，資料消失，不會送到 Server
         */











    }
}
