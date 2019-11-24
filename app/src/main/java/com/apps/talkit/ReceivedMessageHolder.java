package com.apps.talkit;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apps.talkit.classes.Messages;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText;

    ReceivedMessageHolder(View itemView) {
        super(itemView);
        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
    }

    void bind(Messages message) {
        messageText.setText(message.getMessage());
    }
}