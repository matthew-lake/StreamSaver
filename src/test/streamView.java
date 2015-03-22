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


    public streamView(streamModel model) {
        this.model = model;
        setTitle("Stream Saver");
        setBounds(400, 200, 850, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        addDisplay(c);
        addButtons(c);
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

    private void addButtons(Container c) {
        JPanel p = new JPanel();
        download = new JButton("Download");
        download.setFont(font);
        download.setBackground(Color.WHITE);
        download.setForeground(Color.BLACK);
        p.add(download);
        c.add(p,"South");
    }

    public String getText() {
        return urlField.getText();
    }

    public void addDownloadListener(ActionListener pl) {
        download.addActionListener(pl);
    }
}
