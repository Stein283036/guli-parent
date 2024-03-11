package org.stein.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.stein.listener.ExcelSubjectListener;
import org.stein.mapper.EduSubjectMapper;
import org.stein.pojo.bo.ExcelSubjectBO;
import org.stein.pojo.po.EduSubjectPO;
import org.stein.service.EduSubjectService;

import java.io.IOException;

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
            EasyExcel.read(file.getInputStream(), ExcelSubjectBO.class, new ExcelSubjectListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
