package com.sztelmach.saper;

import com.sztelmach.saper.controller.*;
import com.sztelmach.saper.model.*;
import com.sztelmach.saper.view.*;
import com.sztelmach.saper.controller.event.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.Timer;

/**
 * Klasa main - sluzaca do uruchomienia calej aplikacji:
 * <br>- tworzy instancje modelu, widoku oraz kontrolera
 * <br>- tworzy kolejke na zdarzenia w celu komunikacji widok-kontroler
 * <br>- tworzy zegar i generuje za jego pomoca zdarzenie czasu umieczajac je w kolejce
 * <br>- uruchamia kontroler, ktory steruje aplikacja
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class SaperMainClass
{
    public static void main(String[] args)
    {
        BlockingQueue<ControllerEvent> queue = new LinkedBlockingQueue<>();
	Model model = new Model();
	View view = new View(queue, model.setupGameData());
	Controller controller = new Controller(model, view, queue);
	Timer timer = new Timer(1000, (ActionEvent evt) ->
	{
	    try
	    {
		queue.put(new TimerEvent());
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!");
	    }
	});
	timer.start();
	controller.start();
    }
}