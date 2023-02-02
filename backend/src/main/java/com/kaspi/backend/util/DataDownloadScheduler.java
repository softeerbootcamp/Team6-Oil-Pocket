package com.kaspi.backend.util;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasStation;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class DataDownloadScheduler {
    private final GasStationDataDao gasStationDataDao;
    private final List<String> cache = new ArrayList<>();

    public DataDownloadScheduler(GasStationDataDao gasStationDataDao) {
        this.gasStationDataDao = gasStationDataDao;
    }

    @Scheduled(cron = "0 1 1 * * *")
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
            String line = br.readLine();
            LocalDate date = LocalDate.now();
            while ((line = br.readLine()) != null) {
                String[] attribute = line.split(",");
                if (attribute.length <= 1) break;
                GasStation gasStation = GasStation.parseGasStation(attribute);
                if (!cache.contains(gasStation.getGasStationNo())) {
                    System.out.println(line);
                    cache.add(gasStation.getGasStationNo());
                    gasStationDataDao.insertGasStation(gasStation);
                }
                List<GasDetail> gasDetailList = GasDetail.parseListGasDetail(attribute, date);
                gasStationDataDao.insertGasDetails(gasDetailList);
            }
            file.delete();
            WebElement casRadio = driver.findElement(By.xpath("//*[@id=\"rdo2_1\"]"));
            casRadio.click();
            button.sendKeys(Keys.ENTER);
            driver.switchTo().alert().accept();
            Thread.sleep(4000);
            File file2 = new File("/Users/download/현재_판매가격(충전소).csv");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "euc-kr"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] attribute = line.split(",");
                if (attribute.length <= 1) break;
                GasStation gasStation = GasStation.parseGasStation(attribute);
                if (!cache.contains(gasStation.getGasStationNo())) {
                    System.out.println(line);
                    cache.add(gasStation.getGasStationNo());
                    gasStationDataDao.insertGasStation(gasStation);
                }
                GasDetail gasDetail = GasDetail.parseLpgGasDetail(attribute, date);
                gasStationDataDao.insertGasDetail(gasDetail);
            }
            driver.quit();
        } catch (Exception e) {
            System.out.println("크롬 실행 실패");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
