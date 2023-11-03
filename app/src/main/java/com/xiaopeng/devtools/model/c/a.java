package com.xiaopeng.devtools.model.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: FactoryCodeGroupModel.java */
/* loaded from: classes12.dex */
public class a {
    public static final String[] rX = {"9925", "9723", "4227", "9387", "7494", "9444"};
    private List<b> rY = new ArrayList();
    private String rZ;
    private String sa;
    private int sb;
    private boolean sc;

    a() {
    }

    public List<b> fB() {
        return this.rY;
    }

    public boolean fC() {
        return this.sc;
    }

    public void E(boolean z) {
        this.sc = z;
    }

    public int fD() {
        return this.sb;
    }

    public b bp(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        for (int i = 0; i < this.rY.size(); i++) {
            if (str.equals(this.rY.get(i).fF())) {
                return this.rY.get(i);
            }
        }
        return null;
    }

    public static Map<String, a> fE() {
        HashMap hashMap = new HashMap();
        a aVar = new a();
        aVar.rY.add(new b("*#4227*111#*", "中控进入工厂测试", true, false, true, true));
        aVar.rY.add(new b("*#4227*2#*", "整车工厂测试", true, true, false, true));
        aVar.sa = rX[2];
        aVar.rZ = "工厂测试类";
        aVar.sb = 2;
        hashMap.put(aVar.sa, aVar);
        a aVar2 = new a();
        aVar2.rY.add(new b("*#9387*111#*", "回到主桌面", true, false, true, true));
        aVar2.rY.add(new b("*#9387*121#*", "语音识别测试模块", true, false, true, true));
        aVar2.rY.add(new b("*#9387*122#*", "AIOS设置", true, false, true, true));
        aVar2.rY.add(new b("*#9387*123#*", "诊断故障码", true, true, false, true));
        aVar2.rY.add(new b("*#9387*131#*", "打开抓取日志功能,打开串口服务及重启设备", true, true, false, true));
        aVar2.rY.add(new b("*#9387*132#*", "恢复出厂设置，重启设备", true, true, false, true));
        aVar2.rY.add(new b("*#9387*133#*", "预发布环境配置", true, false, true, true));
        aVar2.rY.add(new b("*#9387*134#*", "CAMERA视频文件拷贝", true, true, false, true));
        aVar2.rY.add(new b("*#9387*135#*", "配置CFC", true, false, true, true));
        aVar2.rY.add(new b("*#9387*141#*", "设置一些系统功能", true, true, false, true));
        aVar2.rY.add(new b("*#9387*142#*", "GPS NMEA数据抓取功能", true, true, false, true));
        aVar2.rY.add(new b("*#9387*143#*", "4G APN切换功能", true, true, false, true));
        aVar2.rY.add(new b("*#9387*151#*", "OTA,U盘升级", true, true, false, true));
        aVar2.rY.add(new b("*#9387*161#*", "PC工具服务", true, true, false, true));
        aVar2.rY.add(new b("*#9387*171#*", "一键诊断修复功能", true, true, false, true));
        aVar2.rY.add(new b("*#9387*211#*", "进入智能驾驶测试功能", true, false, true, true));
        aVar2.rY.add(new b("*#9387*212#*", "进入心率检测功能", true, true, true, true));
        aVar2.rY.add(new b("*#9387*213#*", "NEDC模式", true, true, false, true));
        aVar2.rY.add(new b("*#9387*214#*", "XPU激活", true, true, false, true));
        aVar2.rY.add(new b("*#9387*215#*", "车顶摄像头", true, true, false, true));
        aVar2.rY.add(new b("*#9387*2#*", "XPU检测功能", true, true, true, true));
        aVar2.rY.add(new b("*#9387*315#*", "ALS标定功能", true, true, true, true));
        aVar2.rY.add(new b("*#9387*321#*", "工厂模式", true, true, false, true));
        aVar2.rY.add(new b("*#9387*311#*", "进入硬件测试功能", true, false, true, true));
        aVar2.rY.add(new b("*#9387*312#*", "进入MODEM UI功能", true, false, true, true));
        aVar2.rY.add(new b("*#9387*313#*", "硬件DV验证", true, false, true, true));
        aVar2.rY.add(new b("*#9387*314#*", "CAN报文获取", true, true, false, true));
        aVar2.rY.add(new b("*#9387*411#*", "设置驾驶模式功能", true, true, false, true));
        aVar2.rY.add(new b("*#9387*511#*", "离线地图拷贝功能", true, true, false, true));
        aVar2.sa = rX[3];
        aVar2.rZ = "研发调试类";
        aVar2.sb = 3;
        hashMap.put(aVar2.sa, aVar2);
        a aVar3 = new a();
        aVar3.rY.add(new b("*#9925*111#*", "Xmart、MCU 系统及硬件版本号 uniqueID", true, true, true, true));
        aVar3.rY.add(new b("*#9925*121#*", "查看各应用的版本号", true, true, false, true));
        aVar3.rY.add(new b("*#9925*131#*", "设备唯一码信息", true, true, false, true));
        aVar3.rY.add(new b("*#9925*211#*", "显示各 ECU 版本号信息", true, true, false, true));
        aVar3.sa = rX[0];
        aVar3.rZ = "信息查看类";
        aVar3.sb = 0;
        hashMap.put(aVar3.sa, aVar3);
        a aVar4 = new a();
        aVar4.rY.add(new b("*#9723*111#*", "OLED测试模式", true, true, true, true));
        aVar4.rY.add(new b("*#9723*121#*", "展车模式", true, true, true, true));
        aVar4.rY.add(new b("*#9723*122#*", "试驾模式", true, true, true, true));
        aVar4.rY.add(new b("*#9723*131#*", "AI宣传视频", true, true, false, true));
        aVar4.rY.add(new b("*#9723*211#*", "国标检测", true, true, true, true));
        aVar4.sa = rX[1];
        aVar4.rZ = "演示菜单类";
        aVar4.sb = 1;
        hashMap.put(aVar4.sa, aVar4);
        a aVar5 = new a();
        aVar5.rY.add(new b("*#7494*111#*", "售后重置功能", true, true, false, true));
        aVar5.rY.add(new b("*#7494*121#*", "售后维修模式", true, true, false, true));
        aVar5.rY.add(new b("*#8#*", "售后工具", true, true, true, true));
        aVar5.rY.add(new b("*#400#*", "授权模式", true, true, true, true));
        aVar5.sa = rX[4];
        aVar5.rZ = "售后服务类";
        aVar5.sb = 4;
        hashMap.put(aVar5.sa, aVar5);
        a aVar6 = new a();
        aVar6.rY.add(new b("*#1224#*", "平安夜", true, true, true, true));
        aVar6.rY.add(new b("*#1225#*", "圣诞节", true, true, true, true));
        aVar6.rY.add(new b("*#0101#*", "元旦", true, true, true, true));
        aVar6.sa = rX[5];
        aVar6.rZ = "用户关怀类";
        aVar6.sb = 5;
        hashMap.put(aVar6.sa, aVar6);
        return hashMap;
    }
}
