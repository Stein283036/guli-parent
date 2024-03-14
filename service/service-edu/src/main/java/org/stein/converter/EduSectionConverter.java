package org.stein.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.stein.pojo.po.EduSectionPO;
import org.stein.pojo.vo.EduSectionVO;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/14
 */
@Mapper
public interface EduSectionConverter {
    EduSectionConverter INSTANCE = Mappers.getMapper(EduSectionConverter.class);

    List<EduSectionVO> sectionPOListToVOList(List<EduSectionPO> sectionPOList);
}
