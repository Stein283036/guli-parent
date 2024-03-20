package org.stein.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.stein.pojo.po.EduSubjectPO;
import org.stein.pojo.vo.EduSubjectTreeVO;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/11
 */
@Mapper
public interface EduSubjectConverter {
    EduSubjectConverter INSTANCE = Mappers.getMapper(EduSubjectConverter.class);

    List<EduSubjectTreeVO> subjectPOListToDTOList(List<EduSubjectPO> subjectPOList);
}
