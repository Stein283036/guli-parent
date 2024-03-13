package org.stein.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.stein.base.CommonObject;

import java.math.BigDecimal;

/**
 * @author stein
 * @date 2024/3/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("edu_course")
public class EduCoursePO extends CommonObject {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    private String teacherId;
    private String subjectId;
    private String subjectParentId;
    private String title;
    private BigDecimal price;
    private Integer lessonSum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private Long version;
    private String status;
    @TableLogic
    private Boolean isDeleted;
}
