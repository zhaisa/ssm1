package com.jenkins.ssm1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jenkins.ssm1.dao.StudentDao;
import com.jenkins.ssm1.domain.Student;
@Transactional
@Service
public class StudentServiceImpl implements IStudentService {
    
    StudentDao dao;

    @Override
    public void add(int id,String name) {
    dao.add(id,name);
    }

    @Override
    public void del(Long id) {
dao.del(id);
    }

    @Override
    public void upd(Student stu) {
dao.upd(stu);
    }

    @Override
    public Student get(long id) {
        return dao.get(id);
    }

    @Override
    public List<Student> list() {
        return dao.list();
    }
}
