package com.kaspi.backend.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
@Component
public class DataDownloadScheduler {
    @Scheduled(fixedDelay = 2000)
    public void backgroundProcess() {
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", "/Users/download");
            prefs.put("download.prompt_for_download", false);
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.addArguments("headless");
            // 크롬을 사용하기 위한 환경 설정
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            // 크롬실행 객체 만들기
            WebDriver driver = new ChromeDriver(chromeOptions);
            driver.get("https://www.opinet.co.kr/user/opdown/opDownload.do");
            WebElement button = driver.findElement(By.xpath("//*[@id=\"priceInfoVO\"]/div/div[2]/table/tbody/tr/td[2]/a[2]"));
            button.sendKeys(Keys.ENTER);
            driver.switchTo().alert().accept();


            Thread.sleep(4000);
            File file = new File("/Users/download/현재_판매가격(주유소).csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
            String line = null;
            LocalDate date = LocalDate.now();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] attribute = line.split(",");
            }
            file.delete();
            System.out.println("---------------------------");
            WebElement casRadio = driver.findElement(By.xpath("//*[@id=\"rdo2_1\"]"));
            casRadio.click();
            button.sendKeys(Keys.ENTER);
            driver.switchTo().alert().accept();
            Thread.sleep(4000);
            File file2 = new File("/Users/download/현재_판매가격(충전소).csv");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "euc-kr"));
            line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] attribute = line.split(",");
            }
            driver.quit();
        } catch (Exception e) {
            System.out.println("크롬 실행 실패");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
