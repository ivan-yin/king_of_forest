package com.ifzer.common;

import com.ifzer.utils.excel.ExcelExportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 基类控制器
 * Created by ybs on 18/4/19.
 */
@Controller
@RequestMapping
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected void export(List datalist, Class clz , HttpServletResponse response){
        try {
            ExcelExportUtil.exportObjects2Excel(datalist, clz, response);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            try {
                response.getWriter().write(e.getMessage());
            } catch (IOException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
        }
    }
}
