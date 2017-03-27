package com.streamoid.chatbot;

import android.app.Application;
import android.util.Log;

import com.streamoid.bot.chatsdk.external.chat.ChatClient;
import com.streamoid.bot.chatsdk.external.events.request.RequestCallback;
import com.streamoid.bot.chatsdk.external.events.request.RequestItem;
import com.streamoid.bot.chatsdk.external.events.user.UserEvent;
import com.streamoid.bot.chatsdk.external.events.user.UserEventCallback;
import com.streamoid.bot.chatsdk.misc.ConstantValues;
import com.streamoid.bot.chatsdk.misc.Logger;

/**
 * Created by veena on 27/3/17.
 */

public class AppConfig extends Application {
    private String TAG = AppConfig.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        ChatClient.initialize(new ChatClient.Configuration.Builder(getApplicationContext())
                .setClientName("vanheusen")
                .setClientToken(ConstantValues.DEFAULT_TOKEN)
                .setLogLevel(Log.ERROR)
                .setRequestCallback(new RequestCallback() {
                    @Override
                    public void onSuccess(RequestItem requestItem) {
                        Logger.errorLogs(TAG, "Success");
                    }

                    @Override
                    public void onError(RequestItem requestItem) {
                        Logger.errorLogs(TAG, "Error: " + requestItem.getResponse().string);
                    }
                })
                .setUserEventCallback(new UserEventCallback() {
                    @Override
                    public void onEventReceived(UserEvent event) {
                        Logger.errorLogs(TAG, event.toString());
                    }
                })
                .build());
    }
}
