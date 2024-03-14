package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.stein.pojo.po.EduChapterPO;
import org.stein.pojo.vo.EduChapterTreeVO;
import org.stein.result.Result;
import org.stein.service.EduChapterService;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/11
 */
@CrossOrigin
@RequestMapping("/edu/service/chapters")
@RestController
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @DeleteMapping("/{chapterId}")
    public Result deleteChapterById(@PathVariable("chapterId") String chapterId) {
        boolean res = chapterService.deleteChapterById(chapterId);
        return res ? Result.ok() : Result.error().message("章节下面有小节，无法删除");
    }

    @PutMapping
    public Result updateChapterById(@RequestBody EduChapterPO chapterPO) {
        chapterService.updateById(chapterPO);
        return Result.ok();
    }

    /**
     * 根据章节 id 查询章节信息
     */
    @GetMapping("/{chapterId}")
    public Result getChapterById(@PathVariable("chapterId") String chapterId) {
        EduChapterPO chapterPO = chapterService.getById(chapterId);
        return Result.ok().data("chapter", chapterPO);
    }

    /**
     * 添加章节信息
     */
    @PostMapping
    public Result saveChapter(@RequestBody EduChapterPO chapterPO) {
        chapterService.save(chapterPO);
        return Result.ok();
    }

    /**
     * 查询课程下的所有章节（chapter）和小节（section）信息，一个章节下面有多个小节
     *
     * @param courseId 课程 id
     * @return 章节和小节的树形结构
     */
    @GetMapping("/tree/{courseId}")
    public Result treeListChapter(@PathVariable("courseId") String courseId) {
        List<EduChapterTreeVO> chapters = chapterService.treeListSubject(courseId);
        return Result.ok().data("chapters", chapters);
    }
}
