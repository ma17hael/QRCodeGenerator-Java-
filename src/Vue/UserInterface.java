package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TextField;
	private JTextField LinkTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TextLabel = new JLabel("Texte :");
		TextLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TextLabel.setBounds(23, 23, 214, 58);
		contentPane.add(TextLabel);
		
		TextField = new JTextField();
		TextField.setBounds(105, 32, 191, 49);
		contentPane.add(TextField);
		TextField.setColumns(10);
		
		JLabel LinkLabel = new JLabel("Lien :");
		LinkLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LinkLabel.setBounds(23, 125, 214, 58);
		contentPane.add(LinkLabel);
		
		LinkTextField = new JTextField();
		LinkTextField.setColumns(10);
		LinkTextField.setBounds(105, 134, 191, 49);
		contentPane.add(LinkTextField);

	}
}
