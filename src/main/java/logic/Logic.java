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
            Map<Integer, Data> mapA = mapper.fromArrayListToMap(listA);
            Map<Integer, Data> mapB = mapper.fromArrayListToMap(listB);
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
        Collections.sort(listA);
        Collections.sort(listB);
        List<ResultData> resultList = new LinkedList<ResultData>();
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

    private Map<Integer, ResultData> startMap(Map<Integer, Data> mapA, Map<Integer, Data> mapB) {
        Map<Integer, ResultData> resultMap = new HashMap<Integer, ResultData>();
        for (Map.Entry<Integer, Data> dataB : mapB.entrySet()) {
            int sizeResult = resultMap.size();
            for (Map.Entry<Integer, Data> dataA : mapA.entrySet()) {
                if (dataB.getValue().getId() == dataA.getValue().getId()) {
                    resultMap.put(resultMap.size(), new ResultData(dataB.getValue().getId(),
                            dataA.getValue().getValue(), dataB.getValue().getValue()));
                }
            }
            if (sizeResult == resultMap.size()) {
                resultMap.put(resultMap.size(), new ResultData(dataB.getValue().getId(),
                        " ", dataB.getValue().getValue()));
            }
        }
        return resultMap;
    }

}
