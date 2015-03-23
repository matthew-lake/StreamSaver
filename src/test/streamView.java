package test;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class streamView extends JFrame {

    private JLabel urlLabel;
    private JTextField urlField;
    private JButton download;
    private Font font = new Font("SanSerif", Font.PLAIN, 24);
    private streamModel model;
    private JProgressBar progressBar;
    private Container c;
    private JPanel pButton;


    public streamView(streamModel model) {
        this.model = model;
        setTitle("Stream Saver");
        setBounds(400, 200, 850, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        c = getContentPane();
        addDisplay(c);
        addButton(c);
        addProgress();
        model.addView(this);
    }

    private void addDisplay(Container c) {
        JPanel p = new JPanel();
        urlLabel = new JLabel("Video URL:");
        urlLabel.setFont(font);
        urlField = new JTextField("",40);
        p.setPreferredSize(new Dimension(850, 90));
        urlField.setFont(font);
        urlField.setEditable(true);
        urlField.setBackground(Color.WHITE);
        p.add(urlLabel);
        p.add(urlField);
        c.add(p, "North");
    }

    private void addButton(Container c) {
        pButton = new JPanel();
        download = new JButton("Download");
        download.setFont(font);
        pButton.add(download);
        c.add(pButton,"South");
    }

    public void removeButton() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        getContentPane().add(progressBar);
    }

    public void addProgress() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        getContentPane().add(progressBar);
    }

    public void setProgress(int value) {
        System.out.println("Setting progressbar to: " + value);
        progressBar.setValue(value);
        progressBar.repaint();
        progressBar.update(progressBar.getGraphics());
    }

    public String getText() {
        return urlField.getText();
    }

    public void addDownloadListener(ActionListener pl) {
        download.addActionListener(pl);
    }
}
