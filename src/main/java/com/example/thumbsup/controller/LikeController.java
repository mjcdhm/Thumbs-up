package com.example.thumbsup.controller;
import com.example.thumbsup.dto.LikeRequest;
import com.example.thumbsup.service.impl.UserLikesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.thumbsup.service.impl.UsersServiceImpl;
@RestController
@RequestMapping("/api/v1/likes")
@Validated
public class LikeController {
    @Autowired
    private UserLikesServiceImpl userLikesService;
    @PostMapping
    public String dolike(@RequestBody @Validated LikeRequest likeRequest, @RequestAttribute("current_user_id") String userId) {
        userLikesService.likeOrUnLike(userId, likeRequest);
        return "操作成功";
    }


    
}
