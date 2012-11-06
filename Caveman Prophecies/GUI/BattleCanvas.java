package GUI;



import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Game.Game;

public class BattleCanvas extends JFrame implements ActionListener
{
	public static final int SCREEN_H = 650;
	public static final int SCREEN_W = 800;
	public BattleScreen canvas ;
	JMenuItem menuGameSpeed;

	// constructor
	public BattleCanvas() 
	{

		setTitle("Battle!") ;
		setSize(SCREEN_W, SCREEN_H) ;
		setName("THEFRAME");
		addWindowListener(
				new WindowAdapter() {
					
					public void windowClosing(WindowEvent e) {
						System.exit(0);
						//onShutdown() ;
						//						Game.getFrame().setVisible(true);
						//						Game.setNextState(Game.getMap(Game.getCurrentMapIndex()));
					}
				}
		) ;
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		canvas = new BattleScreen(this);
		// The File menu

		JMenu menuFile = new JMenu("File") ;
		menuFile.setMnemonic('F');

		JMenuItem menuFileQuit = new JMenuItem("Quit",'Q') ;
		menuFileQuit.addActionListener(this) ;
		menuFile.add(menuFileQuit);
		menuFileQuit.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));


		// All apps should probably have an About Box
		JMenu menuHelp = new JMenu("Help") ;
		menuHelp.setMnemonic('H');
		JMenuItem menuHelpAbout = new JMenuItem("About",'a') ;
		menuHelpAbout.addActionListener(this) ;
		menuHelp.add(menuHelpAbout);
		menuHelpAbout.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));

		//JMenuBar menuBar = new JMenuBar();
		//		menuBar.add(menuFile);
		//		menuBar.add(menuHelp);
		//		setJMenuBar(menuBar);



		getContentPane().add(canvas);


		setResizable(false);
		this.setVisible(true);


	}

	// Menu actions typically handled in the Frame
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println("something");
		String action = e.getActionCommand() ;
		if (action.compareTo("Quit")==0)
			onShutdown();
		else if (action.compareTo("About")==0)
			onAbout();

	}

	// Shutdown procedure
	protected void onShutdown() 
	{
		// code to ensure a clean exit goes here
		newClose();
		this.dispose();
		//WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		//Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

	}

	public void newClose()
	{

		Game.getFrame().setVisible(true);
		Game.setNextState(Game.getMap(Game.getCurrentMapIndex()));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	// About Box
	protected void onAbout() 
	{
		JOptionPane.showMessageDialog(null, "(c)JEFF BUTLER 2012") ;      
	}



}
