/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TEST;

/**
 *
 * @author kasper
 */
public class TestThread {
    
    public static void main(String args[])
    {
        RunnableDemo R1 = new RunnableDemo();
        R1.start();
        
        RunnableDemo R2 = new RunnableDemo();
        R2.start();
    }
}
