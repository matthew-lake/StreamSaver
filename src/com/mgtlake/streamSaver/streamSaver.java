package com.mgtlake.streamSaver;

public class streamSaver {
    public static void main(String[] args) {
        streamModel model = new streamModel();
        streamView view = new streamView(model);
        new streamController(model,view);
        view.setVisible(true);
    }
}