package luolastogeneraattori.utils;

public class TriangleList {

    private Triangle[] list;
    private int pointer;

    public TriangleList() {
        list = new Triangle[8];
        pointer = 0;
    }

    /**
     * Lisää Triangle-objektin listaan
     * @param t Triangle
     */
    public void add(Triangle t) {
        list[pointer] = t;
        pointer++;
        if (pointer == list.length) {
            expand();
        }
    }
    
    /**
     * Palauttaa indeksiä vastaavan Triangle _Objektin listasta
     * @param i int
     * @return  Triangle
     */
    public Triangle get(int i) {
        return list[i];
    }
    
    /**
     * Poistaa indeksiä vastaavan Triangle -Objektin listasta
     * @param index int
     */
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
    
    /**
     * Palauttaa listan koon
     * @return 
     */
    public int size() {
        return pointer;
    }

    /**
     * Tarkastaa löytyykö parametria vastaava Triangle -Objekti listasta
     * @param t Triangle 
     * @return boolean
     */
    public boolean contains(Triangle t) {
        for (int i = 0; i < pointer; i++) {
            if (list[i].equals(t)) {
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
