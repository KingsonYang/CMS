package com.cms.util;

import java.util.Map;

public class MsgUtil {

    private int code;
    private Map msg;

    private static MsgUtil msgUtil = new MsgUtil();

    public static MsgUtil error(String str, Map<String, Object> map){
        msgUtil.setCode(404);
        msgUtil.setMsg(map);
        return msgUtil;
    }

    public static MsgUtil error(){
        msgUtil.setCode(404);
        return msgUtil;
    }

    public static MsgUtil success(){
        msgUtil.setCode(200);
        return msgUtil;
    }

    public static MsgUtil add(String str, Map<String, Object> map){
        msgUtil.setMsg(map);
        return msgUtil;
    }

    public MsgUtil(){

    }

    public MsgUtil(int code, Map msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map getMsg() {
        return msg;
    }

    public void setMsg(Map msg) {
        this.msg = msg;
    }
}
