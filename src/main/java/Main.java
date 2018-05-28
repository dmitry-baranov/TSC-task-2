import data.Data;
import data.ResultData;
import logic.Logic;
import uInteraction.ReadWrite;

import java.util.*;

public class Main {

    private static final String FILENAMEREAD = "text.txt";

    private static final String FILENAMEWRITE = "result.txt";

    private static Logic logic = new Logic();

    public static void main(String[] args) {
        List<Data> listA = ReadWrite.readFile(FILENAMEREAD);
        List<Data> listB = ReadWrite.readFile(FILENAMEREAD);
        List<ResultData> resultDataArray = logic.startArrayList(listA, listB);
        List<Data> linkedListA = new LinkedList<Data>();
        List<Data> linkedListB = new LinkedList<Data>();
        linkedListA.addAll(listA);
        linkedListB.addAll(listB);
        List<ResultData> resultDataLinked = logic.startLinkedList(listA, listB);
        Map<Integer, Data> mapA = new HashMap<Integer, Data>();
        Map<Integer, Data> mapB = new HashMap<Integer, Data>();
        for (Data A : listA) {
            mapA.put(mapA.size(), A);
        }
        for (Data B : listB) {
            mapA.put(mapA.size(), B);
        }
        Map<Integer, ResultData> resultDataMap = logic.startMap(mapA, mapB);
        ReadWrite.writeFile(FILENAMEWRITE, resultDataArray);
        ReadWrite.writeFile(FILENAMEWRITE, resultDataArray);
        ReadWrite.writeFile(FILENAMEWRITE, resultDataArray);

    }
}
