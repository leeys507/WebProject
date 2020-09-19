package com.wp.domain.student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudentRepository extends JpaRepository<Student, StudentKey>, StudentCustomRepository<Student> {
}

interface StudentCustomRepository<T> {
    Student findBySid(String sid);
}

@Repository
@Transactional
class StudentCustomRepositoryImpl implements StudentCustomRepository<Student> {
    @Autowired
    EntityManager entityManager;

    @Override
    public Student findBySid(String sid) {
        String sql = "SELECT s FROM student s WHERE sid = ?1";
        TypedQuery<Student> query = entityManager.createQuery(sql, Student.class);
        query.setParameter(1, sid);
        
        try {
        	return query.getSingleResult();
        }
        catch(Exception e) {
        	return null;
        }
    }
}