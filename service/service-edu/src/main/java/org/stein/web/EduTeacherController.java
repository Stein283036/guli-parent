package org.stein.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.stein.pojo.dto.EduTeacherDTO;
import org.stein.pojo.po.EduTeacherPO;
import org.stein.result.Result;
import org.stein.service.EduTeacherService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stein
 * @date 2024/3/6
 */
@CrossOrigin
@RequestMapping("/edu/service/teachers")
@RestController
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @PutMapping
    public Result updateTeacher(@RequestBody EduTeacherPO teacherPO) {
        boolean res = teacherService.updateById(teacherPO);
        if (res) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("/{id}")
    public Result getTeacherById(@PathVariable String id) {
        EduTeacherPO teacherPO = teacherService.getById(id);
        return Result.ok().data("teacher", teacherPO);
    }

    @PostMapping
    public Result saveTeacher(@RequestBody EduTeacherPO teacherPO) {
        boolean res = teacherService.save(teacherPO);
        if (res) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("/page/condition/{current}/{size}")
    public Result pageTeachersWithCondition(
            @PathVariable long current,
            @PathVariable long size,
            EduTeacherDTO teacherDTO
    ) {
        Page<EduTeacherPO> page = new Page<>(current, size);
        LambdaQueryWrapper<EduTeacherPO> lqw = new LambdaQueryWrapper<>();

        String name = teacherDTO.getName();
        Integer level = teacherDTO.getLevel();
        LocalDateTime begin = teacherDTO.getBegin();
        LocalDateTime end = teacherDTO.getEnd();

        lqw
                .like(name != null, EduTeacherPO::getName, name)
                .eq(level != null, EduTeacherPO::getLevel, level)
                .between(
                        begin != null && end != null,
                        EduTeacherPO::getGmtCreate,
                        begin,
                        end
                )
                .orderByDesc(EduTeacherPO::getGmtCreate);
        teacherService.page(page, lqw);

        long total = page.getTotal();
        List<EduTeacherPO> records = page.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    @GetMapping("/page/{current}/{size}")
    public Result pageTeachers(@PathVariable("current") long current, @PathVariable("size") long size) {
        Page<EduTeacherPO> page = new Page<>(current, size);
        LambdaQueryWrapper<EduTeacherPO> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(EduTeacherPO::getGmtCreate);
        teacherService.page(page, lqw);
        long total = page.getTotal();
        List<EduTeacherPO> records = page.getRecords();
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("records", records);
        return Result.ok().data(data);
    }

    @DeleteMapping("/{id}")
    public Result removeTeacherById(@PathVariable("id") String id) {
        boolean res = teacherService.removeById(id);
        if (res) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping
    public Result listTeachers() {
        List<EduTeacherPO> teachers = teacherService.list(null);
        return Result.ok().data("teachers", teachers);
    }
}
