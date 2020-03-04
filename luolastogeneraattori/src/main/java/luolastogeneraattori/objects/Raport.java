
package luolastogeneraattori.objects;

public class Raport {
    
    public long time;
    public long memory;
    
    public Raport(long time, long memory) {
        this.time = time;
        this.memory = memory;
    }
    
    public String toString() {
        return "time: " + time + "ms\n"
                +"memory: " + memory;
    }
}
