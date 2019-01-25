package loading;


import controllers.MainActivityContoller;
import singletons.FileStore;

public class LoadingProcesor {

    private FileStore fileStore = FileStore.getFileStore();


    private MainActivityContoller contoller;

    public static ShipLoader shipLoader;


    public LoadingProcesor(MainActivityContoller contoller) {
        this.contoller = contoller;
        //shipLoader.setTextArea(contoller.getTextArea());

    }

    public void manual() {
        process();

    }

    public void auto() {
        boolean process;
        do {
            process = process();
        } while (process);
    }

    public boolean process() {
        String nextLine = fileStore.getNextLine();

        if (nextLine.isEmpty()) {
            return false;
        } else {
            contoller.getTextArea().appendText(nextLine + "\n");
            load();
            contoller.updateShipCounter();
            contoller.updateContainerCounter();
            return true;
        }


    }

    private void load() {
        shipLoader.processLoading();
    }
}
