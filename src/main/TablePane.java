package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;


public class TablePane extends JPanel{

	DecimalFormat rt = new DecimalFormat("0.00###########");

	public JTable tableMain;
	private JComboBox insuranceCBX;
	private JComboBox codeCBX;
	private JComboBox favoritesCBX;
	public double enteredDeductible;
	private JTextField deductibleTextField;
	private JLabel displayDeductibleTextField;
	public JLabel totalLabel;
	public double finalTotal;
	private Controller controller;

	private long previousCalculateCall;
	private JLabel insuranceCount;
	private JLabel codeCount;
	private JLabel favoritesCount;

	public TablePane(Controller x) {
		controller = x;
		
		setLayout(new MigLayout("", "[900\r\npx,grow,left]", "[top][bottom][40px:n]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		add(panel, "cell 0 0,grow");

		JPanel insurancePanel = new JPanel();
		insurancePanel.setFocusable(false);

		JPanel codePanel = new JPanel();
		codePanel.setFocusable(false);

		JLabel lblNewLabel_1_1_1 = new JLabel("CPT Code:  ");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		codeCBX = new JComboBox();
		codeCBX.setAlignmentX(Component.RIGHT_ALIGNMENT);
		codeCBX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codeCBX.setName("");
		codeCBX.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		JButton btnNewButton = new JButton("Add Code\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRowNormal();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));

		codeCount = new JLabel("X\r\n");
		codeCount.setHorizontalAlignment(SwingConstants.LEFT);
		codeCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_codePanel = new GroupLayout(codePanel);
		gl_codePanel.setHorizontalGroup(
			gl_codePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(codeCBX, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(codeCount, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_codePanel.setVerticalGroup(
			gl_codePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codePanel.createSequentialGroup()
					.addGroup(gl_codePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_codePanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_1)
							.addComponent(codeCBX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton))
						.addComponent(codeCount, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		codePanel.setLayout(gl_codePanel);

		JPanel favoritesPanel = new JPanel();
		favoritesPanel.setFocusable(false);

		JLabel lblNewLabel_1_1_2 = new JLabel("Favorites:  ");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		favoritesCBX = new JComboBox();
		favoritesCBX.setAlignmentX(Component.RIGHT_ALIGNMENT);
		favoritesCBX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		favoritesCBX.setName("");
		favoritesCBX.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		JButton btnAddFavorite = new JButton("Add Favorite\r\n");
		btnAddFavorite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRowFavorite();
			}
		});
		btnAddFavorite.setFont(new Font("Tahoma", Font.PLAIN, 12));

		favoritesCount = new JLabel("");
		favoritesCount.setHorizontalAlignment(SwingConstants.LEFT);
		favoritesCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_favoritesPanel = new GroupLayout(favoritesPanel);
		gl_favoritesPanel.setHorizontalGroup(
			gl_favoritesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_favoritesPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(favoritesCBX, 0, 487, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(favoritesCount, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddFavorite, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_favoritesPanel.setVerticalGroup(
			gl_favoritesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_favoritesPanel.createSequentialGroup()
					.addGroup(gl_favoritesPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_favoritesPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1_2)
							.addComponent(btnAddFavorite)
							.addComponent(favoritesCBX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(favoritesCount, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		favoritesPanel.setLayout(gl_favoritesPanel);

		JPanel deducPanel = new JPanel();
		deducPanel.setFocusable(false);

		JLabel lblNewLabel_1_1_3 = new JLabel("Deductible: $");
		lblNewLabel_1_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));

		deductibleTextField = new JTextField();
		deductibleTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					enteredDeductible = Double.parseDouble(getDeductibleTextFieldText());
					setDisplayDeductibleTextFieldText("Deductible: $ " + getDeductibleTextFieldText());
					displayDeductibleTextField.setForeground(Color.black);

				}
				catch(Exception x){
					displayDeductibleTextField.setForeground(Color.red);
				}
				calculateTotal();
			}
		});


		deductibleTextField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		deductibleTextField.setName("");
		deductibleTextField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GroupLayout gl_deducPanel = new GroupLayout(deducPanel);
		gl_deducPanel.setHorizontalGroup(
			gl_deducPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deducPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1_3)
					.addGap(12)
					.addComponent(deductibleTextField, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addGap(475))
		);
		gl_deducPanel.setVerticalGroup(
			gl_deducPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deducPanel.createSequentialGroup()
					.addGroup(gl_deducPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_3)
						.addComponent(deductibleTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		deducPanel.setLayout(gl_deducPanel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_1_1 = new JLabel("Insurance:  ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		insuranceCBX = new JComboBox();
		insuranceCBX.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(insuranceCBX.getSelectedItem() != "") {
					codeCBX.setEnabled(true);
					favoritesCBX.setEnabled(true);

					codeCBX.removeAllItems();
					favoritesCBX.removeAllItems();
					
					if(controller.codeDescriptions == null) {return;}

					for (String s : controller.codeDescriptions.keySet()) {
						if(insuranceCBX.getSelectedItem() != null) {
							String cost = controller.codes.get(s.split(" \\| ")[0]).get(insuranceCBX.getSelectedItem().toString()).get(1).toString().trim();
							if((cost == "" || cost == "0" || cost == null) && controller.hideCodesSetting){

								//codeCBX.addItem(s);

							}else {
								codeCBX.addItem(s + " | " + controller.codeDescriptions.get(s));

							}
						}
					}

					codeCount.setText("(" + codeCBX.getItemCount() + ")");

					for (String s : controller.favoriteCodeDescriptions.keySet()) {
						if(insuranceCBX.getSelectedItem() != null) {
							String cost = controller.codes.get(s.split(" \\| ")[0]).get(insuranceCBX.getSelectedItem().toString()).get(1).toString().trim();
							if((cost == "" || cost == "0" || cost == null) && controller.hideCodesSetting){

								//favoritesCBX.addItem(s);

							}else {
								favoritesCBX.addItem(s + " | " + controller.favoriteCodeDescriptions.get(s));

							}
						}
					}

					favoritesCount.setText("(" + favoritesCBX.getItemCount() + ")");

				}else {
					codeCBX.setEnabled(false);
					favoritesCBX.setEnabled(false);
				}
			}
		});
		insuranceCBX.setAlignmentX(Component.RIGHT_ALIGNMENT);
		insuranceCBX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		insuranceCBX.setName("");
		insuranceCBX.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		insuranceCount = new JLabel("");
		insuranceCount.setHorizontalAlignment(SwingConstants.LEFT);
		insuranceCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_insurancePanel = new GroupLayout(insurancePanel);
		gl_insurancePanel.setHorizontalGroup(
			gl_insurancePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_insurancePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(insuranceCBX, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(insuranceCount, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
		);
		gl_insurancePanel.setVerticalGroup(
			gl_insurancePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_insurancePanel.createSequentialGroup()
					.addGroup(gl_insurancePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1)
						.addComponent(insuranceCBX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(insuranceCount, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		insurancePanel.setLayout(gl_insurancePanel);
		panel.add(insurancePanel);
		panel.add(codePanel);
		panel.add(favoritesPanel);
		panel.add(deducPanel);

		JPanel deducPanel2 = new JPanel();
		deducPanel2.setFocusable(false);
		panel.add(deducPanel2);

		displayDeductibleTextField = new JLabel("Deductible: $");
		displayDeductibleTextField.setForeground(SystemColor.textText);
		displayDeductibleTextField.setHorizontalAlignment(SwingConstants.LEFT);
		displayDeductibleTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnNewButton_1 = new JButton("Remove Row(s)");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableMain.getRowCount() == 0) {return;}
				DefaultTableModel model = (DefaultTableModel) tableMain.getModel();
				int[] selectedRows = tableMain.getSelectedRows();
				for(int i = selectedRows.length - 1; i >= 0 ; i -- ) {
					model.removeRow(selectedRows[i]);
				}
				if(selectedRows.length == 0) {

					model.removeRow(tableMain.getRowCount() -1);

				}
				calculateTotal();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton btnNewButton_1_1 = new JButton("Share Co-Insurance\r\n");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double coInsuranceAmnt = 0.0;
				for(int i = 0; i < tableMain.getRowCount(); i++) {
					if(tableMain.getValueAt(i, 4) != null &&(Double.parseDouble(String.valueOf(tableMain.getValueAt(i, 4)))) != 0.0 && coInsuranceAmnt == 0.0) {
						coInsuranceAmnt = Double.parseDouble(String.valueOf(tableMain.getValueAt(i, 4)));
					}
					tableMain.setValueAt(coInsuranceAmnt, i , 4);
				}
				calculateTotal();
			}
		});
		btnNewButton_1_1.setToolTipText("Applies the first non-zero co-insurance value to all other rows.");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnNewButton_1_1_1 = new JButton("Recalculate");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateTotal();
			}
		});
		btnNewButton_1_1_1.setToolTipText("Forces a recalculate.");
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GroupLayout gl_deducPanel2 = new GroupLayout(deducPanel2);
		gl_deducPanel2.setHorizontalGroup(
			gl_deducPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deducPanel2.createSequentialGroup()
					.addContainerGap()
					.addComponent(displayDeductibleTextField, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_deducPanel2.setVerticalGroup(
			gl_deducPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deducPanel2.createSequentialGroup()
					.addGroup(gl_deducPanel2.createParallelGroup(Alignment.BASELINE)
						.addComponent(displayDeductibleTextField)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_1_1)
						.addComponent(btnNewButton_1_1_1))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		deducPanel2.setLayout(gl_deducPanel2);

		JScrollPane scrollPane = new JScrollPane();

		add(scrollPane, "cell 0 1,grow");

		tableMain = new JTable();
		tableMain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableMain);
		tableMain.getTableHeader().setReorderingAllowed(false);
		tableMain.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPT Code", "Cost", "Affects Deductible", "Co-Pay", "(%) Co-Insurance", "To Deductable", "Remaining Deductile", "To Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Boolean.class, String.class, String.class, String.class, String.class, String.class
			};
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, false, false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableMain.getColumnModel().getColumn(0).setResizable(false);
		tableMain.getColumnModel().getColumn(0).setPreferredWidth(74);
		tableMain.getColumnModel().getColumn(1).setResizable(false);
		tableMain.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableMain.getColumnModel().getColumn(2).setResizable(false);
		tableMain.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableMain.getColumnModel().getColumn(2).setMinWidth(50);
		tableMain.getColumnModel().getColumn(3).setResizable(false);
		tableMain.getColumnModel().getColumn(4).setResizable(false);
		tableMain.getColumnModel().getColumn(4).setPreferredWidth(102);
		tableMain.getColumnModel().getColumn(5).setResizable(false);
		tableMain.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableMain.getColumnModel().getColumn(6).setResizable(false);
		tableMain.getColumnModel().getColumn(6).setPreferredWidth(114);
		tableMain.getColumnModel().getColumn(7).setResizable(false);

		//ADD LISTENER TO CALL CALCULATE TOTAL AUTO...

		tableMain.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				calculateTotal();

			}
		});


