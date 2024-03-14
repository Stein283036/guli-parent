package org.stein.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stein
 * @date 2024/3/11
 */
@Data
public class EduSectionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
}
