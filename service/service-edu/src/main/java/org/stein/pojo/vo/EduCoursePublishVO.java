package org.stein.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author stein
 * @date 2024/3/19
 */
@Data
public class EduCoursePublishVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonSum;
    private String subjectFirstTitle;
    private String subjectSecondTitle;
    private String teacherName;
    private BigDecimal price;
}
