package com.org.nenu.pojo;

import lombok.Data;
import sun.misc.Contended;

import java.sql.Timestamp;


@Contended
@Data
public class Examination {
    private Integer examId;

    private String examItem;

    private String examSpecialty;

    private Integer specialtyId;

    private Timestamp startTime;

    private Timestamp endTime;
}
