package com.lm.three.controller;

import com.lm.three.config.Contants;
import com.lm.three.entity.ReportData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/report/pc")
@RequiredArgsConstructor
public class ReportController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("pageView")
    @ResponseBody
    public Map<String,Object> pageView (@RequestBody ReportData reportData) {
        kafkaTemplate.send(Contants.TOPIC_PAGE , reportData.toString());
        Map<String,Object> result = new HashMap<>();
        result.put("success" , true);
        result.put("data" , "页面数据上报成功!");
        return result;
    }

    @PostMapping("click")
    @ResponseBody
    public Map<String,Object> click (@RequestBody ReportData reportData) {
        kafkaTemplate.send(Contants.TOPIC_CLICK , reportData.toString());
        Map<String,Object> result = new HashMap<>();
        result.put("success" , true);
        result.put("data" , "页面数据上报成功!");
        return result;
    }

    @GetMapping("page")
    public String page() {
        return "report-user-activity";
    }
}
