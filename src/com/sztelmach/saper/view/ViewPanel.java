package com.sztelmach.saper.view;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.IOException;

/**
 * Klasa reprezentujaca panel, na ktorym narysowane sa pola.
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class ViewPanel extends JPanel
{
    /**
     * tablica przechowujaca obrazy pol
     */
    private final BufferedImage IMAGES[] = new BufferedImage[15];
    
    /**
     * Konstruktor klasy {@link ViewPanel} - inicjalizuje tablice
     * {@link ViewPanel#IMAGES} ikonami pol
     */
    public ViewPanel()
    {
	try
	{
	    IMAGES[0] = ImageIO.read(getClass().getResource("images/0.png"));
	    IMAGES[1] = ImageIO.read(getClass().getResource("images/1.png"));
	    IMAGES[2] = ImageIO.read(getClass().getResource("images/2.png"));
	    IMAGES[3] = ImageIO.read(getClass().getResource("images/3.png"));
	    IMAGES[4] = ImageIO.read(getClass().getResource("images/4.png"));
	    IMAGES[5] = ImageIO.read(getClass().getResource("images/5.png"));
	    IMAGES[6] = ImageIO.read(getClass().getResource("images/6.png"));
	    IMAGES[7] = ImageIO.read(getClass().getResource("images/7.png"));
	    IMAGES[8] = ImageIO.read(getClass().getResource("images/8.png"));
	    IMAGES[9] = ImageIO.read(getClass().getResource("images/9.png"));
	    IMAGES[10] = ImageIO.read(getClass().getResource("images/10.png"));
	    IMAGES[11] = ImageIO.read(getClass().getResource("images/11.png"));
	    IMAGES[12] = ImageIO.read(getClass().getResource("images/12.png"));
	    IMAGES[13] = ImageIO.read(getClass().getResource("images/13.png"));
	    IMAGES[14] = ImageIO.read(getClass().getResource("images/14.png"));
	}
	catch(IOException e)
	{
	    System.out.println("Failed to read image!\n");
	}
    }
    
    /**
     * tablica na dane o planszy plynace z modelu
     */
    private byte[][] minefieldData;
    
    /**
     * Metoda przeslaniajaca jej odpowiednik z nadklasy,
     * sluzaca do narysowania planszy.
     * @param g obiekt typu {@link Graphics}
     */
    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	for(int i = 0; i < this.minefieldData.length; i++)
	    for(int j = 0; j < this.minefieldData[0].length; j++)
		g.drawImage(IMAGES[this.minefieldData[i][j]], 25*i, 25*j, null);
    }
    
    /**
     * Metoda odswiezajaca panel - plansze
     * @param minefieldData tablica z danymi o planszy
     */
    public void refreshMinefield(byte[][] minefieldData)
    {
	this.minefieldData = minefieldData;
	repaint();
    }
}