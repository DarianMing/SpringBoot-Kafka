package com.lm.three.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReportMetaData implements Serializable {

    private String title;
    private List<ReportClickData> clickData = new ArrayList<>();

}
