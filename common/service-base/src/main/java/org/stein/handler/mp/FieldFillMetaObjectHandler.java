package org.stein.handler.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author stein
 * @date 2024/3/5
 */
@Component
public class FieldFillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("gmtCreate", LocalDateTime.now(), metaObject)
                .setFieldValByName("gmtModified", LocalDateTime.now(), metaObject)
                .setFieldValByName("version", 1, metaObject)
                .setFieldValByName("isDeleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }
}
