package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class streamView extends JFrame {

    private JTextField display;
    private JButton [] numbers;
    private JButton plus;
    private JButton clear;
    private Font font = new Font("SanSerif", Font.BOLD, 28);
    private streamModel model;


    public streamView(streamModel model) {
        this.model = model;
        setTitle("Calculator");
        setBounds(400,200,250,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        addDisplay(c);
        addButtons(c);
    }

    private void addDisplay(Container c) {
        JPanel p = new JPanel();
        display = new JTextField("" + model.getResult(),9);
        display.setFont(font);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        p.add(display);
        c.add(p,"North");
    }

    private void addButtons(Container c) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,3));
        addNumbers(p);
        addPlus(p);
        addClear(p);
        c.add(p,"Center");
    }

    private void addNumbers(JPanel p) {
        numbers = new JButton [10];
        for (int i=0; i < 10; i++) {
            numbers[i] = new JButton("" + i);
            numbers[i].setFont(font);
            numbers[i].setBackground(Color.WHITE);
            p.add(numbers[i]);
        }
    }

    private void addPlus(JPanel p) {
        plus = new JButton("+");
        plus.setFont(font);
        plus.setBackground(Color.WHITE);
        plus.setForeground(Color.RED);
        p.add(plus);
    }

    private void addClear(JPanel p) {
        clear = new JButton("C");
        clear.setFont(font);
        clear.setBackground(Color.WHITE);
        clear.setForeground(Color.BLUE);
        p.add(clear);
    }

    public void clear() {
        display.setText("" + model.getResult( ));
    }

    public String getText() {
        return display.getText();
    }

    public void setText(String s) {
        display.setText(s);
    }

    public void addPlusListener(ActionListener pl) {
        plus.addActionListener(pl);
    }

    public void addClearListener(ActionListener pl) {
        clear.addActionListener(pl);
    }

    public void addNumberListener(ActionListener pl, int n) {
        numbers[n].addActionListener(pl);
    }
}
