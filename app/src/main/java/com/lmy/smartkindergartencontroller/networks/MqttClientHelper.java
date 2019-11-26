package com.lmy.smartkindergartencontroller.networks;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttClientHelper {

    private static final String TAG = "MqttClientHelper";

    private MqttAndroidClient mqttAndroidClient;

    private Context mContext;
    private MqttClientHelperInterface mListener;

    private String mMqttServerUri;
    private String mMqttServerPort;
    private String mSubscribeTopic;
    private String mPublishTopic;

    public MqttClientHelper(Context mContext, MqttClientHelperInterface listener) {

        this.mContext = mContext;
        this.mListener = listener;

        mMqttServerUri = "tcp://mqtt.eclipse.org";
        mMqttServerPort = "1883";
        mSubscribeTopic = "korea/subscribe";
        mPublishTopic = "korea/publish";
    }

    public void init() {

        mqttAndroidClient = new MqttAndroidClient(mContext, mMqttServerUri+":"+mMqttServerPort, MqttClient.generateClientId());

        try {
            IMqttToken token = mqttAndroidClient.connect(getMqttConnectionOption());

            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "onSuccess: called.");
                    mqttAndroidClient.setBufferOpts(getDisconnectedBufferOptions());    //연결에 성공한경우
                    subscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {   //연결에 실패한경우
                    Log.d(TAG, "onFailure: " + exception.getMessage());
                }
            });
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }
    }

    private DisconnectedBufferOptions getDisconnectedBufferOptions() {

        DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
        disconnectedBufferOptions.setBufferEnabled(true);
        disconnectedBufferOptions.setBufferSize(100);
        disconnectedBufferOptions.setPersistBuffer(true);
        disconnectedBufferOptions.setDeleteOldestMessages(false);

        return disconnectedBufferOptions;
    }


    private MqttConnectOptions getMqttConnectionOption() {

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setWill("aaa", "I am going offline".getBytes(), 1, true);

        return mqttConnectOptions;
    }

    private void subscribe() {
        try {
            mqttAndroidClient.subscribe(mSubscribeTopic, 0, new IMqttMessageListener() { // korea/lmy 구독
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception { // 구독 시 callback 함수
                    String payload = new String(message.getPayload());
                    Log.d(TAG, "messageArrived: " + payload);

                    /**
                     * Temperature : "0"
                     * Humid       : "1"
                     * Ultra       : "2"
                     */
                    String[] payloads = payload.split("/");
                    Log.d(TAG, "messageArrived: " + payloads[0] + ", " + payloads[1]);
                    switch (payloads[0]) {
                        case "TEMP":
                            mListener.sendPayload(0, payloads[1]);
                            break;
                        case "HUMID":
                            mListener.sendPayload(1, payloads[1]);
                            break;
                        case "ULTRA":
                            mListener.sendPayload(2, payloads[1]);
                            break;
                    }

                }
            });
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }
    }

    public boolean publish(int position) {
        try {
            /**
             * Led         : "2"
             * Motor       : "3"
             */
            switch (position) {
                case 2:
                case 3:
                    mqttAndroidClient.publish(mPublishTopic, Integer.toString(position).getBytes(), 0, false);
                    return true;
            }
        }
        catch(MqttException e)
        {
            e.printStackTrace();
            return  false;
        }
        return  false;
    }
}

