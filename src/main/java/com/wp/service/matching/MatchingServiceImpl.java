package com.wp.service.matching;

import com.wp.domain.board.Board;
import com.wp.domain.matching.ChatRoomRepository;
import com.wp.domain.matching.dto.MatchingInsertDTO;
import com.wp.domain.matching.dto.MatchingUpdateDTO;
import com.wp.domain.matchingcomment.MatchingComment;
import com.wp.domain.matchingcomment.MatchingCommentRepository;
import com.wp.domain.student.Student;
import com.wp.domain.student.StudentRepository;
import net.nurigo.java_sdk.api.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wp.domain.matching.Matching;
import com.wp.domain.matching.MatchingRepository;
import com.wp.domain.matching.dto.MatchingGetDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class MatchingServiceImpl implements MatchingService {
    private final MatchingRepository matchingRepository;
    private final StudentRepository studentRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MatchingCommentRepository matchingCommentRepository;

    public MatchingGetDTO findById(long id){
        Matching entity = matchingRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new MatchingGetDTO(entity);
    }

    public Page<Matching> findMatchingList(Pageable pageable, String boardtype){
        if(boardtype == null){
            boardtype = "심부름";
        }
        return matchingRepository.findAllByBoardtype(boardtype, PageRequest.of(pageable.getPageNumber(), 10,
                new Sort(Sort.Direction.DESC, "bno")));
    }

    @Transactional
    public String InsertMatching(MatchingInsertDTO data) {
        return matchingRepository.save(data.toEntity()).getStudentForeignkey_request().getSid();
    }

    @Transactional
    public boolean ProceedMatching(long bno, long cno) {
        Matching entity = matchingRepository.findByBno(bno);
        if (entity == null||!entity.getCheck_success().equals("F")) return false;
        chatRoomRepository.createChatRoom(String.valueOf(bno));
        Student student = matchingCommentRepository.findByCno(cno).getStudentForeignkey();
        entity.update(student, student.getNickname(),"I");
        SendMsg(entity.getStudentForeignkey_request().getSid());
        return true;
    }

    @Transactional
    public String ProceedPage(String sid, long bno){
        Matching entity=matchingRepository.findByBno(bno);
        if (entity==null||!entity.getCheck_success().equals("I"))return "errors/errorPage";
        if(entity.getStudentForeignkey_request().getSid().equals(sid)||entity.getStudentForeignkey_accept().getSid().equals(sid)){
            return "matching/matchingProceeding";
        }
        return "errors/errorPage";
    }

    @Transactional
    public boolean CancelMatching(long bno) {
        Matching entity = matchingRepository.findByBno(bno);
        if (entity == null) return false;
        chatRoomRepository.deleteChatRoom(String.valueOf(bno));
        entity.update(null, null,"F");
        return true;
    }

    @Transactional
    public boolean SuccessMatching(long bno) {
        Matching entity = matchingRepository.findByBno(bno);
        if (entity == null) return false;
        chatRoomRepository.deleteChatRoom(String.valueOf(bno));
        entity.setCheck_success("T");
        return true;
    }
    @Transactional
    public boolean DeleteMatching(long bno) {
        Matching entity = matchingRepository.findByBno(bno);
        if (entity == null) return false;
        entity.delete();
        return true;
    }

    @Transactional
    public long UpdateMatching(long bno, MatchingUpdateDTO dto) {
        Matching entity = matchingRepository.findByBno(bno);
        if (entity == null) return 0;
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setUpdate_date(LocalDateTime.now());
        entity.setBoardtype(dto.getBoardtype());
        return bno;
    }
    @Transactional
    public void updateViewCnt(long bno, int presentReadCount, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Cookie[] cookieFromRequest = request.getCookies();
        String cookieValue = cookieFromRequest[0].getValue();
        if (session.getAttribute("cookieview") == null) {
            session.setAttribute("cookieview", cookieValue);
        } else {
            session.setAttribute("cookie exchange", session.getAttribute("cookieview"));
            if (!session.getAttribute("cookieview").equals(cookieValue)) {
                session.setAttribute("cookieview",  cookieValue);
            }
        }if (!session.getAttribute("cookieview").equals(session.getAttribute("cookie exchange"))) {
            matchingRepository.updateReadCount(bno, presentReadCount + 1);
        }
    }

    @Transactional
    public boolean UpdateAccount(long bno, String account) {
        Matching entity = matchingRepository.findByBno(bno);
        if (entity == null) return false;
        entity.setAccept_account(account);
        return true;
    }

    @Transactional
    public long MaxBno() {
        return matchingRepository.MaxBno()+1;
    }

    public void SendMsg(String sid) {
        Student student = studentRepository.findBySid(sid);
        String api_key = "NCSANGRSVWAQTXAU";
        String api_secret = "9KEIGMZ9NVZWUWJGZ53RMZVYUEHNOJGN";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", student.getPhonenum());    // 수신전화번호
        params.put("from", "01083878003");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", student.getNickname()+"님 매칭요청이 들어왔습니다. 확인해주세요.");
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

    public Page<Matching> searchAll(Pageable pageable, String text, String date, String option){
        String addQuery = "";
        int dateNum = 0;

        if(date.equals("all") && option.equals("all")) {
            return matchingRepository.searchMatchingAll(text, PageRequest.of(pageable.getPageNumber(), 10));
        }
        else {
            if(!option.equals("all")) {
                if(option.equals("title"))
                    addQuery = "match(title) against(?1 in boolean mode)";
                else if(option.equals("writer")) {
                    addQuery = "request_nickname like ?1";
                    text = "%" + text + "%";
                }
                else if(option.equals("commentContent"))
                    addQuery = "bno in (select bno from matchingcomment where match(content) against(?1 in boolean mode))";

                if(date.equals("all"))
                    return matchingRepository.searchMatchingOptions(addQuery, text, PageRequest.of(pageable.getPageNumber(), 10));
            }
            if(!date.equals("all")) {
                if(date.equals("1week"))
                    dateNum = 7;
                else if(date.equals("1month"))
                    dateNum = 30;
                else if(date.equals("6month"))
                    dateNum = 180;

                if(option.equals("all"))
                    return matchingRepository.searchMatchingAllDates(text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
            }
        }
        return matchingRepository.searchMatchingOptionsAndDate(addQuery, text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
    }

    public Page<Matching> searchMatching(Pageable pageable, String boardtype, String text, String date, String option) {
        String addQuery = "";
        int dateNum = 0;

        if(date.equals("all") && option.equals("all")) {
            return matchingRepository.searchMatchingType(boardtype, text, PageRequest.of(pageable.getPageNumber(), 10));
        }
        else {
            if(!option.equals("all")) {
                if(option.equals("title"))
                    addQuery = "match(title) against(?1 in boolean mode) and boardtype = '" + boardtype + "'";
                else if(option.equals("writer")) {
                    addQuery = "boardtype = '" + boardtype + "' and request_nickname like ?1";
                    text = "%" + text + "%";
                }
                else if(option.equals("commentContent"))
                    addQuery = "bno in (select bno from matchingcomment where match(content) against(?1 in boolean mode)) and boardtype = '" + boardtype + "'";

                if(date.equals("all"))
                    return matchingRepository.searchMatchingOptions(addQuery, text, PageRequest.of(pageable.getPageNumber(), 10));
            }
            if(!date.equals("all")) {
                if(date.equals("1week"))
                    dateNum = 7;
                else if(date.equals("1month"))
                    dateNum = 30;
                else if(date.equals("6month"))
                    dateNum = 180;

                if(option.equals("all"))
                    return matchingRepository.searchMatchingTypeDates(boardtype, text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
            }
        }
        return matchingRepository.searchMatchingOptionsAndDate(addQuery, text, dateNum, PageRequest.of(pageable.getPageNumber(), 10));
    }
}
