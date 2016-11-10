package com.teamssd.ui;


public class InfiniteMessageBox implements MessageBox {
    private String str;
    
    public InfiniteMessageBox(String str) {
        this.str = str;
    }

    @Override
    public String content() {
        return str;
    }

    @Override
    public boolean closed() {
        return false;
    }

}
