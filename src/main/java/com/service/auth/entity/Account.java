package com.service.auth.entity;

/**
 * 项目名称：SimpleSpringCloudGateway
 * 包名称:com.service.auth.entity
 * 类描述：
 * 创建人：hejian
 * 创建时间：2019/6/27 17:35
 * 修改人：hejian
 * 修改时间：2019/6/27 17:35
 * 修改备注：
 *
 * @author hejian
 */
public class Account {

    private String username;

    private String pwd;

    private String fsRole;

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getPwd() {
        return pwd;
    }

    public final void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public final String getFsRole() {
        return fsRole;
    }

    public final void setFsRole(String fsRole) {
        this.fsRole = fsRole;
    }
}
