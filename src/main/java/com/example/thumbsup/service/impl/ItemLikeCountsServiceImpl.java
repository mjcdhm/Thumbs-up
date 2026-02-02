package com.example.thumbsup.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.thumbsup.model.ItemLikeCounts;
import com.example.thumbsup.service.ItemLikeCountsService;
import com.example.thumbsup.mapper.ItemLikeCountsMapper;
import org.springframework.stereotype.Service;

/**
* @author 15896
* @description 针对表【item_like_counts(通用点赞计数聚合表)】的数据库操作Service实现
* @createDate 2026-02-01 13:36:17
*/
@Service
public class ItemLikeCountsServiceImpl extends ServiceImpl<ItemLikeCountsMapper, ItemLikeCounts>
    implements ItemLikeCountsService{

}




