package com.sztelmach.saper.model.enums;

/**
 * Klasa typu wyliczeniowego do oznaczania
 * pojedynczego pola.
 * <br>Mozliwe wartosci:
 * <br>{@link Mark#UNMARKED}
 * <br>{@link Mark#FLAGGED}
 * <br>{@link Mark#UNSURE}
 * @author Rafal Sztelmach
 * @version 1.0
 */
public enum Mark
{
    /**pole nieoznaczone*/
    UNMARKED,
    /**pole oznaczone jako zaminowane*/
    FLAGGED,
    /**pole oznaczone znakiem zapytania*/
    UNSURE;
    
    /**
     * Metoda "konwertujaca" wartosc typu {@link Mark} na odpowiadajaca wartosc typu int
     * @param mark wartosc typu {@link Mark} podana do "konwersji" w wartosc calkowita
     * @return wartosc typu calkowitego odpowiadajaca danej wartosci typu {@link Mark}
     */
    public static int markToInt(Mark mark)
    {
	int temp;
	switch(mark)
	{
	    case UNMARKED: temp = 11; break;
	    case FLAGGED: temp = 12; break;
	    case UNSURE: temp = 13; break;
	    default:
		throw new IllegalArgumentException("Unexpected parameter value!");
	}
	return temp;
    }
}