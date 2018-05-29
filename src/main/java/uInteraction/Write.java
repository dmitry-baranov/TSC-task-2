package uInteraction;

import data.ResultData;

import java.util.List;
import java.util.Map;

public class Write {
    public static void writeData(List<ResultData> list) {
        System.out.println("Результат:\nID\tA.VALUE\tB.VALUE");
        for (ResultData e : list) {
            System.out.println("\n" + e.getId() + "\t"
                    + e.getValueA() + "\t" + e.getValueB());
        }
    }

    public static void writeData(Map<Integer, ResultData> map) {
        System.out.println("Результат:\nID\tA.VALUE\tB.VALUE");
        for (Map.Entry<Integer,ResultData> entry: map.entrySet()) {
            System.out.println("\n" + entry.getValue().getId() + "\t"
                    + entry.getValue().getValueA() + "\t" + entry.getValue().getValueB());
        }
    }

}
