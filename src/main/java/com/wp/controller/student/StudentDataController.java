package com.wp.controller.student;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentInsertDTO;
import com.wp.service.student.StudentInfoService;
import com.wp.yufunction.YUFunction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudentDataController {
	private final StudentInfoService studentInfoService;
	private final HttpSession httpSession;

//	@RequestMapping(value = "/studentInfo/studentInfoTest", method = RequestMethod.POST)
//	public String openBoardWrite(@RequestParam(value = "sid", required = false) String sid, Model model) {
//		model.addAttribute("studentDTO", studentInfoService.getStudent("111"));
//		return "studentInfo/studentInfoTest";
//	}
//	
//	@RequestMapping(value = "/studentInfo/studentInfoTest2", method = RequestMethod.GET)
//	public String getStudentInfo(@RequestParam(value = "sid") String sid, Model model) {
//		StudentDTO data;
//		data = studentInfoService.getStudent(sid);
//		if (data == null) {
//			// TODO => 검색 실패 메시지 전달
//			return "redirect:studentInfo/studentInfoTest";
//		}
//		model.addAttribute("studentDTO", data);
//
//		return "studentInfo/studentInfoTest2";
//	}
	
	@PostMapping("/yu/studentInfo/getStudentInfo")
	public StudentGetDTO getStudentInfo(@RequestBody String sid) throws Exception {
		StudentGetDTO data = studentInfoService.getStudent(sid);
		if (data == null) {
			// TODO => 검색 실패 메시지 전달
			return null;
		}
		System.out.println(data.getEmail());
		System.out.println(data.getFirst_login());
		return data;
	}
	@GetMapping("/yu/findNickname")
	public boolean findNickname(String nickname){
		int data = studentInfoService.getEqualNickname(nickname);
		if (data == 0) {
			return false;
		}
		return true;
	}
	@PutMapping("/yu/changeNickname")
	public boolean changeNickname(String nickname,String changeNickname){
		return studentInfoService.changeNickname(nickname,changeNickname);
	}
	@PostMapping("/yu/studentInfo/studentRegistrationData")
	public boolean registerStudent(@ModelAttribute StudentInsertDTO data) throws Exception {
		return studentInfoService.registerStudent(data);
	}
	
	@PutMapping("/yu/studentInfo/updateStudentNickname")
	public String updateStudentNickname(String sid, String nickname) throws Exception {
		StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");

		if(data.getUpdate_date() != null) {
			LocalDate lastUpdateTime = data.getUpdate_dateLocal().toLocalDate();
			LocalDate currentDate = LocalDate.now();
			
			Period period = Period.between(lastUpdateTime, currentDate);
			if(period.getMonths() < 1) return "닉네임을 재변경하려면 1개월 이상 지나야 합니다.";
		}
		else if(data.getNickname().equals(nickname)) return "닉네임이 이전과 같습니다";
		else if(studentInfoService.getEqualNickname(nickname) == 1) return "이미 사용중인 닉네임 입니다";
		
		LocalDateTime now = LocalDateTime.now();
		String beforeNickName = data.getNickname();
		
		if(studentInfoService.updateStudentNickName(sid, nickname, now) == true) {
			data = studentInfoService.getStudent(sid);
			httpSession.setAttribute("studentInfo", data);
			
			YUFunction function = new YUFunction();
			String addVal = function.createSubFolderName(data.getSid());
			String beforeFolderName = beforeNickName + addVal;		// 이전 닉네임 프로필 폴더 이름
			String afterFolderName = data.getNickname() + addVal;	// 바뀐 닉네임 프로필 폴더 이름
			
			String path = System.getProperty("user.dir");
			File folderCheck = new File(path + "\\src\\main\\resources\\static\\images\\profiles\\" + beforeFolderName);
			
			if(folderCheck.exists()) {		// 이전 프로필 존재
				folderCheck.renameTo(new File(path + "\\src\\main\\resources\\static\\images\\profiles\\" + afterFolderName));
			}
			httpSession.setAttribute("folderName", afterFolderName);
			
			return "닉네임 변경 완료";
		}
		return "닉네임 변경 실패";
	}
	
	@PutMapping("/yu/studentInfo/updateStudentEmail")
	public String updateStudentEmail(String sid, String email) throws Exception {
		StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
		if(data.getEmail().equals(email)) return "이메일이 이전과 같습니다";
		if(studentInfoService.updateStudentEmail(sid, email) == true) {
			data = studentInfoService.getStudent(sid);
			httpSession.setAttribute("studentInfo", data);
			return "이메일 변경 완료";
		}
		return "이메일 변경 실패";
	}
	
	@PostMapping("/yu/studentInfo/getStudentCount")
	public int getStudentCount() throws Exception {
		return studentInfoService.getStudentCount();
	}
	
	@GetMapping("/check/sendSMS")
	@ResponseBody
	public String sendSMS(String phoneNumber) {
		Random rand  = new Random();
		String numStr = "";
		for(int i=0; i<4; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr+=ran;
		}

		System.out.println("수신자 번호 : " + phoneNumber);
		System.out.println("인증번호 : " + numStr);
		studentInfoService.certifiedPhoneNumber(phoneNumber,numStr);
		return numStr;
	}
	
	@PostMapping(value = "/yu/studentInfo/studentProfileImageUpload")
	public String studentProfileImageUpload(@RequestParam MultipartFile imageFile) throws Exception {
        StudentGetDTO data = (StudentGetDTO)httpSession.getAttribute("studentInfo");
        YUFunction function = new YUFunction();
        
        String path = System.getProperty("user.dir");
        
		if(imageFile != null) {
			if(imageFile.getSize() > 1024 * 512) return "512KB 이하의 이미지를 등록해야 합니다";
			else if(imageFile.isEmpty()) return "파일이 없습니다";
			String folderName = data.getNickname() + function.createSubFolderName(data.getSid());
			File dest = new File(path + "\\src\\main\\resources\\static\\images\\profiles\\" + folderName + "\\", "profileImage.png");
			
            if(!dest.exists()){
                try{
                    dest.mkdirs(); // 폴더 생성
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
			imageFile.transferTo(dest);
			httpSession.setAttribute("folderName", folderName);
			return "등록 성공";
		}
		return "파일을 찾을 수 없습니다";
	}
	@GetMapping("/yu/studentGetPhone")
	public String getPhonenumber(String sid){
		return studentInfoService.getPhoneNum(sid);
	}
}
