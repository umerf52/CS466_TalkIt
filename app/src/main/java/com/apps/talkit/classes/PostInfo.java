package com.apps.talkit.classes;

import java.io.Serializable;
import java.util.HashMap;

public class PostInfo implements Serializable {
    private String postText;
    private String postID;
    private int numberOfUpvotes;
    private String postTitle;
    private boolean upvoted;
    private HashMap<String, String> commentKeys;
    private HashMap<String, String> commentValues;
    private boolean chatEnabled;
    private boolean isTrigger;

    PostInfo() {
        upvoted = false;
        numberOfUpvotes = 0;
        commentKeys = new HashMap<>();
        commentValues = new HashMap<>();
    }

    public PostInfo(int num, String txt, String title, boolean ce, boolean it) {
        numberOfUpvotes = num;
        postText = txt;
        postTitle = title;
        upvoted = false;
        commentKeys = new HashMap<>();
        commentValues = new HashMap<>();
        chatEnabled = ce;
        isTrigger = it;
    }

    public int getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    public void setNumberOfUpvotes(int numberOfUpvotes) {
        this.numberOfUpvotes = numberOfUpvotes;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public boolean getUpvoted() {
        return upvoted;
    }

    public void setUpvoted(boolean upvoted) {
        this.upvoted = upvoted;
    }

    public boolean getChatEnabled() {
        return chatEnabled;
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public boolean getIsTrigger() {
        return isTrigger;
    }

    public void setIsTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public HashMap<String, String> getCommentKeys() {
        return commentKeys;
    }

    public void setCommentKeys(HashMap<String, String> commentKeys) {
        this.commentKeys = commentKeys;
    }

    public HashMap<String, String> getCommentValues() {
        return commentValues;
    }

    public void setCommentValues(HashMap<String, String> commentValues) {
        this.commentValues = commentValues;
    }
}
