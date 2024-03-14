package org.stein.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stein.converter.EduChapterConverter;
import org.stein.converter.EduSectionConverter;
import org.stein.mapper.EduChapterMapper;
import org.stein.pojo.po.EduChapterPO;
import org.stein.pojo.po.EduSectionPO;
import org.stein.pojo.vo.EduChapterTreeVO;
import org.stein.pojo.vo.EduSectionVO;
import org.stein.service.EduChapterService;
import org.stein.service.EduSectionService;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/14
 */
@Service
public class EduChapterServiceImpl
        extends ServiceImpl<EduChapterMapper, EduChapterPO>
        implements EduChapterService {

    @Autowired
    private EduSectionService sectionService;

    @Override
    public List<EduChapterTreeVO> treeListSubject(String courseId) {
        // 查询所有的章节信息
        LambdaQueryWrapper<EduChapterPO> lqw1 = new LambdaQueryWrapper<>();
        lqw1
                .eq(EduChapterPO::getCourseId, courseId)
                .orderByAsc(EduChapterPO::getSort);
        List<EduChapterPO> chapterPOList = list(lqw1);
        List<EduChapterTreeVO> chapters = EduChapterConverter.INSTANCE.chapterPOListToVOList(chapterPOList);

        // 遍历每一个章节信息，获取所有的小节信息
        for (EduChapterTreeVO chapter : chapters) {
            LambdaQueryWrapper<EduSectionPO> lqw2 = new LambdaQueryWrapper<>();
            lqw2
                    .eq(EduSectionPO::getChapterId, chapter.getId())
                    .eq(EduSectionPO::getCourseId, courseId)
                    .orderByAsc(EduSectionPO::getSort);
            // 根据 chapterId 查询章节下的所有小节
            List<EduSectionPO> sectionPOList = sectionService.list(lqw2);
            List<EduSectionVO> sectionVOList = EduSectionConverter.INSTANCE.sectionPOListToVOList(sectionPOList);
            chapter.setSections(sectionVOList);
        }

        return chapters;
    }

    @Override
    public boolean deleteChapterById(String chapterId) {
        LambdaQueryWrapper<EduSectionPO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduSectionPO::getChapterId, chapterId);
        int count = sectionService.count(lqw);
        if (count != 0) {
            // 章节下面有小节，不删除
            return false;
        }
        // 章节下面没有小节，删除章节信息
        return removeById(chapterId);
    }
}
