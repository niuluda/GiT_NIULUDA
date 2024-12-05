package com.example.model;

import lombok.Data;

@Data
public class NoticeWebsocketResp<T> {
    private String type;
    private T noticeInfo;
 
}