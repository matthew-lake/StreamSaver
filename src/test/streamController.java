package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class streamController {
    private streamModel model;
    private streamView view;
    private boolean start;

    public streamController(streamModel model, streamView view) {
        this.model = model;
        this.view = view;
        start = true;
        view.addDownloadListener(new DownloadActionListener());
    }

    private class DownloadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            model.prep(view.getText());
            start = true;
        }
    }
}
