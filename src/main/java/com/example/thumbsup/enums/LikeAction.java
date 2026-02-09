package com.example.thumbsup.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LikeAction {
    @JsonProperty("like")
    LIKE,
    @JsonProperty("unlike")
    UNLIKE
}
