# Projet PDF + QR Code Generator

Ce projet Java permet de **générer des PDF personnalisés** incluant du texte, des images et des QR Codes.  
Il gère également la **sauvegarde et le chargement des projets** via JSON, et propose une **logique contrôleur** pour orchestrer la génération.
---
## Structure du projet
src/<br>
├─ Vue/<br>
│ ├─ UserInterface.java<br>
│ └─ PersonalizationInterface.java<br>
├─ Modele/<br>
│ ├─ DonnéeFormulaire<br>
│ ├─ DonnéesPersonnalisation.java<br>
│ ├─ ColorData.java<br>
│ └─ ProjectPDF.java<br>
├─ Services/<br>
│ ├─ ServicePDF.java<br>
│ ├─ ServiceQR.java<br>
│ └─ ColorAdapter.java<br>
├─ Controleur/<br>
│ └─ ControleurFormulaire.java<br>
└─ JUNIT/<br>
└─ TestUnit.java<br>

---

## Dossier `Vue/`

### `PersonalizationInterface.java`
- **Rôle :** Interface graphique pour personnaliser le PDF (polices, couleurs, images, QR Code).
- **Fonctionnement :**
    - **Permet de choisir police, taille, couleur, gras, italique, souligné pour le titre et le texte.**
    - **Permet de configurer dimensions et positions pour l’image et le QR Code.**
    - **Prévisualisation en temps réel des polices et couleurs.**
    - **Boutons :**
        - **Générer le PDF :** transmet les données au contrôleur pour créer le `PDF`.
        - **Sauvegarder le projet :** sauvegarde les personnalisations dans un `JSON`.
    - **Exemple d’usage dans ControleurFormulaire :**
```java
PersonalizationInterface window = new PersonalizationInterface(controleur);
window.setVisible(true);
```

### `UserInterface.java`
- **Rôle :** Interface graphique principale pour saisir le titre, texte, lien et choisir le PDF/image.
- **Fonctionnement :**
    - **Champs de saisie pour :**
        - `Titre`, `Texte`, `Lien`
        - `Chemin de sortie PDF`
        - `Chemin de l'image à insérer`
    - **Boutons :**
        - **Passez à la personnalisation :** valide les champs et ouvre `PersonalizationInterface`.
        - **Choisir l'emplacement :** ouvre un `JFileChooser` pour sélectionner le PDF.
        - **Choisir l'image à incorporer :** ouvre un `JFileChooser` pour l’image.
        - **Charger un projet :** charge un projet existant via le `ControleurFormulaire`.
- **Exemple d’usage dans ControleurFormulaire :**
```java
UserInterface window = new UserInterface(controleur);
window.setVisible(true);
```

## Dossier `Modele/`

