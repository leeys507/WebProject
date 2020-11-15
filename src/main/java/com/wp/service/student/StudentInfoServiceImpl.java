package com.wp.service.student;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wp.domain.student.Student;
import com.wp.domain.student.StudentRepository;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.domain.student.dto.StudentGetMyBoardDTO;
import com.wp.domain.student.dto.StudentInsertDTO;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	private final StudentRepository studentRepository;
	
	@Transactional
	public boolean registerStudent(StudentInsertDTO data) {
		System.out.println(data.getDepartment());
		return studentRepository.save(data.toEntity()) != null;
	}
	
	public StudentGetDTO getStudent(String sid) {
		Student entity = studentRepository.findBySid(sid);
		return (entity == null) ? null : new StudentGetDTO(entity);
	}
	
	public boolean updateStudentNickName(String sid, String nickname) {
//		Student entity = studentRepository.findBySid(sid);
//		if(entity == null) return false;
//		entity.setNickname(nickname);
//		return studentRepository.save(entity) != null;
		return studentRepository.updateNickname(sid, nickname) > 0 ? true : false;
	}
	public String getPhoneNum(String sid){
		return studentRepository.findBySid(sid).getPhonenum();
	}
	public String getNickname(String sid) {
		return studentRepository.getBoardByNickname(sid);
	}
	public boolean updateStudentEmail(String sid, String email) {
		return studentRepository.updateEmail(sid, email) > 0 ? true : false;
	}
	
	public int getStudentCount() {
		return (int) studentRepository.count();
	}
	
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

		String api_key = "NCSANGRSVWAQTXAU";
		String api_secret = "9KEIGMZ9NVZWUWJGZ53RMZVYUEHNOJGN";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber);    // 수신전화번호
		params.put("from", "01083878003");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "SMS");
		params.put("text", "핫띵크 휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
		params.put("app_version", "test app 1.2"); // application name and version

		/*try {
			JSONObject obj = (JSONObject)coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
*/
	}
	
	public List<StudentGetMyBoardDTO> getMyBoard(String sid, int limit) {
		return studentRepository.getMyBoard(sid, limit);
	}
}
