package com.sztelmach.saper.controller.event;

/**
 * Klasa reprezentujaca zdarzenie tworzenia nowej planszy.
 * @author Rafal Sztelmach
 */
public class CreateGameEvent extends ControllerEvent
{
    /**
     * szerokosc planszy
     */
    private int width;
    
    /**
     * wysokosc planszy
     */
    private int height;
    
    /**
     * liczba min
     */
    private int mines;

    /**
     * Kontruktor klasy inicjalizujacy zmienne danymi parametrami
     * @param width szerokosc planszy do ustawienia
     * @param height wysokosc planszy do ustawienia
     * @param mines liczba min do ustawienia
     */
    public CreateGameEvent(int width, int height, int mines)
    {
	this.width = width;
	this.height = height;
	this.mines = mines;
    }

    /**
     * getter dla {@link CreateGameEvent#width}
     * @return szerokosc planszy
     */
    public int getWidth()
    {
	return width;
    }

    /**
     * setter dla {@link CreateGameEvent#width}
     * @param width szerokosc planszy do ustawienia
     */
    public void setWidth(int width)
    {
	this.width = width;
    }

    /**
     * getter dla {@link CreateGameEvent#height}
     * @return wysokosc planszy
     */
    public int getHeight()
    {
	return height;
    }

    /**
     * setter dla {@link CreateGameEvent#height}
     * @param height wysokosc planszy do ustawienia
     */
    public void setHeight(int height)
    {
	this.height = height;
    }

    /**
     * getter dla {@link CreateGameEvent#mines}
     * @return liczba min
     */
    public int getMines()
    {
	return mines;
    }

    /**
     * setter dla {@link CreateGameEvent#mines}
     * @param mines liczba min do ustawienia
     */
    public void setMines(int mines)
    {
	this.mines = mines;
    }
}