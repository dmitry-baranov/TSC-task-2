package transform;

import data.Data;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class mapper {
    public static List<Data> fromArrayListToLinkedList(List<Data> list) {
        List<Data> linkedList = new LinkedList<Data>();
        linkedList.addAll(list);
        return (List<Data>) linkedList;
    }
    public static Map<Integer, Data> fromArrayListToMap(List<Data> list) {
        Map<Integer, Data> map = new HashMap<Integer, Data>();
        for (Data A : list) {
            map.put(map.size(), A);
        }
        return map;
    }

}
