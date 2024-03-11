package org.stein;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author stein
 * @date 2024/3/8
 */
@Data
public class ExcelReadModel {
    @ExcelProperty(index = 0, value = "学生编号")
    private Integer id;
    @ExcelProperty(index = 1, value = "学生姓名")
    private String name;
}
