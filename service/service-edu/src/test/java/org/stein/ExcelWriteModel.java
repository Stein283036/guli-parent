package org.stein;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author stein
 * @date 2024/3/8
 */
@Data
public class ExcelWriteModel {
    @ExcelProperty("学生编号")
    private Integer id;
    @ExcelProperty("学生姓名")
    private String name;
}
