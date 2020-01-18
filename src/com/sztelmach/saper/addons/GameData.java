package com.sztelmach.saper.addons;

/**
 * Klasa sluzaca do przekazywania danych gry z modelu do widoku.
 * <br>Zawartosc tablicy {@link GameData#data} (liczby calkowite):
 * <br>0 - odsloniete puste pole
 * <br>1 - 8 - cyfry do wyswietlenia na polach
 * <br>9 - mina na polu (odslonieta automatycznie w wyniku przegranej)
 * <br>10 - mina na polu (odslonieta kliknieciem - podkreslona czerwonym kolorem)
 * <br>11 - zasloniete pole
 * <br>12 - pole oznaczone jako zaminowane - czerwona flaga
 * <br>13 - znak zapytania na polu
 * <br>14 - przy przegranej, pole oflagowane, na ktorym nie bylo miny - skreslona mina
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class GameData
{
    /**
     * tablica z danymi o polach (co wyswietlic na polu)
     */
    private final byte[][] data;
    
    /**
     * liczba calkowita do wyswietlenia w miejscu zegara - czas gry
     */
    private int time;
    
    /**
     * pozostala liczba min: (liczba_min - liczba_pol_oznaczonych_jako_zaminowane)
     */
    private int minesRemaining;

    /**
     * Konstruktor klasy {@link GameData}.
     * Tworzy tablice {@link GameData#data}.
     * @param width szerokosc planszy
     * @param height wysokosc planszy
     */
    public GameData(int width, int height)
    {
	this.data = new byte[width][height];
    }
    
    /**
     * Metoda - getter dla wartosci pola z tablicy {@link GameData#data}
     * @param width wspolrzedne poziome pola
     * @param height wspolrzende pionowe pola
     * @return wartosc calkowita pola z tablicy
     */
    public byte getData(int width, int height)
    {
	return this.data[width][height];
    }

    /**
     * Metoda - setter dla pojedynczego pola z tablicy {@link GameData#data}
     * @param width wspolrzedne poziome pola
     * @param height wspolrzende pionowe pola
     * @param data wartosc calkowita do ustawienia na podanych wspolrzednych
     */
    public void setData(int width, int height, byte data)
    {
	this.data[width][height] = data;
    }

    /**
     * getter dla {@link GameData#time}
     * @return wartosc calkowita - czas w sekundach od rozpoczecia gry
     */
    public int getTime()
    {
	return this.time;
    }

    /**
     * setter dla {@link GameData#time}
     * @param time czas w sekundach do ustawienia
     */
    public void setTime(int time)
    {
	this.time = time;
    }

    /**
     * getter dla {@link GameData#minesRemaining}
     * @return wartosc calkowita - liczba nieoflagowanych min
     */
    public int getMinesRemaining()
    {
	return this.minesRemaining;
    }

    /**
     * setter dla {@link GameData#minesRemaining}
     * @param minesRemaining liczba pozostalych min do ustawienia 
     */
    public void setMinesRemaining(int minesRemaining)
    {
	this.minesRemaining = minesRemaining;
    }
    
    /**
     * Metoda do czytania szerokosci planszy.
     * @return szerokosc planszy
     */
    public int getWidth()
    {
	return data.length;
    }
    
    /**
     * Metoda do czytania wysokosci planszy.
     * @return wysokosc planszy
     */
    public int getHeight()
    {
	return data[0].length;
    }
    
    /**
     * getter dla tablicy {@link GameData#data}
     * @return tablica {@link GameData#data} - informacje o planszy
     */
    public byte[][] getData()
    {
	return data;
    }
}