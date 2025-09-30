package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class PersonalizationInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> FontComboBox;
	private JComboBox<String> FontComboBox_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalizationInterface frame = new PersonalizationInterface();
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
	public PersonalizationInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel AppTitle = new JLabel("Personalisation du PDF");
		AppTitle.setBounds(130, 10, 393, 60);
		AppTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AppTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(AppTitle);
		
		JLabel TextPersonalization = new JLabel("Personnalisation du titre");
		TextPersonalization.setBounds(10, 62, 188, 30);
		TextPersonalization.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(TextPersonalization);
		
		JLabel TextPersonalizationPreview = new JLabel("aA");
		TextPersonalizationPreview.setFont(new Font("Tahoma", Font.PLAIN, 25));
		TextPersonalizationPreview.setHorizontalAlignment(SwingConstants.CENTER);
		TextPersonalizationPreview.setBounds(516, 120, 105, 71);
		contentPane.add(TextPersonalizationPreview);
		
		JLabel VisualisationLabel = new JLabel("Visualisation");
		VisualisationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		VisualisationLabel.setBounds(528, 93, 81, 41);
		contentPane.add(VisualisationLabel);
		
		FontComboBox = new JComboBox<>(GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames());
		FontComboBox.setBounds(83, 102, 159, 30);
		contentPane.add(FontComboBox);
		
		JLabel FontLabel = new JLabel("Police :");
		FontLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		FontLabel.setBounds(20, 102, 75, 30);
		contentPane.add(FontLabel);
		
		JComboBox<?> SizeComboBox = new JComboBox<>();
		SizeComboBox.setBounds(83, 151, 159, 30);
		contentPane.add(SizeComboBox);
		
		JLabel SizeLabel = new JLabel("Taille  :");
		SizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SizeLabel.setBounds(22, 150, 75, 30);
		contentPane.add(SizeLabel);
		
		JLabel ColorLabel = new JLabel("Couleur  :");
		ColorLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ColorLabel.setBounds(10, 201, 75, 30);
		contentPane.add(ColorLabel);
		
		JButton ColorButton = new JButton("Choisir le couleur");
		ColorButton.setBounds(83, 198, 159, 41);
		contentPane.add(ColorButton);
		
		JCheckBox ItalicCheckBox = new JCheckBox("Italique");
		ItalicCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ItalicCheckBox.setBounds(331, 102, 179, 60);
		contentPane.add(ItalicCheckBox);
		
		JLabel LabelEffects = new JLabel("Effets  :");
		LabelEffects.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelEffects.setBounds(312, 81, 75, 30);
		contentPane.add(LabelEffects);
		
		JCheckBox BoldCheckBox = new JCheckBox("Gras");
		BoldCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BoldCheckBox.setBounds(331, 142, 179, 52);
		contentPane.add(BoldCheckBox);
		
		JCheckBox UnderlineCheckBox = new JCheckBox("Souligné");
		UnderlineCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UnderlineCheckBox.setBounds(331, 179, 179, 52);
		contentPane.add(UnderlineCheckBox);
		
		JCheckBox UnderlineCheckBox_1 = new JCheckBox("Souligné");
		UnderlineCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UnderlineCheckBox_1.setBounds(331, 375, 179, 52);
		contentPane.add(UnderlineCheckBox_1);
		
		JLabel ColorLabel_1 = new JLabel("Couleur  :");
		ColorLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ColorLabel_1.setBounds(10, 397, 75, 30);
		contentPane.add(ColorLabel_1);
		
		JButton ColorButton_1 = new JButton("Choisir le couleur");
		ColorButton_1.setBounds(83, 394, 159, 41);
		contentPane.add(ColorButton_1);
		
		JLabel SizeLabel_1 = new JLabel("Taille  :");
		SizeLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SizeLabel_1.setBounds(22, 346, 75, 30);
		contentPane.add(SizeLabel_1);
		
		JComboBox<?> SizeComboBox_1 = new JComboBox<>();
		SizeComboBox_1.setBounds(83, 347, 159, 30);
		contentPane.add(SizeComboBox_1);
		
		JCheckBox BoldCheckBox_1 = new JCheckBox("Gras");
		BoldCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BoldCheckBox_1.setBounds(331, 338, 179, 52);
		contentPane.add(BoldCheckBox_1);
		
		JCheckBox ItalicCheckBox_1 = new JCheckBox("Italique");
		ItalicCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ItalicCheckBox_1.setBounds(331, 298, 179, 60);
		contentPane.add(ItalicCheckBox_1);
		
		FontComboBox_1 = new JComboBox<>(GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames());
		FontComboBox_1.setBounds(83, 298, 159, 30);
		contentPane.add(FontComboBox_1);
		
		JLabel FontLabel_1 = new JLabel("Police :");
		FontLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		FontLabel_1.setBounds(20, 298, 75, 30);
		contentPane.add(FontLabel_1);
		
		JLabel lblPersonnalisationDuTexte = new JLabel("Personnalisation du Texte");
		lblPersonnalisationDuTexte.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersonnalisationDuTexte.setBounds(10, 258, 188, 30);
		contentPane.add(lblPersonnalisationDuTexte);
		
		JLabel LabelEffects_1 = new JLabel("Effets  :");
		LabelEffects_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelEffects_1.setBounds(312, 277, 75, 30);
		contentPane.add(LabelEffects_1);
		
		JLabel VisualisationLabel_1 = new JLabel("Visualisation");
		VisualisationLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		VisualisationLabel_1.setBounds(528, 289, 81, 41);
		contentPane.add(VisualisationLabel_1);
		
		JLabel TextPersonalizationPreview_1 = new JLabel("aA");
		TextPersonalizationPreview_1.setHorizontalAlignment(SwingConstants.CENTER);
		TextPersonalizationPreview_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		TextPersonalizationPreview_1.setBounds(516, 316, 105, 71);
		contentPane.add(TextPersonalizationPreview_1);
		
		JButton btnNewButton = new JButton("Valider la personnalisation");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 460, 611, 60);
		contentPane.add(btnNewButton);

	}
}
