package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JList;
import javax.swing.UIManager;
import java.util.Comparator;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Toolkit;

public class MainWindow extends JFrame {

	private JPanel mainPane;
	private JTabbedPane tabbedPane;

   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	try {
		
            
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           
    } 
    catch (Exception e) {
       // handle exception
    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	static Controller controller = new Controller();
	
	public MainWindow() {
		setTitle("Good Faith Calculator");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 804);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Tab Options");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		mnNewMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Tab");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				addTab();
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Remove Tab\r\n");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(tabbedPane.getTabCount() == 1) {
					tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
					addTab();
				}else {
					tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
					for(int i = 0; i < tabbedPane.getTabCount(); i++) {
						tabbedPane.setTitleAt(i, "Tab: " + (i+1));
					}
					
				}
				
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnOptions = new JMenu("Settings");
		mnOptions.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnOptions.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnOptions);
		
		JCheckBoxMenuItem mntmNewMenuItem_3 = new JCheckBoxMenuItem("Hide unpriced codes");
		mntmNewMenuItem_3.setToolTipText("Hides a majority of CPT codes with 0 or missing prices. (Insurance Specific)");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.hideCodesSetting = !controller.hideCodesSetting;
			}
		});
		mnOptions.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Print Current Table");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterWindow printerWindow = new PrinterWindow(controller);
				try {
					printerWindow.tablePane = (TablePane) tabbedPane.getSelectedComponent();
				} catch (Exception e2) {
					System.out.println("(Print Current Table)" + e2);
				}
				
			}
		});
		mnOptions.add(mntmNewMenuItem_2);
		mainPane = new JPanel();
		mainPane.setBackground(SystemColor.inactiveCaption);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setFocusable(false);
		
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		GroupLayout gl_mainPane = new GroupLayout(mainPane);
		gl_mainPane.setHorizontalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
		);
		gl_mainPane.setVerticalGroup(
			gl_mainPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
		);
		
		mainPane.setLayout(gl_mainPane);
		
		
		
		addTab();
	}
	
	
	
	public void addTab() {
		
		tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), new TablePane(controller));
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
		
	}
}
