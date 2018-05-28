package data;

public class Data implements Comparable<Data> {

    private int id;
    private String value;

    public Data(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int compareTo(Data o) {
        return (this.id < o.id ) ? -1: (this.id > o.id ) ? 1:0 ;
    }
}
