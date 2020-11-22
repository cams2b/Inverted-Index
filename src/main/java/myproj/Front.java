package myproj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTable;

public class Front {

	private JFrame frame;
	private JTextField txtIndexSuccessfullyConstructed;
	private JTextField txtSetN;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Front window = new Front();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Front() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		// tableN ===========================================================
		final JPanel tableN = new JPanel();
		frame.getContentPane().add(tableN, "name_504495339590411");
		tableN.setLayout(null);
		
		
		tableN.setVisible(false);
		
		// setN ==============================================================
		final JPanel setN = new JPanel();
		frame.getContentPane().add(setN, "name_503838539867116");
		setN.setLayout(null);
		
		txtSetN = new JTextField();
		txtSetN.setText("Set N");
		txtSetN.setBounds(36, 56, 130, 26);
		setN.add(txtSetN);
		txtSetN.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int given = Integer.parseInt(txtSetN.getText());
				//System.out.println(given);
				//frame.setVisible(false);
				
				try {
					CallTopN.callTop(given);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[][] data = null;
				System.out.println("Generating ...");
				try {
					data = CallTopN.tableInput(given);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] cols = {"Word", "Count"};
				
				table = new JTable(data, cols);
				tableN.add(table);
				setN.setVisible(false);
				tableN.setVisible(true);
				
				//Table.DisplayTable(given);
			}
		});
		btnSubmit.setBounds(66, 113, 117, 29);
		setN.add(btnSubmit);
		setN.setVisible(false);
		
		
		// SELECT PANEL ===============================
		final JPanel selectPanel = new JPanel();
		frame.getContentPane().add(selectPanel, "name_477338883685518");
		selectPanel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Search for a Term");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_1.setBounds(42, 69, 154, 38);
		selectPanel.add(btnNewButton_1);
		
		JButton btnTopn = new JButton("Top-N");
		btnTopn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectPanel.setVisible(false);
				setN.setVisible(true);
				
				
			}
		});
		btnTopn.setBounds(42, 142, 154, 38);
		selectPanel.add(btnTopn);
		
		txtIndexSuccessfullyConstructed = new JTextField();
		txtIndexSuccessfullyConstructed.setText("Index Successfully Constructed");
		txtIndexSuccessfullyConstructed.setBounds(114, 19, 213, 38);
		selectPanel.add(txtIndexSuccessfullyConstructed);
		txtIndexSuccessfullyConstructed.setColumns(10);
		selectPanel.setVisible(false);
		
		// INDEX PANEL ===========================================
		final JPanel indexPanel = new JPanel();
		frame.getContentPane().add(indexPanel, "name_477275041567856");
		indexPanel.setLayout(null);
		indexPanel.setVisible(true);
		
		JButton btnNewButton = new JButton("Construct Index");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				indexPanel.setVisible(false);
				selectPanel.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(149, 50, 164, 55);
		indexPanel.add(btnNewButton);
	}

}
