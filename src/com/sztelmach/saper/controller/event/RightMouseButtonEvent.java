package com.sztelmach.saper.controller.event;

/**
 * Klasa reprezentujaca zdarzenie nacisniecia prawego przycisku myszy na planszy.
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class RightMouseButtonEvent extends ControllerEvent
{
    /**
     * wspolrzedna pozioma na planszy
     */
    private int x;
    
    /**
     * wspolrzedna pionowa na planszy
     */
    private int y;

    /**
     * Kontruktor klasy inicjalizujacy wspolrzedne.
     * @param x wspolrzedna pozioma do ustawienia
     * @param y wspolrzedna pionowa do ustawienia
     */
    public RightMouseButtonEvent(int x, int y)
    {
	this.x = x;
	this.y = y;
    }

    /**
     * getter dla {@link RightMouseButtonEvent#x}
     * @return wspolrzedna pozioma na planszy
     */
    public int getX()
    {
	return x;
    }

    /**
     * setter dla {@link RightMouseButtonEvent#x}
     * @param x wspolrzedna pozioma do ustawienia
     */
    public void setX(int x)
    {
	this.x = x;
    }

    /**
     * getter dla {@link RightMouseButtonEvent#y}
     * @return wspolrzedna pionowa na planszy 
     */
    public int getY()
    {
	return y;
    }

    /**
     * setter dla {@link RightMouseButtonEvent#y}
     * @param y wspolrzedna pozioma do ustawienia
     */
    public void setY(int y)
    {
	this.y = y;
    }
}