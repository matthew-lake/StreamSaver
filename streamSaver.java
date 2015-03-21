package streamSaver;

public class streamSaver {

    public static void main(String[] args) {
        streamModel model = new streamModel();
        CalcView view = new CalcView(model);
        new CalcController(model,view);
        view.setVisible(true);
    }
}
