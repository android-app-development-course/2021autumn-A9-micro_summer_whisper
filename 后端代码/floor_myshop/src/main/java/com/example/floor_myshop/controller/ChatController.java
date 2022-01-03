package com.example.floor_myshop.controller;

import com.example.floor_myshop.model.ApiResponse;
import com.example.floor_myshop.vo.ChatMessageVo;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tong
 * @date 2021/12/23
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    private Map<Integer, LinkedList<ChatMessageVo>> msgPool = new ConcurrentHashMap<>();

    @PostMapping("/sendMessage")
    public ApiResponse sendMessage(@RequestBody ChatMessageVo chatMessageVo){
        LinkedList<ChatMessageVo> deq = msgPool.get(chatMessageVo.getHostId());
        if (deq == null) {
            deq = new LinkedList<>();
            msgPool.put(chatMessageVo.getHostId(),deq);
        }
        deq.addLast(chatMessageVo);
        return ApiResponse.success("发送消息成功");
    }

    @GetMapping("/getMessage/{id}")
    public ApiResponse getMessage(@PathVariable("id") Integer id){
        LinkedList<ChatMessageVo> deq = msgPool.get(id);
        if (deq == null) {
            deq = new LinkedList<>();
        }
        LinkedList<ChatMessageVo> data = new LinkedList<>(deq);
        deq.clear();
        return ApiResponse.success("获取消息成功",data);
    }



}
