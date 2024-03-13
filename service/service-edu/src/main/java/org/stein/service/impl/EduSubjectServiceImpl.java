package org.stein.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.stein.converter.EduSubjectConverter;
import org.stein.listener.ExcelSubjectListener;
import org.stein.mapper.EduSubjectMapper;
import org.stein.pojo.bo.ExcelSubjectBO;
import org.stein.pojo.dto.EduSubjectTreeDTO;
import org.stein.pojo.po.EduSubjectPO;
import org.stein.service.EduSubjectService;

import java.io.IOException;
import java.util.List;

/**
 * @author stein
 * @date 2024/3/8
 */
@Service
public class EduSubjectServiceImpl
        extends ServiceImpl<EduSubjectMapper, EduSubjectPO>
        implements EduSubjectService {

    @Override
    public void saveSubjectFromExcel(MultipartFile file) {
        try {
            EasyExcel.read(
                            file.getInputStream(),
                            ExcelSubjectBO.class,
                            new ExcelSubjectListener(this)
                    )
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EduSubjectTreeDTO> treeListSubject() {
        // 查询所有的一级课程分类
        LambdaQueryWrapper<EduSubjectPO> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(EduSubjectPO::getParentId, 0);
        List<EduSubjectPO> subjectFirstList = list(lqw1);

        // 将所有的一级课程分类转换成 EduSubjectTreeDTO 列表
        List<EduSubjectTreeDTO> eduSubjectTreeDTOList =
                EduSubjectConverter.INSTANCE.subjectPOListToDTOList(subjectFirstList);

        // 遍历所有的一级课程分类，查询出每一个二级课程分类
        eduSubjectTreeDTOList.forEach(subjectFirstDTO -> {
            LambdaQueryWrapper<EduSubjectPO> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(EduSubjectPO::getParentId, subjectFirstDTO.getId());
            List<EduSubjectPO> subjectSecondList = list(lqw2);

            // 将所有的二级课程分类转换成 EduSubjectTreeDTO 列表
            List<EduSubjectTreeDTO> children =
                    EduSubjectConverter.INSTANCE.subjectPOListToDTOList(subjectSecondList);
            subjectFirstDTO.setChildren(children);
        });

        return eduSubjectTreeDTOList;
    }
}
