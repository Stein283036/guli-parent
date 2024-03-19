package org.stein.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.pojo.po.EduCoursePO;
import org.stein.pojo.vo.EduCoursePublishVO;

/**
 * @author stein
 * @date 2024/3/11
 */
public interface EduCourseService extends IService<EduCoursePO> {

    String saveCourse(EduCourseDTO eduCourseDTO);

    EduCourseDTO getCourseById(String courseId);

    boolean updateCourse(EduCourseDTO courseDTO);

    EduCoursePublishVO getCoursePublishInfoByCourseId(String courseId);

    boolean publishCourse(String courseId);
}
