package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bank.Bank;
import bank.Client;
import bank.SavingAccount;
import bank.SpendingAccount;

import javax.swing.ButtonGroup;

public class AccountO extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public AccountO(Bank bank, Client client, DefaultTableModel model2) {
		
		
		Object[] rowData = new Object[4];
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(25, 60, 70, 14);
		contentPanel.add(lblAmount);
		
		textField_1 = new JTextField();
		textField_1.setBounds(125, 57, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(25, 85, 46, 14);
		contentPanel.add(lblType);
		
		JRadioButton rdbtnSpendingAccount = new JRadioButton("Spending account");
		buttonGroup.add(rdbtnSpendingAccount);
		rdbtnSpendingAccount.setBounds(122, 81, 148, 23);
		contentPanel.add(rdbtnSpendingAccount);
		
		JRadioButton rdbtnSavingAccount = new JRadioButton("Saving account");
		buttonGroup.add(rdbtnSavingAccount);
		rdbtnSavingAccount.setBounds(122, 107, 132, 23);
		contentPanel.add(rdbtnSavingAccount);
		
		textField_2 = new JTextField();
		textField_2.setBounds(190, 172, 86, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);
		
		JLabel lblDepositPerioddays = new JLabel("Deposit period (days):");
		lblDepositPerioddays.setBounds(180, 147, 160, 14);
		contentPanel.add(lblDepositPerioddays);
		lblDepositPerioddays.setVisible(false);
		
		rdbtnSavingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDepositPerioddays.setVisible(true);
				textField_2.setVisible(true);
			}
		});
	
		rdbtnSpendingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDepositPerioddays.setVisible(false);
				textField_2.setVisible(false);
			}
			
		});
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							bank.deserialize();
							if (rdbtnSavingAccount.isSelected()) {
								SavingAccount sa = new SavingAccount();
								if (!textField_1.getText().equals("")) {
									sa.addMoneySaving(Double.parseDouble(textField_1.getText()));
									sa.setPeriodOfTime(Integer.parseInt(textField_2.getText()));
									bank.addSavingAccount(sa, client);
									rowData[0] = sa.getIBAN();
									rowData[1] = "Saving account";
									rowData[2] = sa.getMoney();
									rowData[3] = client.getCNP();
									model2.addRow(rowData);
								} else {
									System.out.println("Cannot open an empty saving account");
								}
							} else if (rdbtnSpendingAccount.isSelected()) {
								SpendingAccount se = new SpendingAccount(); 
								if (!textField_1.getText().equals("")) 
									se.addMoney(Double.parseDouble(textField_1.getText()));
								bank.addSpendingAccount(se, client);
								rowData[0] = se.getIBAN();
								rowData[1] = "Spending account";
								rowData[2] = se.getMoney();
								rowData[3] = client.getCNP();
								model2.addRow(rowData);
							}
							bank.serialize();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
