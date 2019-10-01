package com._520.student.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> listAll();
    Student get(Long id);

    void delete(Long id);

    void save(Student stu);

    void update(Student stu);

    List<Student> advanceQuery(
            @Param("studentName") String studentName,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge
    );
}
