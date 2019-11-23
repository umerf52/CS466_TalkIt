package com.apps.talkit.classes;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class PostInfo implements Serializable {
    private String postText;
    private String postID;
    private int numberOfUpvotes;
    private String postTitle;
    private boolean upvoted;
    private Map<String, String> comments;
    private boolean chatEnabled;

    PostInfo() {
    }

    public PostInfo(String id, int num, String txt, String title, boolean ce) {
        postID = id;
        numberOfUpvotes = num;
        postText = txt;
        postTitle = title;
        upvoted = false;
        comments = Collections.emptyMap();
        chatEnabled = ce;
    }

    public int getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    public void setNumberOfUpvotes(int numberOfUpvotes) {
        this.numberOfUpvotes = numberOfUpvotes;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
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

    public Map<String, String> getComments() {
        return comments;
    }

    public void setComments(Map<String, String> comments) {
        this.comments = comments;
    }

    public boolean getChatEnabled() {
        return chatEnabled;
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }
}
