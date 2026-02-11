package com.selenium.pratice.exam;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrokeLinkDemo {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.nanshangeneral.com.tw/");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2));


        List<String> hrefs = wait.until(webDriver -> {
            try {
                List<WebElement> linkWebElements = webDriver.findElements(By.tagName("a"));
                if (linkWebElements.isEmpty()) return null;

                List<String> list = new ArrayList<>();
                for (WebElement e : linkWebElements) {
                    // 這裡若 stale，catch 後回 null 讓 wait 重試
                    String href = e.getAttribute("href");
                    if (href != null && !href.isBlank()) list.add(href);
                }
                return list;
            } catch (StaleElementReferenceException ex) {
                return null; // 讓 WebDriverWait 下一輪 polling 重做
            }
        });
        System.out.println("Total links: " + hrefs.size());


        // 用 JS 抓取所有 href，完全避免 StaleElementReferenceException
//        @SuppressWarnings("unchecked")
//        List<String> urls = (List<String>) ((JavascriptExecutor) driver).executeScript(
//                "return Array.from(document.querySelectorAll('a'))" +
//                ".map(a => a.href).filter(href => href && href.trim() !== '')"
//        );
//        System.out.println("Total links: " + urls.size());
//
        for (String url : hrefs) {
            try {
                checkBrokenLink(url);
            } catch (ClassCastException | MalformedURLException e) {
                System.out.println("Invalid URL: " + url);
            }

        }

        driver.quit();
    }

    public static void checkBrokenLink(String linkUrl) throws IOException {
        URL targetUrl = new URL(linkUrl);
        HttpsURLConnection connection = (HttpsURLConnection) targetUrl.openConnection();

        // === SSL 繞過憑證驗證 ===
        try {
            // 1. 建立「信任所有憑證」的 TrustManager，不做任何驗證直接放行
            TrustManager[] trustAll = new TrustManager[]{
                    new X509TrustManager() {
                        // 不回傳任何受信任的 CA，等於不限制
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        // 不檢查客戶端憑證（直接放行）
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        // 不檢查伺服器憑證（直接放行）← 關鍵：跳過憑證是否過期、是否由受信任 CA 簽發
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // 2. 用上面的 TrustManager 初始化 SSLContext（TLS 協定）
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAll, new java.security.SecureRandom());

            // 3. 將「不驗證」的  套用到這條連線
            connection.setSSLSocketFactory(sc.getSocketFactory());

            // 4. 跳過 hostname 驗證（憑證的域名與實際連線域名不符也放行）
            connection.setHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            System.out.println("SSL 設定失敗：" + linkUrl);
        }

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        int statusCode = connection.getResponseCode();
        if (statusCode >= 400) {
            System.out.println("Broken link: " + linkUrl + " | Status: " + statusCode);
        }
    }

}
