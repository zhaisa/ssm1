package com.jenkins.ssm1.controller;

import com.jenkins.ssm1.domain.Student;
import com.jenkins.ssm1.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private IStudentService service;
    @RequestMapping("/add")
    public  String add(int id,String name){
        service.add(id,name);
        return "index";
    }
    @RequestMapping("/del")
    public  String del(Long id){
        service.del(id);
        return "delete sucess";
    }
    @RequestMapping("/upd")
    public  String upd(){
        Student stu=new Student();
        stu.setId(1L);
        stu.setName("jingke");
        service.upd(stu);
        return "upd sucess";
    }
    @RequestMapping("/get")
    public  Student get(Long id){
        Student stu =service.get(id);
        return stu;
    }
    @RequestMapping("/list")
    public List<Student> list(){
        List<Student> list =service.list();
        return list;
    }
}
