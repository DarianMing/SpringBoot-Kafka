package com.lm.three.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReportData implements Serializable {

    private String userId;
    private String createTime;
    private String page;
    private String operate;
    private ReportMetaData metaData;

}
