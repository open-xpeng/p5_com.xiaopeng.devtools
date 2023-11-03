package com.xiaopeng.commonfunc.bean.factorytest;

/* loaded from: classes11.dex */
public class FacRadioBean {
    private int bassGain;
    private int freqPoint;
    private int gBanlanceLevel;
    private int gFaderLevel;
    private int heroLoudnessOn;
    private int mMuteState;
    private int mTunerLevel;
    private int midGain;
    private int pInputSource;
    private int primaryVolume;
    private int radioCurrentBand;
    private int radioCurrentFreq;
    private int state;
    private int trebleGain;
    private int tunerpower;

    public int getPrimaryVolume() {
        return this.primaryVolume;
    }

    public int getRadioCurrentBand() {
        return this.radioCurrentBand;
    }

    public int getRadioCurrentFreq() {
        return this.radioCurrentFreq;
    }

    public int getFreqPoint() {
        return this.freqPoint;
    }

    public int getpInputSource() {
        return this.pInputSource;
    }

    public int getTunerpower() {
        return this.tunerpower;
    }

    public int getBassGain() {
        return this.bassGain;
    }

    public int getMidGain() {
        return this.midGain;
    }

    public int getTrebleGain() {
        return this.trebleGain;
    }

    public int getgBanlanceLevel() {
        return this.gBanlanceLevel;
    }

    public int getgFaderLevel() {
        return this.gFaderLevel;
    }

    public int getHeroLoudnessOn() {
        return this.heroLoudnessOn;
    }

    public int getMuteState() {
        return this.mMuteState;
    }

    public int getmTunerLevel() {
        return this.mTunerLevel;
    }

    public int getState() {
        return this.state;
    }

    public String toString() {
        return "当前收音机band='" + this.radioCurrentBand + "'\n当前收音机频点×100='" + this.radioCurrentFreq + "'\nfreqPoint='" + this.freqPoint + "'\npInputSource='" + this.pInputSource + "'\n收音机打开状态='" + this.tunerpower + "'\nbassGain='" + this.bassGain + "'\nmidGain='" + this.midGain + "'\ntrebleGain='" + this.trebleGain + "'\ngBanlanceLevel='" + this.gBanlanceLevel + "'\ngFaderLevel='" + this.gFaderLevel + "'\nheroLoudnessOn='" + this.heroLoudnessOn + "'\nmMuteState='" + this.mMuteState + "'\nmTunerLevel='" + this.mTunerLevel + "'\nstate='" + this.state + "'\n";
    }
}
