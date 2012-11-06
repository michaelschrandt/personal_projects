package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import Game.Game;

public class BasicCanvas extends JPanel implements MouseListener, ActionListener, KeyListener
{
	BasicFrame parent = null;
	// member vars

	//Timer time;
	File file = null;
	JFileChooser fc = null;
	JToolBar toolbar = new JToolBar(1);
	Graphics g;
	public JLabel tellPlayer;
	// constructor
	public BasicCanvas(BasicFrame frame)
	{
		setLayout(new BorderLayout()) ;

		parent = frame;

		addMouseListener(this);
		parent.addKeyListener(this);


		int width = WIDTH;
		int height = HEIGHT;

		//time = new Timer(250, this);
		//time.setActionCommand("t");
		setBackground(Color.white);
		JButton me = new JButton();

		toolbar.setFloatable(true);
		toolbar.setBounds(width*2, height*2, width, height);
		tellPlayer = new JLabel();
		tellPlayer.setPreferredSize(new Dimension(800, 70));
		tellPlayer.setBackground(Color.BLACK);
		tellPlayer.setOpaque(true);
		frame.add(BorderLayout.SOUTH, tellPlayer);
		tellPlayer.setAlignmentY(TOP_ALIGNMENT);
		tellPlayer.setVerticalAlignment(SwingConstants.TOP);


		tellPlayer.setForeground(Color.WHITE);

		repaint();
		tellPlayer.setVisible(false);
	}


	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g) ;

		Game.draw();
		//print the final buffer to the screen
		//drawBuffer(buffer);
	}

	public JLabel getLabel()
	{
		return tellPlayer;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e);
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand() ;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Game.handleEvents(e);

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}