package Vue;

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

	public UserInterface(ControleurFormulaire controle) {
		this.controleur = controle;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 531);
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
		ValidateButton.setBounds(26, 420, 471, 58);
		ValidateButton.addActionListener(e ->  {
			try {
		        String titre = TitleTextField.getText().trim();
		        String texte = TextField.getText().trim();
		        String lien = LinkTextField.getText().trim();
		        String cheminPDF = cheminPDFField.getText().trim();

		        if (titre.isEmpty() || lien.isEmpty() || cheminPDF.isEmpty()) {
		            javax.swing.JOptionPane.showMessageDialog(
		                this,
		                "Veuillez remplir tous les champs et sélectionner un emplacement pour le PDF.",
		                "Erreur",
		                javax.swing.JOptionPane.ERROR_MESSAGE
		            );
		            return;
		        }

		        controleur.demandeUInterfaceDonnées(titre, texte, lien, cheminPDF);
		        javax.swing.JOptionPane.showMessageDialog(
		            this,
		            "Le PDF a été généré avec succès !",
		            "Succès",
		            javax.swing.JOptionPane.INFORMATION_MESSAGE
		        );

		    } catch (Exception ex) {
		        javax.swing.JOptionPane.showMessageDialog(
		            this,
		            "Une erreur inattendue est survenue : " + ex.getMessage(),
		            "Erreur",
		            javax.swing.JOptionPane.ERROR_MESSAGE
		        );
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

	}
}
