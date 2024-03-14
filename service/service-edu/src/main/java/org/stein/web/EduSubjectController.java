package org.stein.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.stein.pojo.vo.EduSubjectTreeVO;
import org.stein.pojo.po.EduSubjectPO;
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

    @GetMapping("/second/{subjectParentId}")
    public Result listSubjectSecondBySubjectParentId(
            @PathVariable("subjectParentId") String subjectParentId
    ) {
        LambdaQueryWrapper<EduSubjectPO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduSubjectPO::getParentId, subjectParentId);
        List<EduSubjectPO> subjectSecondList = eduSubjectService.list(lqw);
        return Result.ok().data("subjectSecondList", subjectSecondList);
    }

    @GetMapping("/first")
    public Result listSubjectFirst() {
        LambdaQueryWrapper<EduSubjectPO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduSubjectPO::getParentId, "0");
        List<EduSubjectPO> subjectFirstList = eduSubjectService.list(lqw);
        return Result.ok().data("subjectFirstList", subjectFirstList);
    }

    @GetMapping("/tree")
    public Result treeListSubject() {
        List<EduSubjectTreeVO> subjects = eduSubjectService.treeListSubject();
        return Result.ok().data("subjects", subjects);
    }

    @PostMapping
    public Result saveSubjectFromExcel(MultipartFile file) {
        eduSubjectService.saveSubjectFromExcel(file);
        return Result.ok();
    }
}
