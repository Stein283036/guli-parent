package org.stein.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.stein.pojo.po.EduCoursePO;
import org.stein.pojo.vo.EduCoursePublishVO;

/**
 * @author stein
 * @date 2024/3/11
 */
public interface EduCourseMapper extends BaseMapper<EduCoursePO> {
    EduCoursePublishVO selectCoursePublishInfo(@Param("courseId") String courseId);

    int publishCourse(@Param("courseId") String courseId);
}
