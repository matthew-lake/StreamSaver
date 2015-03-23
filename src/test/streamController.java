package test;

import javafx.stage.FileChooser;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class streamController {
    private streamModel model;
    private streamView view;

    public streamController(streamModel model, streamView view) {
        this.model = model;
        this.view = view;
        view.addDownloadListener(new DownloadActionListener());
    }

    private class DownloadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            model.prep(view.getText());
        }
    }
}
