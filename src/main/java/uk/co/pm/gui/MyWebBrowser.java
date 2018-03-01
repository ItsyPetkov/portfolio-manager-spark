package uk.co.pm.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MyWebBrowser extends JFrame {
	
	private JTextField URLbar;
	private JEditorPane displayWindow;
	private MouseListener mListener;
	
	public MyWebBrowser(){
		super("My Web Browser");
		
		URLbar = new JTextField("Please enter a URL address!");
		URLbar.setForeground(Color.GRAY);
		URLbar.setFont(new java.awt.Font("Arial", Font.ITALIC, 12));
		URLbar.setEditable(true);
		URLbar.addMouseListener(mListener = new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				URLbar.setText("http://");
				URLbar.setFont(new JTextField().getFont());
				URLbar.setForeground(Color.black);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {	
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		});
		
		URLbar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadHTMLData(arg0.getActionCommand());
			}
			
		});
		add(URLbar, BorderLayout.NORTH);
		
		displayWindow = new JEditorPane();
		displayWindow.setEditable(false);
		displayWindow.addHyperlinkListener(new HyperlinkListener(){

			@Override
			public void hyperlinkUpdate(HyperlinkEvent arg0) {
				if(arg0.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
					loadHTMLData(arg0.getURL().toString());
				}
			}
			
		});
		
		add(new JScrollPane(displayWindow), BorderLayout.CENTER);
		setSize(800, 600);
		setVisible(true);
	}
	
	private void loadHTMLData(String URL){
		try{
			displayWindow.setPage(URL);
			URLbar.setText(URL);
		}
		catch(Exception e){
			System.out.println("Woah shit something went wrong there!");
		}
	}
}
