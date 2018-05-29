package data;

public class ResultData {

    private int id;
    private String valueA;
    private String valueB;

    public ResultData(int id, String valueA, String valueB) {
        this.id = id;
        this.valueA = valueA;
        this.valueB = valueB;
    }


    public int getId() {
        return id;
    }

    public String getValueA() {
        return valueA;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA;
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB;
    }

}
