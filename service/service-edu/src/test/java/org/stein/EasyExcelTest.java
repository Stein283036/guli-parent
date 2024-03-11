package org.stein;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stein
 * @date 2024/3/8
 */
@SpringBootTest
public class EasyExcelTest {
    @Test
    public void testEasyExcelRead() {
        String filename = "src/test/resources/demo2.xlsx";
        EasyExcel.read(filename, ExcelReadModel.class, new ExcelListener()).sheet("demo2").doRead();
    }

    @Test
    public void testEasyExcelWrite() {
        String filename = "src/test/resources/demo2.xlsx";

        List<ExcelWriteModel> data = new ArrayList<>();
        ExcelWriteModel s1 = new ExcelWriteModel();
        s1.setId(1);
        s1.setName("Stein");
        ExcelWriteModel s2 = new ExcelWriteModel();
        s2.setId(2);
        s2.setName("Tommy");
        ExcelWriteModel s3 = new ExcelWriteModel();
        s3.setId(3);
        s3.setName("Jame");
        data.add(s1);
        data.add(s2);
        data.add(s3);

        EasyExcel.write(filename, ExcelWriteModel.class).sheet("demo2").doWrite(data);
    }
}
