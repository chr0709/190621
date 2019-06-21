package com.example.demo.bean;

import java.util.List;

public class LoginBean {

    /**
     * code : 10000
     * msg : 请求成功
     * data : {"_id":"5cf35917ad68c43c3cd0258d","name":"200004055019","nickname":"WeYue-200004055019","password":"9f529ec5da73dc8a25c0306c94ac8c6f","token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiMjAwMDA0MDU1MDE5IiwiaWF0IjoxNTU5NDYxMTg2LCJleHAiOjE1NjIwNTMxODZ9.lC88gEbE5HMyfoikBjMz7Yy6ipuN6UlADVBHkA1Fwyk","icon":"/images/avatar/default_avatar.jpg","brief":"To be. or not to be. that is the question.","likebooks":[]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 5cf35917ad68c43c3cd0258d
         * name : 200004055019
         * nickname : WeYue-200004055019
         * password : 9f529ec5da73dc8a25c0306c94ac8c6f
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiMjAwMDA0MDU1MDE5IiwiaWF0IjoxNTU5NDYxMTg2LCJleHAiOjE1NjIwNTMxODZ9.lC88gEbE5HMyfoikBjMz7Yy6ipuN6UlADVBHkA1Fwyk
         * icon : /images/avatar/default_avatar.jpg
         * brief : To be. or not to be. that is the question.
         * likebooks : []
         */

        private String _id;
        private String name;
        private String nickname;
        private String password;
        private String token;
        private String icon;
        private String brief;
        private List<?> likebooks;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public List<?> getLikebooks() {
            return likebooks;
        }

        public void setLikebooks(List<?> likebooks) {
            this.likebooks = likebooks;
        }
    }
}
