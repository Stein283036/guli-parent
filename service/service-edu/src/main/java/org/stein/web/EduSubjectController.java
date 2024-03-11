package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.stein.result.Result;
import org.stein.service.EduSubjectService;

/**
 * @author stein
 * @date 2024/3/8
 */
@CrossOrigin
@RequestMapping("/edu/service/subject")
@RestController
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping
    public Result saveSubjectFromExcel(MultipartFile file) {
        eduSubjectService.saveSubjectFromExcel(file);
        return Result.ok();
    }
}
