package com.xiaopeng.lib.framework.netchannelmodule.messaging.events;

import android.support.annotation.Nullable;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IMessaging;

/* loaded from: classes12.dex */
public class ReportingEvent implements IEvent {
    private IMessaging.CHANNEL mChannel;
    private byte[] mMessageBody;
    private long mMessageId;
    private int mProtocolReasonCode;
    private int mReasonCode;
    private IEvent.TYPE mType;

    public ReportingEvent(IEvent.TYPE type) {
        this.mType = type;
    }

    public ReportingEvent reasonCode(int i) {
        this.mReasonCode = i;
        return this;
    }

    public ReportingEvent messageId(long j) {
        this.mMessageId = j;
        return this;
    }

    public ReportingEvent message(byte[] bArr) {
        this.mMessageBody = bArr;
        return this;
    }

    public ReportingEvent protocolReasonCode(int i) {
        this.mProtocolReasonCode = i;
        return this;
    }

    public ReportingEvent channel(IMessaging.CHANNEL channel) {
        this.mChannel = channel;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent
    public IMessaging.CHANNEL channel() {
        return this.mChannel;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent
    public IEvent.TYPE type() {
        return this.mType;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent
    public int reasonCode() {
        return this.mReasonCode;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent
    public long messageId() {
        return this.mMessageId;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent
    @Nullable
    public String messageTopic() {
        return null;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent
    @Nullable
    public byte[] messageContent() {
        return this.mMessageBody;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IEvent
    public int protocolReasonCode() {
        return this.mProtocolReasonCode;
    }
}
