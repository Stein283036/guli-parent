package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.result.Result;
import org.stein.service.EduCourseService;

/**
 * @author stein
 * @date 2024/3/11
 */
@CrossOrigin
@RequestMapping("/edu/service/courses")
@RestController
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping
    public Result getCourseById(@RequestParam("courseId") String courseId) {
        EduCourseDTO courseDTO = eduCourseService.getCourseById(courseId);
        return Result.ok().data("courseInfo", courseDTO);
    }

    /**
     * 添加课程基本信息
     */
    @PostMapping
    public Result saveCourse(@RequestBody EduCourseDTO eduCourseDTO) {
        String courseId = eduCourseService.saveCourse(eduCourseDTO);
        return Result.ok().data("courseId", courseId);
    }
}