		/*table.addKeyListener(new KeyListener().keyPressed(KeyEvent e) {



			@Override
			public void keyPressed(KeyEvent e) {
				int focusRow = table.getSelectedRow();
				int focusColumn = table.getSelectedColumn();

				if(table.getValueAt(focusRow, focusColumn).toString().matches("0")) {

					table.setValueAt("", focusRow, focusColumn);
					System.out.println("(Table FL) focus location: " + focusRow + " | " + focusColumn);

				}


			}
		});

		*/

		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 0 2,grow");
		panel_1.setLayout(null);

		totalLabel = new JLabel("Patient Pays: $");
		totalLabel.setHorizontalAlignment(SwingConstants.LEFT);
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		totalLabel.setBounds(509, 10, 257, 25);
		panel_1.add(totalLabel);

		//POPULATE CBX FUNCTION

		insuranceCBX.addItem("");
		codeCBX.addItem("");
		favoritesCBX.addItem("");
		
		if(controller.insuranceNames == null) {return;}

		for (String s : controller.insuranceNames) {
			insuranceCBX.addItem(s);
		}


		//--END--
	}

	public void addRowNormal() {
		
		if(codeCBX.getSelectedItem() != null) {
			
			String selectedCode = codeCBX.getSelectedItem().toString().split(" \\| ")[0];
			String selectedInsurance = insuranceCBX.getSelectedItem().toString();

			if(selectedCode != "" && selectedInsurance != "") {

				DefaultTableModel model = (DefaultTableModel) tableMain.getModel();

				Vector<Object> tempVector = new Vector();

				tempVector.add(selectedCode);
				tempVector.add(controller.codes.get(selectedCode).get(selectedInsurance).get(1));
				tempVector.add(false);
				tempVector.add(0);
				tempVector.add(0);

				model.addRow(tempVector);

				calculateTotal();

			}
			
		}

		
	}

	public void addRowFavorite() {
		
		if(favoritesCBX.getSelectedItem() != null) {
			
			String selectedCode = favoritesCBX.getSelectedItem().toString().split(" \\| ")[0];
			String selectedInsurance = insuranceCBX.getSelectedItem().toString();

			if(selectedCode != "" && selectedInsurance != "") {
				DefaultTableModel model = (DefaultTableModel) tableMain.getModel();

				Vector tempVector = new Vector();

				tempVector.add(selectedCode);
				tempVector.add(controller.codes.get(selectedCode).get(selectedInsurance).get(1));
				tempVector.add(false);
				tempVector.add(0);
				tempVector.add(0);

				model.addRow(tempVector);

				calculateTotal();

			}
		}

	}

	public void calculateTotal() {

		if(System.currentTimeMillis() - previousCalculateCall == 0 || tableMain.getRowCount()== 0) {
			//System.out.println("(Calculate Total) Function called too soon. Exiting...");
			previousCalculateCall = System.currentTimeMillis();
        	return;
		}

		previousCalculateCall = System.currentTimeMillis();

		DefaultTableModel tableModel = (DefaultTableModel) tableMain.getModel();

		finalTotal = 0.0;
        Double remainingDeductible = enteredDeductible;

        if(remainingDeductible == null){
            remainingDeductible = 0.0;
        }

        for(int i = 0; i < tableModel.getRowCount(); i++){
            for(int j = 0; j < tableModel.getColumnCount(); j++){
                if(tableModel.getValueAt(i, 2) == null){
                	tableModel.setValueAt(false, i, 2);
                }
                if(tableModel.getValueAt(i, j) == null || String.valueOf((tableModel.getValueAt(i, j))).equalsIgnoreCase("")){
                	tableModel.setValueAt(0.00, i ,j);
                }
            }

            ArrayList<Object> rowInfo = new ArrayList();

            for(int b = 1; b < tableMain.getColumnCount(); b++) {
            	if(tableModel.getValueAt(i, b) == null || String.valueOf((tableModel.getValueAt(i, b))).equalsIgnoreCase("")){
            		rowInfo.add(0.0);
            	}else {
            		rowInfo.add(tableModel.getValueAt(i, b));
            	}


            }
            System.out.println(i + " " + rowInfo);
            //rowInfo [0] is cost ... [4] is deductible met amt
            double cost = Double.parseDouble(String.valueOf(rowInfo.get(0)));
            boolean countsToDeduc = Boolean.parseBoolean(String.valueOf(rowInfo.get(1)));
            double copay = Double.parseDouble(String.valueOf(rowInfo.get(2)));
            double coinsurancePercent = Double.parseDouble(String.valueOf(rowInfo.get(3)));
            double deductibleMetAmount = Double.parseDouble(String.valueOf(rowInfo.get(4)));
            double rowTotal = 0;

            tableModel.setValueAt("0", i, 5);
            tableModel.setValueAt("0", i, 6);

            if(enteredDeductible != 0 && countsToDeduc){
                if(cost >= remainingDeductible){
                    deductibleMetAmount = Math.abs(remainingDeductible - cost);
                    tableModel.setValueAt(String.format("%.2f", remainingDeductible), i, 5);
                    remainingDeductible = 0.0;
                }if(cost < remainingDeductible){
                    remainingDeductible = remainingDeductible - cost;
                    rowTotal = cost;
                    tableModel.setValueAt(round(cost), i, 5);
                    tableModel.setValueAt(String.format("%.2f", remainingDeductible), i, 6);
                }if(remainingDeductible == 0.0) {
                    if (coinsurancePercent != 0.0) {
                        rowTotal = calculateCoinsurance(coinsurancePercent, deductibleMetAmount);
                    } else {
                        rowTotal = deductibleMetAmount;
                    }
                    rowTotal += Double.parseDouble(String.valueOf(tableModel.getValueAt(i, 5)));

                }
            }else{
                if(coinsurancePercent != 0.0){
                	//Add a && countsToDeduc to remove the ability to have a row be affected by coins without a deductible
                    rowTotal = calculateCoinsurance(coinsurancePercent, cost);
                }else if(copay == 0){
                    rowTotal = cost;
                }
            }

            if(copay != 0.0){rowTotal = copay;}
            rowTotal = round(rowTotal);
            tableModel.setValueAt(String.format("%.2f", rowTotal),i,7);
            finalTotal += rowTotal;

        }

        finalTotal = round(finalTotal);
        totalLabel.setText("Patient Pays: $" + String.format("%.2f", finalTotal));

    }

	private double calculateCoinsurance(Double percent, Double amount){
        return (percent / 100) * amount;
    }

	private Double round(Double n){return Double.parseDouble(rt.format(n));}

	public String getDeductibleTextFieldText() {
		return deductibleTextField.getText();
	}
	public void setDeductibleTextFieldText(String text) {
		deductibleTextField.setText(text);
	}
	public String getDisplayDeductibleTextFieldText() {
		return displayDeductibleTextField.getText();
	}
	public void setDisplayDeductibleTextFieldText(String text_1) {
		displayDeductibleTextField.setText(text_1);
	}
	public TableModel getTableModel() {
		return tableMain.getModel();
	}
	public void setTableModel(TableModel model) {
		tableMain.setModel(model);
	}
}
