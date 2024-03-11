package org.stein.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author stein
 * @date 2024/3/11
 */
@Data
public class EduSubjectTreeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<EduSubjectTreeDTO> children;
}
