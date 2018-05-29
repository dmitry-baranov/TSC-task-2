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
    public static Map<Integer, List<String>> fromArrayListToMap(List<Data> list) {
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for (Data a : list) {
            if (!map.containsKey(a.getId())) {
                map.put(a.getId(), new LinkedList<String>());
            }
            map.get(a.getId()).add(a.getValue());
        }
        return map;
    }

}
