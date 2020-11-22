package myproj;

import java.awt.EventQueue;
import myproj.IndexSelect;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class IndexFrame {

	private JFrame frame;
	private JFrame frame2;
	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				
				try {
					IndexFrame window = new IndexFrame();
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
	public IndexFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConstructInvertedIndex = new JButton("Construct Tolstoy Index");
		
		
		btnConstructInvertedIndex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					System.out.println("generating");
					String in = "gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//Tolstoy gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//index_out";
					testing.submitHadoopFsJob(in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
			   
				IndexSelect.Select();
				
				
			}
		});
		
		btnConstructInvertedIndex.setBounds(20, 6, 170, 51);
		frame.getContentPane().add(btnConstructInvertedIndex);
		
		JButton btnNewButton = new JButton("Construct Hugo Index");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generating Hugo");
				String in = "gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//Hugo gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//index_out";
				try {
					testing.submitHadoopFsJob(in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				   
				IndexSelect.Select();
			}
		});
		btnNewButton.setBounds(20, 69, 170, 51);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Construct Comedy Index");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generating Comedy");

				String in = "gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//comedy gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//index_out";
				try {
					testing.submitHadoopFsJob(in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				   
				IndexSelect.Select();
			}
		});
		btnNewButton_1.setBounds(20, 132, 170, 51);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Construct History index");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generating History");

				String in = "gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//histories gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//index_out";
				try {
					testing.submitHadoopFsJob(in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				   
				IndexSelect.Select();
			}
		});
		btnNewButton_2.setBounds(249, 6, 170, 51);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Construct Poetry Index");
		btnNewButton_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generating Poetry");

				String in = "gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//poetry gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//index_out";
				try {
					testing.submitHadoopFsJob(in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				   
				IndexSelect.Select();
				
			}
		});
		btnNewButton_2_1.setBounds(249, 69, 170, 51);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Construct Tragedy Index");
		btnNewButton_2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generating Tragedy");

				String in = "gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//tragedies gs://dataproc-staging-us-central1-1037520934349-n3dl9izu//index_out";
				try {
					testing.submitHadoopFsJob(in);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				   
				IndexSelect.Select();
				
				
			}
		});
		btnNewButton_2_2.setBounds(249, 132, 170, 51);
		frame.getContentPane().add(btnNewButton_2_2);
	}
	

	
	
}








