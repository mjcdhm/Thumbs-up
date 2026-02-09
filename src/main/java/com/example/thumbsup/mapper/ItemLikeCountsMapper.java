package com.example.thumbsup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thumbsup.model.ItemLikeCounts;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ItemLikeCountsMapper extends BaseMapper<ItemLikeCounts> {

    /**
     * 更新计数表
     * 这里的逻辑是：
     * 1. 尝试插入一条新记录，默认数量为 1 (假设是第一次点赞)
     * 2. 如果主键冲突(已存在)，则执行 like_count = like_count + delta
     *
     * 注意：VALUES 里的 1 是为了应对"第一次插入"的情况。
     * 但如果是"取消点赞"且记录不存在（理论上Service层会拦截，但为了SQL健壮性），
     * 我们通常依靠Service层保证只有"点赞"时才会触发"新插入"。
     */
    @Insert("INSERT INTO item_like_counts (target_id, target_type, like_count) " +
            "VALUES (#{targetId}, #{targetType}, 1) " +
            "ON DUPLICATE KEY UPDATE " +
            "like_count = like_count + #{delta}")
    int upsertLikeCount(@Param("targetId") String targetId,
                        @Param("targetType") Integer targetType,
                        @Param("delta") int delta);
}