package com.sztelmach.saper.model.enums;

/**
 * Klasa typu wyliczeniowego do zapisu wartosci (etykiety)
 * pojedynczego pola (wartosc jest brana pod uwage gdy pole jest odsloniete).
 * <br>Mozliwe wartosci:
 * <br>{@link Label#EMPTY}
 * <br>{@link Label#ONE}
 * <br>{@link Label#TWO}
 * <br>{@link Label#THREE}
 * <br>{@link Label#FOUR}
 * <br>{@link Label#FIVE}
 * <br>{@link Label#SIX}
 * <br>{@link Label#SEVEN}
 * <br>{@link Label#EIGHT}
 * <br>{@link Label#MINE}
 * @author Rafal Sztelmach
 * @version 1.0
 */
public enum Label
{
    /**puste*/ EMPTY,
    /**1*/ ONE,
    /**2*/ TWO,
    /**3*/ THREE,
    /**4*/ FOUR,
    /**5*/ FIVE,
    /**6*/ SIX,
    /**7*/ SEVEN,
    /**8*/ EIGHT,
    /**mina*/ MINE;
    
    /**
     * Metoda "konwertujaca" liczbe calkowita na odpowiadajaca wartosc typu {@link Label}
     * @param number liczba calkowita podana do "konwersji" w wartosc typu {@link Label}
     * @return wartosc typu {@link Label} odpowiadajaca danej wartosci calkowitej
     */
    public static Label intToLabel(int number)
    {
	Label temp;
	switch(number)
	{
	    case 0: temp = EMPTY; break;
	    case 1: temp = ONE; break;
	    case 2: temp = TWO; break;
	    case 3: temp = THREE; break;
	    case 4: temp = FOUR; break;
	    case 5: temp = FIVE; break;
	    case 6: temp = SIX; break;
	    case 7: temp = SEVEN; break;
	    case 8: temp = EIGHT; break;
	    default: 
		throw new IllegalArgumentException("Unexpected parameter value!");
	}
	return temp;
    }
    
    /**
     * Metoda "konwertujaca" wartosc typu {@link Label} na odpowiadajaca wartosc typu int
     * @param label wartosc typu {@link Label} podana do "konwersji" w wartosc calkowita
     * @return wartosc typu calkowitego odpowiadajaca danej wartosci typu {@link Label}
     */
    public static int labelToInt(Label label)
    {
	int temp;
	switch(label)
	{
	    case EMPTY: temp = 0; break;
	    case ONE: temp = 1; break;
	    case TWO: temp = 2; break;
	    case THREE: temp = 3; break;
	    case FOUR: temp = 4; break;
	    case FIVE: temp = 5; break;
	    case SIX: temp = 6; break;
	    case SEVEN: temp = 7; break;
	    case EIGHT: temp = 8; break;
	    default: 
		throw new IllegalArgumentException("Unexpected parameter value!");
	}
	return temp;
    }
}