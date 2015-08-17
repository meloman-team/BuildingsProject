
package buildings.threads;

public class CriticalSection {
    
    boolean bool;//свободно
                        //false - занято
    public CriticalSection (){
        bool = true;
    }
    
    public synchronized void release () throws InterruptedException {
        if(bool) this.wait();
        bool = true;
    }
    
    
    public synchronized void acquire() throws InterruptedException {
        while(!bool) {
            this.wait();
        }
        bool = false;
    }
    
}
