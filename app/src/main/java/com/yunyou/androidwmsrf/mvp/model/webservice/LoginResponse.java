package com.yunyou.androidwmsrf.mvp.model.webservice;

import java.io.Serializable;

/**
 * @author WMJ
 * @version 2019/07/01
 */
public class LoginResponse extends BaseResponse implements Serializable {
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body implements Serializable {
        private String username;
        private String name;
        private boolean mobileLogin;
        private String JSESSIONID;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isMobileLogin() {
            return mobileLogin;
        }

        public void setMobileLogin(boolean mobileLogin) {
            this.mobileLogin = mobileLogin;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
    }
}