### `DonnéeFormulaire.java`
- **Rôle :** Contient le contenu du PDF (`titre`, `texte`, `lien`) ainsi que sa personnalisation pour centralisation.
- **Fonctionnement :**
    - `Titre` : String.
    - `Texte` : String.
    - `Lien` : String.
    - Personnalisation via `DonnéesPersonalisation`
    - Utilsé par `ControleurFormulaire` pour sauvegarder les données et les utilisé pour la `génération de PDF/QR Code.
- **Exemple :**
```java
DonnéeFormulaire contenu = new DonnéeFormulaire();
contenu.setTitle("Ceci est un exemple");
contenu.setTexte("Cette exemple démontre le fonctionnement de DonnéeFormulaire");
contenu.setLien("http://Ceci_est_le_lien/DuQRCode.com`");
```

### `DonnéesPersonnalisation.java`
- **Rôle :** Contient toutes les options de personnalisation pour le PDF.
- **Fonctionnement :**
  - Polices : `FontName`, `FontSize`, `Bold`, `Italic`, `Underline`.
  - Couleurs via `ColorData`.
  - Dimensions et positions pour l’image et le QR Code.
- **Exemple :**
```java
DonnéesPersonnalisation perso = new DonnéesPersonnalisation();
perso.setTitleFontName("Arial");
perso.setTitleFontSize(18);
perso.setTitleUnderline(true);
```

### `ColorData.java`
- **Rôle :** Représente une couleur RGB.
- **Fonctionnement :** Stocke les valeurs `r`, `g`, `b` pour être utilisées par `iText7`.

### `ProjectPDF.java`
- **Rôle :** Représente un projet PDF complet.
- **Fonctionnement :**
    - Contient le `titre`, `texte`, `lien` et `options de personnalisation`.
    - Sauvegarde au format JSON via `ProjectPDF.sauvegarderProjet()`.
    - Chargement depuis JSON via `ProjectPDF.chargetProjet()`.
- **Exemple :**
```java
ProjectPDF projet = new ProjectPDF();
projet.setTitre("Mon PDF");
projet.setTexte("Ceci est un texte");
projet.setLien("https://example.com");
projet.setPersonnalisation(perso);
```

### Dossier `Services/`

### `ServiceQR.java`
- **Rôle :** Génère un QR Code à partir d’une `URL` ou `texte`.
- **Fonctionnement :**
    - Vérifie que les données sont valides.
    - Utilise `ZXing (QRCodeWriter)` pour générer le `BitMatrix`.
    - Convertit le `BitMatrix` en `BufferedImage` puis en Image iText.
    - Retourne `null` si les données sont invalides.
- **Exemple :**
```java
Image qr = ServiceQR.genererQRCode("https://github.com", 200, 200);
```

## `ServicePDF.java`
- **Rôle :** Génère un PDF complet avec `texte`, `images` et `QR Codes`.
- **Fonctionnement :**
    - Validation des paramètres.
    - Configuration des `polices` : recherche dans `C:\Windows\Fonts`, fallback sur `Helvetica`.
- **Création du PDF :**
    - Ajout du `titre` avec `police`, `taille`, `couleur` et `soulignement`.
    - Ajout du `texte` avec personnalisation.
    - Ajout d’image si fourni (dimensions et position personnalisables).
    - Génération et ajout du `QR Code` via `ServiceQR`.
    - Ajout du lien texte sous le `QR Code`.
    - Gestion des exceptions.
- **Exemple :**
```java
ServicePDF.generatePDF(
    "Titre Test",
    "Ceci est un texte",
    "https://openai.com",
    "C:\\temp\\monpdf.pdf",
    "C:\\temp\\image.png",
    perso
);
```

## Dossier `Controleur/`

## `ControleurFormulaire.java`
- **Rôle :** Interface entre les données utilisateur et la génération PDF.
- **Fonctionnement :**
    - demandeUInterfaceDonnées() : reçoit `titre`, `texte`, `lien` et chemins `PDF/Image`.
    - Valide les champs et stocke les données.
    - Appelle `ServicePDF.generatePDF()` pour créer le PDF.

### Dossier `JUNIT/`

### `TestUnit.java`
- **Rôle :** Contient les tests unitaires.
- **Tests inclus :**
    - **QR Code :** lien valide, lien vide, dimensions correctes.
    - **PDF :** génération valide, génération avec champs vides.
    - **JSON :** sauvegarde et chargement.
    - **Contrôleur :** validation des données et exceptions.
    - **Outils :** `JUnit 5` (assertThrows, assertDoesNotThrow, assertTrue, assertEquals).
    - **Fichiers temporaires pour les tests**
        - **tempPDF :** PDF temporaire pour test de génération.
        - **tempIMG :** Image temporaire pour test d’insertion.
        - **tempJSON :** JSON temporaire pour test de sauvegarde/chargement.

## `Fonctionnalités principales`
- **Génération de PDF personnalisés** (`titre`, `texte`, `image`, `QR Code`).
- **Gestion des projets via JSON** (sauvegarde et chargement).
- **Tests unitaires complets pour chaque fonctionnalité.**
- **Séparation claire :** Modele pour les données, Services pour le traitement, Controleur pour la logique métier.

## `Technologies utilisées`
- `Java 17+`
- `iText7` (PDF)
- `ZXing` (QR Codes)
- `JUnit 5` (tests unitaires)