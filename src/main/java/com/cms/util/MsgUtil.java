package com.cms.util;

import java.util.Map;

public class MsgUtil {

    private int code;
    private Map msg;

    private static MsgUtil msgUtil = new MsgUtil();

    public static MsgUtil error(){
        return msgUtil;
    }

    public static MsgUtil success(){
        return new MsgUtil(200,null);
    }

    public static MsgUtil add(String str, Map<String, Object> map){
        msgUtil.setCode(202);
        msgUtil.setMsg(map);
        return msgUtil;
    }

    public MsgUtil(){

    }

    public MsgUtil(int status, Map msg) {
        this.code = status;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int status) {
        this.code = status;
    }

    public Map getMsg() {
        return msg;
    }

    public void setMsg(Map msg) {
        this.msg = msg;
    }
}
