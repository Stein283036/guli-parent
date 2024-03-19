package org.stein.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stein
 * @date 2024/3/19
 */
@Data
public class EduCourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String status;
}
