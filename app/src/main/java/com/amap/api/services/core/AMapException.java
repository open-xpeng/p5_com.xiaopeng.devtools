package com.amap.api.services.core;

import android.support.v4.media.MediaPlayer2;
import com.alibaba.sdk.android.man.util.MANConfig;
import com.xiaopeng.lib.framework.moduleinterface.appresourcemodule.IAppResourceException;

/* loaded from: classes11.dex */
public class AMapException extends Exception {
    private String a;
    private int b;

    public AMapException(String str) {
        super(str);
        this.a = "";
        this.b = 1000;
        this.a = str;
        a(str);
    }

    public AMapException() {
        this.a = "";
        this.b = 1000;
    }

    public String getErrorMessage() {
        return this.a;
    }

    public int getErrorCode() {
        return this.b;
    }

    private void a(String str) {
        if ("用户签名未通过".equals(str)) {
            this.b = 1001;
        } else if ("用户key不正确或过期".equals(str)) {
            this.b = 1002;
        } else if ("请求服务不存在".equals(str)) {
            this.b = 1003;
        } else if ("访问已超出日访问量".equals(str)) {
            this.b = 1004;
        } else if ("用户访问过于频繁".equals(str)) {
            this.b = MediaPlayer2.MEDIAPLAYER2_STATE_ERROR;
        } else if ("用户IP无效".equals(str)) {
            this.b = 1006;
        } else if ("用户域名无效".equals(str)) {
            this.b = 1007;
        } else if ("用户MD5安全码未通过".equals(str)) {
            this.b = 1008;
        } else if ("请求key与绑定平台不符".equals(str)) {
            this.b = 1009;
        } else if ("IP访问超限".equals(str)) {
            this.b = 1010;
        } else if ("服务不支持https请求".equals(str)) {
            this.b = 1011;
        } else if ("权限不足，服务请求被拒绝".equals(str)) {
            this.b = 1012;
        } else if ("开发者删除了key，key被删除后无法正常使用".equals(str)) {
            this.b = 1013;
        } else if ("请求服务响应错误".equals(str)) {
            this.b = 1100;
        } else if ("引擎返回数据异常".equals(str)) {
            this.b = 1101;
        } else if ("服务端请求链接超时".equals(str)) {
            this.b = 1102;
        } else if ("读取服务结果超时".equals(str)) {
            this.b = 1103;
        } else if ("请求参数非法".equals(str)) {
            this.b = 1200;
        } else if ("缺少必填参数".equals(str)) {
            this.b = 1201;
        } else if ("请求协议非法".equals(str)) {
            this.b = 1202;
        } else if ("其他未知错误".equals(str)) {
            this.b = 1203;
        } else if ("协议解析错误 - ProtocolException".equals(str)) {
            this.b = 1801;
        } else if ("socket 连接超时 - SocketTimeoutException".equals(str)) {
            this.b = 1802;
        } else if ("url异常 - MalformedURLException".equals(str)) {
            this.b = 1803;
        } else if ("未知主机 - UnKnowHostException".equals(str)) {
            this.b = 1804;
        } else if ("未知错误".equals(str)) {
            this.b = 1900;
        } else if ("无效的参数 - IllegalArgumentException".equals(str)) {
            this.b = 1901;
        } else if ("http或socket连接失败 - ConnectionException".equals(str)) {
            this.b = 1806;
        } else if ("IO 操作异常 - IOException".equals(str)) {
            this.b = 1902;
        } else if ("空指针异常 - NullPointException".equals(str)) {
            this.b = 1903;
        } else if ("tableID格式不正确不存在".equals(str)) {
            this.b = 2000;
        } else if ("ID不存在".equals(str)) {
            this.b = IAppResourceException.REASON_FILE_NOT_FOUND;
        } else if ("服务器维护中".equals(str)) {
            this.b = IAppResourceException.REASON_MGR_DB_ERROR;
        } else if ("key对应的tableID不存在".equals(str)) {
            this.b = 2003;
        } else if ("找不到对应的userid信息,请检查您提供的userid是否存在".equals(str)) {
            this.b = 2100;
        } else if ("App key未开通“附近”功能,请注册附近KEY".equals(str)) {
            this.b = 2101;
        } else if ("已开启自动上传".equals(str)) {
            this.b = 2200;
        } else if ("USERID非法".equals(str)) {
            this.b = 2201;
        } else if ("NearbyInfo对象为空".equals(str)) {
            this.b = 2202;
        } else if ("两次单次上传的间隔低于7秒".equals(str)) {
            this.b = 2203;
        } else if ("Point为空，或与前次上传的相同".equals(str)) {
            this.b = 2204;
        } else if ("规划点（包括起点、终点、途经点）不在中国陆地范围内".equals(str)) {
            this.b = 3000;
        } else if ("规划点（起点、终点、途经点）附近搜不到路".equals(str)) {
            this.b = IAppResourceException.REASON_NO_PERMISSION;
        } else if ("路线计算失败，通常是由于道路连通关系导致".equals(str)) {
            this.b = 3002;
        } else if ("起点终点距离过长".equals(str)) {
            this.b = MANConfig.NETWORK_PAGELOAD_EVENT_ID;
        } else if ("短串分享认证失败".equals(str)) {
            this.b = 4000;
        } else if ("短串请求失败".equals(str)) {
            this.b = IAppResourceException.REASON_BINDER_FAILED;
        } else {
            this.b = 1800;
        }
    }
}
