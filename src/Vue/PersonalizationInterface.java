package Vue;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.ControleurFormulaire;
import Modele.DonnéesPersonnalisation;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Modele.ColorData;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JTextField;

public class PersonalizationInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JComboBox<String> FontComboBox;
	public JComboBox<String> FontComboBox_1;
	public JComboBox<Integer> SizeComboBox;
	public JComboBox<Integer> SizeComboBox_1;
	public JCheckBox ItalicCheckBox;
	public JCheckBox BoldCheckBox;
	public JCheckBox UnderlineCheckBox;
	public JLabel TextPersonalizationPreview;
	public JCheckBox ItalicCheckBox_1;
	public JCheckBox BoldCheckBox_1;
	public JCheckBox UnderlineCheckBox_1;
	public JLabel TextPersonalizationPreview_1;
	public JTextField QRCodeLengthField;
	public JTextField QRCodeWidthField;
	public JTextField ImageWidthField;
	public JTextField ImageLengthField;
	public JComboBox<String> QRCodePositionComboBox;
	public JComboBox<String> ImagePositionComboBox;
	
	private void updateTitlePreviewFont() {
		String fontName = (String) FontComboBox.getSelectedItem();
		int fontSize = (Integer) SizeComboBox.getSelectedItem();
		int style = Font.PLAIN;
		if (BoldCheckBox.isSelected()) style |= Font.BOLD;
		if (ItalicCheckBox.isSelected()) style |= Font.ITALIC;
		Font font = new Font(fontName, style, fontSize);
		TextPersonalizationPreview.setFont(font);
		if (UnderlineCheckBox.isSelected()) {
			TextPersonalizationPreview.setFont(font.deriveFont(
				font.getAttributes()
			));
			java.util.Map<java.awt.font.TextAttribute, Object> attrs = new java.util.HashMap<>(font.getAttributes());
			attrs.put(java.awt.font.TextAttribute.UNDERLINE, java.awt.font.TextAttribute.UNDERLINE_ON);
			TextPersonalizationPreview.setFont(font.deriveFont(attrs));
		}
		
	}
	
	private void updateTextPreviewFont() {
		String fontName = (String) FontComboBox_1.getSelectedItem();
		int fontSize = (Integer) SizeComboBox_1.getSelectedItem();
		int style = Font.PLAIN;
		if (BoldCheckBox_1.isSelected()) style |= Font.BOLD;
		if (ItalicCheckBox_1.isSelected()) style |= Font.ITALIC;
		Font font = new Font(fontName, style, fontSize);
		TextPersonalizationPreview_1.setFont(font);
		if (UnderlineCheckBox_1.isSelected()) {
			TextPersonalizationPreview_1.setFont(font.deriveFont(
				font.getAttributes()
			));
			java.util.Map<java.awt.font.TextAttribute, Object> attrs = new java.util.HashMap<>(font.getAttributes());
			attrs.put(java.awt.font.TextAttribute.UNDERLINE, java.awt.font.TextAttribute.UNDERLINE_ON);
			TextPersonalizationPreview_1.setFont(font.deriveFont(attrs));
		}
		
	}
	
	public PersonalizationInterface(ControleurFormulaire controleur) {
		Integer[] sizes = new Integer[33];
		for (int i = 0, val = 8; val <= 72; i++, val += 2) {
			sizes[i] = val;
		}
		String[] positions = {"A Gauche", "Centré", "A Droite"};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel AppTitle = new JLabel("Personnalisation du PDF");
		AppTitle.setBounds(263, 11, 393, 60);
		AppTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AppTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(AppTitle);
		
		JLabel TextPersonalization = new JLabel("Personnalisation du titre");
		TextPersonalization.setBounds(10, 62, 188, 30);
		TextPersonalization.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(TextPersonalization);
		
		TextPersonalizationPreview = new JLabel("aA");
		TextPersonalizationPreview.setFont(new Font("Tahoma", Font.PLAIN, 25));
		TextPersonalizationPreview.setHorizontalAlignment(SwingConstants.CENTER);
		TextPersonalizationPreview.setBounds(505, 123, 105, 71);
		contentPane.add(TextPersonalizationPreview);
		
		JLabel VisualisationLabel = new JLabel("Visualisation");
		VisualisationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		VisualisationLabel.setBounds(517, 96, 81, 41);
		contentPane.add(VisualisationLabel);
		
		FontComboBox = new JComboBox<>(GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames());
		FontComboBox.setBounds(83, 102, 159, 30);
		FontComboBox.addActionListener(e -> updateTitlePreviewFont());
		contentPane.add(FontComboBox);
		
		JLabel FontLabel = new JLabel("Police :");
		FontLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		FontLabel.setBounds(20, 102, 75, 30);
		contentPane.add(FontLabel);
		
		SizeComboBox = new JComboBox<>(sizes);
		SizeComboBox.setBounds(83, 151, 159, 30);
		SizeComboBox.addActionListener(e -> updateTitlePreviewFont());
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
		ColorButton.addActionListener(e -> {
			Color chosen = JColorChooser.showDialog(this, "Choisir une couleur", TextPersonalizationPreview.getForeground());
			if (chosen != null) {
				TextPersonalizationPreview.setForeground(chosen);
			}
		});
		contentPane.add(ColorButton);
		
		ItalicCheckBox = new JCheckBox("Italique");
		ItalicCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ItalicCheckBox.setBounds(331, 102, 179, 60);
		ItalicCheckBox.addActionListener(e -> updateTitlePreviewFont());
		contentPane.add(ItalicCheckBox);
		
		JLabel LabelEffects = new JLabel("Effets  :");
		LabelEffects.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LabelEffects.setBounds(312, 81, 75, 30);
		contentPane.add(LabelEffects);
		
		BoldCheckBox = new JCheckBox("Gras");
		BoldCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BoldCheckBox.setBounds(331, 142, 179, 52);
		BoldCheckBox.addActionListener(e -> updateTitlePreviewFont());
		contentPane.add(BoldCheckBox);
		
		UnderlineCheckBox = new JCheckBox("Souligné");
		UnderlineCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UnderlineCheckBox.setBounds(331, 179, 179, 52);
		UnderlineCheckBox.addActionListener(e -> updateTitlePreviewFont());
		contentPane.add(UnderlineCheckBox);
		
		UnderlineCheckBox_1 = new JCheckBox("Souligné");
		UnderlineCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UnderlineCheckBox_1.setBounds(331, 375, 179, 52);
		UnderlineCheckBox_1.addActionListener(e -> updateTextPreviewFont());
		contentPane.add(UnderlineCheckBox_1);
		
		JLabel ColorLabel_1 = new JLabel("Couleur  :");
		ColorLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ColorLabel_1.setBounds(10, 397, 75, 30);
		contentPane.add(ColorLabel_1);
		
		TextPersonalizationPreview_1 = new JLabel("aA");
		TextPersonalizationPreview_1.setHorizontalAlignment(SwingConstants.CENTER);
		TextPersonalizationPreview_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		TextPersonalizationPreview_1.setBounds(505, 322, 105, 71);
		contentPane.add(TextPersonalizationPreview_1);
		
		JButton ColorButton_1 = new JButton("Choisir le couleur");
		ColorButton_1.setBounds(83, 394, 159, 41);
		ColorButton_1.addActionListener(e -> {
			Color chosen = JColorChooser.showDialog(this, "Choisir une couleur", TextPersonalizationPreview_1.getForeground());
			if (chosen != null) {
				TextPersonalizationPreview_1.setForeground(chosen);
			}
		});
		contentPane.add(ColorButton_1);
		
		JLabel SizeLabel_1 = new JLabel("Taille  :");
		SizeLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SizeLabel_1.setBounds(22, 346, 75, 30);
		contentPane.add(SizeLabel_1);
		
		SizeComboBox_1 = new JComboBox<>(sizes);
		SizeComboBox_1.setBounds(83, 347, 159, 30);
		SizeComboBox_1.addActionListener(e -> updateTextPreviewFont());
		contentPane.add(SizeComboBox_1);
		
		BoldCheckBox_1 = new JCheckBox("Gras");
		BoldCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BoldCheckBox_1.setBounds(331, 338, 179, 52);
		BoldCheckBox_1.addActionListener(e -> updateTextPreviewFont());
		contentPane.add(BoldCheckBox_1);
		
		ItalicCheckBox_1 = new JCheckBox("Italique");
		ItalicCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ItalicCheckBox_1.setBounds(331, 298, 179, 60);
		ItalicCheckBox_1.addActionListener(e -> updateTextPreviewFont());
		contentPane.add(ItalicCheckBox_1);
		
		FontComboBox_1 = new JComboBox<>(GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames());
		FontComboBox_1.setBounds(83, 298, 159, 30);
		FontComboBox_1.addActionListener(e -> updateTextPreviewFont());
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
		VisualisationLabel_1.setBounds(516, 291, 81, 41);
		contentPane.add(VisualisationLabel_1);
		
		JButton ValidateButton = new JButton("Générer le PDF Personnalisé");
		ValidateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ValidateButton.setBounds(10, 460, 662, 60);
		ValidateButton.addActionListener(e -> {
			try {
				DonnéesPersonnalisation perso = new DonnéesPersonnalisation();
				// --- Titre ---
				perso.setTitleFontName((String) FontComboBox.getSelectedItem());
				perso.setTitleFontSize((Integer) SizeComboBox.getSelectedItem());
				perso.setTitleBold(BoldCheckBox.isSelected());
				perso.setTitleItalic(ItalicCheckBox.isSelected());
				perso.setTitleUnderline(UnderlineCheckBox.isSelected());
				perso.setTitleColor(new ColorData(TextPersonalizationPreview.getForeground()));

				// --- Texte ---
				perso.setTextFontName((String) FontComboBox_1.getSelectedItem());
				perso.setTextFontSize((Integer) SizeComboBox_1.getSelectedItem());
				perso.setTextBold(BoldCheckBox_1.isSelected());
				perso.setTextItalic(ItalicCheckBox_1.isSelected());
				perso.setTextUnderline(UnderlineCheckBox_1.isSelected());
				perso.setTextColor(new ColorData(TextPersonalizationPreview_1.getForeground()));
				
				// --- IMAGE/QRCode ---
				perso.setIMGLength(Integer.parseInt(ImageLengthField.getText().trim()));
				perso.setIMGWidth(Integer.parseInt(ImageWidthField.getText().trim()));
				perso.setIMGPosition((String) ImagePositionComboBox.getSelectedItem());
				perso.setQRLength(Integer.parseInt(QRCodeLengthField.getText().trim()));
				perso.setQRWidth(Integer.parseInt(QRCodeWidthField.getText().trim()));
				perso.setQRPosition((String) QRCodePositionComboBox.getSelectedItem());
				
				controleur.demandePersonalization(perso);
				javax.swing.JOptionPane.showMessageDialog(
						this,
						"Le PDF a été généré avec succès !",
						"Succès",
						javax.swing.JOptionPane.INFORMATION_MESSAGE
						);
			} catch (Exception ex){
				 javax.swing.JOptionPane.showMessageDialog(
				        this,
				        "Une erreur inattendue est survenue : " + ex.getMessage(),
				        "Erreur",
				        javax.swing.JOptionPane.ERROR_MESSAGE
				 );
			}
		});
		contentPane.add(ValidateButton);
		
		JLabel QRCodePersonalizationLabel = new JLabel("Personnalisation du QRCode");
		QRCodePersonalizationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		QRCodePersonalizationLabel.setBounds(613, 62, 188, 30);
		contentPane.add(QRCodePersonalizationLabel);
		
		JLabel LengthLabel = new JLabel("Longueur :");
		LengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LengthLabel.setBounds(636, 101, 75, 30);
		contentPane.add(LengthLabel);
		
		JLabel WidthLabel = new JLabel("Largeur :");
		WidthLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		WidthLabel.setBounds(636, 141, 75, 30);
		contentPane.add(WidthLabel);
		
		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition.setBounds(636, 179, 75, 30);
		contentPane.add(lblPosition);
		
		QRCodeLengthField = new JTextField();
		QRCodeLengthField.setBounds(721, 103, 192, 31);
		contentPane.add(QRCodeLengthField);
		QRCodeLengthField.setColumns(10);
		
		QRCodeWidthField = new JTextField();
		QRCodeWidthField.setColumns(10);
		QRCodeWidthField.setBounds(721, 142, 192, 31);
		contentPane.add(QRCodeWidthField);
		
		QRCodePositionComboBox = new JComboBox<>(positions);
		QRCodePositionComboBox.setBounds(721, 183, 192, 27);
		contentPane.add(QRCodePositionComboBox);
		
		JLabel lblPosition_1 = new JLabel("Position :");
		lblPosition_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosition_1.setBounds(636, 375, 75, 30);
		contentPane.add(lblPosition_1);
		
		ImagePositionComboBox = new JComboBox<>(positions);
		ImagePositionComboBox.setBounds(721, 379, 192, 27);
		contentPane.add(ImagePositionComboBox);
		
		ImageWidthField = new JTextField();
		ImageWidthField.setColumns(10);
		ImageWidthField.setBounds(721, 338, 192, 31);
		contentPane.add(ImageWidthField);
		
		JLabel WidthLabel_1 = new JLabel("Largeur :");
		WidthLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		WidthLabel_1.setBounds(636, 337, 75, 30);
		contentPane.add(WidthLabel_1);
		
		JLabel LengthLabel_1 = new JLabel("Longueur :");
		LengthLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LengthLabel_1.setBounds(636, 297, 75, 30);
		contentPane.add(LengthLabel_1);
		
		ImageLengthField = new JTextField();
		ImageLengthField.setColumns(10);
		ImageLengthField.setBounds(721, 299, 192, 31);
		contentPane.add(ImageLengthField);
		
		JLabel ImagePersonalizationLabel = new JLabel("Personnalisation du Image");
		ImagePersonalizationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ImagePersonalizationLabel.setBounds(613, 258, 188, 30);
		contentPane.add(ImagePersonalizationLabel);
		
		JButton SaveButton = new JButton("Sauvegarder le projet");
		SaveButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SaveButton.setBounds(682, 460, 232, 60);
		SaveButton.addActionListener(e -> {
			    // Demander si on veut aussi sauvegarder la personnalisation
			    int choix = JOptionPane.showConfirmDialog(null,
			            "Voulez-vous aussi sauvegarder la personnalisation ?",
			            "Sauvegarde",
			            JOptionPane.YES_NO_OPTION);
			    DonnéesPersonnalisation perso = new DonnéesPersonnalisation();
				// --- Titre ---
				perso.setTitleFontName((String) FontComboBox.getSelectedItem());
				perso.setTitleFontSize((Integer) SizeComboBox.getSelectedItem());
				perso.setTitleBold(BoldCheckBox.isSelected());
				perso.setTitleItalic(ItalicCheckBox.isSelected());
				perso.setTitleUnderline(UnderlineCheckBox.isSelected());
				perso.setTitleColor(new ColorData(TextPersonalizationPreview.getForeground()));

				// --- Texte ---
				perso.setTextFontName((String) FontComboBox_1.getSelectedItem());
				perso.setTextFontSize((Integer) SizeComboBox_1.getSelectedItem());
				perso.setTextBold(BoldCheckBox_1.isSelected());
				perso.setTextItalic(ItalicCheckBox_1.isSelected());
				perso.setTextUnderline(UnderlineCheckBox_1.isSelected());
				perso.setTextColor(new ColorData(TextPersonalizationPreview_1.getForeground()));
					
				// --- IMAGE/QRCode ---
				perso.setIMGLength(Integer.parseInt(ImageLengthField.getText().trim()));
				perso.setIMGWidth(Integer.parseInt(ImageWidthField.getText().trim()));
				perso.setIMGPosition((String) ImagePositionComboBox.getSelectedItem());
				perso.setQRLength(Integer.parseInt(QRCodeLengthField.getText().trim()));
				perso.setQRWidth(Integer.parseInt(QRCodeWidthField.getText().trim()));
				perso.setQRPosition((String) QRCodePositionComboBox.getSelectedItem());
			    
				controleur.SauvegardeProject(perso, choix);
		});
		contentPane.add(SaveButton);

	}
}
