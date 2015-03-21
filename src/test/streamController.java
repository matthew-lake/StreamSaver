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
//        view.addPlusListener(new PlusActionListener());
//        view.addClearListener(new ClearActionListener());
//        for (int i=0; i < 10; i++) {
//            view.addNumberListener(new NumberActionListener(),i);
//        }
    }


    private class NumberActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String val = e.getActionCommand();
            if (start || view.getText().equals("0"))
                view.setText(val);
            else
                view.setText(view.getText() + val);
            start = false;
        }
    }

    private class PlusActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            model.add(Integer.parseInt(view.getText()));
            view.setText("" + model.getResult());
            start = true;
        }
    }

    private class ClearActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.clear();
            view.clear();
            start = true;
        }
    }
}
