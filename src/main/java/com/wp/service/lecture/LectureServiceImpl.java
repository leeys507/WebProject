package com.wp.service.lecture;

import com.wp.domain.lecture.Lecture;
import com.wp.domain.lecture.LectureRepository;
import com.wp.domain.lecture.dto.LectureGetDTO;
import com.wp.domain.lecture.dto.LectureInsertDTO;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationGetDTO;
import com.wp.domain.lectureevaluation.dto.LectureEvaluationInsertDTO;
import com.wp.domain.student.dto.StudentGetDTO;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final HttpSession session;
    public boolean insertLecture() throws InterruptedException {
        StudentGetDTO sdata = (StudentGetDTO) session.getAttribute("studentInfo");
        List<LectureInsertDTO> data =searchLecture(sdata,(String)session.getAttribute("password"));
        if(lectureRepository.existsBySid(sdata.getSid())!=0&&!data.isEmpty()){
            return false;
        }
        for(LectureInsertDTO ldata:data){
            lectureRepository.save(ldata.toEntity());
        }
        return true;
    }

    public List<Lecture> getLectureList(String sid) {
        return lectureRepository.findBySid(sid);
    }

    public LectureGetDTO getLecture(String sid, int lno) {
        Lecture data=lectureRepository.findBySidAndlno(sid,lno);
        if(data==null){
            return null;
        }
        return new LectureGetDTO(data);
    }

    public List<LectureInsertDTO> searchLecture(StudentGetDTO student, String password) throws InterruptedException {
        List<LectureInsertDTO> leData=new ArrayList<LectureInsertDTO>();
        WebDriver driver;
        WebElement element;
        String WEB_DRIVER_ID = "webdriver.chrome.driver";
        String URL = "http://portal.yu.ac.kr/sso/login.jsp?type=portal&cReturn_Url=http%3A%2F%2Fwww.yu.ac.kr%2F_korean%2Fmain%2Findex.php";
        String path = System.getProperty("user.dir");
        System.setProperty(WEB_DRIVER_ID , path+"\\src\\main\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu","--blink-settings=imagesEnabled=false");
        driver = new ChromeDriver(options);
        driver.get(URL);
        WebElement id = driver.findElement(By.id("userId"));
        id.clear();
        id.sendKeys(student.getSid());
        WebElement pw = driver.findElement(By.id("userPwd"));
        pw.clear();
        pw.sendKeys(password);
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
            LectureInsertDTO data = new LectureInsertDTO();
            data.setLecturenum(Integer.parseInt(driver.findElement(By.id("gdList_cell_"+i+"_0")).getText()));
            data.setLecturename(driver.findElement(By.id("gdList_cell_"+i+"_1")).getText());
            data.setProfessor(driver.findElement(By.id("gdList_cell_"+i+"_5")).getText());
            data.setSid(student.getSid());
            leData.add(data);
        }
        driver.quit();
        return leData;
    }
}
