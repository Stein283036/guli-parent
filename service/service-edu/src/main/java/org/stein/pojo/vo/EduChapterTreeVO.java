package org.stein.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author stein
 * @date 2024/3/11
 */
@Data
public class EduChapterTreeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<EduSectionVO> sections; // 章节下的所有小结
}
