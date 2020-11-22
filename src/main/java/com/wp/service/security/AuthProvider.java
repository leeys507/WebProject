package com.wp.service.security;

import com.wp.domain.student.StudentRepository;
import com.wp.domain.student.dto.StudentGetDTO;
import com.wp.service.student.StudentInfoService;
import com.wp.yufunction.YUFunction;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {

    private final HttpSession httpSession;
    private final StudentRepository studentRepository;
    private final StudentInfoService studentInfoService;
    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        setSSL();
        String name = login(id,password);
        if(name.isEmpty()){
            return null;
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if(studentRepository.existsBySid(id)) {
    		StudentGetDTO data = studentInfoService.getStudent(id);
    		httpSession.setAttribute("studentInfo", data);
    		httpSession.setAttribute("id", id);
    		httpSession.setAttribute("password", password);
    		
    		String folderName = data.getNickname() + new YUFunction().createSubFolderName(data.getSid());
    		String path = System.getProperty("user.dir");
    		File file = new File(path + "\\src\\main\\resources\\static\\images\\profiles\\" + folderName + "\\profileImage.png");
    		if(file.exists()) {
    			httpSession.setAttribute("folderName", folderName);
    		}
    		else {
    			httpSession.setAttribute("folderName", null);
    		}
    		
            grantedAuthorityList.add(new SimpleGrantedAuthority("USER"));
            return new UsernamePasswordAuthenticationToken(id, password, grantedAuthorityList);
        }
        httpSession.setAttribute("resSid", id);
        grantedAuthorityList.add(new SimpleGrantedAuthority("GUEST"));
        return new UsernamePasswordAuthenticationToken(id, password, grantedAuthorityList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    public String login(String userNo, String userPwd) throws IOException {
        String userName;
        Map<String,String> loginCookie;
        String G = "http://portal.yu.ac.kr/sso/login.jsp?type=lms&cReturn_Url=http%3A%2F%2Flms.yu.ac.kr%2Filos%2Flo%2Flogin_sso.acl";
        String A = "https://portal.yu.ac.kr/sso/login_process.jsp";
        
        Connection.Response loginPageResponse = Jsoup.connect(G)
                .timeout(3000)
                .execute();
        Map<String, String> loginTryCookie = loginPageResponse.cookies();
        Document loginPageDocument = loginPageResponse. parse();
        Elements e= loginPageDocument.select("fieldset.field_wrap input");
        String p = e.get(4).val();

        String userAgent = "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)";

        Connection.Response res = Jsoup.connect(A)
                .userAgent(userAgent)
                .timeout(3000)
                .header("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "ko-KR")
                .header("Host", "portal.yu.ac.kr")
                .header("Referer", "http://portal.yu.ac.kr/sso/login.jsp?type=lms&cReturn_Url=http%3A%2F%2Flms.yu.ac.kr%2Filos%2Flo%2Flogin_sso.acl")
                .data("userId" , userNo)
                .data("password", userPwd)
                .data("_enpass_login_", "submit")
                .data("cReturn_Url", "http://lms.yu.ac.kr/ilos/lo/login_sso.acl")
                .data("type", "lms")
                .data("login_lan", "ko")
                .data("p", p)
                .data("login_gb", "0")
                .cookies(loginTryCookie)
                .method(Connection.Method.POST)
                .execute();

        loginCookie = res.cookies();

        Document Home = Jsoup.connect("http://lms.yu.ac.kr/ilos/lo/login_sso.acl")
                .userAgent(userAgent)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "ko-KR")
                .header("Host", "lms.yu.ac.kr")
                .header("Referer", "http://lms.yu.ac.kr/ilos/lo/login_sso.acl")
                .cookies(loginCookie)
                .get();
        userName = Home.select("strong.site-font-color").html();

        return userName;
    }
    
    public void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // TODO Auto-generated method stub

            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // TODO Auto-generated method stub

            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                // TODO Auto-generated method stub
                return null;
            }

        }};
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }
        );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
}