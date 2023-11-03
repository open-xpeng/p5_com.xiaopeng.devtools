package com.xiaopeng.lib.framework.moduleinterface.carcontroller.v2extend;

import com.xiaopeng.lib.framework.moduleinterface.carcontroller.AbstractEventMsg;

/* loaded from: classes12.dex */
public interface IScuController {

    /* loaded from: classes12.dex */
    public static class AllParklotDataEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class ErrorTipsUpdateEventMsg extends AbstractEventMsg<Integer> {
    }

    /* loaded from: classes12.dex */
    public static class LeftAvmDataUpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class RightAvmDataUpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class SensorDataUpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class SlotForPatkUpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2AltimeterEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2FrontRadarDataEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2FrontRadarFaultStEventMsg extends AbstractEventMsg<int[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2FrontRadarLevelEventMsg extends AbstractEventMsg<int[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2ScuAvmBox1UpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2ScuAvmBox2UpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2ScuSlot1UpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2ScuSlot2UpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2ScuSlot3UpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2ScuSlot4UpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2ScuSlot5UpdateEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2SlotThetaEventMsg extends AbstractEventMsg<int[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2TailRadarDataEventMsg extends AbstractEventMsg<float[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2TailRadarFaultStEventMsg extends AbstractEventMsg<int[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2TailRadarLevelEventMsg extends AbstractEventMsg<int[]> {
    }

    /* loaded from: classes12.dex */
    public static class V2TargetParkingPositionEventMsg extends AbstractEventMsg<int[]> {
    }

    float[] getAltimeterV2() throws Exception;

    int[] getTargetParkingPositionV2() throws Exception;
}
