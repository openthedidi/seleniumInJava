package com.selenium.pratice;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriveOptionDemo {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        // 常用設定
        // 關閉瀏覽器跳出「Chrome正受到自動測試軟體控制」的訊息
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        // 禁用瀏覽器彈出視窗
        options.addArguments("--disable-popup-blocking");
        // 關掉瀏覽器的視窗 (無頭模式)，在背景執行
         options.addArguments("--headless");
        // 禁用GPU加速，在某些系統上可以避免一些問題
        options.addArguments("--disable-gpu");
        // 設定瀏覽器視窗大小
        options.addArguments("--window-size=1920,1080");
        // 在Linux系統上執行時，有時需要這個選項來避免Chrome當掉
        options.addArguments("--no-sandbox");
        // 解決在Docker或CI環境中資源限制的問題
        options.addArguments("--disable-dev-shm-usage");
        // 以無痕模式啟動
        options.addArguments("--incognito");
        // 禁用所有擴充功能
        options.addArguments("--disable-extensions");
        // 忽略憑證錯誤
        options.addArguments("--ignore-certificate-errors");

        // 將設定好的 options 傳入 ChromeDriver
        ChromeDriver chromeDriver = new ChromeDriver(options);

        chromeDriver.get("https://www.google.com");

        System.out.println(chromeDriver.getTitle());
        System.out.println(chromeDriver.getCurrentUrl());
        chromeDriver.quit();
    }


}
