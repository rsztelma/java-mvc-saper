package com.sztelmach.saper.model;

import com.sztelmach.saper.model.enums.*;
import com.sztelmach.saper.addons.*;

/**
 * Klasa Model wedlug wzorca MVC.
 * <br>Reprezentuje model gry Saper.
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class Model
{
    /**
     * Obiekt typu {@link Minefield} reprezentujacy plansze do gry
     */
    private Minefield minefield;
    
    /**
     * Obiekt typu {@link Status} opisujacy stan gry
     */
    private Status status;
    
    /**
     * Obiekt typu {@link Timer} reprezentujacy zegar odmierzajacy czas gry
     */
    private final Timer timer;

    /**
     * Zmienna calkowita do wyswietlania jako pozostala ilosc nieoflagowanych min
     */
    private int minesRemaining;
    
    /**
     * Kontruktor domyslny klasy {@link	Model}. Tworzy plansze o parametrach
     * dla gry o trudnosci dla poczatkujacych. Ustawia stan gry {@link Model#status}
     * na {@link Status#START} oraz tworzy zegar odliczajacy czas gry {@link Model#timer}.
     */
    public Model()
    {
	this.minefield = new Minefield(Difficulty.BEGINNER_WIDTH,
		Difficulty.BEGINNER_HEIGHT, Difficulty.BEGINNER_NR_OF_MINES);
	this.minesRemaining = Difficulty.BEGINNER_NR_OF_MINES;
	this.status = Status.START;
	this.timer = new Timer();
    }

    /**
     * getter dla {@link Model#status}
     * @return wartosc typu {@link Status} zapisana w {@link Model#status}
     */
    public Status getStatus()
    {
	return status;
    }
    
    /**
     * setter dla {@link Model#status}
     * @param status dana wartosc typu {@link Status} do ustawienia
     */
    public void setStatus(Status status)
    {
	this.status = status;
    }

    /**
     * getter dla {@link Model#minesRemaining}
     * @return wartosc typu calkowitego zapisana w {@link Model#minesRemaining}
     *  - liczba nieoznaczonych min
     */
    public int getMinesRemaining()
    {
	return minesRemaining;
    }

    /**
     * setter dla {@link Model#minesRemaining}
     * @param minesRemaining wartosc calkowita do ustawienia
     */
    public void setMinesRemaining(int minesRemaining)
    {
	this.minesRemaining = minesRemaining;
    }
    
    /**
     * Metoda tworzaca nowa plansze do gry o zadanych parametrach.
     * @param width szerokosc planszy
     * @param height wysokosc planszy
     * @param nrOfMines liczba min
     */
    public void createNewGame(int width, int height, int nrOfMines)
    {
	this.minefield = new Minefield(width, height, nrOfMines);
	this.setMinesRemaining(nrOfMines);
	this.setStatus(Status.START);
	this.timer.setGameTime(0); /**reset timer*/
    }
    
    /**
     * Metoda sprawdzajaca czy stan gry to {@link Status#PLAYING}.
     * @return <i>true</i> jesli stan gry to - w trakcie rozgrywki ({@link Status#PLAYING})
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean isStatusPlaying()
    {
	return this.getStatus() == Status.PLAYING;
    }
    
    /**
     * Metoda sprawdzajaca czy stan gry to {@link Status#START}.
     * @return <i>true</i> jesli stan gry to - poczatek rozgrywki ({@link Status#START})
     * <br><i>false</i> w przeciwnym przypadku 
     */
    public boolean isStatusStart()
    {
	return this.getStatus() == Status.START;
    }
    
    /**
     * Metoda sprawdzajaca czy stan gry to {@link Status#VICTORY}.
     * @return <i>true</i> jesli stan gry to - zwyciestwo
     * ({@link Status#VICTORY})
     * <br><i>false</i> w przeciwnym przypadku 
     */
    public boolean isStatusVictory()
    {
	return this.getStatus() == Status.VICTORY;
    }
    
    /**
     * Metoda sprawdzajaca czy stan gry to {@link Status#DEFEAT}.
     * @return <i>true</i> jesli stan gry to - porazka
     * ({@link Status#DEFEAT})
     * <br><i>false</i> w przeciwnym przypadku 
     */
    public boolean isStatusDefeat()
    {
	return this.getStatus() == Status.DEFEAT;
    }
    
    /**
     * Metoda do inkrementacji czasu na zegarze.
     * Zegar jest inkrementowany tylko w czasie rozgrywki.
     * @return <i>true</i> jesli zegar zostal zinkrementowany
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean incrementTimer()
    {
	if(isStatusPlaying())
	{
	    this.timer.incrementGameTime();
	    return true;
	}
	else
	    return false;
    }
    
    /**
     * Metoda odkrywajaca pole o wskazanych w parametrach wspolrzednych.
     * <br>Do sekwencyjnego odsloniecia sasiadujacych pustych pol uzyto
     * metody rekurencyjnej {@link Minefield#uncoverSquare(int, int)}.
     * <br>Jesli stan gry byl {@link Status#START} zostaje on zmieniony na
     * {@link Status#PLAYING} w celu uruchomienia zegara.
     * @param width wspolrzedna pozioma pola na planszy
     * @param height wspolrzedna pionowa pola na planszy
     * @return <i>true</i> jesli odkryto conajmniej jedno pole
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean uncoverSquare(int width, int height)
    {
	boolean temp;
	switch(getStatus())
	{
	    case START:
		this.setStatus(Status.PLAYING);
		this.minefield.setupMinefield(width, height);
	    case PLAYING:
		temp = this.minefield.uncoverSquare(width, height);
		if(temp == false) /**jesli nie odkryto zadnego pola*/
		    return false;
		if(this.minefield.isMined(width, height))
		    this.setStatus(Status.DEFEAT);
		else if(this.minefield.isDone())
		    this.setStatus(Status.VICTORY);
		return true;
	    default:
		return false;
	}
    }
    
    /**
     * Metoda do zmiany oznaczenia pola o podanych wspolrzednych,
     * modyfikuje ona rowniez zmienna {@link Model#minesRemaining} w przypadku
     * gdy zmieniono oznaczenie.
     * @param width wspolrzedna pozioma pola
     * @param height wspolrzedna pionowa pola
     * @return <i>true</i> jesli dokonano zmiany oznaczenia pola
     * <br><i>false</i> w przeciwnym przypadku
     */
    public boolean markSquare(int width, int height)
    {
	if(this.isStatusPlaying())
	{
	    boolean temp = this.minefield.markSquare(width, height);
	    if(temp) /**jesli zmienilo sie oznaczenie na polu*/
	    {
		this.setMinesRemaining(this.minefield.getNrOfRemainingMines());
		return true;
	    }
	    else
		return false;
	}
	else
	    return false;
    }
    
    /**
     * Metoda prywatna do tworzenia obiektu przechowujacego
     * najwazniejsze informacje o grze - wariant dla gry w stanie poczatkowym
     * @return obiekt klasy {@link GameData} zawierajacy dane o polach,
     * czasie gry i ilosci nieoflagowanych bomb
     */
    private GameData startGameData()
    {
	GameData data = new GameData(this.getWidth(), this.getHeight());
	data.setMinesRemaining(this.getMinesRemaining());
	data.setTime(this.timer.getGameTime());
	for(int i = 0; i < this.getWidth(); i++)
	    for(int j = 0; j < this.getHeight(); j++)
		data.setData(i, j, (byte)11); /**ustaw wszystkie pola na zasloniete*/
	return data;
    }
    
    /**
     * Metoda prywatna do tworzenia obiektu przechowujacego
     * najwazniejsze informacje o grze - wariant dla gry w trakcie
     * @return obiekt klasy {@link GameData} zawierajacy dane o polach,
     * czasie gry i ilosci nieoflagowanych bomb
     */
    private GameData playingGameData()
    {
	GameData data = new GameData(this.getWidth(), this.getHeight());
	data.setMinesRemaining(this.getMinesRemaining());
	data.setTime(this.timer.getGameTime());
	for(int i = 0; i < this.getWidth(); i++)
	    for(int j = 0; j < this.getHeight(); j++)
	    {
		if(this.minefield.isUncovered(i, j))
		    data.setData(i, j,
			(byte)Label.labelToInt(this.minefield.getLabel(i, j)));
		else
		    data.setData(i, j,
			(byte)Mark.markToInt(this.minefield.getMark(i, j)));
	    }
	return data;
    }
    
    /**
     * Metoda prywatna do tworzenia obiektu przechowujacego
     * najwazniejsze informacje o grze - wariant dla gry wygranej
     * @return obiekt klasy {@link GameData} zawierajacy dane o polach,
     * czasie gry i ilosci nieoflagowanych bomb
     */
    private GameData victoryGameData()
    {
	GameData data = new GameData(this.getWidth(), this.getHeight());
	data.setMinesRemaining(0);
	data.setTime(this.timer.getGameTime());
	for(int i = 0; i < this.getWidth(); i++)
	    for(int j = 0; j < this.getHeight(); j++)
	    {
		if(this.minefield.isUncovered(i, j))
		    data.setData(i, j,
			(byte)Label.labelToInt(this.minefield.getLabel(i, j)));
		else	/**nieodkryte pola sa oznaczane jako oflagowane*/
		    data.setData(i, j, (byte)12);
	    }
	return data;
    }
    
    /**
     * Metoda prywatna do tworzenia obiektu przechowujacego
     * najwazniejsze informacje o grze - wariant dla gry przegranej
     * @return obiekt klasy {@link GameData} zawierajacy dane o polach,
     * czasie gry i ilosci nieoflagowanych bomb
     */
    private GameData defeatGameData()
    {
	GameData data = new GameData(this.getWidth(), this.getHeight());
	data.setMinesRemaining(this.getMinesRemaining());
	data.setTime(this.timer.getGameTime());
	for(int i = 0; i < this.getWidth(); i++)
	    for(int j = 0; j < this.getHeight(); j++)
	    {
		if(this.minefield.isUncovered(i, j))
		{
		    if(this.minefield.isMined(i, j)) /**pole ktore spowodowalo porazke*/
			data.setData(i, j, (byte)10); /**czerwona mina*/
		    else
			data.setData(i, j,
			    (byte)Label.labelToInt(this.minefield.getLabel(i, j)));
		}
		else if(this.minefield.isMined(i, j))
		{
		    if(this.minefield.isFlagged(i, j))
			data.setData(i, j, (byte)12); /**flaga*/
		    else
			data.setData(i, j, (byte)9); /**mina*/
		}
		else if(this.minefield.isFlagged(i, j))
		    data.setData(i, j, (byte)14); /**skreslona mina*/
		else
		    data.setData(i, j,
			(byte)Mark.markToInt(this.minefield.getMark(i, j)));
	    }
	return data;
    }
    
    /**
     * Metoda do tworzenia obiektu przechowujacego najwazniejsze
     * informacje o grze w zaleznosci od obecnego stanu gry
     * @return obiekt klasy {@link GameData} zawierajacy dane o polach,
     * czasie gry i ilosci nieoflagowanych bomb
     */
    public GameData setupGameData()
    {
	switch(this.status)
	{
	    case START:	    return startGameData();
	    case PLAYING:   return playingGameData();
	    case VICTORY:   return victoryGameData();
	    case DEFEAT:    return defeatGameData();
	    default:
		throw new IllegalArgumentException("Unexpected parameter value!");
	}
    }
    
    /**
     * getter dla szerokosci planszy
     * @return szerokosc planszy
     */
    public int getWidth()
    {
	return minefield.getWidth();
    }
    
    /**
     * getter dla wysokosci planszy
     * @return wysokosc planszy
     */
    public int getHeight()
    {
	return minefield.getHeight();
    }
    
    /**
     * getter dla liczby min na planszy
     * @return szerokosc planszy
     */
    public int getMines()
    {
	return minefield.getNrOfMines();
    }
}