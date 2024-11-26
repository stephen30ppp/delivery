package org.example.delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.delivery.Entity.User;
import org.example.delivery.mapper.UserMapper;
import org.example.delivery.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
