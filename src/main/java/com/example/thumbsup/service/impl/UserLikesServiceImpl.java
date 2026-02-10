package com.example.thumbsup.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.thumbsup.dto.LikeRequest;
import com.example.thumbsup.enums.LikeAction;
import com.example.thumbsup.model.UserLikes;
import com.example.thumbsup.service.UserLikesService;
import com.example.thumbsup.mapper.UserLikesMapper;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal.DecimalMaxValidatorForBigInteger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import com.example.thumbsup.mapper.UserLikesMapper;
import com.example.thumbsup.mapper.ItemLikeCountsMapper;
import org.springframework.transaction.annotation.Transactional;
import com.github.f4b6a3.ulid.UlidCreator;
/**
* @author 15896
* @description 针对表【user_likes(用户点赞行为明细表)】的数据库操作Service实现
* @createDate 2026-02-01 13:36:52
*/
@Service
@MapperScan("com.example.thumbsup.mapper")
public class UserLikesServiceImpl extends ServiceImpl<UserLikesMapper, UserLikes>
    implements UserLikesService{
    @Autowired
    private UserLikesMapper userLikesMapper;

    @Autowired
    private ItemLikeCountsMapper itemLikeCountsMapper;
    @Autowired
    private DecimalMaxValidatorForBigInteger decimalMaxValidatorForBigInteger;

    //    @Override
//    @Transactional(rollbackFor = Exception.class);
    public void likeOrUnLike(String userId, LikeRequest request) {
// 1. 先查询当前状态 (幂等性检查的关键)
        // SELECT * FROM user_likes WHERE user_id = ? AND target_id = ?
        UserLikes currentLike = userLikesMapper.selectOne(new LambdaQueryWrapper<UserLikes>()
                .eq(UserLikes::getUserId, userId)
                .eq(UserLikes::getTargetId, request.getTargetId()));
        boolean needOperate = false;
        Integer statusToUpdate=0;//点赞状态更新
        Integer countDelta=0;//加一或者减一
        //点赞操作
        if(request.getLikeAction()== LikeAction.LIKE){
            if(currentLike!=null&&currentLike.getStatus()==0){
                needOperate=true;
                statusToUpdate=1;
                countDelta=1;
            }
            else{
                return;
            }
        }
        //取消点赞操作
        else{
            if(currentLike!=null&&currentLike.getStatus()==1){
                needOperate=false;
                statusToUpdate=0;
                countDelta=-1;
            }
        }
        //更新操作，对点赞明细表和点赞计数表进行修改
        String id=(currentLike!=null?UlidCreator.getUlid().toString():currentLike.getId());
        userLikesMapper.upsertUserLike(
                id,
                userId,
                request.getTargetId(),
                request.getTargetType(),
                statusToUpdate
        );
        itemLikeCountsMapper.upsertLikeCount(
                request.getTargetId(),
                request.getTargetType(),
                countDelta
        );

    }

}



