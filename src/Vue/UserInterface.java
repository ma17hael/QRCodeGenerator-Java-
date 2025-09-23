package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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
		TextLabel.setBounds(25, 88, 214, 58);
		contentPane.add(TextLabel);
		
		TextField = new JTextField();
		TextField.setBounds(107, 97, 191, 49);
		contentPane.add(TextField);
		TextField.setColumns(10);
		
		JLabel LinkLabel = new JLabel("Lien :");
		LinkLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LinkLabel.setBounds(25, 190, 214, 58);
		contentPane.add(LinkLabel);
		
		LinkTextField = new JTextField();
		LinkTextField.setColumns(10);
		LinkTextField.setBounds(107, 199, 191, 49);
		contentPane.add(LinkTextField);
		
		JButton ValidateButton = new JButton("Validation");
		ValidateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ValidateButton.setBounds(351, 92, 127, 156);
		contentPane.add(ValidateButton);
		
		JLabel TitleLabel = new JLabel("Génération de PDF avec QRCode");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TitleLabel.setBounds(25, 10, 453, 59);
		contentPane.add(TitleLabel);

	}
}
