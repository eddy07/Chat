package com.dev.app.chat.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.dev.app.chat.R;
import com.dev.app.chat.adapter.MessageAdapter;
import com.dev.app.chat.model.Message;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.EmojiconGridFragment;
import com.rockerhieu.emojicon.EmojiconsFragment;
import com.rockerhieu.emojicon.emoji.Emojicon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,EmojiconGridFragment.OnEmojiconClickedListener,
        EmojiconsFragment.OnEmojiconBackspaceClickedListener {

    private ListView listView;
    private EmojiconEditText messageEdit;
    private ImageButton sendBtn;
    private MessageAdapter messageAdapter;
    private FrameLayout emojiLayout;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
        messageEdit = (EmojiconEditText) findViewById(R.id.message);
        sendBtn = (ImageButton) findViewById(R.id.sendBtn);
        ImageView emojiBtn = (ImageView)findViewById(R.id.emojiBtn);
         emojiLayout = (FrameLayout)findViewById(R.id.emojicons);
        loadMessage(messages);
        messageAdapter = new MessageAdapter(this,messages);
        listView.setAdapter(messageAdapter);
        listView.setDividerHeight(0);
        sendBtn.setOnClickListener(this);
        setEmojiconFragment(false);

        emojiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if (emojiLayout.isShown()){
                    emojiLayout.setVisibility(View.GONE);
                }else{
                    emojiLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        messageEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hideEmojiLayout(emojiLayout);
                }
            }
        });
    }

    private void loadMessage(List<Message> messages){
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setDate(new Date());
        message.setMe(false);
        message.setMessage("Hello @all, my name is Eddy, proud to be in member of this group");
        message.setName("Eddy");
        messages.add(message);
    }

    private void sendMessage(String message){
        messageEdit.setText("");
        Message messageMe = new Message();
        messageMe.setId(UUID.randomUUID().toString());
        messageMe.setName("Joel");
        messageMe.setMe(true);
        messageMe.setDate(new Date());
        messageMe.setMessage(message);
        messageAdapter.update(messageMe);
    }


    @Override
    public void onClick(View view) {
        String message = messageEdit.getText().toString();
        if(!TextUtils.isEmpty(message))
        sendMessage(message);
        hideEmojiLayout(emojiLayout);
    }

    /**
     * It called, when click on icon of Emoticons.
     * @param emojicon
     */
    @Override
    public void onEmojiconClicked(Emojicon emojicon) {

        EmojiconsFragment.input(messageEdit, emojicon);
    }

    /**
     * It called, when click on backspace button of Emoticons.
     * @param view
     */
    @Override
    public void onEmojiconBackspaceClicked(View view) {

        EmojiconsFragment.backspace(messageEdit);
    }

    /**
     * Set the Emoticons in Fragment.
     * @param useSystemDefault
     */
    private void setEmojiconFragment(boolean useSystemDefault) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.emojicons, EmojiconsFragment.newInstance(useSystemDefault))
                .commit();
    }

    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void hideEmojiLayout(FrameLayout emojiLayout){
        if(emojiLayout.isShown()){
            emojiLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed(){
       hideEmojiLayout(emojiLayout);
    }
}
