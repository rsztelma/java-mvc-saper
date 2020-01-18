package com.sztelmach.saper.view;

import com.sztelmach.saper.addons.*;
import com.sztelmach.saper.controller.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.BlockingQueue;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Klasa reprezentujaca widok aplikacji zrealizowana przy pomocy Swing'a.
 * @author Rafal Sztelmach
 * @version 1.0
 */
public class View
{
    /**
     * kolejka do komunikacji z kontrolerem
     */
    private final BlockingQueue<ControllerEvent> queue;
    
    /**
     * ramka okna aplikacji
     */
    private ViewFrame frame;

    /**
     * Kontruktor klasy. Tworzy widok oraz go odswieza.
     * @param queue kolejka do komunikacji z kontrolerem
     * @param data obiekt z informacjami o planszy do gry
     */
    public View(BlockingQueue<ControllerEvent> queue, GameData data)
    {
	this.queue = queue;
	SwingUtilities.invokeLater(() ->
	{
	    frame = new ViewFrame();
	    frame.setVisible(true);
	    refreshView(data);
	});
    }
    
    /**
     * Metoda sluzaca do odswiezania widoku na podstawie otrzymanych danych gry.
     * @param data dane o planszy do gry
     */
    public void refreshView(GameData data)
    {
	SwingUtilities.invokeLater(() ->
	{
	    frame.setPanelSize(data.getWidth()*25, data.getHeight()*25);
	    frame.setSize(data.getWidth()*25 + 16, data.getHeight()*25 + 93);
	    frame.setTimer(data.getTime());
	    frame.setRemainingMines(data.getMinesRemaining());
	    frame.refreshMinefield(data.getData());
	});
    }
    
    /**
     * Metoda wyswietlajaca okno z informacja o wygraniu gry.
     */
    public void victoryDialog()
    {
	SwingUtilities.invokeLater(() ->
	{
	    JOptionPane.showMessageDialog(frame, "Congratulation! You won!", "VICTORY", JOptionPane.INFORMATION_MESSAGE);
	});
    }

    /**
     * Metoda wyswietlajaca okno z informacja o przegraniu gry.
     */
    public void defeatDialog()
    {
	SwingUtilities.invokeLater(() ->
	{
	    JOptionPane.showMessageDialog(frame, "You lost! Good luck next time.", "DEFEAT", JOptionPane.INFORMATION_MESSAGE);
	});
    }
    
    /**
     * Metoda wyswietlajaca okno z informacja o ustawieniu zbyt duzej
     * ilosci min w oknie ustawien parametrow poziomu custom.
     */
    public void customOKButtonErrorDialog()
    {
	SwingUtilities.invokeLater(() ->
	{
	    JOptionPane.showMessageDialog(frame, "Too many mines!", "ERROR", JOptionPane.ERROR_MESSAGE);
	});
    }
    
    /**
     * Klasa reprezentujaca ramke widoku ze wszystkimi komponentami.
     * <br>Klasa umieszona wewnatrz klasy {@link View}, gdyz wymaga dostepu do
     * kolejki zadeklarowanej w tej klasie.
     * @author Rafal Sztelmach
     * @version 1.0
     */
    public class ViewFrame extends JFrame
    {

	private ViewPanel panel;
	private ButtonGroup difficultyButtonGroup;
	private JButton restartGameButton;
	private JButton customOKButton;
	private JButton customCancelButton;
	private JDialog customDialog;
	private JLabel minesRemainingLabel;
	private JLabel timerLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel minesLabel;
	private JLabel customDialogLabel;
	private JMenu mainMenu;
	private JMenu newGameMenu;
	private JMenuBar menuBar;
	private JMenuItem exitMenuItem;
	private JRadioButtonMenuItem beginnerRadioButtonMenuItem;
	private JRadioButtonMenuItem intermediateRadioButtonMenuItem;
	private JRadioButtonMenuItem expertRadioButtonMenuItem;
	private JRadioButtonMenuItem customRadioButtonMenuItem;
	private JPopupMenu.Separator mainMenuSeparator;
	private JSpinner widthSpinner;
	private JSpinner heightSpinner;
	private JSpinner minesSpinner;

	/**
	 * Kontruktor klasy {@link ViewFrame}.
	 * <br>Wywoluje metode inicjalizujaca komponenty
	 * {@link ViewFrame#initComponents()}
	 */
	public ViewFrame()
	{
	    initComponents();
	}

