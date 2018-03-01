package uk.co.pm.gui;

import javax.swing.JFrame;

public class MyGUI {
	MyWebBrowser browser = new MyWebBrowser();
	
	public MyGUI(){
		closeBrowser();
	}
	
	private void closeBrowser(){
		browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
