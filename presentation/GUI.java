package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bank.Bank;
import bank.Client;
import bank.SavingAccount;
import bank.SpendingAccount;

public class GUI implements Serializable{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTable table;
	private JTable table2;
	private JPanel contentPane_1;
	private JButton btnAddClient_1;
	private JButton btnDeleteClient_1;
	private JButton btnAddAccount_1;
	private JButton btnDeleteAccount_1;
	private JButton btnAddamount;
	private JButton btnWithdrawAmount_1;
	private Bank bank;
	private int click;
	private int click2;
	private JTextField textField;
	
	private SavingAccount findSaving(String client, String iban) {
		for (SavingAccount s : Bank.getMyBank().get(client).getSavingA()) {
			if (s.getIBAN().equals(iban))
				return s;
		}
		return null;
	}
	
	private SpendingAccount findSpending(String client, String iban) {
		for (SpendingAccount s : Bank.getMyBank().get(client).getSpendingA()) {
			if (s.getIBAN().equals(iban))
				return s;
		}
		return null;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		initialize();
	}
	
	private void initialize() {
		
		bank = new Bank();
		
		bank.deserialize();
		
		List<SpendingAccount> sp = new ArrayList<SpendingAccount>();
		List<SavingAccount> sa = new ArrayList<SavingAccount>();
		Client client = new Client();
		for(String c : Bank.getMyBank().keySet()) {
			sp = Bank.getMyBank().get(c).getSpendingA();
			client = Bank.getMyBank().get(c);
			for (SpendingAccount ac : sp) 
				ac.addObserver(client);
			sa = Bank.getMyBank().get(c).getSavingA();
			for (SavingAccount ac : sa) 
				ac.addObserver(client);
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Font font = new Font("",1,12);
		Object[] rows = new Object[5];
		
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setLayout(null);
		
		JButton btnAddClient = new JButton("ADD CLIENT");
		btnAddClient.setBounds(27, 29, 141, 23);
		frame.getContentPane().add(btnAddClient);
		
		JButton btnDeleteClient = new JButton("DELETE CLIENT");
		btnDeleteClient.setBounds(27, 75, 141, 23);
		frame.getContentPane().add(btnDeleteClient);
		
		JButton btnAddAccount = new JButton("ADD ACCOUNT");
		btnAddAccount.setBounds(321, 29, 130, 23);
		frame.getContentPane().add(btnAddAccount);
		
		JButton btnDeleteAccount = new JButton("DELETE ACCOUNT");
		btnDeleteAccount.setBounds(321, 75, 130, 23);
		frame.getContentPane().add(btnDeleteAccount);
		
		JButton btnDepositAmount = new JButton("DEPOSIT AMOUNT");
		btnDepositAmount.setBounds(555, 29, 141, 23);
		frame.getContentPane().add(btnDepositAmount);
		
		JButton btnWithdrawAmount = new JButton("WITHDRAW AMOUNT");
		btnWithdrawAmount.setBounds(555, 75, 141, 23);
		frame.getContentPane().add(btnWithdrawAmount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 285, 484, 220);
		
		textField = new JTextField();
        textField.setBounds(864, 64, 86, 20);
        contentPane_1.add(textField);
        textField.setColumns(10);
		textField.setVisible(true);
		
		/*
		 *  CLIENTS TABLE
		 */
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Object[] columns = {"CNP","Name","Address"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1050, 599);
		frame.setContentPane(contentPane_1);
		
		scrollPane.setBounds(507, 285, 484, 126);
		contentPane_1.add(scrollPane);

		scrollPane.setViewportView(table);
		
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        table.setFont(font);
        table.setRowHeight(30);
        
        
        /*
		 *  ACCOUNTS TABLE
		 */
        
        Object[] columns2 = {"IBAN","Type","Balance","Account holder"};
        DefaultTableModel model2 = new DefaultTableModel();
        model2.setColumnIdentifiers(columns2);
     
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1050, 599);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(507, 148, 484, 126);
		contentPane_1.add(scrollPane_1);
		
		table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		
        table2.setModel(model2);
        table2.setBackground(Color.LIGHT_GRAY);
        table2.setForeground(Color.black);
        table2.setFont(font);
        table2.setRowHeight(30);
        
        table.setBackground(Color.white);
        table.setFont(font);
        table2.setBackground(Color.WHITE);
        table2.setFont(font);
        
        btnAddClient_1 = new JButton("ADD CLIENT");
        btnAddClient_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ClientO newWindow = new ClientO(bank,model);
        		newWindow.setVisible(true);
        	}
        });
        btnAddClient_1.setBounds(158, 30, 142, 23);
        contentPane_1.add(btnAddClient_1);
        
        btnDeleteClient_1 = new JButton("DELETE CLIENT");
        btnDeleteClient_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		bank.deserialize();
				String client = (String) table.getValueAt(click, 0);
				bank.removeClient(client,(Client)Bank.getMyBank().get(client));
				model.removeRow(click);
        		bank.serialize();
        	}
        });
        btnDeleteClient_1.setBounds(158, 63, 142, 23);
        contentPane_1.add(btnDeleteClient_1);
        
        btnAddAccount_1 = new JButton("ADD ACCOUNT");
        btnAddAccount_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String client = (String) table.getValueAt(click, 0);
        		Client c = Bank.getMyBank().get(client);
        		AccountO account = new AccountO(bank,c,model2);
        		account.setVisible(true);
        		bank.serialize();
        	}
        });
        btnAddAccount_1.setBounds(489, 30, 142, 23);
        contentPane_1.add(btnAddAccount_1);
        
        btnDeleteAccount_1 = new JButton("CLOSE ACCOUNT");
        btnDeleteAccount_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String account = (String) table2.getValueAt(click2, 0);
        		String client = (String) table2.getValueAt(click2, 3);
        		Client c = Bank.getMyBank().get(client);
        		if (table2.getValueAt(click2, 1).equals("Saving account")) {
        			bank.removeSavingAccount(findSaving(client,account), c);
        		} else if (table2.getValueAt(click2, 1).equals("Spending account")) {
        			bank.removeSpendingAccount(findSpending(client,account), c);
        		}
        		model2.removeRow(click2);
        		bank.serialize();
        	}
        });
        btnDeleteAccount_1.setBounds(489, 82, 142, 23);
        contentPane_1.add(btnDeleteAccount_1);
        table.setVisible(true);
        
        
        
        btnAddamount = new JButton("DEPOSIT AMOUNT");
        btnAddamount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
 
        		String account = (String) table2.getValueAt(click2, 0);
        		String client = (String) table2.getValueAt(click2, 3);
        		Client c = Bank.getMyBank().get(client);
        		
        		if (table2.getValueAt(click2, 1).equals("Saving account")) {
        			bank.addMoneyToSaving(c,findSaving(client,account), Double.parseDouble(textField.getText()));
        			SavingAccount sa = findSaving(client,account);
        			model2.setValueAt(sa.getMoney(), click2, 2);
        		} else if (table2.getValueAt(click2, 1).equals("Spending account")) {
        			bank.addMoneyToSpending(c,findSpending(client,account), Double.parseDouble(textField.getText()));
        			SpendingAccount sp = findSpending(client,account);
        			model2.setValueAt(sp.getMoney(), click2, 2);
        		}  		
        		bank.serialize();
        	}
        });
        btnAddamount.setBounds(824, 30, 167, 23);
        contentPane_1.add(btnAddamount);
        
        btnWithdrawAmount_1 = new JButton("WITHDRAW AMOUNT");
        btnWithdrawAmount_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
       
        		String account = (String) table2.getValueAt(click2, 0);
        		String client = (String) table2.getValueAt(click2, 3);
        		Client c = Bank.getMyBank().get(client);
        		
        		if (table2.getValueAt(click2, 1).equals("Saving account")) {
        			bank.withdrawMoneyFromSaving(c,findSaving(client,account));
        			SavingAccount sa = findSaving(client,account);
        			model2.setValueAt(sa.getMoney(), click2, 2);
        		} else if (table2.getValueAt(click2, 1).equals("Spending account")) {
        			bank.withdrawMoneyFromSpending(c,findSpending(client,account), Double.parseDouble(textField.getText()));
        			SpendingAccount sp = findSpending(client,account);
        			model2.setValueAt(sp.getMoney(), click2, 2);
        		}  		
        		bank.serialize();
        	}
        });
        btnWithdrawAmount_1.setBounds(824, 95, 167, 23);
        contentPane_1.add(btnWithdrawAmount_1);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(37, 148, 408, 260);
        contentPane_1.add(scrollPane_2);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane_2.setViewportView(textArea);
        
        JButton btnDisplayAll = new JButton("DISPLAY ALL");
        btnDisplayAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		while(model.getRowCount() > 0){
					   for(int i = 0 ; i < model.getRowCount();i++)
					      model.removeRow(i);
				}
				while(model2.getRowCount() > 0){
					   for(int i = 0 ; i < model2.getRowCount();i++)
					      model2.removeRow(i);
				}

				model.setRowCount(0);
				model2.setRowCount(0);
				
				for (String client : Bank.getMyBank().keySet()) {
					rows[0] = client;
					rows[1] = Bank.getMyBank().get(client).getName();
					rows[2] = Bank.getMyBank().get(client).getAddress();
					model.addRow(rows);
				}
				
				for (String client : Bank.getMyBank().keySet()) {
					if (Bank.getMyBank().get(client).getSavingA().size()>0) {
						for (SavingAccount s : Bank.getMyBank().get(client).getSavingA()) {
							rows[0] = s.getIBAN();
							rows[1] = "Saving account";
							rows[2] = s.getMoney();
							rows[3] = Bank.getMyBank().get(client).getCNP();
							model2.addRow(rows);
						}
					}
					if (Bank.getMyBank().get(client).getSpendingA().size()>0) {
						for (SpendingAccount s : Bank.getMyBank().get(client).getSpendingA()) {
							rows[0] = s.getIBAN();
							rows[1] = "Spending account";
							rows[2] = s.getMoney();
							rows[3] = Bank.getMyBank().get(client).getCNP();
							model2.addRow(rows);
						}
					}	
				}
        	}
        });
        btnDisplayAll.setBounds(251, 450, 157, 23);
        contentPane_1.add(btnDisplayAll);
        
        JButton btnClearAll = new JButton("CLEAR ALL");
        btnClearAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		for( int i = model.getRowCount() - 1; i >= 0; i-- ) 
			        model.removeRow(i);
				
				for( int i = model2.getRowCount() - 1; i >= 0; i-- ) 
			        model2.removeRow(i);
				
				textArea.setText("");
        	}
        });
        btnClearAll.setBounds(421, 450, 157, 23);
        contentPane_1.add(btnClearAll);
        table2.setVisible(true);
        
        PrintStream log = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(log);
        
        JButton btnUpdateClient = new JButton("UPDATE CLIENT"); // FA-L SA MEARGA INSTANT!!!
        btnUpdateClient.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String client = (String) table.getValueAt(click2, 0);
        		ClientO2 edit = new ClientO2(bank,Bank.getMyBank().get(client));
        		edit.setVisible(true);
        		edit.okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						model.setValueAt(Bank.getMyBank().get(client).getName(),click, 1);
	        			model.setValueAt(Bank.getMyBank().get(client).getAddress(),click, 2);
					}
				});
        	}
        });
        btnUpdateClient.setBounds(158, 97, 142, 23);
        contentPane_1.add(btnUpdateClient);
        
        btnUpdateClient.setVisible(false);
        
        JButton btnGenerateReport = new JButton("GENERATE REPORT");
        btnGenerateReport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {        		
        		String client = (String) table.getValueAt(click, 0);
        		
        		try{
				    PrintWriter writer = new PrintWriter("bill"+client+".txt", "UTF-8");
				    writer.println("Bill for client: "+client);
				    writer.println("Information about spending accounts:");
				    if (Bank.getMyBank().get(client).getSpendingA().size()!=0){
					    for (SpendingAccount se : Bank.getMyBank().get(client).getSpendingA()) {
					    	writer.println("IBAN: "+se.getIBAN());
					    	writer.println("Balance: "+se.getMoney());
					    	writer.println();
					    }
				    } else {
				    	writer.println("No spending accounts");
				    }
				    writer.println();
				    writer.println("Information about saving accounts:");
				    if (Bank.getMyBank().get(client).getSavingA().size()!=0){
					    for (SavingAccount sa : Bank.getMyBank().get(client).getSavingA()) {
					    	writer.println("IBAN: "+sa.getIBAN());
					    	writer.println("Balance: "+sa.getMoney());
					    	writer.println();
					    }
				    } else {
				    	writer.println("No saving accounts");
				    }
				    writer.close();
				} catch (IOException ex) {
				    System.out.println("There was a problem while printing the bill. Please try again!");
				}
        	}
        });
        btnGenerateReport.setBounds(588, 450, 156, 23);
        contentPane_1.add(btnGenerateReport);
        
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt){
				click = table.getSelectedRow();
			}
		});
		
		table2.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt){
				click2 = table2.getSelectedRow();
			}
		});
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				bank.serialize();
			}
		});
	}
}
