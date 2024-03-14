package org.stein.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stein.converter.EduCourseConverter;
import org.stein.converter.EduCourseDescriptionConverter;
import org.stein.handler.exception.GuliException;
import org.stein.mapper.EduCourseMapper;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.pojo.po.EduCourseDescriptionPO;
import org.stein.pojo.po.EduCoursePO;
import org.stein.result.StatusCode;
import org.stein.service.EduCourseDescriptionService;
import org.stein.service.EduCourseService;

/**
 * @author stein
 * @date 2024/3/11
 */
@Service
public class EduCourseServiceImpl
        extends ServiceImpl<EduCourseMapper, EduCoursePO>
        implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Transactional
    @Override
    public String saveCourse(EduCourseDTO courseDTO) {
        // 向 edu_course 表中添加课程基本信息
        EduCoursePO coursePO = EduCourseConverter.INSTANCE.courseDTOToCoursePO(courseDTO);
        boolean flag1 = save(coursePO);

        // 向 edu_course_description 表中添加课程描述信息
        String courseId = coursePO.getId();
        EduCourseDescriptionPO courseDescriptionPO = new EduCourseDescriptionPO();
        courseDescriptionPO.setDescription(courseDTO.getDescription());
        courseDescriptionPO.setId(courseId);
        boolean flag2 = courseDescriptionService.save(courseDescriptionPO);

        if (!(flag1 && flag2)) {
            throw new GuliException(StatusCode.ERROR);
        }

        return courseId;
    }

    @Override
    public EduCourseDTO getCourseById(String courseId) {
        LambdaQueryWrapper<EduCoursePO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduCoursePO::getId, courseId);
        EduCoursePO coursePO = getOne(lqw);
        EduCourseDTO courseDTO = EduCourseConverter.INSTANCE.coursePOToCourseDTO(coursePO);
        courseDTO.setDescription(courseDescriptionService.getById(courseId).getDescription());
        return courseDTO;
    }

    @Transactional
    @Override
    public boolean updateCourse(EduCourseDTO courseDTO) {
        // 修改课程的基本信息
        EduCoursePO coursePO = EduCourseConverter.INSTANCE.courseDTOToCoursePO(courseDTO);
        boolean res1 = updateById(coursePO);

        // 修改课程的描述信息
        EduCourseDescriptionPO courseDescriptionPO =
                EduCourseDescriptionConverter.INSTANCE.courseDTOTocourseDescriptionPO(courseDTO);
        boolean res2 = courseDescriptionService.updateById(courseDescriptionPO);

        return res1 && res2;
    }
}
