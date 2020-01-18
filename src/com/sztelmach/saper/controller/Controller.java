package com.sztelmach.saper.controller;

import com.sztelmach.saper.addons.Difficulty;
import com.sztelmach.saper.model.*;
import com.sztelmach.saper.view.*;
import com.sztelmach.saper.controller.event.*;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

/**
 * Klasa reprezentujaca kontroler aplikacji. Posiada odwolania na model i widok,
 * kolejke zdarzen do komunikacji z widokiem oraz mape zdarzen na postawie ktorych
 * podejmuje odpowiednie dzialania.
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class Controller
{
    /**
     * Model aplikacji
     */
    private final Model model;
    
    /**
     * Widok aplikacji
     */
    private final View view;
    
    /**
     * kolejka na zdarzenia kontrolera do komunikacji z widokiem
     */
    private final BlockingQueue<ControllerEvent> queue;
    
    /**
     * mapa przypisujaca zdarzeniom odpowiednie metody do wykonania przez kontroler
     */
    private final HashMap<Class<? extends ControllerEvent>, ControllerInterface> map;

    /**
     * Kontruktor kontrolera. Ustawia otrzymane parametry, tworzy mape zdarzen
     * i wywoluje funkcje, ktora ja inicjalizuje.
     * @param model model aplikacji
     * @param view widok aplikacji
     * @param queue kolejka na zdarzenia kontrolera
     */
    public Controller(Model model, View view, BlockingQueue<ControllerEvent> queue)
    {
	this.model = model;
	this.view = view;
	this.queue = queue;
	this.map = new HashMap<>();
	setupMap();
    }
    
    /**
     * Metoda, ktora inicjalizuje mape {@link Controller#map}
     */
    private void setupMap()
    {
	map.put(RestartGameEvent.class, (ControllerInterface) (ControllerEvent evt) ->
	{
	    model.createNewGame(model.getWidth(), model.getHeight(), model.getMines());
	    view.refreshView(model.setupGameData());
	});
	map.put(CreateGameEvent.class, (ControllerInterface) (ControllerEvent evt) ->
	{
	    CreateGameEvent event = (CreateGameEvent) evt;
	    if(Difficulty.customMaxNrOfMines(event.getWidth(), event.getHeight(), event.getMines()))
	    {
		model.createNewGame(event.getWidth(), event.getHeight(), event.getMines());
		view.refreshView(model.setupGameData());
	    }
	    else
		view.customOKButtonErrorDialog();
	});
	map.put(TimerEvent.class, (ControllerInterface) (ControllerEvent evt) ->
	{
	    if(model.incrementTimer())
		view.refreshView(model.setupGameData());
	});
	map.put(QuitGameEvent.class, (ControllerInterface) (ControllerEvent evt) ->
	{
	    System.exit(0);
	});
	map.put(LeftMouseButtonEvent.class, (ControllerInterface) (ControllerEvent evt) ->
	{
	    LeftMouseButtonEvent event = (LeftMouseButtonEvent) evt;
	    if(model.uncoverSquare(event.getX(), event.getY()))
	    {
		view.refreshView(model.setupGameData());
		if(model.isStatusVictory())
		    view.victoryDialog();
		else if(model.isStatusDefeat())
		    view.defeatDialog();
	    }
	});
	map.put(RightMouseButtonEvent.class, (ControllerInterface) (ControllerEvent evt) ->
	{
	    RightMouseButtonEvent event = (RightMouseButtonEvent) evt;
	    if(model.markSquare(event.getX(), event.getY()))
		view.refreshView(model.setupGameData());
	});
    }
    
    /**
     * Metoda symulujaca prace kontrolera. Kontroler pobiera zdarzenie z kolejki,
     * dopasowuje je do funkcji z mapy i wykonuje odpowiednia funkcje, to wszystko
     * w nieskonczonej petli.
     */
    public void start()
    {
	ControllerEvent evt;
	ControllerInterface conInt;
	while(true)
	{
	    try
	    {
		evt = queue.take();
		conInt = map.get(evt.getClass());
		conInt.execute(evt);
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.take interrupted!");
	    }
	}
    }
}