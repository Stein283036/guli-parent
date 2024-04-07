package org.stein.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stein.base.CommonObject;
import org.stein.converter.EduCourseConverter;
import org.stein.converter.EduCourseDescriptionConverter;
import org.stein.handler.exception.GuliException;
import org.stein.mapper.EduCourseMapper;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.pojo.po.EduChapterPO;
import org.stein.pojo.po.EduCourseDescriptionPO;
import org.stein.pojo.po.EduCoursePO;
import org.stein.pojo.po.EduSectionPO;
import org.stein.pojo.query.EduCourseQuery;
import org.stein.pojo.vo.EduCoursePublishVO;
import org.stein.result.StatusCode;
import org.stein.service.EduChapterService;
import org.stein.service.EduCourseDescriptionService;
import org.stein.service.EduCourseService;
import org.stein.service.EduSectionService;

import java.util.List;

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

    @Autowired
    private EduSectionService sectionService;

    @Autowired
    private EduChapterService chapterService;

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

    @Override
    public EduCoursePublishVO getCoursePublishInfoByCourseId(String courseId) {
        return baseMapper.selectCoursePublishInfo(courseId);
    }

    @Override
    public boolean publishCourse(String courseId) {
        return baseMapper.publishCourse(courseId) > 0;
    }

    @Override
    public Page<EduCoursePO> pageCoursesWithCondition(Long current, Long size, EduCourseQuery courseQuery) {
        Page<EduCoursePO> page = new Page<>(current, size);
        LambdaQueryWrapper<EduCoursePO> lqw = new LambdaQueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        lqw
                .like(title != null, EduCoursePO::getTitle, title)
                .eq(status != null, EduCoursePO::getStatus, status)
                .orderByDesc(CommonObject::getGmtCreate);
        page(page, lqw);
        return page;
    }

    @Transactional
    @Override
    public boolean removeCourseWithCascadeById(String courseId) {
        // 删除课程小节 edu_section
        LambdaQueryWrapper<EduSectionPO> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(courseId != null, EduSectionPO::getCourseId, courseId);
        List<EduSectionPO> sectionPOList = sectionService.list(lqw1);
        for (EduSectionPO sectionPO : sectionPOList) {
            sectionService.deleteSectionById(sectionPO.getId());
        }

        // 删除课程章节 edu_chapter
        LambdaQueryWrapper<EduChapterPO> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(courseId != null, EduChapterPO::getCourseId, courseId);
        boolean res2 = chapterService.remove(lqw2);

        // 删除课程描述 edu_course_description
        LambdaQueryWrapper<EduCourseDescriptionPO> lqw3 = new LambdaQueryWrapper<>();
        lqw3.eq(courseId != null, EduCourseDescriptionPO::getId, courseId);
        boolean res3 = courseDescriptionService.remove(lqw3);

        // 删除课程信息 edu_course
        LambdaQueryWrapper<EduCoursePO> lqw4 = new LambdaQueryWrapper<>();
        lqw4.eq(courseId != null, EduCoursePO::getId, courseId);
        boolean res4 = remove(lqw4);

        return res2 && res3 && res4;
    }
}
