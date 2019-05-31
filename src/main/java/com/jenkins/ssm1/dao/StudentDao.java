package com.jenkins.ssm1.dao;

import com.jenkins.ssm1.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface StudentDao {
    @Insert("insert into student(name) values(#{name}) where id=#{id}")
    void  add(int id,String name);
    @Delete("DELET FROM student WHERE id=#{id}")
    void del(Long id);
    @Update("UPDATE student set name=#{name} WHERE id=#{id} ")
    void upd(Student stu);
    @Select("SELECT * FROM student WHERE id =#{id}")
    Student get(Long id);
     @Select("SELECT * FROM student")
    List<Student> list();
}
