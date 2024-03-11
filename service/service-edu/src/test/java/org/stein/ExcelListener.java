package org.stein;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author stein
 * @date 2024/3/8
 */
public class ExcelListener extends AnalysisEventListener<ExcelReadModel> {

    @Override
    public void invoke(ExcelReadModel excelReadModel, AnalysisContext analysisContext) {
        System.out.println("***" + excelReadModel + "***");
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("headMap = " + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
