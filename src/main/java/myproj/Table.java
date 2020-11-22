package myproj;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTable;
import java.awt.GridBagConstraints;

public class Table {

	private JFrame frame;
	private JTable table;
	private static int num;
	/**
	 * Launch the application.
	 */
	public static void DisplayTable(int n) {
		
		num = n;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table window = new Table();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public Table() throws IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, InterruptedException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		
		//frame.getContentPane().setLayout(null);
		CallTopN.callTop(num);
		
		String[][] data = CallTopN.tableInput(num);
		
		String[] col = {"Word", "Count"};
		 
		
		
		
		table = new JTable(data, col);
		
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		frame.getContentPane().add(table, gbc_table);
		
		JButton btnSubmit = new JButton("Return");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// WHEN BUTTON IS CLICKED.
				frame.setVisible(false);
				IndexSelect.Select();
				
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 3;
		frame.getContentPane().add(btnSubmit, gbc_btnSubmit);
	}

}
