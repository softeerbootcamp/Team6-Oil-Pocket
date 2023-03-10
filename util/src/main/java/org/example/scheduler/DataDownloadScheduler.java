
package org.example.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.example.service.gasdata.GasDataService;
import org.example.service.gasdata.LpgGasDataCallback;
import org.example.service.gasdata.NomalGasDataCallback;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataDownloadScheduler {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.oil}")
    private String oilStation;
    @Value("${file.lpg}")
    private String lpgStation;
    @Value("${slack.token}")
    String token;
    @Value("${slack.url}")
    String url;

    private final GasDataService gasDataService;

    //local
    //@Scheduled(fixedDelay = 300000)
    //server
    @Scheduled(cron = "0 1 1 * * *", zone = "Asia/Seoul")
    public void backgroundProcess() throws Exception {
        try {
            log.debug("스케쥴러 시작");
            notifyToSlack("start");
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", filePath);
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
            log.debug("스케쥴러 종료");
            notifyToSlack("end");
        } catch (Exception e) {
            log.error("크롬 실행 실패 : {}", e.getMessage());
            throw new Exception(e);
        }
    }

    public void fileDownload(WebDriver driver) throws IOException, InterruptedException {
        WebElement button = driver.findElement(By.xpath("//*[@id=\"priceInfoVO\"]/div/div[2]/table/tbody/tr/td[2]/a[2]"));
        button.sendKeys(Keys.ENTER);
        driver.switchTo().alert().accept();
        gasDataService.batchInsertGasInfo(oilStation, false);


        WebElement casRadio = driver.findElement(By.xpath("//*[@id=\"rdo2_1\"]"));
        casRadio.click();
        button.sendKeys(Keys.ENTER);
        driver.switchTo().alert().accept();
        gasDataService.batchInsertGasInfo(lpgStation, true);
    }
    private void notifyToSlack(String message) {
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback("Post");
        slackAttachment.setColor("good");
        slackAttachment.setTitle("Data save " + message);
        slackAttachment.setText(LocalDateTime.now().toString());

        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setAttachments(Collections.singletonList(slackAttachment));
        slackMessage.setIcon(":floppy_disk:");
        slackMessage.setText("Scheduler");
        SlackApi api = new SlackApi(url+ token);
        api.call(slackMessage);
    }
}
