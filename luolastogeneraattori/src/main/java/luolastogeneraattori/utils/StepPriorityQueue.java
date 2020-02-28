package luolastogeneraattori.utils;

import luolastogeneraattori.objects.Point;

public class StepPriorityQueue {

    private Step[] list;
    private int pointer;

    public StepPriorityQueue() {
        list = new Step[8];
        pointer = 0;
    }

    public void add(Step s) {
        Step[] newList = new Step[list.length];
        int from = 0;
        int to = 0;
        while (from < pointer) {
            if (s.getDistance() < list[from].getDistance()) {
                break;
            } else {
                newList[to] = list[from];
                from++;
                to++;
            }
        }
        newList[to] = s;
        to++;
        while (from < pointer) {
            newList[to] = list[from];
            from++;
            to++;
        }
        list = newList;
        pointer++;
        if (pointer == list.length) {
            expand();
        }
    }
    
    public Step pop() {
        Step ret = list[0];
        remove(ret);
        return ret;
    }
    
    public Step get(int i) {
        return list[i];
    }
    
    public Step[] toArray() {
        Step[] ret = new Step[pointer];
        for (int i = 0; i < pointer; i++) {
            ret[i] = list[i];
        }
        return ret;
    }
    
    public void remove(Step s) {
        Step[] newList = new Step[list.length];
        int fromIndex = 0;
        int toIndex = 0;
        int newPointer = pointer;
        while (fromIndex < pointer) {
            if (!list[fromIndex].equals(s)) {
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

    public boolean contains(Step s) {
        for (int i = 0; i < pointer; i++) {
            if (list[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    private void expand() {
        Step[] newList = new Step[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }
    
}
