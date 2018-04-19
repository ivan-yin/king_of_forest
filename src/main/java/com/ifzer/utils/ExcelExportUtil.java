package com.ifzer.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.github.crab2died.converter.DefaultConvertible;
import com.github.crab2died.converter.WriteConvertible;
import com.github.crab2died.handler.ExcelHeader;
import com.github.crab2died.utils.Utils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by nelson on 2018-04-19.
 */
public class ExcelExportUtil {

    public static void exportObjects2Excel(List<?> data, Class clazz, HttpServletResponse response) throws Exception {
        String fileName = new String(("export-"+ clazz.getSimpleName() + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss")).getBytes(), "utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
//        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        exportExcelNoModuleHandler(data, clazz, true, null, true)
                .write(response.getOutputStream());
    }
    private static Workbook exportExcelNoModuleHandler(List<?> data, Class clazz, boolean isWriteHeader,
                                                String sheetName, boolean isXSSF) throws Exception {

        Workbook workbook;
        if (isXSSF) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }
        Sheet sheet;
        if (null != sheetName && !"".equals(sheetName)) {
            sheet = workbook.createSheet(sheetName);
        } else {
            sheet = workbook.createSheet();
        }
        Row row = sheet.createRow(0);
        List<ExcelHeader> headers = Utils.getHeaderList(clazz);
        if (isWriteHeader) {
            // 写标题
            for (int i = 0; i < headers.size(); i++) {
                row.createCell(i).setCellValue(headers.get(i).getTitle());
            }
        }
        // 写数据
        Object _data;
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i + 1);
            _data = data.get(i);
            for (int j = 0; j < headers.size(); j++) {
                final String filed = headers.get(j).getFiled();
                row.createCell(j).setCellValue(getProperty(_data, filed,
                        headers.get(j).getWriteConverter()));
            }
        }
        return workbook;
    }


    /**
     * 根据属性名与属性类型获取字段内容
     *
     * @param bean             对象
     * @param fieldName        字段名
     * @param writeConvertible 写入转换器
     * @return 对象指定字段内容
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException    异常
     * @throws IntrospectionException    异常
     */
    public static String getProperty(Object bean, String fieldName, WriteConvertible writeConvertible)
            throws InvocationTargetException, IllegalAccessException, IntrospectionException {

        if (bean == null || fieldName == null){
            throw new IllegalArgumentException("Operating bean or filed class must not be null");
        }
        final Field field = ReflectUtil.getField(bean.getClass(), fieldName);
        final Class<?> type = field.getType();
        Method method = ReflectUtil.getMethod(bean.getClass(), fieldName);
//        Method method = getterOrSetter(bean.getClass(), fieldName, Utils.FieldAccessType.GETTER);
        Object object = method.invoke(bean);
        if (type == Date.class || type == Timestamp.class){
            return new ExcelWriteConv.DateConv().execWrite(object).toString();
        }
        if (null != writeConvertible && writeConvertible.getClass() != DefaultConvertible.class) {
            // 写入转换器
            object = writeConvertible.execWrite(object);
        }
        return object == null ? "" : object.toString();
    }

}
