package com.wp.domain.student;

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
		
	@Transactional
	@Modifying
	@Query(value = "UPDATE student SET nickname = :nickname, update_time = NOW() WHERE sid = :sid", nativeQuery = true)
	int updateNickname(@Param("sid") String sid, @Param("nickname") String nickname);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE student s SET s.email = :email WHERE s.sid = :sid", nativeQuery = true)
	int updateEmail(@Param("sid") String sid, @Param("email") String email);
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
