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
    private EduCourseService courseService;

    @PutMapping
    public Result updateCourse(@RequestBody EduCourseDTO courseDTO) {
        courseService.updateCourse(courseDTO);
        return Result.ok();
    }

    @GetMapping
    public Result getCourseById(@RequestParam("courseId") String courseId) {
        EduCourseDTO courseDTO = courseService.getCourseById(courseId);
        return Result.ok().data("courseInfo", courseDTO);
    }

    /**
     * 添加课程基本信息
     */
    @PostMapping
    public Result saveCourse(@RequestBody EduCourseDTO courseDTO) {
        String courseId = courseService.saveCourse(courseDTO);
        return Result.ok().data("courseId", courseId);
    }
}
