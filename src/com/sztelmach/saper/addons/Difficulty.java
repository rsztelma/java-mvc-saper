package com.sztelmach.saper.addons;

/**
 * Klasa zawierajaca dane o rozmiarach i ilosci min dla poziomow trudnosci gry
 * @author Rafal Sztelmach
 * @version 1.0
 */
public final class Difficulty
{
    public static final int BEGINNER_WIDTH = 8;
    public static final int BEGINNER_HEIGHT = 8;
    public static final int BEGINNER_NR_OF_MINES = 10;
    
    public static final int INTERMEDIATE_WIDTH = 16;
    public static final int INTERMEDIATE_HEIGHT = 16;
    public static final int INTERMEDIATE_NR_OF_MINES = 40;
    
    public static final int EXPERT_WIDTH = 30;
    public static final int EXPERT_HEIGHT = 16;
    public static final int EXPERT_NR_OF_MINES = 99;
    
    public static final int CUSTOM_MIN_WIDTH = 8;
    public static final int CUSTOM_MIN_HEIGHT = 8;
    public static final int CUSTOM_MIN_NR_OF_MINES = 5;
    public static final int CUSTOM_MAX_WIDTH = 30;
    public static final int CUSTOM_MAX_HEIGHT = 24;
    public static final int CUSTOM_MAX_NR_OF_MINES = 240;
    
    /**
     * Metoda prywatna sprawdzajaca czy liczba min spelnia regule dla trybu gry
     * z dowolnymi wymiarami.
     * <br>Wedlug reguly miny nie moga zajmowac wiecej niz 1/3 pol.
     * @param width szerokosc planszy
     * @param height wysokosc planszy
     * @param nrOfMines liczba min
     * @return <i>true</i> jesli liczba min spelnia regule
     * <br><i>false</i> w przeciwnym przypadku
     */
    public static boolean customMaxNrOfMines(int width, int height, int nrOfMines)
    {
	int maxNrOfMines = width * height / 3;
	return nrOfMines <= maxNrOfMines;
    }
}