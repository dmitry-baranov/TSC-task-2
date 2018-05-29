
import logic.Logic;

public class Main {

    private static final String FILENAMEREADA = "textA.txt";

    private static final String FILENAMEREADB = "textB.txt";

    private static Logic logic = new Logic();

    public static void main(String[] args) {

        logic.start(FILENAMEREADA, FILENAMEREADB);

    }
}
