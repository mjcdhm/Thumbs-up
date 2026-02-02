package com.example.thumbsup.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.thumbsup.model.UserLikes;
import com.example.thumbsup.service.UserLikesService;
import com.example.thumbsup.mapper.UserLikesMapper;
import org.springframework.stereotype.Service;

/**
* @author 15896
* @description 针对表【user_likes(用户点赞行为明细表)】的数据库操作Service实现
* @createDate 2026-02-01 13:36:52
*/
@Service
public class UserLikesServiceImpl extends ServiceImpl<UserLikesMapper, UserLikes>
    implements UserLikesService{

}




