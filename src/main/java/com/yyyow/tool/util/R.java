package com.yyyow.tool.util;

import java.util.HashMap;

public final class R extends HashMap {

    private final static int ERRORCODE = -1;

    private final static int SUCCESSCODE = 0;

    private final static String ERRORMSG = "error";

    private final static String SUCCESSMSG = "success";

    private R() {
    }

    /**
     * 通用错误返回
     *
     * @return
     */
    public static R error() {
        R r = new R();
        r.put("code", ERRORCODE);
        r.put("msg", ERRORMSG);
        return r;
    }

    /**
     * 自定义code msg
     *
     * @param code 错误码
     * @param msg  错误信息
     * @return
     */
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 自定义code码
     *
     * @param code
     * @return
     */
    public static R error(int code) {
        R r = new R();
        r.put("code", code);
        r.put("msg", ERRORMSG);
        return r;
    }

    /**
     * 自定义msg
     *
     * @param msg
     * @return
     */
    public static R error(String msg) {
        R r = new R();
        r.put("code", ERRORCODE);
        r.put("msg", msg);
        return r;
    }

    /**
     * 成功返回信息
     * @return
     */
    public static R success (){
        R r = new R();
        r.put("code", SUCCESSCODE);
        r.put("msg", SUCCESSMSG);
        return r;
    }

    /**
     * 自定义code msg 追加data
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static R success (int code ,String msg ,Object data){
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data",data);
        return r;
    }

    /**
     * 成功追加data
     * @param data
     * @return
     */
    public static R success (Object data){
        R r = new R();
        r.put("code", SUCCESSCODE);
        r.put("msg", SUCCESSMSG);
        r.put("data",data);
        return r;
    }


}
