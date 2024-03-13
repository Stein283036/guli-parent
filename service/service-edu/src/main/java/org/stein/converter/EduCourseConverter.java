package org.stein.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.pojo.po.EduCoursePO;

/**
 * @author stein
 * @date 2024/3/11
 */
@Mapper
public interface EduCourseConverter {
    EduCourseConverter INSTANCE = Mappers.getMapper(EduCourseConverter.class);

    EduCoursePO courseDTOToCoursePO(EduCourseDTO courseDTO);

    EduCourseDTO coursePOToCourseDTO(EduCoursePO coursePO);
}
