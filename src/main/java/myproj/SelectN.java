package myproj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectN {

	private JFrame frame;
	private JTextField numInput;

	/**
	 * Launch the application.
	 */
	public static void SetN() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectN window = new SelectN();
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
	public SelectN() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		numInput = new JTextField();
		numInput.setText("Give a number");
		GridBagConstraints gbc_numInput = new GridBagConstraints();
		gbc_numInput.insets = new Insets(0, 0, 5, 0);
		gbc_numInput.anchor = GridBagConstraints.WEST;
		gbc_numInput.gridx = 2;
		gbc_numInput.gridy = 2;
		frame.getContentPane().add(numInput, gbc_numInput);
		numInput.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// WHEN BUTTON IS CLICKED.
				int given = Integer.parseInt(numInput.getText());
				//System.out.println(given);
				frame.setVisible(false);
				Table.DisplayTable(given);
				
				
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 3;
		frame.getContentPane().add(btnSubmit, gbc_btnSubmit);
	}

}
