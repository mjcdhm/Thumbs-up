package com.example.thumbsup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thumbsup.model.UserLikes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserLikesMapper extends BaseMapper<UserLikes> {

    /**
     * 插入或更新点赞记录 (Upsert)
     * 如果记录不存在 -> 插入
     * 如果记录已存在 (唯一索引冲突) -> 更新 status 字段
     */
    @Insert("INSERT INTO user_likes (id, user_id, target_id, target_type, status, create_time, update_time) " +
            "VALUES (#{id}, #{userId}, #{targetId}, #{targetType}, #{status}, NOW(), NOW()) " +
            "ON DUPLICATE KEY UPDATE " +
            "status = #{status}, " +
            "update_time = NOW()")
    int upsertUserLike(@Param("id") String id,
                       @Param("userId") String userId,
                       @Param("targetId") String targetId,
                       @Param("targetType") Integer targetType,
                       @Param("status") Integer status);
}