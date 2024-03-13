package org.stein.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.pojo.po.EduCoursePO;

/**
 * @author stein
 * @date 2024/3/11
 */
public interface EduCourseService extends IService<EduCoursePO> {

    String saveCourse(EduCourseDTO eduCourseDTO);

    EduCourseDTO getCourseById(String courseId);
}
