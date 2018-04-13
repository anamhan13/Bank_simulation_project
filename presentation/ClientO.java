package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bank.Bank;
import bank.Client;

public class ClientO extends JDialog implements Serializable {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public ClientO(Bank bank,DefaultTableModel model2) {
		setBounds(100, 100, 349, 195);
		getContentPane().setLayout(null);
		Object[] rowData = new Object[3];
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 125, 337, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Client client = new Client(textField.getText(),textField_1.getText(),textField_2.getText());
						if(bank.addClient(client)!=-1) {
							rowData[0]=client.getCNP();
							rowData[1]=client.getName();
							rowData[2]=client.getAddress();
							model2.addRow(rowData);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel label = new JLabel("Name:");
		label.setBounds(25, 11, 41, 23);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(107, 12, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCnp = new JLabel("CNP:");
		lblCnp.setBounds(25, 44, 46, 14);
		getContentPane().add(lblCnp);
		
		textField_1 = new JTextField();
		textField_1.setBounds(107, 41, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(107, 72, 86, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(25, 73, 72, 14);
		getContentPane().add(lblAddress);
	}
}
