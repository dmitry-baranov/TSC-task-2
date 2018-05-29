import data.Data;
import data.ResultData;
import errors.MyException;
import logic.Logic;
import uInteraction.Read;

import java.util.*;

public class Main {

    private static final String FILENAMEREADA = "textA.txt";

    private static final String FILENAMEREADB = "textB.txt";

    private static Logic logic = new Logic();

    public static void main(String[] args) {

        logic.start(FILENAMEREADA, FILENAMEREADB);

    }
}
