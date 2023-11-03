package com.xiaopeng.devtools.bean.factorytest;

/* loaded from: classes12.dex */
public class RadioBean {
    private String MuteState;
    private String bassGain;
    private String freqPoint;
    private String gBanlanceLevel;
    private String gFaderLevel;
    private String heroLoudnessOn;
    private String mTunerLevel;
    private String midGain;
    private String pInputSource;
    private String primaryVolume;
    private String radioCurrentBand;
    private String radioCurrentFreq;
    private String state;
    private String trebleGain;
    private String tunerpower;

    public String getPrimaryVolume() {
        return this.primaryVolume;
    }

    public void setPrimaryVolume(String str) {
        this.primaryVolume = str;
    }

    public String getRadioCurrentBand() {
        return this.radioCurrentBand;
    }

    public void setRadioCurrentBand(String str) {
        this.radioCurrentBand = str;
    }

    public String getRadioCurrentFreq() {
        return this.radioCurrentFreq;
    }

    public void setRadioCurrentFreq(String str) {
        this.radioCurrentFreq = str;
    }

    public String getFreqPoint() {
        return this.freqPoint;
    }

    public void setFreqPoint(String str) {
        this.freqPoint = str;
    }

    public String getPInputSource() {
        return this.pInputSource;
    }

    public void setPInputSource(String str) {
        this.pInputSource = str;
    }

    public String getTunerpower() {
        return this.tunerpower;
    }

    public void setTunerpower(String str) {
        this.tunerpower = str;
    }

    public String getBassGain() {
        return this.bassGain;
    }

    public void setBassGain(String str) {
        this.bassGain = str;
    }

    public String getMidGain() {
        return this.midGain;
    }

    public void setMidGain(String str) {
        this.midGain = str;
    }

    public String getTrebleGain() {
        return this.trebleGain;
    }

    public void setTrebleGain(String str) {
        this.trebleGain = str;
    }

    public String getGBanlanceLevel() {
        return this.gBanlanceLevel;
    }

    public void setGBanlanceLevel(String str) {
        this.gBanlanceLevel = str;
    }

    public String getGFaderLevel() {
        return this.gFaderLevel;
    }

    public void setGFaderLevel(String str) {
        this.gFaderLevel = str;
    }

    public String getHeroLoudnessOn() {
        return this.heroLoudnessOn;
    }

    public void setHeroLoudnessOn(String str) {
        this.heroLoudnessOn = str;
    }

    public String getMuteState() {
        return this.MuteState;
    }

    public void setMuteState(String str) {
        this.MuteState = str;
    }

    public String getMTunerLevel() {
        return this.mTunerLevel;
    }

    public void setMTunerLevel(String str) {
        this.mTunerLevel = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String toString() {
        return "current radio band='" + this.radioCurrentBand + "'\ncurrent radio freq ×100='" + this.radioCurrentFreq + "'\nfreqPoint='" + this.freqPoint + "'\npInputSource='" + this.pInputSource + "'\nradio on off status='" + this.tunerpower + "'\nbassGain='" + this.bassGain + "'\nmidGain='" + this.midGain + "'\ntrebleGain='" + this.trebleGain + "'\ngBanlanceLevel='" + this.gBanlanceLevel + "'\ngFaderLevel='" + this.gFaderLevel + "'\nheroLoudnessOn='" + this.heroLoudnessOn + "'\nMuteState='" + this.MuteState + "'\nmTunerLevel='" + this.mTunerLevel + "'\nstate='" + this.state + "'\n";
    }
}
