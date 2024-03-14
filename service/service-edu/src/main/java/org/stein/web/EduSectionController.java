package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.stein.pojo.po.EduSectionPO;
import org.stein.result.Result;
import org.stein.service.EduSectionService;

/**
 * @author stein
 * @date 2024/3/14
 */
@CrossOrigin
@RequestMapping("/edu/service/sections")
@RestController
public class EduSectionController {
    @Autowired
    private EduSectionService sectionService;

    @GetMapping("/{sectionId}")
    public Result getSectionById(@PathVariable("sectionId") String sectionId) {
        EduSectionPO sectionPO = sectionService.getById(sectionId);
        return Result.ok().data("section", sectionPO);
    }

    @PutMapping
    public Result updateSectionById(@RequestBody EduSectionPO sectionPO) {
        sectionService.updateById(sectionPO);
        return Result.ok();
    }

    @DeleteMapping("/{sectionId}")
    public Result deleteSectionById(@PathVariable("sectionId") String sectionId) {
        sectionService.removeById(sectionId);
        return Result.ok();
    }

    @PostMapping
    public Result saveSection(@RequestBody EduSectionPO sectionPO) {
        sectionService.save(sectionPO);
        return Result.ok();
    }
}
