package com.example.thumbsup.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.thumbsup.model.Users;
import com.example.thumbsup.service.UsersService;
import com.example.thumbsup.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author 15896
* @description 针对表【users(用户基础表)】的数据库操作Service实现
* @createDate 2026-02-01 13:37:01
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




