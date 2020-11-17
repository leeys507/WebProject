package com.wp.domain.schedule;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {


    List<Schedule> findBySid(String sid);

    @Transactional
    Optional<Schedule> deleteBySid(String sid);

}
// User findByUsernameAndPassword(String username, String password);//=SELECT *FROM user WHERE username=? AND password=?;
/*
    UserRepository는 선언만해놔도 여러가지 함수를 통해

    자동으로 mysql에 save, delete, update, select등을 다할수 있는 듯하다.

    다만 쪼금더 복잡한 방식으로 데이터를 찾아야 할때

    예를들어 내정보를 보기위해선 쿼리가 하나론부족하다

    즉 아이디와 비밀번호가 동시에 맞아떨어져야 데이터를 찾을 수 있는 정당성을 부여 받을 테니깐

    그러한 복잡다다한 쿼리문을 추가해서 만들고 싶으면 위 문장처럼 만들어줘야 하나봄.

    참고로 위문장은 이름에서도 알수있듯이 select문장이며 where에 해당하는 두 키워드가 username과 password이며 and 의

    조건을 만족하는 데이터를 찾아 return 해준다.
 */