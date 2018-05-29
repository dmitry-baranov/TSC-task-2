package logic;

import data.Data;
import data.ResultData;
import errors.MyException;
import transform.mapper;
import uInteraction.Read;
import uInteraction.Write;
import java.util.*;

public class Logic {
    public void start(String fileA, String fileB) {
        try {
            //Считывание
            List<Data> listA = Read.readFile(fileA);
            List<Data> listB = Read.readFile(fileB);
            //Выполнение запроса с использованием ArrayList
            Write.writeData(startArrayList(listA, listB));
            //Преобразование ArrayList в LinkedList
            List<Data> linkedListA = mapper.fromArrayListToLinkedList(listA);
            List<Data> linkedListB = mapper.fromArrayListToLinkedList(listB);
            //Выполнение запроса с использованием LinkedList
            Write.writeData(startLinkedList(linkedListA, linkedListB));
            //Преобразование ArrayList в Map
            Map<Integer, List<String>> mapA = mapper.fromArrayListToMap(listA);
            Map<Integer, List<String>> mapB = mapper.fromArrayListToMap(listB);
            //Выполнение запроса с использованием Map
            Write.writeData(startMap(mapA, mapB));
        } catch (MyException e) {
            System.out.println(e.getResponse().getErrorCode() + " " + e.getResponse().getErrorMessage());
        }
    }

    private List<ResultData> startArrayList(List<Data> listA, List<Data> listB) {
        List<ResultData> resultList = new ArrayList<ResultData>();
        for (Data dataB : listB) {
            int sizeResult = resultList.size();
            for (Data dataA : listA) {
                if (dataB.getId() == dataA.getId()) {
                    resultList.add(new ResultData(dataB.getId(), dataA.getValue(), dataB.getValue()));
                }
            }
            if (sizeResult == resultList.size()) {
                resultList.add(new ResultData(dataB.getId(), " ", dataB.getValue()));
            }
        }
        return resultList;
    }

//    private List<ResultData> startLinkedList(List<Data> listA, List<Data> listB) {
//        Collections.sort(listA);
//        Collections.sort(listB);
//        List<ResultData> resultList = new LinkedList<ResultData>();
//        for (Data dataB : listB) {
//            int sizeResult = resultList.size();
//            for (Data dataA : listA) {
//                if (dataB.getId() == dataA.getId()) {
//                    resultList.add(new ResultData(dataB.getId(), dataA.getValue(), dataB.getValue()));
//                }
//            }
//            if (sizeResult == resultList.size()) {
//                resultList.add(new ResultData(dataB.getId(), " ", dataB.getValue()));
//            }
//        }
//        return resultList;
//    }

    private List<ResultData> startLinkedList(List<Data> listA, List<Data> listB) {
        List<ResultData> resultList = new LinkedList<ResultData>();
        Collections.sort(listA);
        Collections.sort(listB);
        Iterator<Data> iteratorA = listA.iterator();
        Iterator<Data> iteratorB = listB.iterator();
        if (listA.size() == 0 && listB.size() == 0) {
            return resultList;
        } else if (listA.size() == 0) {
            while (iteratorB.hasNext()) {
                Data dataB = iteratorB.next();
                resultList.add(new ResultData(dataB.getId(), " ", dataB.getValue()));
            }
        } else {
            Data dataA = iteratorA.next();
            Data dataB = iteratorB.next();
            int j = 0;
            int i = 0;
            while ((iteratorB.hasNext() || iteratorA.hasNext()) && (iteratorB.hasNext() || i>=0)) {
                i = dataB.compareTo(dataA);
                if (i > 0) {
                    if (iteratorA.hasNext()) {
                        dataA = iteratorA.next();
                    }
                } else if (i < 0) {
                    if (j == 0) {
                        resultList.add(new ResultData(dataB.getId(), " ", dataB.getValue()));
                        j = 0;
                        i = 0;
                    }
                    if (iteratorB.hasNext()) {
                        dataB = iteratorB.next();
                        j = 0;
                    }
                } else if (i == 0){
                    resultList.add(new ResultData(dataB.getId(), dataA.getValue(), dataB.getValue()));
                    dataA = iteratorA.next();
                    j++;
                }
            }
            if (!(iteratorB.hasNext() || i >= 0) && j ==0) {
                resultList.add(new ResultData(dataB.getId(), " ", dataB.getValue()));
            }
        }
        return resultList;
    }

//    private Map<Integer, ResultData> startMap(Map<Integer, Data> mapA, Map<Integer, Data> mapB) {
//        Map<Integer, ResultData> resultMap = new HashMap<Integer, ResultData>();
//        for (Map.Entry<Integer, Data> dataB : mapB.entrySet()) {
//            int sizeResult = resultMap.size();
//            for (Map.Entry<Integer, Data> dataA : mapA.entrySet()) {
//                if (dataB.getValue().getId() == dataA.getValue().getId()) {
//                    resultMap.put(resultMap.size(), new ResultData(dataB.getValue().getId(),
//                            dataA.getValue().getValue(), dataB.getValue().getValue()));
//                }
//            }
//            if (sizeResult == resultMap.size()) {
//                resultMap.put(resultMap.size(), new ResultData(dataB.getValue().getId(),
//                        " ", dataB.getValue().getValue()));
//            }
//        }
//        return resultMap;
//    }

    private Map<Integer, List<String>> startMap(Map<Integer, List<String>> mapA, Map<Integer, List<String>> mapB) {
        Map<Integer, List<String>> resultMap = new HashMap<Integer, List<String>>();
        for (Map.Entry<Integer, List<String>> entryB : mapB.entrySet()) {
            if (mapA.containsKey(entryB.getKey())) {
                if (!resultMap.containsKey(entryB.getKey())) {
                    resultMap.put(entryB.getKey(), new LinkedList<String>());
                }
                for (String dataB : entryB.getValue()) {
                    for (String dataA : mapA.get(entryB.getKey())) {
                        resultMap.get(entryB.getKey()).add(dataA + "\t" + dataB);
                    }
                }
            } else {
                resultMap.put(entryB.getKey(), new LinkedList<String>());
                for (String dataB : entryB.getValue()) {
                    resultMap.get(entryB.getKey()).add(" \t" + dataB);
                }

            }
        }
        return resultMap;
    }

}
