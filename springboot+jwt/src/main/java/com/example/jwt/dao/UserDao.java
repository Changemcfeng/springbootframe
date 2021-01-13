package com.example.jwt.dao;


import com.example.jwt.utils.User;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserDao extends Mapper<User> {
}