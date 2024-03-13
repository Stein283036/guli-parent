package org.stein.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author stein
 * @date 2024/3/11
 */
@Data
public class EduCourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
    private String title;
    private BigDecimal price;
    private Integer lessonSum;
    private String cover;
    private String description;
}
