package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;



import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PrinterWindow extends JDialog {
	
	private static Controller controller;
	public TablePane tablePane;
	protected Patient patient;
	
	private JPanel buttonPane;
	private JButton printButton;
	private JButton cancelButton;
	private JTree fileTree;
	private JTextField userNameTF;
	private JTextField userTitleTF;
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField DOBTF;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new PrinterWindow(controller);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected class Patient{
		
		protected String firstName = "(!*NO FIRST NAME*!)";
		protected String lastName = "(!*NO LAST NAME*!)";
		protected String DOB = "(!*NO DOB*!)";
		
		protected String address = "(!*NO ADDRESS*!)";
		protected String phone = "(!*NO Phone*!)";
		
		public Patient(String firstName, String lastName, String DOB) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.DOB = DOB;
			//this.address = address;
			//this.phone = phone;
		}
		
	}

	
	public PrinterWindow(Controller x) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setTitle("Print Calculation");
		controller = x;
		setResizable(false);
		setBounds(100, 100, 549, 345);
		JPanel panel = new JPanel();
		{
			buttonPane = new JPanel();
			{
				printButton = new JButton("Print");
				printButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						printTemplate();
					}
				});
				printButton.setActionCommand("OK");
				getRootPane().setDefaultButton(printButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(buttonPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane, tabbedPane});
		
		JPanel panel_1_1 = new JPanel();
		tabbedPane.addTab("Patient Information", null, panel_1_1, null);
		
		JLabel lblNewLabel_1_2 = new JLabel("First Name:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		firstNameTF = new JTextField();
		firstNameTF.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Last Name:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lastNameTF = new JTextField();
		lastNameTF.setColumns(10);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("DOB:");
		lblNewLabel_1_1_1_1.setToolTipText("MM/DD/YYYY");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		DOBTF = new JTextField();
		DOBTF.setToolTipText("MM/DD/YYYY");
		DOBTF.setColumns(10);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1_1.createSequentialGroup()
							.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(firstNameTF, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel_1_1.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(lastNameTF, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_1_1.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(DOBTF, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_2)
						.addComponent(firstNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(1)
							.addComponent(lastNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(1)
							.addComponent(DOBTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("User Information", null, panel_1, null);
		
		userNameTF = new JTextField(controller.userName);
		userNameTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setUserName(userNameTF.getText());
			}
		});
		userNameTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Full Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		userTitleTF = new JTextField(controller.userTitle);
		userTitleTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setTitle(userTitleTF.getText());
			}
		});
		userTitleTF.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Title:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userNameTF, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(userTitleTF, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(userNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(userTitleTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(146, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Select a Template:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		fileTree = new JTree();
		scrollPane.setViewportView(fileTree);
		fileTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("templates") {
				{
					add(new DefaultMutableTreeNode(""));
				}
			}
		));
		panel.setLayout(gl_panel);
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap(344, Short.MAX_VALUE)
					.addComponent(printButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(printButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
		);
		gl_buttonPane.linkSize(SwingConstants.VERTICAL, new Component[] {printButton, cancelButton});
		gl_buttonPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {printButton, cancelButton});
		buttonPane.setLayout(gl_buttonPane);
		getContentPane().setLayout(groupLayout);
		
		getTemplates();
	}
	
	public void createPatientClass() {
		
		patient = new Patient(firstNameTF.getText(), lastNameTF.getText(), DOBTF.getText());
		
	}
	
	public void getTemplates() {
		
		File fileLocation;
		File[] filesList;
		final ArrayList<DefaultMutableTreeNode> nodes = new ArrayList();
		
		try {
			fileLocation = new File(controller.templateFileLocation);
			filesList = fileLocation.listFiles();
			
		} catch (Exception e) {
			return;
		}
		
		for (File x : filesList) {
			
			nodes.add(new DefaultMutableTreeNode(x));
			
		}
		
		fileTree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("templates") {
					{
						for(DefaultMutableTreeNode x : nodes) {
							add(new DefaultMutableTreeNode(x));
							
						}
					}
				}
			));
		
	}
	
	public void printTemplate() {
		
		if(tablePane.tableMain.getRowCount() > 0) {
			
			createPatientClass();
			
			HashMap<String, String> printElements = new HashMap();
			
			printElements.put("{{userName}}", controller.userName);
			printElements.put("{{userTitle}}", controller.userTitle);
			
			printElements.put("{{locationOfCare.name}}", controller.locationOfCare.name);
			printElements.put("{{locationOfCare.address}}", controller.locationOfCare.address);
			printElements.put("{{locationOfCare.phone}}", controller.locationOfCare.phone);
			printElements.put("{{locationOfCare.fax}}", controller.locationOfCare.fax);
			
			printElements.put("{{patient.firstName}}", patient.firstName);
			printElements.put("{{patient.lastName}}", patient.lastName);
			printElements.put("{{patient.DOB}}", patient.DOB);
			printElements.put("{{patient.address}}", patient.address);
			printElements.put("{{patient.phone}}", patient.phone);
			
			ArrayList<String> rowData = new ArrayList(tablePane.tableMain.getRowCount());
			String rowString;
			
			//rowData "cptCode|description|copayAmnt|coinsuranceAmnt|toDeductible|remainingDeductibletoMeet|rowTotal" last index will only be the final total.
			
			for(int i = 0; i < tablePane.tableMain.getRowCount(); i++) {
				rowString = "";
				for (int j = 0; j < tablePane.tableMain.getColumnCount(); j++) {
					if(j == 1) {
						rowString += controller.codeDescriptions.get(tablePane.tableMain.getValueAt(i,0).toString()) + "|";
						continue;
					}
					if((j+1) != tablePane.tableMain.getColumnCount()){
						rowString += tablePane.tableMain.getValueAt(i,j).toString() + "|";
					}else{			
						rowString += tablePane.tableMain.getValueAt(i,j).toString();
					}
					
				}
				
				rowData.add(rowString);
				
			}
			
			System.out.println("(printTemplate) " + rowData);
			System.out.println("(printTemplate) " + fileTree.getLastSelectedPathComponent());
			
			
			
			
			
		}
		
	}
}
