package logic;

import data.Data;
import data.ResultData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {
    public List<ResultData> startArrayList(List<Data> listA, List<Data> listB) {
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

    public List<ResultData> startLinkedList(List<Data> listA, List<Data> listB) {
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

    public Map<Integer, ResultData> startMap(Map<Integer, Data> mapA, Map<Integer, Data> mapB) {
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
