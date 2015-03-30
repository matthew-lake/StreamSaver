package com.mgtlake.streamSaver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class streamController {
    private streamModel model;
    private streamView view;

    public streamController(streamModel model, streamView view) {
        this.model = model;
        this.view = view;
        view.addDownloadListener(new DownloadActionListener());
        view.addHelpListener(new HelpActionListener());
        model.init();
    }

    private class DownloadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            model.prep(view.getText());
        }
    }

    private class HelpActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            model.showHelp();
        }
    }
}
