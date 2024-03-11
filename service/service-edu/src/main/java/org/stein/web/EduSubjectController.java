package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.stein.pojo.dto.EduSubjectTreeDTO;
import org.stein.result.Result;
import org.stein.service.EduSubjectService;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/8
 */
@CrossOrigin
@RequestMapping("/edu/service/subjects")
@RestController
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @GetMapping("/tree")
    public Result treeListSubject() {
        List<EduSubjectTreeDTO> subjects = eduSubjectService.treeListSubject();
        return Result.ok().data("subjects", subjects);
    }

    @PostMapping
    public Result saveSubjectFromExcel(MultipartFile file) {
        eduSubjectService.saveSubjectFromExcel(file);
        return Result.ok();
    }
}
