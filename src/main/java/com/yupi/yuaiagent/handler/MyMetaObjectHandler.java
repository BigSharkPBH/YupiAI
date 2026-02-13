package com.yupi.yuaiagent.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatis-Plus 字段自动填充处理器
 * 负责给 createTime 等字段自动赋值
 */
@Component // 必须加@Component，让Spring扫描到这个类！
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入操作时自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 核心：给 createTime 字段填充当前时间
        // 参数说明：
        // 1. metaObject：元对象（MyBatis-Plus 内部对象）
        // 2. "createTime"：实体类中的字段名（必须和实体类一致，大小写敏感）
        // 3. Date.class：字段类型
        // 4. new Date()：要填充的值（当前系统时间）
        this.strictInsertFill(
                metaObject,
                "createTime",
                Date.class,
                new Date()
        );
        this.strictInsertFill(
                metaObject,
                "updateTime",
                Date.class,
                new Date()
        );
    }

    /**
     * 更新操作时自动填充（可选，比如 updateTime 字段）
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 如果有 updateTime 字段，可在这里配置：
        // this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictUpdateFill(
                metaObject,
                "updateTime",
                Date.class,
                new Date()
        );
    }
}