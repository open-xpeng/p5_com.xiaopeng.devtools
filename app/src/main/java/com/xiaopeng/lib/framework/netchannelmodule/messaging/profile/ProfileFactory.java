package com.xiaopeng.lib.framework.netchannelmodule.messaging.profile;

import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IMessaging;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.MessagingException;
import com.xiaopeng.lib.framework.netchannelmodule.messaging.exception.MessagingExceptionImpl;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class ProfileFactory {
    private static Map<IMessaging.CHANNEL, AbstractChannelProfile> map = new HashMap();

    public static AbstractChannelProfile channelProfile(IMessaging.CHANNEL channel) throws MessagingException {
        switch (channel) {
            case COMMUNICATION:
                AbstractChannelProfile abstractChannelProfile = map.get(channel);
                if (abstractChannelProfile == null) {
                    CommunicationChannelProfile communicationChannelProfile = new CommunicationChannelProfile();
                    map.put(channel, communicationChannelProfile);
                    return communicationChannelProfile;
                }
                return abstractChannelProfile;
            case REPORTING:
                AbstractChannelProfile abstractChannelProfile2 = map.get(channel);
                if (abstractChannelProfile2 == null) {
                    ReportingChannelProfile reportingChannelProfile = new ReportingChannelProfile();
                    map.put(channel, reportingChannelProfile);
                    return reportingChannelProfile;
                }
                return abstractChannelProfile2;
            case TESTING:
                AbstractChannelProfile abstractChannelProfile3 = map.get(channel);
                if (abstractChannelProfile3 == null) {
                    TestingChannelProfile testingChannelProfile = new TestingChannelProfile();
                    map.put(channel, testingChannelProfile);
                    return testingChannelProfile;
                }
                return abstractChannelProfile3;
            default:
                throw new MessagingExceptionImpl(16);
        }
    }
}
