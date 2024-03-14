package org.stein.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.stein.pojo.po.EduChapterPO;
import org.stein.pojo.vo.EduChapterTreeVO;

import java.util.List;

/**
 * @author stein
 * @date 2024/3/14
 */
@Mapper
public interface EduChapterConverter {
    EduChapterConverter INSTANCE = Mappers.getMapper(EduChapterConverter.class);

    List<EduChapterTreeVO> chapterPOListToVOList(List<EduChapterPO> chapterPOList);
}
