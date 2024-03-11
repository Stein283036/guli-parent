package org.stein.pojo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author stein
 * @date 2024/3/8
 */
@Data
public class ExcelSubjectBO {
    @ExcelProperty(index = 0, value = "一级分类")
    private String subjectFirst;

    @ExcelProperty(index = 1, value = "二级分类")
    private String subjectSecond;
}
