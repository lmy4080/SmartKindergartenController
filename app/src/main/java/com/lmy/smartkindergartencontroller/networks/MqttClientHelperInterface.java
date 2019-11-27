package com.lmy.smartkindergartencontroller.networks;

public interface MqttClientHelperInterface {

    void sendPayload(int flag, String payload);

    void moveVideoSite(String videoUrl);
}
