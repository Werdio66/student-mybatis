package com._520.student.mybatis.mapper;

import java.util.List;

public interface UserMapper {

    List<User> get();

    void save(User user);
}
