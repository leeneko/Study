package com.leeneko.study;

class ChatData {
    private String id;
    private String otherMsg;
    private String myMsg;
    public ChatData(String id, String otherMsg, String myMsg) {
        this.id = id;
        this.otherMsg = otherMsg;
        this.myMsg = myMsg;
    }
    public String getId() { return id; }
    public String getOtherMsg() { return otherMsg; }
    public String getMyMsg() { return myMsg; }
}
