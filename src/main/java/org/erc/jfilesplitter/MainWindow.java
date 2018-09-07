/**
    This file is part of J-File-Splitter.

    J-File-Splitter is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    J-File-Splitter is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with J-File-Splitter.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.erc.jfilesplitter;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Class MainWindow.
 */
public class MainWindow {
	
	/** The main frame. */
	private Frame mainFrame;

	/**
	 * Instantiates a new main window.
	 */
	public MainWindow() {
		mainFrame = new Frame("File Splitter");
		mainFrame.setSize(500,200);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new GridLayout(4, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
		
		// File Panel
		Panel fileSelectionPanel = new Panel();
		
		fileSelectionPanel.setLayout(new FlowLayout());
		Label msglabel = new Label();
		msglabel.setAlignment(Label.RIGHT);
	    msglabel.setText("File");
	    fileSelectionPanel.add(msglabel);
	    
	    TextField fileFile = new TextField();
	    fileFile.setColumns(40);
	    fileFile.setEditable(false);
	    fileSelectionPanel.add(fileFile);
	    
	    Button pickFile = new Button();
	    pickFile.setLabel("...");
	    fileSelectionPanel.add(pickFile);
	    
	    // Options panel
	    
	    Panel optionsPanel = new Panel();
	    optionsPanel.setLayout(new FlowLayout());
	    Label msglabelParts = new Label();
	    msglabelParts.setText("Part size");
	    optionsPanel.add(msglabelParts);
	    
	    TextField numberTextBox = new TextField();
	    numberTextBox.setColumns(8);
	    numberTextBox.setEditable(false);
	    optionsPanel.add(numberTextBox);
	    
	    Choice typeSep = new Choice();
	    typeSep.add("bytes");
	    typeSep.add("Kilobytes");
	    typeSep.add("Megabytes");
	    typeSep.add("Gigabytes");
	    typeSep.add("Lines");
	    optionsPanel.add(typeSep);
	    
	    
	    Button startButton = new Button();
	    startButton.setLabel("Start");
	    optionsPanel.add(startButton);
	    
	    
	    Panel infoPanel = new Panel();
	    
	    
	    mainFrame.add(fileSelectionPanel);
	    mainFrame.add(optionsPanel);
	    mainFrame.add(infoPanel);
	    mainFrame.setVisible(true); 
		
	}
}
