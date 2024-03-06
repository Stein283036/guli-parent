package org.stein.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.stein.base.CommonObject;

/**
 * @author stein
 * @date 2024/3/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("edu_teacher")
public class EduTeacherPO extends CommonObject {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private String intro;

    private String career;

    private Integer level;

    private String avatar;

    private Integer sort;
}
