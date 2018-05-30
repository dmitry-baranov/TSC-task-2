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


    private List<ResultData> startLinkedList(List<Data> listA, List<Data> listB) {
        List<ResultData> resultList = new LinkedList<ResultData>();
        listA.add(new Data(0, "Check"));
        listB.add(new Data(0, "Check"));
        ListIterator<Data> iteratorA = listA.listIterator();
        ListIterator<Data> iteratorB = listB.listIterator();
        Data dataA = iteratorA.next();
        Data dataB = iteratorB.next();
        int addIndicator = 0;
        int compareTo;
        while (iteratorB.hasNext()) {
            compareTo = dataB.compareTo(dataA);
            if (compareTo > 0 && iteratorA.hasNext()) {
                dataA = iteratorA.next();
            } else if (compareTo < 0 || dataA.getValue().equals("Check")) {
                if (addIndicator == 0) {
                    resultList.add(new ResultData(dataB.getId(), " ", dataB.getValue()));
                    addIndicator = 0;
                    dataB = iteratorB.next();
                } else {
                    addIndicator = 0;
                    dataB = iteratorB.next();
                    dataA = iteratorA.previous();
                    if (iteratorA.hasPrevious()) {
                        dataA = iteratorA.previous();
                    }
                    while ((dataA.getId() >= dataB.getId()) && iteratorA.hasPrevious()) {
                        dataA = iteratorA.previous();
                    }
                    dataA = iteratorA.next();
                }
            } else {
                resultList.add(new ResultData(dataB.getId(), dataA.getValue(), dataB.getValue()));
                if (iteratorA.hasNext()) {
                    dataA = iteratorA.next();
                }
                addIndicator++;
            }
        }
        return resultList;
    }


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
