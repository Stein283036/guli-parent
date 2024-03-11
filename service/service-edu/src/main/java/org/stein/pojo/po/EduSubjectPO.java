package org.stein.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.stein.base.CommonObject;

/**
 * @author stein
 * @date 2024/3/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("edu_subject")
public class EduSubjectPO extends CommonObject {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String title;

    private String parentId;

    private Integer sort;
}
