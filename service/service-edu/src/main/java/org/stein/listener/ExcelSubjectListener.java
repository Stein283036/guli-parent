package org.stein.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Component;
import org.stein.pojo.bo.ExcelSubjectBO;
import org.stein.pojo.po.EduSubjectPO;
import org.stein.service.EduSubjectService;

import java.util.Objects;

/**
 * @author stein
 * @date 2024/3/8
 */
@Component
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectBO> {

    private final EduSubjectService eduSubjectService;

    public ExcelSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelSubjectBO excelSubjectBO, AnalysisContext analysisContext) {
        String subjectFirst = excelSubjectBO.getSubjectFirst();
        String subjectSecond = excelSubjectBO.getSubjectSecond();

        EduSubjectPO subjectFirstExists = isSubjectFirstExists(subjectFirst);
        if (subjectFirstExists == null) {
            EduSubjectPO eduSubjectPO = new EduSubjectPO();
            eduSubjectPO.setTitle(subjectFirst);
            eduSubjectPO.setParentId("0");
            eduSubjectService.save(eduSubjectPO);
        }

        EduSubjectPO subjectSecondExists = isSubjectSecondExists(subjectSecond);
        if (subjectSecondExists == null) {
            EduSubjectPO eduSubjectPO = new EduSubjectPO();
            eduSubjectPO.setTitle(subjectSecond);
            eduSubjectPO.setParentId(Objects.requireNonNull(subjectFirstExists).getId());
            eduSubjectService.save(eduSubjectPO);
        }
    }

    private EduSubjectPO isSubjectFirstExists(String subjectFirst) {
        LambdaQueryWrapper<EduSubjectPO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduSubjectPO::getTitle, subjectFirst).eq(EduSubjectPO::getParentId, 0);
        return eduSubjectService.getOne(lqw);
    }

    private EduSubjectPO isSubjectSecondExists(String subjectSecond) {
        LambdaQueryWrapper<EduSubjectPO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EduSubjectPO::getTitle, subjectSecond);
        return eduSubjectService.getOne(lqw);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
