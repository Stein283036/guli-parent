package org.stein.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.stein.pojo.po.EduChapterPO;
import org.stein.pojo.vo.EduChapterTreeVO;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/14
 */
public interface EduChapterService extends IService<EduChapterPO> {

    List<EduChapterTreeVO> treeListSubject(String courseId);
}
