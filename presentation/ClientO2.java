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

import bank.Bank;
import bank.Client;

public class ClientO2 extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	public JButton okButton;

	public ClientO2(Bank bank, Client client) {
		
		setBounds(100, 100, 356, 201);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblChangeName = new JLabel("Change name?");
		lblChangeName.setBounds(40, 30, 94, 14);
		contentPanel.add(lblChangeName);
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(40, 51, 109, 23);
		contentPanel.add(rdbtnYes);
		
		JRadioButton rdbtnYes_1 = new JRadioButton("Yes");
		rdbtnYes_1.setBounds(185, 51, 109, 23);
		contentPanel.add(rdbtnYes_1);
		
		textField = new JTextField();
		textField.setBounds(40, 85, 109, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(185, 85, 109, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		rdbtnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setVisible(true);
			}
		});
		
		rdbtnYes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setVisible(true);
			}
		});
		
		
		JLabel lblChangeAddress = new JLabel("Change address?");
		lblChangeAddress.setBounds(185, 30, 109, 14);
		contentPanel.add(lblChangeAddress);
		textField_1.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			    okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (rdbtnYes.isSelected()) 
							Bank.getMyBank().get(client.getCNP()).setName(textField.getText());
						if (rdbtnYes_1.isSelected())
							Bank.getMyBank().get(client.getCNP()).setAddress(textField_1.getText());
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
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
