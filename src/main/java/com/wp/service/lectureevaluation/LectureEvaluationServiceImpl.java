package com.wp.service.lectureevaluation;
import com.wp.domain.lectureevaluation.LectureEvaluation;
import com.wp.domain.lectureevaluation.LectureEvaluationRepository;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class LectureEvaluationServiceImpl implements LectureEvaluationService {
    private final LectureEvaluationRepository lectureEvaluationRepository;
    private final HttpSession session;
    public List<LectureEvaluation> getLectureEvaluationList(String sid) {
        return lectureEvaluationRepository.findBySid(sid);
    }
    @Transactional
    public boolean insertLectureEvaluation() throws InterruptedException {
        StudentGetDTO sdata = (StudentGetDTO) session.getAttribute("studentInfo");
        List<LectureEvaluationInsertDTO> data =searchLecture(sdata,(String)session.getAttribute("password"));
        if(lectureEvaluationRepository.existsBySid(sdata.getSid())!=0&&data==null){
            return false;
        }
        for(LectureEvaluationInsertDTO ledata:data){
            lectureEvaluationRepository.save(ledata.toEntity());
        }
        return true;
    }

    public LectureEvaluationGetDTO getLectureEvaluation(String sid, int lno) {
        LectureEvaluation data=lectureEvaluationRepository.findBySidAndlno(sid,lno);
        if(data==null){
            return null;
        }
        return new LectureEvaluationGetDTO(data);
    }

    @Transactional
    public boolean updateLectureEvaluation(long lno, String content, int star) {
        LectureEvaluation data = lectureEvaluationRepository.findByLno(lno);
        data.update(content,star);
        return true;
    }

    public List<LectureEvaluationInsertDTO> searchLecture(StudentGetDTO student, String password) throws InterruptedException {
        List<LectureEvaluationInsertDTO> leData=new ArrayList<LectureEvaluationInsertDTO>();
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
            LectureEvaluationInsertDTO data = new LectureEvaluationInsertDTO();
            data.setLecturenum(Integer.parseInt(driver.findElement(By.id("gdList_cell_"+i+"_0")).getText()));
            data.setLecturename(driver.findElement(By.id("gdList_cell_"+i+"_1")).getText());
            data.setProfessor(driver.findElement(By.id("gdList_cell_"+i+"_5")).getText());
            data.setSid(student.getSid());
            data.setNickname(student.getNickname());
            leData.add(data);
        }
        driver.quit();
        return leData;
    }
}
