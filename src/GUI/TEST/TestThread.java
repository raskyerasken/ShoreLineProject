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
        ThreadDemo R1 = new ThreadDemo();
        R1.start();
        
        ThreadDemo R2 = new ThreadDemo();
        R2.start();
    }
}
