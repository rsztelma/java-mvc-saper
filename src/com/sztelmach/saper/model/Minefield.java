package com.sztelmach.saper.model;

import com.sztelmach.saper.model.enums.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Klasa reprezentujaca plansze do gry
 * <br>Plansza to tablica pol - obiektow klasy {@link Square}
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class Minefield
{
    /**
     * tablica dwuwymiarowa obiektow typu {@link Square}
     */
    private final Square[][] minefield;
    
    /**
     * szerokosc planszy
     */
    private final int width;
    
    /**
     * wysokosc planszy
     */
    private final int height;
    
    /**
     * laczna liczba min na planszy
     */
    private final int nrOfMines;
    
    /**
     * liczba pol oznaczonych jako zaminowane,
     * <br>sluzy do sledzenia ile pozostalo nieoznaczonych min na planszy,
     */
    private int nrOfSquaresFlagged;
    
    /**
     * liczba odkrytych pol,
     * <br>sluzy do sledzenia postepu gry
     */
    private int nrOfUncoveredSquares;

    /**
     * Kontruktor klasy {@link Minefield}.
     * <br>Ustawia wartosci zmiennych:
     * <br>{@link Minefield#nrOfSquaresFlagged} = 0
     * <br>{@link Minefield#nrOfUncoveredSquares} = 0
     * <br>Tworzy rowniez tablice dwuwymiarowa {@link Minefield#minefield} z
     * obiektow typu {@link Square}.
     * @param width zadana szerokosc planszy wpisana do {@link Minefield#width}
     * @param height zadana wysokosc planszy wpisana do {@link Minefield#height}
     * @param nrOfMines zadana liczba min wpisana do {@link Minefield#nrOfMines}
     */
    public Minefield(int width, int height, int nrOfMines)
    {
        this.width = width;
        this.height = height;
        this.nrOfMines = nrOfMines;
        this.nrOfSquaresFlagged = 0;
        this.nrOfUncoveredSquares = 0;
        this.minefield = new Square[this.width][this.height];
        for(int i = 0; i < this.width; i++)
            for(int j = 0; j < this.height; j++)
                minefield[i][j] = new Square();
    }
    
    /**
     * getter dla zmiennej {@link Minefield#nrOfSquaresFlagged}
     * @return wartosc zmiennej {@link Minefield#nrOfSquaresFlagged}
     */
    public int getNrOfSquaresFlagged()
    {
        return this.nrOfSquaresFlagged;
    }
    
    /**
     * setter dla zmiennej {@link Minefield#nrOfSquaresFlagged}
     * @param nrOfSquaresMarkedAsMined wartosc calkowita do ustawienia
     */
    public void setNrOfSquaresFlagged(int nrOfSquaresMarkedAsMined)
    {
        this.nrOfSquaresFlagged = nrOfSquaresMarkedAsMined;
    }
    
    /**
     * getter dla zmiennej {@link Minefield#nrOfUncoveredSquares}
     * @return wartosc zmiennej {@link Minefield#nrOfUncoveredSquares}
     */
    public int getNrOfUncoveredSquares()
    {
        return this.nrOfUncoveredSquares;
    }
    
    /**
     * setter dla zmiennej {@link Minefield#nrOfUncoveredSquares}
     * @param nrOfUncoveredSquares wartosc calkowita do ustawienia
     */
    public void setNrOfUncoveredSquares(int nrOfUncoveredSquares)
    {
        this.nrOfUncoveredSquares = nrOfUncoveredSquares;
    }
    
    /**
     * getter dla stalej {@link Minefield#width}
     * @return szerokosc planszy
     */
    public int getWidth()
    {
        return this.width;
    }
    
    /**
     * getter dla stalej {@link Minefield#height}
     * @return wysokosc planszy
     */
    public int getHeight()
    {
        return this.height;
    }
    
    /**
     * getter dla stalej {@link Minefield#nrOfMines}
     * @return laczna liczba min na planszy
     */
    public int getNrOfMines()
    {
        return this.nrOfMines;
    }
    
    /**
     * Metoda sprawdzajaca czy odkryto wszystkie niezaminowane pola.
     * <br>Metoda sluzy do sprawdzania czy wygrano gre, aby wygrac nie trzeba
     * oflagowac wszystkich min.
     * @return <i>true</i> jesli odkryto wszystkie niezaminowane pola
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean isDone()
    {
        return (getNrOfUncoveredSquares() == getWidth() * getHeight() - getNrOfMines());
    }
    
    /**
     * Metoda sprawdzajaca czy pole o danych wspolrzednych jest odkryte
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return <i>true</i> jesli pole o podanych wsporzednych jest odkryte
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean isUncovered(int width, int height)
    {
        return (minefield[width][height].isUncovered());
    }
    
    /**
     * Metoda sprawdzajaca czy pole o danych wspolrzednych jest zaminowane
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return <i>true</i> jesli pole o podanych wsporzednych jest zaminowane
     * <br><i>false</i> w przeciwnym przypadku 
     */
    public boolean isMined(int width, int height)
    {
        return (minefield[width][height].getLabel() == Label.MINE);
    }
    
    /**
     * Metoda sprawdzajaca czy pole o danych wspolrzednych jest puste
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return <i>true</i> jesli pole o podanych wsporzednych jest puste
     * <br><i>false</i> w przeciwnym przypadku 
     */
    public boolean isEmpty(int width, int height)
    {
	return (minefield[width][height].getLabel() == Label.EMPTY);
    }
    
    /**
     * Metoda sprawdzajaca czy pole o danych wspolrzednych jest oznaczone
     * jako zaminowane
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return <i>true</i> jesli pole o podanych wsporzednych jest
     * oznaczone jako zaminowane
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean isFlagged(int width, int height)
    {
        return (minefield[width][height].getMark() == Mark.FLAGGED);
    }
    
    /**
     * Metoda zwracajaca liczbe pozostalych min
     * @return liczba pozostalych min
     * <br>moze przyjmowac wartosc ujemna gdy liczba oznaczonych pol
     * jako zaminowane jest wieksza od liczby faktycznych min
     */
    public int getNrOfRemainingMines()
    {
        return (this.getNrOfMines() - this.getNrOfSquaresFlagged());
    }
    
    /**
     * Metoda do zmiany odsloniecia/zasloniecia pola
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @param uncovered wartosc logiczna do ustawienia
     */
    public void setUncovered(int width, int height, boolean uncovered)
    {
        minefield[width][height].setUncovered(uncovered);
    }
    
    /**
     * Metoda do odczytu wartosci pola
     * (czy pole jest puste, ma wpisana cyfre lub jest zaminowane)
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return wartosc typu {@link Label} danego pola
     */
    public Label getLabel(int width, int height)
    {
        return (minefield[width][height].getLabel());
    }
    
    /**
     * Metoda do zmiany wartosci pola
     * (ustawienie miny, cyfry lub pustego pola)
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @param label wartosc typu {@link Label} do ustawienia
     */
    public void setLabel(int width, int height, Label label)
    {
        minefield[width][height].setLabel(label);
    }
    
    /**
     * Metoda do odczytu oznaczenia danego pola
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return wartosc typu {@link Mark} - oznaczenie danego pola
     */        
    public Mark getMark(int width, int height)
    {
        return (minefield[width][height].getMark());
    }
    
    /**
     * Metoda do zmiany oznaczenia pola na zadane
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @param mark wartosc typu {@link Mark} do ustawienia
     */
    public void setMark(int width, int height, Mark mark)
    {
        minefield[width][height].setMark(mark);
    }
    
    /**
     * Metoda do zmiany oznaczenia pola
     * <br>oznaczenie jest determinowane na podstawie obecnie ustawionego
     * <br>cykl oznaczen: {@link Mark#UNMARKED} - {@link Mark#FLAGGED} 
     * - {@link Mark#UNSURE}
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return <i>true</i> jesli dokonano zmiany oznaczenia
     * <br><i>false</i> jesli nie dokonano zmiany - pole bylo odkryte
     */
    public boolean markSquare(int width, int height)
    {
        /**jesli pole odsloniete*/
        if(this.isUncovered(width, height))
            return false;
        /**jesli nieodsloniete*/
        switch(this.getMark(width, height))
        {
            /**jesli nieoznaczony*/
            case UNMARKED:
                /**oznacz jako zaminowany*/
                this.setMark(width, height, Mark.FLAGGED);
                /**inkrementuj licznik pol oznaczonych jako zaminowane*/
                this.setNrOfSquaresFlagged(this.getNrOfSquaresFlagged() + 1);
                break;
            /**jesli zaminowany*/
            case FLAGGED:
                /**oznacz jako znak zapytania*/
                this.setMark(width, height, Mark.UNSURE);
                /**dekrementuj licznik pol oznaczonych jako zaminowane*/
                this.setNrOfSquaresFlagged(this.getNrOfSquaresFlagged() - 1);
                break;
            /**jesli znak zapytania*/
            case UNSURE:
                /**ustaw jako nieoznaczony*/
                this.setMark(width, height, Mark.UNMARKED);
                break;
        }
        return true;
    }
    
    /**
     * Metoda prywatna sluzaca do ustawienia min na planszy.
     */
    private void setupMines()
    {
        ArrayList<Integer> list = new ArrayList<>(this.getWidth()*this.getHeight());
        for(int i = 0; i < this.getWidth()*this.getHeight(); i++)
	    list.add(i);
        Collections.shuffle(list, new Random()); /**losowe przemieszanie wartosci*/
        for(int k = 0; k < this.nrOfMines; k++) /**ustawienie min*/
            this.setLabel(list.get(k)/getHeight(), list.get(k)%getHeight(), Label.MINE);
    }
    
    /**
     * Metoda prywatna sluzaca do ustawienia cyfr na polach.
     * Sprawdzane jest otoczenie kazdego niezaminowanego pola i na podstawie
     * ilosci min w tym otoczeniu (sasiadujace pola) wpisywana jest do pola
     * odpowiadajaca wartosc typu {@link Label} przy uzyciu metody statycznej
     * {@link Label#intToLabel}
     */
    private void setupNumbers()
    {
        int temp; /**zmienna do zliczania min w okolicy pola*/
	for(int i = 0; i < this.getWidth(); i++) /**kazde pole osobno oprocz min*/
            for(int j = 0; j < this.getHeight(); j++)
		if(this.isMined(i, j) == false)
		{
		    temp = 0; /**na poczatku jest zero min w otoczeniu pola*/
		    for(int m = i - 1; m <= i + 1; m++) /**sprawdzenie 8 pol naokolo*/
			for(int n = j - 1; n <= j + 1; n++) /**oraz siebie, co nic nie zmieni*/
			    if(m >= 0 && m < this.getWidth() && n >= 0 && n < this.getHeight())
				if(this.isMined(m, n)) /**jak zaminowany to inkrementacja temp*/
				    temp++;
		    this.setLabel(i, j, Label.intToLabel(temp));
		}
    }
    
    /**
     * Metoda sluzaca do ustawienia pola do gry - min i cyfr poprzez wywolanie
     * dwoch metod prywatnych {@link Minefield#setupMines()}
     * oraz {@link Minefield#setupNumbers()}.
     */
    public void setupMinefield()
    {
	setupMines();
	setupNumbers();
    }
    
    /**
     * Metoda odkrywajaca niezaminowane pole. Pole zostanie odsloniete jesli:
     * <br>nie jest juz odkryte
     * <br>nie jest oznaczone jako zaminowane
     * <br><br>Metoda jest wywolywana rekurencyjnie rowniez dla sasiednich
     * pol w celu odsloniecia pustych sasiadow, ale tylko wtedy gdy obecne
     * pole jest puste.
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return <i>true</i> w przypadku sukcesu - odkrycia pola
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean uncoverSquare(int width, int height)
    {
	if(this.isUncovered(width, height) || this.isFlagged(width, height))
	    return false; /**pole jest odkryte lub oznaczone jako zaminowane*/
	this.setMark(width, height, Mark.UNMARKED); /**ustaw jako nieoznaczone*/
	this.setUncovered(width, height, true); /**odkryj i zwieksz licznik odkrytych pol*/
	this.setNrOfUncoveredSquares(this.getNrOfUncoveredSquares() + 1);
	if(this.isEmpty(width, height)) /**jesli jest puste to sprawdz sasiadow*/
	{ /**odkrywanie sasiednich pustych pol przy pomocy rekurencji*/
	    for(int i = width - 1; i <= width + 1; i++)
		for(int j = height - 1; j <= height + 1; j++)
		    if(i >= 0 && i < getWidth() && j >= 0 && j < getHeight())
			if(i != width || j != height)
			    uncoverSquare(i, j);
	}
	return true;
    }
}