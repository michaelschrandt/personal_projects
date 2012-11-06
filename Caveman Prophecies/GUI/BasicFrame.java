package GUI;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class BasicFrame extends JFrame implements ActionListener
{
	public static final int SCREEN_H = 500;
	public static final int SCREEN_W = 800;
	public BasicCanvas canvas ;
	JMenuItem menuGameSpeed;

	// constructor
	public BasicFrame() 
	{

		setTitle("The Caveman Prophecies") ;
		setSize(SCREEN_W, SCREEN_H) ;
		setName("THEFRAME");
		addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						onShutdown() ;
					}
				}
		) ;

		canvas = new BasicCanvas(this);
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

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		//setJMenuBar(menuBar);



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
		System.exit(0);
	}


	// About Box
	protected void onAbout() 
	{
		JOptionPane.showMessageDialog(null, "(c)JEFF BUTLER 2012") ;      
	}



}
