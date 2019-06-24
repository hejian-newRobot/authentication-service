package com.service.auth.serviceauth.dto;

/**
 * 项目名称：SimpleSpringCloudGateway
 * 包名称:com.learn.user.dao
 * 类描述：
 * 创建人：hejian
 * 创建时间：2019/6/20 10:12
 * 修改人：hejian
 * 修改时间：2019/6/20 10:12
 * 修改备注：
 *
 * @author hejian
 */


import com.service.auth.serviceauth.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 账户数据库操作类
 * MongoDB操作接口
 */
@Component
@Mapper
public interface UserDao {

    /**
     * 根据用户名查找账户信息
     *
     * @param username 用户名
     * @return 账户信息
     */
    User findByUserName(@Param("userName") String username);

    void deleteAll();

    void save(@Param("user") User user);
}