	/**
	 * Metoda inicjalizujaca komponenty widoku.
	 */
	private void initComponents()
	{
	    panel = new ViewPanel();
	    difficultyButtonGroup = new ButtonGroup();
	    restartGameButton = new JButton();
	    customOKButton = new JButton();
	    customCancelButton = new JButton();
	    customDialog = new JDialog();
	    minesRemainingLabel = new JLabel();
	    timerLabel = new JLabel();
	    widthLabel = new JLabel();
	    heightLabel = new JLabel();
	    minesLabel = new JLabel();
	    customDialogLabel = new JLabel();
	    mainMenu = new JMenu();
	    newGameMenu = new JMenu();
	    menuBar = new JMenuBar();
	    exitMenuItem = new JMenuItem();
	    beginnerRadioButtonMenuItem = new JRadioButtonMenuItem();
	    intermediateRadioButtonMenuItem = new JRadioButtonMenuItem();
	    expertRadioButtonMenuItem = new JRadioButtonMenuItem();
	    customRadioButtonMenuItem = new JRadioButtonMenuItem();
	    mainMenuSeparator = new JPopupMenu.Separator();
	    widthSpinner = new JSpinner();
	    heightSpinner = new JSpinner();
	    minesSpinner = new JSpinner();

	    customDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
	    customDialog.setTitle("Custom game");
	    customDialog.setAlwaysOnTop(true);

	    widthLabel.setText("Width");
	    heightLabel.setText("Height");
	    minesLabel.setText("Mines (no more than 1/3 of all squares)");
	    customDialogLabel.setText("Choose width, height and number of mines:");

	    customOKButton.setText("OK");
	    customOKButton.addActionListener((ActionEvent evt) ->
	    {
		customOKButtonActionPerformed(evt);
	    });

	    customCancelButton.setText("Cancel");
	    customCancelButton.addActionListener((ActionEvent evt) ->
	    {
		customCancelButtonActionPerformed(evt);
	    });

	    JFormattedTextField tf;

	    widthSpinner.setModel(new SpinnerNumberModel(Difficulty.CUSTOM_MIN_WIDTH, Difficulty.CUSTOM_MIN_WIDTH,
		    Difficulty.CUSTOM_MAX_WIDTH, 1));
	    tf = ((JSpinner.DefaultEditor) widthSpinner.getEditor()).getTextField();
	    tf.setEditable(false);

	    heightSpinner.setModel(new SpinnerNumberModel(Difficulty.CUSTOM_MIN_HEIGHT, Difficulty.CUSTOM_MIN_HEIGHT,
		    Difficulty.CUSTOM_MAX_HEIGHT, 1));
	    tf = ((JSpinner.DefaultEditor) heightSpinner.getEditor()).getTextField();
	    tf.setEditable(false);

	    minesSpinner.setModel(new SpinnerNumberModel(Difficulty.CUSTOM_MIN_NR_OF_MINES, Difficulty.CUSTOM_MIN_NR_OF_MINES,
		    Difficulty.CUSTOM_MAX_NR_OF_MINES, 1));
	    tf = ((JSpinner.DefaultEditor) minesSpinner.getEditor()).getTextField();
	    tf.setEditable(false);

	    GroupLayout customDialogLayout = new GroupLayout(customDialog.getContentPane());
	    customDialog.getContentPane().setLayout(customDialogLayout);
	    customDialogLayout.setHorizontalGroup(
		    customDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addGroup(customDialogLayout.createSequentialGroup()
				    .addContainerGap()
				    .addGroup(customDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					    .addGroup(customDialogLayout.createSequentialGroup()
						    .addGroup(customDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							    .addComponent(widthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(heightSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(minesSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						    .addGroup(customDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
							    .addComponent(widthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							    .addComponent(heightLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							    .addComponent(minesLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					    .addGroup(GroupLayout.Alignment.TRAILING, customDialogLayout.createSequentialGroup()
						    .addGap(0, 0, Short.MAX_VALUE)
						    .addComponent(customOKButton)
						    .addGap(18, 18, 18)
						    .addComponent(customCancelButton)
						    .addContainerGap())
					    .addComponent(customDialogLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	    );
	    customDialogLayout.setVerticalGroup(
		    customDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addGroup(customDialogLayout.createSequentialGroup()
				    .addGap(4, 4, 4)
				    .addComponent(customDialogLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				    .addGroup(customDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(widthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(widthLabel))
				    .addGap(18, 18, 18)
				    .addGroup(customDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(heightLabel)
					    .addComponent(heightSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				    .addGap(18, 18, 18)
				    .addGroup(customDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(minesSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					    .addComponent(minesLabel))
				    .addGap(18, 18, 18)
				    .addGroup(customDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(customOKButton)
					    .addComponent(customCancelButton))
				    .addGap(0, 11, Short.MAX_VALUE))
	    );

	    customDialog.pack();
	    customDialog.setLocationRelativeTo(this);
	    customDialog.setResizable(false);

	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Saper by Rafal Sztelmach");

	    minesRemainingLabel.setText("0");
	    timerLabel.setText("0");

	    restartGameButton.setText("Restart game");
	    restartGameButton.addActionListener((ActionEvent evt) ->
	    {
		restartGameButtonActionPerformed(evt);
	    });

	    panel.addMouseListener(new MouseAdapter()
	    {
		@Override
		public void mousePressed(MouseEvent evt)
		{
		    panelMousePressed(evt);
		}
	    });

	    GroupLayout panelLayout = new GroupLayout(panel);
	    panel.setLayout(panelLayout);
	    panelLayout.setHorizontalGroup(
		    panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addGap(0, 0, Short.MAX_VALUE)
	    );
	    panelLayout.setVerticalGroup(
		    panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addGap(0, 0, Short.MAX_VALUE)
	    );

	    mainMenu.setText("Menu");
	    newGameMenu.setText("New game");

	    difficultyButtonGroup.add(beginnerRadioButtonMenuItem);
	    beginnerRadioButtonMenuItem.setSelected(true);
	    beginnerRadioButtonMenuItem.setText("Beginner");
	    beginnerRadioButtonMenuItem.addActionListener((ActionEvent evt) ->
	    {
		beginnerRadioButtonMenuItemActionPerformed(evt);
	    });
	    newGameMenu.add(beginnerRadioButtonMenuItem);

	    difficultyButtonGroup.add(intermediateRadioButtonMenuItem);
	    intermediateRadioButtonMenuItem.setText("Intermediate");
	    intermediateRadioButtonMenuItem.addActionListener((ActionEvent evt) ->
	    {
		intermediateRadioButtonMenuItemActionPerformed(evt);
	    });
	    newGameMenu.add(intermediateRadioButtonMenuItem);

	    difficultyButtonGroup.add(expertRadioButtonMenuItem);
	    expertRadioButtonMenuItem.setText("Expert");
	    expertRadioButtonMenuItem.addActionListener((ActionEvent evt) ->
	    {
		expertRadioButtonMenuItemActionPerformed(evt);
	    });
	    newGameMenu.add(expertRadioButtonMenuItem);

	    difficultyButtonGroup.add(customRadioButtonMenuItem);
	    customRadioButtonMenuItem.setText("Custom");
	    customRadioButtonMenuItem.addActionListener((ActionEvent evt) ->
	    {
		customRadioButtonMenuItemActionPerformed(evt);
	    });
	    newGameMenu.add(customRadioButtonMenuItem);

	    mainMenu.add(newGameMenu);
	    mainMenu.add(mainMenuSeparator);

	    exitMenuItem.setText("Exit");
	    exitMenuItem.addActionListener((ActionEvent evt) ->
	    {
		exitMenuItemActionPerformed(evt);
	    });
	    mainMenu.add(exitMenuItem);

	    menuBar.add(mainMenu);
	    setJMenuBar(menuBar);

	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
		    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addGroup(layout.createSequentialGroup()
				    .addGap(5)
				    .addComponent(minesRemainingLabel)
				    .addGap(5)
				    .addComponent(restartGameButton)
				    .addGap(5)
				    .addComponent(timerLabel))
			    .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    );
	    layout.setVerticalGroup(
		    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    .addGroup(layout.createSequentialGroup()
				    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					    .addComponent(minesRemainingLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					    .addComponent(timerLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					    .addComponent(restartGameButton))
				    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				    .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );

	    pack();
	    setLocationRelativeTo(null);
	    setResizable(false);
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk OK w oknie dialogowym. 
	 */
	private void customOKButtonActionPerformed(ActionEvent evt)
	{
	    int width = (Integer)widthSpinner.getValue();
	    int height = (Integer)heightSpinner.getValue();
	    int mines = (Integer)minesSpinner.getValue();
	    try
	    {
		queue.put(new CreateGameEvent(width, height, mines));
		customDialog.setVisible(false);
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!\n");
	    }
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk Cancel w oknie dialogowym. 
	 */
	private void customCancelButtonActionPerformed(ActionEvent evt)
	{
	    customDialog.setVisible(false);
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk Restart game. 
	 */
	private void restartGameButtonActionPerformed(ActionEvent evt)
	{
	    try
	    {
		queue.put(new RestartGameEvent());
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!\n");
	    }
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w panel do gry (ktores pole). 
	 */
	private void panelMousePressed(MouseEvent evt)
	{
	    try
	    {
		if(evt.getButton() == MouseEvent.BUTTON1)
		    queue.put(new LeftMouseButtonEvent(evt.getX()/25, evt.getY()/25));
		else
		    queue.put(new RightMouseButtonEvent(evt.getX()/25, evt.getY()/25));
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!\n");
	    }
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk 
	 * utworzenia nowej gry w menu o poziomie trudnosci beginner.
	 */
	private void beginnerRadioButtonMenuItemActionPerformed(ActionEvent evt)
	{
	    try
	    {
		queue.put(new CreateGameEvent(Difficulty.BEGINNER_WIDTH,
		    Difficulty.BEGINNER_HEIGHT, Difficulty.BEGINNER_NR_OF_MINES));
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!\n");
	    }
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk 
	 * utworzenia nowej gry w menu o poziomie trudnosci intermediate.
	 */
	private void intermediateRadioButtonMenuItemActionPerformed(ActionEvent evt)
	{
	    try
	    {
		queue.put(new CreateGameEvent(Difficulty.INTERMEDIATE_WIDTH,
		    Difficulty.INTERMEDIATE_HEIGHT, Difficulty.INTERMEDIATE_NR_OF_MINES));
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!\n");
	    }
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk 
	 * utworzenia nowej gry w menu o poziomie trudnosci expert.
	 */
	private void expertRadioButtonMenuItemActionPerformed(ActionEvent evt)
	{
	    try
	    {
		queue.put(new CreateGameEvent(Difficulty.EXPERT_WIDTH,
		    Difficulty.EXPERT_HEIGHT, Difficulty.EXPERT_NR_OF_MINES));
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!\n");
	    }
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk 
	 * utworzenia nowej gry w menu o poziomie trudnosci custom.
	 */
	private void customRadioButtonMenuItemActionPerformed(ActionEvent evt)
	{
	    customDialog.setVisible(true);
	}

	/**
	 * Metoda obslugujaca zdarzenie klikniecia w przycisk 
	 * wyjscia z gry w menu.
	 */
	private void exitMenuItemActionPerformed(ActionEvent evt)
	{
	    try
	    {
		queue.put(new QuitGameEvent());
	    }
	    catch(InterruptedException e)
	    {
		System.out.println("Queue.put interrupted!\n");
	    }
	}
	
	/**
	 * setter dla etykiety zegara
	 * @param seconds liczba sekund do ustawienia na etykiecie zegara
	 */
	public void setTimer(int seconds)
	{
	    timerLabel.setText(String.valueOf(seconds));
	}
	
	/**
	 * setter dla etykiety pozostalych min
	 * @param mines liczba min do ustawienia na etykiecie pozostalych min
	 */
	public void setRemainingMines(int mines)
	{
	    minesRemainingLabel.setText(String.valueOf(mines));
	}
	
	/**
	 * Metoda odswiezajaca panel z polami - plansze do gry.
	 * @param data tablica z danymi o planszy na podstawie ktorej odrysowywana
	 * jest cala plansza
	 */
	public void refreshMinefield(byte data[][])
	{
	    panel.refreshMinefield(data);
	}
	
	/**
	 * Metoda ustawiajaca rozmiar panelu na ten podany w parametrach.
	 * @param width szerokosc panelu do ustawienia
	 * @param height wysokosc panelu do ustawienia
	 */
	public void setPanelSize(int width, int height)
	{
	    panel.setSize(width, height);
	}
    }
}