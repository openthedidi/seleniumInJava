package com.selenium.pratice.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatePickerDemo {
    public static void main(String[] args) {
        //移除Chrome的網站通知（Notifications）權限，例如位置存取
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://hotel.eztravel.com.tw/");
        String targetYear = "2026";
        String targetMonth = "06";
        String targetData = "25";

        while(true) {

                WebElement checkInBtn = driver.findElement(By.id("hotel-search-date-select-start"));
                checkInBtn.click();

                WebElement nextYearMonthBtn = driver.findElement(By.xpath("//button[@title='Next Month']"));
                WebElement previousMonthBtn = driver.findElement(By.xpath("//button[@title='Previous Month']"));

                WebElement firstYearMonthInDatePicker = driver.findElement(By.xpath("//div[@class='dpicker__month-container '][1]//div[@class='dpicker__current-month']"));
                String formateFirstYear = DatePickerDemo.getYear(firstYearMonthInDatePicker.getText());
                String formateFirstMonth = DatePickerDemo.getMonth(firstYearMonthInDatePicker.getText());



                WebElement secondYearMonthInDatePicker = driver.findElement(By.xpath("//div[@class='dpicker__month-container '][2]//div[@class='dpicker__current-month']"));
                String formateSecondYear = DatePickerDemo.getYear(secondYearMonthInDatePicker.getText());
                String formateSecondMonth = DatePickerDemo.getMonth(secondYearMonthInDatePicker.getText());


                if(formateSecondYear.equals(targetYear) && formateSecondMonth.equals(targetMonth)) {
                    String targetXpath = "//div[@aria-label='month  " + targetYear + "-" + targetMonth + "']//div[contains(@class,'dpicker__day dpicker__day')]";
                    List<WebElement> dateList = driver.findElements(By.xpath(targetXpath));
                    for (WebElement date : dateList) {
                        if (date.getText().equals(targetData)) {
                            date.click();
                            break;
                        }
                    }
                    break;
                }else {
                    nextYearMonthBtn.click();
                }
        }
        driver.quit();
    }



    private static String getYear(String yearMonth){
        return yearMonth.replace("年", "/").replace("月", "").split("/")[0].trim();
    }

    private static String getMonth(String yearMonth){
        Map<String,String> monthMap = new HashMap<>();
        monthMap.put("一","01");
        monthMap.put("二","02");
        monthMap.put("三","03");
        monthMap.put("四","04");
        monthMap.put("五","05");
        monthMap.put("六","06");
        monthMap.put("七","07");
        monthMap.put("八","08");
        monthMap.put("九","09");
        monthMap.put("十","10");
        monthMap.put("十一","11");
        monthMap.put("十二","12");
        String unFormateDate = yearMonth.replace("年", "/").replace("月", "").replace(" ", "").split("/")[1];
        return monthMap.get(unFormateDate);
    }

}
