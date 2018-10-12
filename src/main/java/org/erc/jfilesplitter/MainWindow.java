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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * The Class MainWindow.
 */
public class MainWindow {
	
	/** The main frame. */
	private Frame mainFrame;

	private TextField fileFile;
	
	private AWTProgressBar bar;
	
	private Splitter splitter;
	
	/**
	 * File selection
	 */
	private void fileSelector() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showSaveDialog(mainFrame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    try {
		    	File selectedFolder = fc.getSelectedFile();
				fileFile.setText(selectedFolder.getCanonicalPath());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(mainFrame, Messages.getString("MainWindow.bt.file.error.msg"), Messages.getString("MainWindow.bt.file.error.title"),JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}
	

	/**
	 * Instantiates a new main window.
	 */
	public MainWindow() {
		buildUI();
	}
	
	/**
	 * Build UI
	 */
	private void buildUI() {
		
		mainFrame = new Frame(Messages.getString("MainWindow.window.title")); //$NON-NLS-1$
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
	    msglabel.setText(Messages.getString("MainWindow.lb.file")); //$NON-NLS-1$
	    fileSelectionPanel.add(msglabel);
	    
	    fileFile = new TextField();
	    fileFile.setColumns(40);
	    fileFile.setEditable(false);
	    fileSelectionPanel.add(fileFile);
	    
	    Button pickFile = new Button();
	    pickFile.setLabel(Messages.getString("MainWindow.bt.file")); //$NON-NLS-1$
	    pickFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileSelector();
			}
		});
	    fileSelectionPanel.add(pickFile);
	   
	    // Options panel
	    Panel optionsPanel = new Panel();
	    optionsPanel.setLayout(new FlowLayout());
	    Label msglabelParts = new Label();
	    msglabelParts.setText(Messages.getString("MainWindow.lb.parsize")); //$NON-NLS-1$
	    optionsPanel.add(msglabelParts);
	    
	    TextField numberTextBox = new TextField();
	    numberTextBox.setColumns(8);
	    numberTextBox.setEditable(false);
	    optionsPanel.add(numberTextBox);
	    
	    Choice typeSep = new Choice();
	    for (SplitMode mode : SplitMode.values()) {
	    	typeSep.add(Messages.getString("MainWindow.cmb.type." + mode.name())); //$NON-NLS-1$
	    }
	    optionsPanel.add(typeSep);
	    
	    Button startButton = new Button();
	    startButton.setLabel(Messages.getString("MainWindow.bt.start")); //$NON-NLS-1$
	    optionsPanel.add(startButton);
	    startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = fileFile.getText();
				if(fileName!=null && !fileName.trim().isEmpty()) {
					SplitMode mode = SplitMode.values()[typeSep.getSelectedIndex()];
					splitter.split(fileName, mode);
				}
				bar.setCurrent(bar.getCurrent()+1);
			}
		});
	    
	    // Info Panel
	    Panel infoPanel = new Panel();
	    infoPanel.setLayout(new GridLayout(2,1));
	    bar = new AWTProgressBar();
	    bar.setFocusable(false);
	    bar.setSize(200, 40);
	    bar.setMax(100);
	    bar.setCurrent(50);
	    infoPanel.add(bar);
	    
	    mainFrame.add(fileSelectionPanel);
	    mainFrame.add(optionsPanel);
	    mainFrame.add(infoPanel);
	    
	    splitter = new Splitter();
	    splitter.setListener(new SplitterListener() {
			
			@Override
			public void tick(long currentSize,long totalSize,String currentFileName) {
				bar.setMax(totalSize);
				bar.setCurrent(currentSize);
				//TODO File Name
			}
			
			@Override
			public void start() {
				fileSelectionPanel.setEnabled(false);
				optionsPanel.setEnabled(false);
			}
			
			@Override
			public void end(int completed) {
				fileSelectionPanel.setEnabled(true);
				optionsPanel.setEnabled(true);
			}
		});
	    
	}
	
	/**
	 * Show
	 */
	public void show() {
		mainFrame.setVisible(true);
	}
}
