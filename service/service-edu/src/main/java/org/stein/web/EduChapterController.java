package org.stein.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
