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

public class SearchTable {

	private JFrame frame;
	private JTable table;
	private static String term;
	/**
	 * Launch the application.
	 */
	public static void GenerateSearch(String t) {
		
		term = t;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchTable window = new SearchTable();
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
	 */
	public SearchTable() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
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
		
		
		try {
			CallTerm.callTop(term);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[][] data = CallTerm.termTable();
		
		
		String[] col = {"Document", "Count"};
		 
		
		
		
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
