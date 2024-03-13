package org.stein.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.stein.base.CommonObject;

/**
 * @author stein
 * @date 2024/3/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("edu_section")
public class EduSectionPO extends CommonObject {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    private String courseId;
    private String chapterId;
    private String title;
    private String videoSourceId;
    private String videoOriginalName;
    private Integer sort;
    private Long playCount;
    private Boolean isFree;
    private Float duration;
    private String status;
    private Long size;
    private Long version;
}
