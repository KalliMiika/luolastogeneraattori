package luolastogeneraattori.utils;

public class TriangleList {

    private Triangle[] list;
    private int pointer;

    public TriangleList() {
        list = new Triangle[8];
        pointer = 0;
    }

    public void add(Triangle t) {
        list[pointer] = t;
        pointer++;
        if (pointer == list.length) {
            expand();
        }
    }
    
    public Triangle get(int i) {
        return list[i];
    }
    
    public void remove(int index) {
        Triangle[] newList = new Triangle[list.length];
        int fromIndex = 0;
        int toIndex = 0;
        while (fromIndex < list.length) {
            if (fromIndex != index) {
                newList[toIndex] = list[fromIndex];
                toIndex++;
            }
            fromIndex++;
        }
        pointer--;
        list = newList;
    }
    
    public int size() {
        return pointer;
    }

    public boolean contains(Triangle t) {
        for (Triangle i : list) {
            if (i.equals(t)) {
                return true;
            }
        }
        return false;
    }
    
    private void expand() {
        Triangle[] newList = new Triangle[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
}
