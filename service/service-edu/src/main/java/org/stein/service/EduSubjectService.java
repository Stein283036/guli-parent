package org.stein.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import org.stein.pojo.po.EduSubjectPO;
import org.stein.pojo.vo.EduSubjectTreeVO;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/8
 */
public interface EduSubjectService extends IService<EduSubjectPO> {

    void saveSubjectFromExcel(MultipartFile file);

    List<EduSubjectTreeVO> treeListSubject();
}
