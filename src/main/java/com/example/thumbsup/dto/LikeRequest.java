package com.example.thumbsup.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import com.example.thumbsup.enums.LikeAction;
@Data
public class LikeRequest {
    @NotBlank(message = "被点赞内容id不能为0")
    @JsonProperty("target_id")
    private String targetId;
    @NotNull(message = "被点赞内容类型不能为空")
    @JsonProperty("target_type")
    private Integer targetType;
    @NotNull(message = "操作类型不能为空")
    private LikeAction likeAction;
}
