package com.ifzer.utils.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import com.github.crab2died.converter.WriteConvertible;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Created by nelson on 2018-04-19.
 */
public class ExcelWriteConv {
    public static class DateConv implements WriteConvertible{
        @Override
        public Object execWrite(Object object) {
            if (Objects.isNull(object)){
                return "";
            }
            return DateUtil.formatDateTime((Date) object);
        }
    }
}
