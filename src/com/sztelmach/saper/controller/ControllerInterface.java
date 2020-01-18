package com.sztelmach.saper.controller;

import com.sztelmach.saper.controller.event.*;

/**
 * Interfejs do definiowania funkcji zaleznych od zdarzenia kontrolera
 * @author Rafal Sztelmach
 * @version 1.0
 */
public interface ControllerInterface
{
    /**
     * Metoda do implementacji funkcji zaleznych od zdarzen.
     * @param evt zdarzenie kontrolera
     */
    public void execute(ControllerEvent evt);
}