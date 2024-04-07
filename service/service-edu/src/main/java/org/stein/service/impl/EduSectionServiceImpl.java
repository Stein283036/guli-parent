package org.stein.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stein.feign.AliyunVodClient;
import org.stein.mapper.EduSectionMapper;
import org.stein.pojo.po.EduSectionPO;
import org.stein.service.EduSectionService;

/**
 * @author stein
 * @date 2024/3/14
 */
@Service
public class EduSectionServiceImpl
        extends ServiceImpl<EduSectionMapper, EduSectionPO>
        implements EduSectionService {

    @Autowired
    private AliyunVodClient aliyunVodClient;

    @Override
    public void deleteSectionById(String sectionId) {
        EduSectionPO sectionPO = getById(sectionId);
        String videoSourceId = sectionPO.getVideoSourceId();
        if (!"".equals(videoSourceId)) {
            aliyunVodClient.deleteVideo(videoSourceId);
        }
        removeById(sectionId);
    }
}
