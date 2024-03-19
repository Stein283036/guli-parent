package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.stein.handler.exception.GuliException;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.pojo.vo.EduCoursePublishVO;
import org.stein.result.Result;
import org.stein.result.StatusCode;
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

    @PostMapping("/publish/{courseId}")
        public Result publishCourse(@PathVariable("courseId") String courseId) {
        return courseService.publishCourse(courseId) ? Result.ok() : Result.error();
    }

    @GetMapping("/publish/{courseId}")
    public Result getCoursePublishInfoByCourseId(@PathVariable("courseId") String courseId) {
        EduCoursePublishVO coursePublishVO = courseService.getCoursePublishInfoByCourseId(courseId);
        return Result.ok().data("coursePublishInfo", coursePublishVO);
    }

    @PutMapping
    public Result updateCourse(@RequestBody EduCourseDTO courseDTO) {
        if (!courseService.updateCourse(courseDTO)) {
            throw new GuliException(StatusCode.ERROR);
        }
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
