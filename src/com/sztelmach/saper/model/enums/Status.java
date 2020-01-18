package com.sztelmach.saper.model.enums;

/**
 * Klasa typu wyliczeniowego sluzaca do definiowania stanu gry.
 * <br>Mozliwe wartosci:
 * <br>{@link Status#START}
 * <br>{@link Status#PLAYING}
 * <br>{@link Status#VICTORY}
 * <br>{@link Status#DEFEAT}
 * @author Rafal Sztelmach
 * @version 1.0
 */
public enum Status
{
    /**poczatek rozgrywki - oczekiwanie na wykonanie pierwszego ruchu*/
    START,
    /**w trakcie rozgrywki*/
    PLAYING,
    /**gra wygrana*/
    VICTORY,
    /**gra przegrana*/
    DEFEAT
}