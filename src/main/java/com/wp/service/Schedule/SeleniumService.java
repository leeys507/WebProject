package com.wp.service.Schedule;

import com.gargoylesoftware.css.parser.Locatable;
import com.wp.domain.student.dto.StudentGetDTO;

import lombok.RequiredArgsConstructor;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SeleniumService {

    //WebDriver
    private WebDriver driver;

    JavascriptExecutor js;
    private WebElement webElement;

    //Properties
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

    //크롤링 할 URL
    private String base_url;

    public SeleniumService() {
        super();
        String path = System.getProperty("user.dir");
        System.out.println(path);
        
        //System Property SetUp
        System.setProperty(WEB_DRIVER_ID , path+"\\src\\main\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");

        //Driver SetUp
        ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);

        base_url = "https://std.yu.ac.kr/std/std_login.jsp";
    }

    public String crawl(int con3, int con2, int con1, String searchname, String searchnum, int grade) {

        String all="";

        try {

            driver.get(base_url);
            //System.out.println(driver.getPageSource());
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            //driver.get(base_url);
            // System.out.println(driver.getPageSource());

            //iframe으로 구성된 곳은 해당 프레임으로 전환시킨다.
            //driver.switchTo().frame(driver.findElement(By.id("loginbox")));

            //iframe 내부에서 id 필드 탐색

            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            HttpSession session = request.getSession(true);

            webElement = driver.findElement(By.id("userno"));
            String Id = (String)session.getAttribute("id");
            webElement.sendKeys(Id);

            //iframe 내부에서 pw 필드 탐색
            webElement = driver.findElement(By.id("passwd"));
            String pw =(String)session.getAttribute("password");
            webElement.sendKeys(pw);

            webElement=driver.findElement(By.xpath("//input[@onclick='check();return false;']"));//홈페이지 로그인버튼클릭
            webElement.click();

/*
            WebDriverWait myWaitVar = new WebDriverWait(driver,20);
            webElement = myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElements(By.xpath("//*[@class='w2output depth3_out depth3_on']")).get(0)));
            webElement.click();*/


            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            webElement=driver.findElement(By.id("rptSmUnitMenu_1_outSmUnitMenu"));//수업관리
            webElement.click();


            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


            webElement=driver.findElement(By.id("rptSmUnitMenu_1_rptPgmMenu_3_outPgmMenu"));//시간표/수업계획서조회
            webElement.click();



            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            driver.switchTo().frame(driver.findElement(By.id("iframe0")));

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 여기서 부터 시작.------------------------

            if(con1!=-1){//만약 전공을 선택 했다면


                webElement = driver.findElement(By.id("sbSearchUnivCd_button"));//대학버튼클릭
                webElement.click();


                if(con3==-1){

                    //con3가 대학
                    //con2가 학부
                    //con1이 전공
                    con2+=1;
                    con1+=1;


                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                    String uid="sbSearchUnivCd_itemTable_"+Integer.toString(con2);

                    webElement = driver.findElement(By.id(uid));//대학선택
                    webElement.click();


                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


                    webElement = driver.findElement(By.id("sbSearchSustCd_button"));//학부
                    webElement.click();

                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                    String uid2="sbSearchSustCd_itemTable_"+Integer.toString(con1);

                    webElement = driver.findElement(By.id(uid2));//학부선택
                    webElement.click();

                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                }
                else{//con3가있는경우
                    //con1=0, con2=4,con3=2

                    con2+=1;
                    con3+=1;
                    System.out.println("바뀐후:"+con3);
                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                    String uid1="sbSearchUnivCd_itemTable_"+Integer.toString(con3);


                    webElement = driver.findElement(By.id(uid1));//대학선택
                    webElement.click();

                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                    webElement = driver.findElement(By.id("sbSearchSustCd_button"));//학부
                    webElement.click();

                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                    String uid2="sbSearchSustCd_itemTable_"+Integer.toString(con2);

                    WebElement Element = driver.findElement(By.id("someID"));
                    js.executeScript("arguments[0].scrollIntoView();", Element);

                    webElement = driver.findElement(By.id(uid2));//학부선택
                    webElement.click();

                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


                    webElement = driver.findElement(By.id("sbSearchMjCd_button"));//전공
                    webElement.sendKeys("\n");

                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


                    String uid3="sbSearchSustCd_itemTable_"+Integer.toString(con1);

                    Thread.sleep(500);

                    webElement = driver.findElement(By.id(uid3));//전공선택
                    webElement.sendKeys("\n");

                    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

                }

            }
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            if(searchname!=null)//과목명을 검색했다면
            {
                webElement = driver.findElement(By.id("inSearchSbjtNm"));//교과목명
                webElement.sendKeys(searchname);
            }

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            if(searchnum!=null){

                webElement = driver.findElement(By.id("inTlsnNo"));//수강번호
                webElement.sendKeys(searchnum);
            }

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);



            webElement = driver.findElement(By.id("sbSearchShyrCd_button"));//학년
            webElement.click();

            String temp="sbSearchShyrCd_itemTable_"+Integer.toString(grade);

            webElement = driver.findElement(By.id(temp));//학년선택
            webElement.click();

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);



