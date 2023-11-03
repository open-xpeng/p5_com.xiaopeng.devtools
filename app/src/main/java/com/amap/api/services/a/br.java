package com.amap.api.services.a;

import com.alibaba.sdk.android.man.util.MANConfig;
import com.amap.api.services.core.AMapException;
import com.xiaopeng.commonfunc.bean.event.CommonEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CoreUtil.java */
/* loaded from: classes11.dex */
public class br {
    public static void b(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                return;
            }
            String string = jSONObject.getString("status");
            if (string.equals("1")) {
                return;
            }
            if (string.equals("0") && !jSONObject.has("infocode")) {
                throw new AMapException("未知错误");
            }
            int i = jSONObject.getInt("infocode");
            if (string.equals("0")) {
                if (i != 22000) {
                    switch (i) {
                        case 10001:
                            throw new AMapException("用户key不正确或过期");
                        case 10002:
                            throw new AMapException("请求服务不存在");
                        case CommonEvent.DIAGNOSIS_DISMISS_LOADING_DIALOG /* 10003 */:
                            throw new AMapException("访问已超出日访问量");
                        case CommonEvent.DIAGNOSIS_LOGICTREE_INIT_FINISH /* 10004 */:
                            throw new AMapException("用户访问过于频繁");
                        case CommonEvent.DISPLAY_ONE_CLICK_DIAGNOSIS_RUNNING /* 10005 */:
                            throw new AMapException("用户IP无效");
                        case CommonEvent.DISPLAY_LOGICTREE_TOAST /* 10006 */:
                            throw new AMapException("用户域名无效");
                        case CommonEvent.UPGRADE_LOGICTREE_FAIL /* 10007 */:
                            throw new AMapException("用户签名未通过");
                        case CommonEvent.UPGRADE_LOGICTREE_SUCCESS /* 10008 */:
                            throw new AMapException("用户MD5安全码未通过");
                        case CommonEvent.REMOTE_ONE_CLICK_DIAGNOSIS_INIT_FINISH /* 10009 */:
                            throw new AMapException("请求key与绑定平台不符");
                        case CommonEvent.REMOTE_ONE_CLICK_DIAGNOSIS_RESULT /* 10010 */:
                            throw new AMapException("IP访问超限");
                        case CommonEvent.REMOTE_ONE_CLICK_DIAGNOSIS_INTERACTIVE /* 10011 */:
                            throw new AMapException("服务不支持https请求");
                        case CommonEvent.REMOTE_ONE_CLICK_DIAGNOSIS_LOG_UPLOAD /* 10012 */:
                            throw new AMapException("权限不足，服务请求被拒绝");
                        case CommonEvent.REMOTE_ONE_CLICK_DIAGNOSIS_LOG_UPLOAD_PASSWORD /* 10013 */:
                            throw new AMapException("开发者删除了key，key被删除后无法正常使用");
                        default:
                            switch (i) {
                                case 20000:
                                    throw new AMapException("请求参数非法");
                                case 20001:
                                    throw new AMapException("缺少必填参数");
                                case 20002:
                                    throw new AMapException("请求协议非法");
                                case 20003:
                                    throw new AMapException("其他未知错误");
                                default:
                                    switch (i) {
                                        case 20800:
                                            throw new AMapException("规划点（包括起点、终点、途经点）不在中国陆地范围内");
                                        case 20801:
                                            throw new AMapException("规划点（起点、终点、途经点）附近搜不到路");
                                        case 20802:
                                            throw new AMapException("路线计算失败，通常是由于道路连通关系导致");
                                        case 20803:
                                            throw new AMapException("起点终点距离过长");
                                        default:
                                            switch (i) {
                                                case MANConfig.AGGREGATION_INTERVAL /* 30000 */:
                                                    throw new AMapException("请求服务响应错误");
                                                case 30001:
                                                    throw new AMapException("引擎返回数据异常");
                                                case 30002:
                                                    throw new AMapException("服务端请求链接超时");
                                                case 30003:
                                                    throw new AMapException("读取服务结果超时");
                                                default:
                                                    switch (i) {
                                                        case 32000:
                                                            throw new AMapException("key对应的tableID不存在");
                                                        case 32001:
                                                            throw new AMapException("ID不存在");
                                                        case 32002:
                                                            throw new AMapException("服务器维护中");
                                                        default:
                                                            switch (i) {
                                                                case 32200:
                                                                    throw new AMapException("找不到对应的userid信息,请检查您提供的userid是否存在");
                                                                case 32201:
                                                                    throw new AMapException("App key未开通“附近”功能,请注册附近KEY");
                                                                default:
                                                                    throw new AMapException(jSONObject.getString("info"));
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
                }
                throw new AMapException("tableID格式不正确不存在");
            }
        } catch (JSONException e) {
            a(e, "CoreUtil", "paseAuthFailurJson");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static String a(Date date) {
        return date != null ? new SimpleDateFormat("HH:mm").format(date) : "";
    }

    public static Date M(String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        try {
            return new SimpleDateFormat("HH:mm").parse(str);
        } catch (ParseException e) {
            a(e, "CoreUtil", "parseTime");
            return null;
        }
    }

    public static void a(Throwable th, String str, String str2) {
        r bn = r.bn();
        if (bn != null) {
            bn.c(th, str, str2);
        }
        th.printStackTrace();
    }
}
