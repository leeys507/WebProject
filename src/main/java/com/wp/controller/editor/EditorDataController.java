package com.wp.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
public class EditorDataController {
    @RequestMapping(value="/mine/imageUpload.do", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request,
                            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload, 
            @RequestParam(value="board") String board, @RequestParam(value="boardtype") String boardtype,
            @RequestParam(value="sid") String sid, @RequestParam(value="nickname") String nickname) throws Exception{
        // 랜덤 문자 생성
        UUID uid = UUID.randomUUID();

        OutputStream out = null;
        PrintWriter printWriter = null;

        //인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        try{

            //파일 이름 가져오기
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();

            //이미지 경로 생성
            String chgSid = createSubForderName(sid);
            String forderName = nickname + chgSid;
            
            String path = "ckImage/" + board + "/" + boardtype + "/" + forderName + "/";// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path);

            //해당 디렉토리 확인
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 폴더 생성
                }catch(Exception e){
                    e.getStackTrace();
                }
            }

            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화

            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/mine/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName + 
            		"&board=" + board + "&boardtype=" + boardtype + "&forderName=" + forderName;  // 작성화면

            // 업로드시 메시지 출력
            printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            printWriter.flush();

        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(out != null) { out.close(); }
                if(printWriter != null) { printWriter.close(); }
            } catch(IOException e) { e.printStackTrace(); }
        }

        return;
    }


    @RequestMapping(value="/mine/ckImgSubmit.do")
    public void ckSubmit(@RequestParam(value="uid") String uid
            , @RequestParam(value="fileName") String fileName
            , @RequestParam(value="board") String board, @RequestParam(value="boardtype") String boardtype,
            @RequestParam(value="forderName") String forderName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        //서버에 저장된 이미지 경로
        String path = "ckImage/" + board + "/" + boardtype + "/" + forderName + "/";

        String sDirPath = path + uid + "_" + fileName;

        File imgFile = new File(sDirPath);

        //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
        if(imgFile.isFile()){
            byte[] buf = new byte[1024];
            int readByte = 0;
            int length = 0;
            byte[] imgBuf = null;

            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            ServletOutputStream out = null;

            try{
                fileInputStream = new FileInputStream(imgFile);
                outputStream = new ByteArrayOutputStream();
                out = response.getOutputStream();

                while((readByte = fileInputStream.read(buf)) != -1){
                    outputStream.write(buf, 0, readByte);
                }

                imgBuf = outputStream.toByteArray();
                length = imgBuf.length;
                out.write(imgBuf, 0, length);
                out.flush();

            }catch(IOException e){

            }finally {
                outputStream.close();
                fileInputStream.close();
                out.close();
            }
        }
    }
    
    private String createSubForderName(String sid) {
        String chgSid = "";
        char c;
        int val = -2;
        int res;
        
        for(int i = 0; i < sid.length(); i++) {
        	res = ((int)sid.charAt(i) + 60 + (i * (-val) + 3));
        	if(res > 122) res -= 12;
        	c = (char)res;
        	chgSid += c;
        }
        chgSid += (char)((int)sid.charAt(3) + (int)sid.charAt(5) + 1);
        chgSid += (char)((int)sid.charAt(4) + (int)sid.charAt(7) + 1);
        
        return chgSid;
    }
}
