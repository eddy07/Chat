package com.dev.app.chat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev.app.chat.R;
import com.dev.app.chat.model.Message;
import com.dev.app.chat.utils.DateFormatUtil;
import com.rockerhieu.emojicon.EmojiconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eddy on 05/01/17.
 */

public class MessageAdapter extends BaseAdapter {
    private final Context context;
    private List<Message> messages = new ArrayList<>();

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }


    public void update(Message message){
        messages.add(message);
        notifyDataSetChanged();
    }


    private static class ViewHolder{
        TextView nameTv;
        EmojiconTextView messageTv;
        TextView dateTv;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Message message = messages.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolder = new ViewHolder();
        if(message.isMe()) {
            convertView = inflater.inflate(R.layout.message_me_item, parent, false);
        }else{
            convertView = inflater.inflate(R.layout.message_item, parent, false);
            viewHolder.nameTv = (TextView)convertView.findViewById(R.id.name);
            viewHolder.nameTv.setText(message.getName());
        }
        viewHolder.messageTv = (EmojiconTextView)convertView.findViewById(R.id.message);
        viewHolder.dateTv = (TextView)convertView.findViewById(R.id.date);
        viewHolder.messageTv.setText(message.getMessage());
        viewHolder.dateTv.setText(DateFormatUtil.getHour(message.getDate()));
        convertView.setTag(viewHolder);
        return convertView;
    }



}
