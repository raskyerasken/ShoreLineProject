/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Threading;

import java.security.InvalidParameterException;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 *
 * @author ander
 */
public class ShoreLineThreading 
{
    private static final int DEFAULT_TIME_INTERVAL = 3;
    private final String name;
    private final ImageView viewer;
    private final List<ShoreLineThreadingModel> filesImported;
    private int filesImportedIndex;
    private Thread t = null;
    private boolean suspended;
    private boolean done;
    
    public ShoreLineThreading(String name, ImageView viewer, List<ShoreLineThreadingModel> filesImported)
    {
        if (filesImported == null || filesImported.isEmpty())
        {
            throw new InvalidParameterException("No images to show");
        }
        this.name = name;
        this.viewer = viewer;
        this.filesImported = filesImported;
        viewer.setImage(filesImported.get(0).getImage());
    }
    
    //@Override
    public void run()
    {
        while (!done)
        {
            ShoreLineThreadingModel files = filesImported.get(filesImportedIndex++);
            viewer.setImage(files.getImage());
            filesImportedIndex %= filesImported.size();

            long startTime = System.currentTimeMillis();
            long timeToGo = files.getDuration() * 1000;

            while (!done && System.currentTimeMillis() < startTime + timeToGo)
            {
                if (suspended)
                {
                    timeToGo -= System.currentTimeMillis() - startTime;
                    suspendFileImport();
                }
                else
                {
                    timedDelay(timeToGo);
                    timeToGo -= System.currentTimeMillis() - startTime;
                }
                startTime = System.currentTimeMillis();
            }
        }
    }
    
    private void suspendFileImport()
    {
        synchronized (t)
        {
            try
            {
                System.out.println("Suspending thread");
                t.wait();
            }
            catch (InterruptedException ex)
            {
                // Wake up!
            }
        }
    }
    
    private void timedDelay(long timeToGo)
    {
        System.out.println("Sleeping for " + timeToGo);
        try
        {
            Thread.sleep(timeToGo);
        }
        catch (InterruptedException ex)
        {
            // Just wake-up!
        }
    }
    
    public void start()
    {
        t = new Thread((Runnable) this);
        suspended = false;
        done = false;
        filesImportedIndex = 0;
        t.start();
    }
        
    public void stop()
    {
        done = true;
        if (t.getState() == Thread.State.TIMED_WAITING || t.getState() == Thread.State.WAITING)
        {
            t.interrupt();
        }
        viewer.setImage(filesImported.get(0).getImage());
    }

    public void pause()
    {
        suspended = true;
        if (t.getState() == Thread.State.TIMED_WAITING)
        {
            t.interrupt();
        }
    }

    public void resume()
    {
        suspended = false;
        synchronized (t)
        {
            t.notify();
        }
    }

    public String getName()
    {
        return name;
    }

    public boolean isSuspended()
    {
        return suspended;
    }
}
