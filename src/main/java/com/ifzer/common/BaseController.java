package com.ifzer.common;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.github.crab2died.annotation.ExcelField;
import com.github.crab2died.handler.ExcelHeader;
import com.ifzer.utils.excel.ExcelExportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 基类控制器
 * Created by ybs on 18/4/19.
 */
@Controller
@RequestMapping
public class BaseController<Entity extends Serializable> {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected Class<Entity> eClz;

    @SuppressWarnings("unchecked")
    protected BaseController() {
        final Class<? extends BaseController> clz = getClass();
        Type genType = clz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            LOGGER.warn(clz.getSimpleName() + "'s superclass not ParameterizedType");
//            eClz = (Class<Entity>) Object.class;
        }else {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (!(params[0] instanceof Class)) {
                LOGGER.warn(clz.getSimpleName() + " not set the actual class on superclass generic parameter");
//                eClz = (Class<Entity>) Object.class;
            }else {
                eClz = (Class<Entity>) params[0];
            }
        }
    }


    @Autowired
    private IService<Entity> service;

    protected void export(List datalist, Class clz, HttpServletResponse response) {
        try {
            ExcelExportUtil.exportObjects2Excel(datalist, clz, response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            try {
                response.getWriter().write(e.getMessage());
            } catch (IOException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
        }
    }

    @GetMapping("export")
    public void export(HttpServletResponse response) {
        final List<Entity> datas = service.selectList(new EntityWrapper<Entity>());
        export(datas, eClz, response);
    }


    /**
     * <p>根据JAVA对象注解获取Excel表头信息</p>
     *
     * @param clz 类型
     * @return 表头信息
     */
    protected List<ExcelHeader> getHeaderList(Class<?> clz) {
        List<ExcelHeader> headers = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        for (Class<?> clazz = clz; clazz != Object.class; clazz = clazz.getSuperclass()) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        for (Field field : fields) {
            // 是否使用ExcelField注解
            if (field.isAnnotationPresent(ExcelField.class)) {
                ExcelField er = field.getAnnotation(ExcelField.class);
                headers.add(new ExcelHeader(er.title(), er.order(),
                        null, null,
                        field.getName(), field.getType()));
            }
        }
        Collections.sort(headers);
        return headers;
    }


    @GetMapping("pageJson")
    @ResponseBody
    protected JquiPage pageJson(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int rows,
                                String sort, String order) {
        final boolean asc = StrUtil.equalsIgnoreCase("asc", order);
        return JquiPage.fromMyBatisPage(service.selectPage(new Page<>(page, rows, sort, asc)));
    }

}
