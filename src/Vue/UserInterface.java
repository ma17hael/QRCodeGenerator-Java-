package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.ControleurFormulaire;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TitleTextField;
	private JTextField LinkTextField;
	private ControleurFormulaire controleur;
	private JTextField TextField;
	private JTextField cheminPDFField;
	private JButton choisirFichierButton;
	private JLabel lblNewLabel;

	public UserInterface(ControleurFormulaire controle) {
		this.controleur = controle;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TitleField = new JLabel("Titre :");
		TitleField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TitleField.setBounds(25, 48, 265, 58);
		contentPane.add(TitleField);
		
		TitleTextField = new JTextField();
		TitleTextField.setBounds(107, 57, 371, 49);
		contentPane.add(TitleTextField);
		TitleTextField.setColumns(10);
		
		JLabel LinkLabel = new JLabel("Lien :");
		LinkLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LinkLabel.setBounds(25, 251, 210, 58);
		contentPane.add(LinkLabel);
		
		LinkTextField = new JTextField();
		LinkTextField.setColumns(10);
		LinkTextField.setBounds(107, 260, 371, 49);
		contentPane.add(LinkTextField);
		
		JButton ValidateButton = new JButton("Générer le PDF");
		ValidateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ValidateButton.setBounds(25, 491, 471, 58);
		ValidateButton.addActionListener(e ->  {
			try {
		        String texte = TextField.getText();
		        String lien = LinkTextField.getText();
		        String titre = TitleTextField.getText();
		        String cheminPDF = cheminPDFField.getText();
		        if (texte.isEmpty() || lien.isEmpty()) {
		            throw new IllegalArgumentException("Veuillez remplir tous les champs.");
		        }
		        controleur.demandeUInterfaceDonnées(titre, texte, lien, cheminPDF);
		    } catch (IllegalArgumentException ex) {
		        System.err.println("Erreur UI : " + ex.getMessage());
		    } catch (Exception ex) {
		        System.err.println("Erreur inattendue UI : " + ex.getMessage());
		    }
		});
		contentPane.add(ValidateButton);
		
		JLabel TitleAppLabel = new JLabel("Génération de PDF avec QRCode");
		TitleAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleAppLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TitleAppLabel.setBounds(25, 10, 453, 59);
		contentPane.add(TitleAppLabel);
		
		TextField = new JTextField();
		TextField.setBounds(107, 121, 371, 120);
		contentPane.add(TextField);
		TextField.setColumns(10);
		
		JLabel TextLabel = new JLabel("Texte :");
		TextLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TextLabel.setBounds(25, 153, 87, 58);
		contentPane.add(TextLabel);
		
		cheminPDFField = new JTextField();
		cheminPDFField.setBounds(25, 330, 472, 30);
		cheminPDFField.setEditable(false); // l'utilisateur ne peut pas écrire dedans
		contentPane.add(cheminPDFField);

		choisirFichierButton = new JButton("Choisir l'emplacement");
		choisirFichierButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		choisirFichierButton.setBounds(25, 370, 471, 30);
		choisirFichierButton.addActionListener(e -> {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Enregistrer le PDF");
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers PDF", "pdf");
		    fileChooser.setFileFilter(filter);

		    int result = fileChooser.showSaveDialog(this);
		    if (result == JFileChooser.APPROVE_OPTION) {
		        File fichier = fileChooser.getSelectedFile();
		        String chemin = fichier.getAbsolutePath();
		        if (!chemin.toLowerCase().endsWith(".pdf")) {
		            chemin += ".pdf";
		        }
		        cheminPDFField.setText(chemin);
		    }
		});
		contentPane.add(choisirFichierButton);
		
		lblNewLabel = new JLabel("Le PDF à bien été chargée dans le répertoire selectionné");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 410, 471, 71);
		contentPane.add(lblNewLabel);

	}
}
