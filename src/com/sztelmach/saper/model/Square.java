package com.sztelmach.saper.model;

import com.sztelmach.saper.model.enums.*;

/**
 * Klasa reprezentujaca pojedynczne pole na planszy
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class Square
{
    /**
     * zmienna logiczna mowiaca o tym czy pole jest odkryte
     * <br><i>true</i> - odkryte
     * <br><i>false</i> - nieodkryte
     */
    private boolean uncovered;
    
    /**
     * obiekt klasy {@link Label} reprezentujacy co znajduje sie na polu
     */
    private Label label;
    
    /**
     * obiekt klasy {@link Mark} reprezentujacy oznaczenie pola
     */
    private Mark mark;

    /**
     * Kontruktor klasy {@link Square}.
     * <br>Ustawia wartosci zmiennych na:
     * <br>{@link Square#uncovered} = <i>false</i>
     * <br>{@link Square#label} = {@link Label#EMPTY}
     * <br>{@link Square#mark} = {@link Mark#UNMARKED}
     */
    public Square()
    {
        this.uncovered = false;
        this.label = Label.EMPTY;
        this.mark = Mark.UNMARKED;
    }

    /**
     * getter dla zmiennej {@link Square#uncovered}
     * @return wartosc zmiennej {@link Square#uncovered}
     */
    public boolean isUncovered()
    {
        return uncovered;
    }
    
    /**
     * setter dla zmiennej {@link Square#uncovered}
     * @param uncovered wartosc logiczna do ustawienia
     */
    public void setUncovered(boolean uncovered)
    {
        this.uncovered = uncovered;
    }
    
    /**
     * getter dla zmiennej {@link Square#label}
     * @return wartosc zmiennej {@link Square#label}
     */
    public Label getLabel()
    {
        return label;
    }
    
    /**
     * setter dla zmiennej {@link Square#label}
     * @param label wartosc typu {@link Label} do ustawienia
     */
    public void setLabel(Label label)
    {
        this.label = label;
    }
    
    /**
     * getter dla zmiennej {@link Square#mark}
     * @return wartosc zmiennej {@link Square#mark}
     */
    public Mark getMark()
    {
        return mark;
    }
    
    /**
     * setter dla zmiennej {@link Square#mark}
     * @param mark wartosc typu {@link Mark} do ustawienia
     */
    public void setMark(Mark mark)
    {
        this.mark = mark;
    }
}