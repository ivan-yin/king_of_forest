package com.ifzer.modules.users.service.impl;

import com.ifzer.modules.users.entity.Users;
import com.ifzer.modules.users.mapper.UsersMapper;
import com.ifzer.modules.users.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类: UsersServiceImpl
 * </p>
 *
 * @author nelson
 * @since 2018-04-19
* @version 1.0
* @copyright ifzer.com
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
