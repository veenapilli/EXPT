package com.streamoid.chatbot;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.streamoid.bot.chatsdk.external.chat.ChatClient;
import com.streamoid.bot.chatsdk.misc.ConstantValues;
import com.streamoid.bot.chatsdk.misc.util.TextUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnStartChat;
    private String startButtonTag = "startBtn";
    private Toolbar toolbar;
    private String userGender;
    private String clientName;
    private String userId;

    private Spinner genderDropdown;
    private Spinner userDropdown;
    private Spinner clientDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupListeners();
    }

    private void setupListeners() {
        mBtnStartChat.setTag(startButtonTag);
        mBtnStartChat.setOnClickListener(this);
    }

    private void setupViews() {
        mBtnStartChat = (Button) findViewById(R.id.start_chat_button);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("SDK test");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black));

        clientDropdown = (Spinner)findViewById(R.id.client_spinner);
        String[] client_items = new String[]{ConstantValues.CLIENT1, ConstantValues.CLIENT2};
        ArrayAdapter<String> client_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, client_items);
        clientDropdown.setAdapter(client_adapter);
        clientDropdown.setSelected(true);

        genderDropdown = (Spinner)findViewById(R.id.gender_spinner);
        String[] items = new String[]{"women", "men"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, items);
        genderDropdown.setAdapter(adapter);
        genderDropdown.setSelected(true);

        userDropdown = (Spinner)findViewById(R.id.user_spinner);
        String[] user_items = new String[]{"user1", "user2", "user3", "user 4", "user 5", "user 6"};
        ArrayAdapter<String> user_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, user_items);
        userDropdown.setAdapter(user_adapter);
        userDropdown.setSelected(true);

    }

    @Override
    public void onClick(View v) {
        String tag = null != v.getTag() ? v.getTag().toString() : "";
        if(tag.equals(startButtonTag)){
            //   Navigator.switchActivity(this, ChatHomeActivity.class, false);
            userGender = genderDropdown.getSelectedItem().toString();
            userId = userDropdown.getSelectedItem().toString();
            clientName = clientDropdown.getSelectedItem().toString();
            ChatClient.updateClient(this, ConstantValues.DEFAULT_TOKEN,
                    TextUtil.isStringNotNullOrEmpty(clientName) ? clientName :
                            ConstantValues.DEFAULT_CLIENT);
            ChatClient.startChat(this, userId, userGender);

        }
    }
}
