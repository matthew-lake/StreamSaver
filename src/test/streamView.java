package test;

import java.awt.*;
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
        setBounds(400,200,850,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        addDisplay(c);
        addButtons(c);
    }

    private void addDisplay(Container c) {
        JPanel p = new JPanel();
        urlLabel = new JLabel("Video URL:");
        urlLabel.setFont(font);
        urlField = new JTextField("" + model.getResult(),40);
        p.setPreferredSize(new Dimension(850, 90));
        urlField.setFont(font);
        urlField.setEditable(true);
        urlField.setBackground(Color.WHITE);
        p.add(urlLabel);
        p.add(urlField);
        c.add(p,"North");
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
//
//    private void addNumbers(JPanel p) {
//        numbers = new JButton [10];
//        for (int i=0; i < 10; i++) {
//            numbers[i] = new JButton("" + i);
//            numbers[i].setFont(font);
//            numbers[i].setBackground(Color.WHITE);
//            p.add(numbers[i]);
//        }
//    }
//
//    private void addPlus(JPanel p) {
//        plus = new JButton("+");
//        plus.setFont(font);
//        plus.setBackground(Color.WHITE);
//        plus.setForeground(Color.RED);
//        p.add(plus);
//    }
//
//    private void addClear(JPanel p) {
//        clear = new JButton("C");
//        clear.setFont(font);
//        clear.setBackground(Color.WHITE);
//        clear.setForeground(Color.BLUE);
//        p.add(clear);
//    }

    public void clear() {
        urlField.setText("" + model.getResult());
    }

    public String getText() {
        return urlField.getText();
    }

    public void setText(String s) {
        urlField.setText(s);
    }

//    public void addPlusListener(ActionListener pl) {
//        plus.addActionListener(pl);
//    }
//
//    public void addClearListener(ActionListener pl) {
//        clear.addActionListener(pl);
//    }
//
//    public void addNumberListener(ActionListener pl, int n) {
//        numbers[n].addActionListener(pl);
//    }
}
