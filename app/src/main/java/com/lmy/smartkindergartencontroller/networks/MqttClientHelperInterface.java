package com.lmy.smartkindergartencontroller.networks;

public interface MqttClientHelperInterface {
    public void sendPayload(int flag, String payload);
}
