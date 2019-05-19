package com.jenkins.ssm1.service;

import com.jenkins.ssm1.domain.Student;

import java.util.List;

public interface IStudentService {
    void  add(int id,String name);
    void  del(Long id);
    void upd(Student stu);
    Student get(long id);
    List<Student> list();

}
