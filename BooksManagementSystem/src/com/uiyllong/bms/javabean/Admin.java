package com.uiyllong.bms.javabean;

/**
 * Created by uilong on 2016/5/25.
 */
public class Admin {

    private Integer admin_id;
    private String password;
    private AdminInfo admininfo;

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminInfo getAdmininfo() {
        return admininfo;
    }

    public void setAdmininfo(AdminInfo admininfo) {
        this.admininfo = admininfo;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", password='" + password + '\'' +
                ", admininfo=" + admininfo +
                '}';
    }
}
