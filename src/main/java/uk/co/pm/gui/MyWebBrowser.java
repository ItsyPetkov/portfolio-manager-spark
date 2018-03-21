package uk.co.pm.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class MyWebBrowser extends JFrame {
	
	private JTextField URLbar;
	private JComboBox box;
	private JFrame optionsFrame;
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
				createOptionsFrame();
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
			e.printStackTrace();

			System.out.println("Something went wrong there! PLease make sure you have entered the http:// followed by the address of the website.");
		}
	}
	
	private void createOptionsFrame(){
		optionsFrame = new JFrame("Select option!");
		Container contentPane = optionsFrame.getContentPane();
		JLabel label = new JLabel("Please select one of the following.");
		
		String[] urls = {"Equities", "Hello", "Prices", "Quarters"};
		box = new JComboBox(urls);
		box.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String)box.getSelectedItem();
				switch(s){
				case "Equities":
					URLbar.setText("http://localhost:4567/equities");
					break;
				case "Hello":
					URLbar.setText("http://localhost:4567/hello");
					break;
				case "Prices":
					URLbar.setText("http://localhost:4567/equities/prices");
					break;
				case "Quarters":
					URLbar.setText("http://localhost:4567/equities/prices/Financials");
				}
			}
		});
		box.setSelectedItem(urls[0]);
		
		JButton apply = new JButton("Apply");
		apply.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getActionCommand() == "Apply"){
					optionsFrame.dispose();
				}
			}
			
		});
		
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.add(label);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel(new GridLayout(1,1));
		panel2.add(box);
		contentPane.add(panel2, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel(new GridLayout(1,1));
		panel3.add(apply);
		contentPane.add(panel3, BorderLayout.SOUTH);
		
		optionsFrame.pack();
		optionsFrame.setVisible(true);
	}
}
