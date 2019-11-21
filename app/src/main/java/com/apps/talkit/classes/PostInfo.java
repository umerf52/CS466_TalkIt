package com.apps.talkit.classes;

import java.io.Serializable;

public class PostInfo implements Serializable {
    private String postText;
    private String postID;
    private int numberOfUpvotes;
    private String postTitle;
    private boolean upvoted;

    PostInfo() {
    }

    public PostInfo(String id, int num, String txt, String title) {
        postID = id;
        numberOfUpvotes = num;
        postText = txt;
        postTitle = title;
        upvoted = false;
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
}