/*
            webElement = driver.findElement(By.id("sbSearchUnivCd_itemTable_1"));//대학선택
            webElement.click();
            Thread.sleep(1000);

            webElement=driver.findElement(By.id("w2selectbox_col_button"));//학부(과) 1
            webElement.click();
            webElement=driver.findElement(By.id("sbSearchSustCd_itemTable_1"));//학부(과) 2
            webElement.click();
            //여기선 아마 일단 table정보 받아와서 우리웹에서 사용자가 클릭한 정보와 같은지 확인하고 맞으면 해당 학과이름의 id이름값을 받아와서 클릭하는 수밖에 없을 듯.



            webElement = driver.findElement(By.id("inSearchSbjtNm"));//교과목명
            String select1="교양학부";
            webElement.sendKeys(select);




            webElement = driver.findElement(By.id("inTlsnNo"));//수강번호
            String select2="교양학부";
            webElement.sendKeys(select);



            webElement = driver.findElement(By.id("sbSearchShyrCd_button"));//학년
            webElement.click();
            webElement = driver.findElement(By.id("sbSearchShyrCd_itemTable_0"));//0-전체, 1-1학년, 2-2학년 ...
            webElement.click();

*/


            webElement = driver.findElement(By.id("trSearch_center"));//조회버튼
            webElement.click();


            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


            webElement =driver.findElement(By.xpath("//td[@id='gdTmLsn_rowCount']"));
            String rownum=webElement.findElement(By.tagName("nobr")).getText();
            String change=rownum.replace("[건수:", "").replace("]","");
            int to = Integer.parseInt(change);

            webElement =driver.findElement(By.xpath("//tbody[@id='gdTmLsn_body_tbody']"));//시간표/수업계획서의 테이블아이디

            List<WebElement> row_content =webElement.findElements(By.tagName("tr"));//검색 시 나타난 수업개수



            int control=0;
            if(to>18)
            {
                control=18;
            }
            else{
                control=to;
            }
//13개
            for(int row=0;row<control;row++)// 검색 후시간표/수업계획서 내용전부 크롤링  18
            {

                List<WebElement> col_content=row_content.get(row).findElements(By.tagName("td"));//하나의 수업안에 들어있는 내용전부.




                for(int col=2;col<19;col++)
                {
                    String content=col_content.get(col).findElement(By.tagName("nobr")).getText().replace("\n","");

                    if(col==8 || col==12 || col==13 || col==14)
                    {
                        continue;
                    }
                    // System.out.print(content);

                    all=all+content+"*";
                }

                all=all+"!";

            }



            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

/*

            webElement
            WebElement.sendKeys(Keys.ENTER);

            driver.findElement(By.className("w2output depth3_out depth3_sel")).click();
            Thread.sleep(20000);
*/


        } catch (Exception e) {

            e.printStackTrace();

        } finally {


            driver.close();
            return all;
        }

    }
}

