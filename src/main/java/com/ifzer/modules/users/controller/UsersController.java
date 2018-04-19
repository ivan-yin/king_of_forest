package com.ifzer.modules.users.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.crab2died.ExcelUtils;
import com.ifzer.common.RespData;
import com.ifzer.modules.users.entity.Users;
import com.ifzer.modules.users.service.IUsersService;
import com.ifzer.utils.ExcelExportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器: UsersController
 * </p>
 * @author nelson
 * @since 2018-04-19
 * @version 1.0
 * @copyright ifzer.com
 */
@Controller
@RequestMapping("/users/")
public class UsersController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
//    @Qualifier("userService")
    private IUsersService usersService;

    @GetMapping("list")
    @ResponseBody
    public RespData list(){
        RespData respData = new RespData();
        final List<Users> users = usersService.selectList(new EntityWrapper<>());
        respData.setListData(users);
        return respData;

    }

    @GetMapping("page")
    @ResponseBody
    public RespData page(@RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "10")  int size){
        RespData respData = new RespData();
        final Page<Users> userPage = usersService.selectPage(new Page<>(page, size));
        respData.setPageData(userPage);
        return respData;

    }

    @GetMapping("export")
    public void export(HttpServletResponse response){
        final List<Users> users = usersService.selectList(new EntityWrapper<>());
        try {
            ExcelExportUtil.exportObjects2Excel(users, Users.class, response);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
    }

}
