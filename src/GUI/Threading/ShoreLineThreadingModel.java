/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Threading;

import javafx.scene.image.Image;

/**
 *
 * @author ander
 */
public class ShoreLineThreadingModel 
{
     private static final int DEFAULT_DURATION = 3;
    private Image filesImported;
    private String title;
    private int duration;
    
    public ShoreLineThreadingModel(Image anImage, String aTitle, int aDuration)
    {
        filesImported = anImage;
        title = aTitle;
        duration = aDuration;
    }
    
    public ShoreLineThreadingModel(Image anImage, String aTitle)
    {
        this(anImage, aTitle, DEFAULT_DURATION);
    }
    
    public Image getImage()
    {
        return filesImported;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public int getDuration()
    {
        return duration;
    }
}
