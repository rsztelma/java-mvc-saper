package com.sztelmach.saper.model;

/**
 * Klasa reprezentujaca zegar odmierzajacy czas gry
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class Timer
{
    /**
     * zmienna przechowujaca czas od rozpoczecia gry mierzony w sekundach
     */
    private int gameTime;

    /**
     * getter dla zmiennej {@link Timer#gameTime}
     * @return czas gry w sekundach
     */
    public int getGameTime()
    {
	return this.gameTime;
    }

    /**
     * setter dla zmiennej {@link Timer#gameTime}
     * @param gameTime czas zadany w sekundach do ustawienia
     */
    public void setGameTime(int gameTime)
    {
	this.gameTime = gameTime;
    }
    
    /**
     * Metoda inkrementujaca czas gry - zmienna {@link Timer#gameTime}
     */
    public void incrementGameTime()
    {
	this.gameTime++;
    }
}