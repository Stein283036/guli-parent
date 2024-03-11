package org.stein.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.stein.pojo.dto.EduSubjectTreeDTO;
import org.stein.pojo.po.EduSubjectPO;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/11
 */
@Mapper
public interface EduSubjectPOConverter {
    EduSubjectPOConverter INSTANCE = Mappers.getMapper(EduSubjectPOConverter.class);

    List<EduSubjectTreeDTO> subjectPOListToDTOList(List<EduSubjectPO> subjectPOList);
}
