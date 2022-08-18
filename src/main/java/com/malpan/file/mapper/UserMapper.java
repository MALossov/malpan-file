package com.malpan.file.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.malpan.file.model.User;

public interface UserMapper extends BaseMapper<User> {
    void insertUser(User user);
    List<User> selectUser();
}
