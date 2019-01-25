package loading.reports;

import java.io.File;

public abstract class ReportGeneratorInterface<T> {

    public ReportGeneratorInterface() {
        new File("Reports").mkdir();

    }

    abstract void generateRaport(T t);
}
