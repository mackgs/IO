package loading.sending;


import javafx.scene.control.TextArea;
import loading.reports.ReportGenerator;
import singletons.StockRoom;

public abstract class Sender<T> {

    protected StockRoom stockRoom;

    protected ReportGenerator reportGenerator;

    protected static TextArea textArea;


    public abstract void send(T t);

    public abstract void removeFromStock(T t);

    public static void setTextArea(TextArea textArea) {
        Sender.textArea = textArea;
    }
}
