
package org.example.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.service.gasdata.GasDataService;
import org.example.service.gasdata.LpgGasDataCallback;
import org.example.service.gasdata.NomalGasDataCallback;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataDownloadScheduler {
    // /home/ubuntu/download/
    public static final String FILE_PATH = "/User/download";
    public static final String GAS_STATION = "/현재_판매가격(주유소).csv";
    public static final String LPG_STATION = "/현재_판매가격(충전소).csv";

    private GasDataService gasDataService;

    //server
    //@Scheduled(cron = "0 1 1 * * *", zone = "Asia/Seoul")

    //local
    @Scheduled(fixedDelay = 300000)
    public void backgroundProcess() {
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", FILE_PATH);
            prefs.put("download.prompt_for_download", false);
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.addArguments("headless");
            chromeOptions.addArguments("no-sandbox");
            chromeOptions.addArguments("disable-dev-shm-usage");
            chromeOptions.addArguments("lang=ko");
            // 크롬을 사용하기 위한 환경 설정
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            // 크롬실행 객체 만들기
            WebDriver driver = new ChromeDriver(chromeOptions);
            driver.get("https://www.opinet.co.kr/user/opdown/opDownload.do");
            fileDownload(driver);
            driver.quit();
        } catch (Exception e) {
            System.out.println("크롬 실행 실패");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fileDownload(WebDriver driver) throws IOException, InterruptedException {
        WebElement button = driver.findElement(By.xpath("//*[@id=\"priceInfoVO\"]/div/div[2]/table/tbody/tr/td[2]/a[2]"));
        button.sendKeys(Keys.ENTER);
        driver.switchTo().alert().accept();
        gasDataService.insertGasInfo(GAS_STATION, new NomalGasDataCallback());


        WebElement casRadio = driver.findElement(By.xpath("//*[@id=\"rdo2_1\"]"));
        casRadio.click();
        button.sendKeys(Keys.ENTER);
        driver.switchTo().alert().accept();
        gasDataService.insertGasInfo(LPG_STATION, new LpgGasDataCallback()); //TODO --> 싱글톤 & 함수형 인터페이스
    }
}
