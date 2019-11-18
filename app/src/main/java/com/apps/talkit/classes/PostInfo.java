package com.apps.talkit.classes;

public class PostInfo {
    private String postText;
    private String postID;
    private int numberOfUpvotes;
    private String postTitle;

    PostInfo() {
    }

    PostInfo(String id, int num, String txt, String title) {
        postID = id;
        numberOfUpvotes = num;
        postText = txt;
        postTitle = title;
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
}
