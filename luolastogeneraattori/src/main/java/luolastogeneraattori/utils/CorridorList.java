package luolastogeneraattori.utils;

import luolastogeneraattori.objects.Corridor;

public class CorridorList {

    private Corridor[] list;
    private int pointer;

    public CorridorList() {
        list = new Corridor[8];
        pointer = 0;
    }

    public void add(Corridor c) {
        list[pointer] = c;
        pointer++;
        if (pointer == list.length) {
            expand();
        }
    }
    
    public Corridor get(int i) {
        return list[i];
    }
    
    public Corridor[] toArray() {
        Corridor[] ret = new Corridor[pointer];
        for (int i = 0; i < pointer; i++) {
            ret[i] = list[i];
        }
        return ret;
    }
    
    public void remove(Corridor cor) {
        Corridor[] newList = new Corridor[list.length];
        int fromIndex = 0;
        int toIndex = 0;
        int newPointer = pointer;
        while (fromIndex < pointer) {
            if (!list[fromIndex].equals(cor)) {
                newList[toIndex] = list[fromIndex];
                toIndex++;
            } else {
                newPointer--;
            }
            fromIndex++;
        }
        pointer = newPointer;
        list = newList;
    }
    
    public int size() {
        return pointer;
    }

    public boolean contains(Corridor c) {
        for (int i = 0; i < pointer; i++) {
            if (list[i].equals(c)) {
                return true;
            }
        }
        return false;
    }
    
    public CorridorList clearDuplicates() {
        CorridorList newList = new CorridorList();
        for (int i = 0; i < pointer; i++) {
            if (!newList.contains(list[i])) {
                newList.add(list[i]);
            }
        }
        return newList;
    }
    
    private void expand() {
        Corridor[] newList = new Corridor[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
    
}
