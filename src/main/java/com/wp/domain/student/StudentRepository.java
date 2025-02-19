package com.wp.domain.student;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wp.domain.student.dto.StudentGetDTO;

public interface StudentRepository extends JpaRepository<Student, StudentGetDTO>, StudentCustomRepository {
	@Query(value = "SELECT * FROM student s WHERE s.sid = :sid", nativeQuery = true)
	Student findBySid(@Param("sid") String sid);

	boolean existsBySid(String sid);
	
	@Query(value = "SELECT s.nickname FROM student s WHERE s.sid = :sid", nativeQuery = true)
	String getBoardByNickname(@Param("sid") String sid);
	
	@Query(value = "select sum(c) from " + 
			"(select count(*) as c from board where sid = :sid " + 
			"union " + 
			"select count(*) as c from matching where request_sid = :sid) as t;", nativeQuery = true)
	int getMyBoardCount(@Param("sid") String sid);
	
	@Query(value = "select sum(like_count) from board where sid = :sid and check_delete = 'F'")
	Integer getMyLikeCount(@Param("sid") String sid);
	
	@Query(value = "SELECT count(nickname) FROM student where nickname = :nickname", nativeQuery = true)
	int getEqualNickname(@Param("nickname") String nickname);
		
	@Transactional
	@Modifying
	@Query(value = "UPDATE student SET nickname = :nickname, update_date = :date WHERE sid = :sid", nativeQuery = true)
	int updateNickname(@Param("sid") String sid, @Param("nickname") String nickname, @Param("date") LocalDateTime date);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE student s SET s.email = :email WHERE s.sid = :sid", nativeQuery = true)
	int updateEmail(@Param("sid") String sid, @Param("email") String email);
	
	@Query(value = "SELECT * FROM student s WHERE s.nickname = :nickname", nativeQuery = true)
    Student findByNickname(@Param("nickname") String nickname);
}

//@Repository
//@Transactional
//class StudentCustomRepositoryImpl implements StudentCustomRepository<Student> {
//    @Autowired
//    EntityManager entityManager;
//
//    @Override
//    public Student findBySid(String sid) {
//        String sql = "SELECT s FROM student s WHERE sid = ?1";
//        TypedQuery<Student> query = entityManager.createQuery(sql, Student.class);
//        query.setParameter(1, sid);
//        
//        try {
//        	return query.getSingleResult();
//        }
//        catch(Exception e) {
//        	return null;
//        }
//    }
//}
