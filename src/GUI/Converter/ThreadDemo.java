/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Converter;

/**
 *
 * @author kasper
 */
public class ThreadDemo {
    
    private Thread t;
    private String threadName;
    
    
      public void run() throws InterruptedException
    {
        System.out.println("Creating" + threadName );
        try {
            for (int i = 4; i > 0; i--) 
            {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Thread" + threadName + "exiting.");
        }
        

                
            }
        
    public void start () 
    {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread ((Runnable) this, threadName);
                    t.start();
        }
    }
   
    
    
}
