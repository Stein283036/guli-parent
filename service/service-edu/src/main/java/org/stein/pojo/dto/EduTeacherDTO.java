package org.stein.pojo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author stein
 * @date 2024/3/6
 */
@Data
public class EduTeacherDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer level;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
}
