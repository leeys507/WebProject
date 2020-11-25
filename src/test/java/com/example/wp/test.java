package com.example.wp;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class test {
    private WebDriver driver;
    private WebElement element;
    private String url;
    //Properties 설정
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String TEST_URL = "http://portal.yu.ac.kr/sso/login.jsp?type=portal&cReturn_Url=http%3A%2F%2Fwww.yu.ac.kr%2F_korean%2Fmain%2Findex.php";
    @Before
    public void before() {
        String path = System.getProperty("user.dir");
        System.setProperty(WEB_DRIVER_ID , path+"\\src\\main\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu","--blink-settings=imagesEnabled=false");
        driver = new ChromeDriver(options);
    }

    @Test
    public void selenium_example() throws InterruptedException {
        driver.get(TEST_URL);
        WebElement id = driver.findElement(By.id("userId"));
        id.clear();
        id.sendKeys("21411856");
        WebElement pw = driver.findElement(By.id("userPwd"));
        pw.clear();
        pw.sendKeys("qazwsx2487@");
        WebElement button = driver.findElement(By.id("btn_login"));
        button.submit();
        driver.get("https://std.yu.ac.kr/std/websquare/websquare.jsp?w2xPath=/std/com/cmmn/main/CmmnMainFrame.xml"); // Scraping 할 페이지로 이동 합니다.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        element=driver.findElement(By.id("rptSmUnitMenu_0_outSmUnitMenu"));
        element.click();
        element=driver.findElement(By.id("rptSmUnitMenu_0_rptPgmMenu_12_outPgmMenu"));
        element.click();
        driver.switchTo().frame(driver.findElement(By.id("iframe0")));
        Thread.sleep(200);
        driver.switchTo().frame(driver.findElement(By.id("ifrTab4")));
        for(int i=0;i<15;i++){
            if(driver.findElement(By.id("gdList_cell_"+i+"_0")).getText().isEmpty()){
                break;
            }
            System.out.print(driver.findElement(By.id("gdList_cell_"+i+"_0")).getText()+" ");
            System.out.print(driver.findElement(By.id("gdList_cell_"+i+"_1")).getText()+" ");
            System.out.println(driver.findElement(By.id("gdList_cell_"+i+"_5")).getText());
        }
        driver.quit(); // 브라우저 종료
    }
}
