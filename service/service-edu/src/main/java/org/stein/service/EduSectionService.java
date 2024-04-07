package org.stein.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.stein.pojo.po.EduSectionPO;

/**
 * @author stein
 * @date 2024/3/14
 */
public interface EduSectionService extends IService<EduSectionPO> {

    void deleteSectionById(String sectionId);
}
