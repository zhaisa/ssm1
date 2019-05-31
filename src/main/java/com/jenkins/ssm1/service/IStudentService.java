package com.jenkins.ssm1.service;

import java.util.List;

import com.jenkins.ssm1.domain.Student;

public interface IStudentService {
    void  add(int id,String name);
    void  del(Long id);
    void upd(Student stu);
    Student get(long id);
    List<Student> list();

}
