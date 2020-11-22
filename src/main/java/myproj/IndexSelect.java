package myproj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IndexSelect {

	private JFrame frame;
	private JTextField Select;
	private JTextField txtPleaseSelectAction;
	private JButton delIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Select();
	}
	public static void Select() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndexSelect window = new IndexSelect();
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
	public IndexSelect() {
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
		
		Select = new JTextField();
		Select.setText("Successfully Constructed");
		Select.setBounds(117, 6, 184, 47);
		frame.getContentPane().add(Select);
		Select.setColumns(10);
		
		txtPleaseSelectAction = new JTextField();
		txtPleaseSelectAction.setText("Please Select Action");
		txtPleaseSelectAction.setBounds(117, 76, 184, 47);
		frame.getContentPane().add(txtPleaseSelectAction);
		txtPleaseSelectAction.setColumns(10);
		
		JButton btnSearchForTerm = new JButton("Search for Term");
		btnSearchForTerm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("goto search");
				frame.setVisible(false);
				SearchTerm.Term();
			}
		});
		btnSearchForTerm.setBounds(117, 152, 184, 29);
		frame.getContentPane().add(btnSearchForTerm);
		
		JButton btnTopn = new JButton("Top-N");
		btnTopn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SelectN.SetN();
			}
		});
		btnTopn.setBounds(117, 209, 184, 29);
		frame.getContentPane().add(btnTopn);
		
		delIndex = new JButton("Delete Index");
		delIndex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String projectId = "invindex";

			    // The ID of your GCS bucket
			    String bucketName = "dataproc-staging-us-central1-1037520934349-n3dl9izu";
				
				
				String obj0 = "index_out/part-r-00000";
			    String obj1 = "index_out/_SUCCESS";
			    String obj2 = "index_out/";

			    // The path to your file to upload
			    //String filePath = "/Users/cameronbeeche/Desktop/Fall/cs1660/project/input/I.txt";
			  
				System.out.println(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
				upload.deleteObject(projectId, bucketName, obj0);
				upload.deleteObject(projectId, bucketName, obj1);
				upload.deleteObject(projectId, bucketName, obj2);
				
			}
		});
		delIndex.setBounds(117, 243, 184, 29);
		frame.getContentPane().add(delIndex);
	}
}
