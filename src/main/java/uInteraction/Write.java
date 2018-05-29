package uInteraction;

import data.ResultData;
import java.util.List;
import java.util.Map;

public class Write {
    public static void writeData(List<ResultData> list) {
        System.out.println("Результат:\nID\tA.VALUE\tB.VALUE");
        for (ResultData e : list) {
            System.out.println(e.getId() + "\t"
                    + e.getValueA() + "\t" + e.getValueB());
        }
    }

    public static void writeData(Map<Integer, List<String>> map) {
        System.out.println("Результат:\nID\tA.VALUE\tB.VALUE");
        for (Map.Entry<Integer,List<String>> entry: map.entrySet()) {
            for (String s : entry.getValue()) {
                System.out.println(entry.getKey() + "\t"
                        + s);
            }

        }
    }
}
