```


DELIMITER $$

DROP PROCEDURE IF EXISTS TEST;

CREATE PROCEDURE TEST()
BEGIN
    DECLARE ID VARCHAR(20);     -- 可以声明 ID 作为变量名
    DECLARE CID VARCHAR(20);    -- 声明其他变量
    DECLARE YWYT VARCHAR(20);
    DECLARE done INT DEFAULT 0;
    
    -- 游标定义
    DECLARE CUR CURSOR FOR SELECT ID, COST_ID FROM cost_package_details;
    
    -- 处理游标结束的条件
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
    
    -- 打开游标
    OPEN CUR;
    
    -- 游标循环
    READ_LOOP: LOOP
        -- 获取游标当前行的数据
        FETCH CUR INTO ID, CID;  -- 使用 ID 和 CID 获取数据

        -- 如果到达游标的最后一行，退出循环
        IF done THEN
            LEAVE READ_LOOP;
        END IF;

        -- 调试输出：打印当前 ID 和 CID
        SELECT CONCAT('Processing ID: ', ID, ', CID: ', CID) AS debug_info;

        -- 使用 CID 查询 cost_base 表，并将结果存入 YWYT 变量中
        SELECT cost.biz_purpose INTO YWYT FROM cost_base cost WHERE cost.id = CID;

        -- 调试输出：打印查询结果
        SELECT CONCAT('Query Result - YWYT: ', YWYT) AS query_result;

        -- 使用 CID 更新 cost_base 表中的字段，例如 status 和 biz_purpose
        UPDATE cost_base
        SET status = 'processed', biz_purpose = YWYT
        WHERE id = CID;

        -- 调试输出：显示更新操作已执行
        SELECT CONCAT('Updated cost_base for CID: ', CID) AS update_info;

    END LOOP READ_LOOP;

    -- 关闭游标
    CLOSE CUR;
END $$

DELIMITER ;

```

​    

    
