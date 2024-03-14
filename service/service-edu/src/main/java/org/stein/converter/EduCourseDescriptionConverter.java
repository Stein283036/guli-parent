package org.stein.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.stein.pojo.dto.EduCourseDTO;
import org.stein.pojo.po.EduCourseDescriptionPO;

/**
 * @author stein
 * @date 2024/3/14
 */
@Mapper
public interface EduCourseDescriptionConverter {
    EduCourseDescriptionConverter INSTANCE = Mappers.getMapper(EduCourseDescriptionConverter.class);

    EduCourseDescriptionPO courseDTOTocourseDescriptionPO(EduCourseDTO courseDTO);
}
