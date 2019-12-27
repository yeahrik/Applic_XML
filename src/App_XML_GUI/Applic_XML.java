package App_XML_GUI;



import javax.swing.*;
import javax.imageio.ImageIO;
import javax.xml.parsers.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.logging.*;

import com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.*;


public class Applic_XML
{
    // variables Documen et HashTable
    static Document documentCree = null;
    static Hashtable lesNoeuds = new Hashtable();
    private static String typesNoeuds[] =
            {"","ELEMENT", "ATTRIBUTE", "TEXT", "CDATA_SECTION",
                    "ENTITY_REFERENCE", "ENTITY", "PROCESSING_INSTRUCTION",
                    "COMMENT", "DOCUMENT", "DOCUMENT_TYPE",
                    "DOCUMENT_FRAGMENT", "NOTATION"};

    private JPanel defaultPanel;
    private JLabel jLabel1;
    private JButton buttonCreateKitchen;
    private JLabel labelLongueurX;
    private JLabel labelLargeurY;
    private JButton buttonAjoutElem;
    private JComboBox jcbTypes;


    // constructeur
    public Applic_XML() {
        buttonCreateKitchen.addActionListener(e -> HandlerBtnCreateKitchen());
        buttonAjoutElem.addActionListener(e -> HandlerBtnAjouterElement());
    }



    // Fenetre "Create Kitchen"
    private void HandlerBtnCreateKitchen() {

        // composants
        JTextField jtfDimX = new JTextField();
        jtfDimX.setText("1000");
        JTextField jtfDimY = new JTextField();
        jtfDimY.setText("1000");
        // panel
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Contructeur cuisines"));
        panel.add(new JLabel("Veuillez entrer les dimensions de la cuisine. :"));
        panel.add(new JLabel("Longueur (X) :"));
        panel.add(jtfDimX);
        panel.add(new JLabel("Largeur (Y) :"));
        panel.add(jtfDimY);

        int result = JOptionPane.showConfirmDialog(null, panel, "Constructeur cuisines custom",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if(jtfDimX.getText().equals("") || jtfDimY.getText().equals(""))
                infoBox("Veuillez remplir tous les champs","Erreur");
            else {
                // OKK
                labelLongueurX.setText(jtfDimX.getText());
                labelLargeurY.setText(jtfDimY.getText());

                // activer choix type et bouton ajouter
                jcbTypes.setEnabled(true);
                buttonAjoutElem.setEnabled(true);



            }
        }
    }

    // Bouton "Ajouter Element"
    private void HandlerBtnAjouterElement() {
        System.out.println("Ajout d'un élément");

        // panel
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Ajout d'un element de type '" + jcbTypes.getSelectedItem() + "'"));
        panel.add(new JLabel("Veuillez entrer les dimensions (x,y,z) de l'element :"));

        // attr : dimx, dimy, dimz (commun a tous elements)
        JTextField jtfDimX = new JTextField();
        jtfDimX.setText("100");
        JTextField jtfDimY = new JTextField();
        jtfDimY.setText("150");
        JTextField jtfDimZ = new JTextField();
        jtfDimZ.setText("50");
        panel.add(new JLabel("Longueur (X) :"));
        panel.add(jtfDimX);
        panel.add(new JLabel("Largeur (Y) :"));
        panel.add(jtfDimY);
        panel.add(new JLabel("Profondeur (Z) :"));
        panel.add(jtfDimZ);


        // CASE ???
        // IF meuble :
        // essence bois
        ///TODO: SOIT teinte_melaminé ?
        JTextField jtfEssBois = new JTextField();
        jtfEssBois.setText("chene");
        JTextField jtfTeinMel = new JTextField();
        jtfTeinMel.setText("clair");
        panel.add(new JLabel("Essence du bois :"));
        panel.add(jtfEssBois);
        panel.add(new JLabel("Teinte bois :"));
        panel.add(jtfTeinMel);

        // if armoire

        // if meuble_tiroirs

        // if composite

        // if electro


        int result = JOptionPane.showConfirmDialog(null, panel, "Ajout d'un element",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {

            // OK - ajouter element au DOM ici

            String type_elem = jcbTypes.getSelectedItem().toString();
            String dimx = jtfDimX.getText();
            String dimy = jtfDimY.getText();
            String dimz = jtfDimZ.getText();
            //String ess_bois = jtfEssBois.getText();
            //String tein_bois = jtfTeinMel.getText();

            Element el = documentCree.createElement(type_elem);
            System.out.println("Elément créé");
            //String attribut = TFAttribut.getText();


            //String valAttribut = TFValeurAttribut.getText();
            el.setAttribute("dimx", dimx);
            el.setAttribute("dimy", dimy);
            el.setAttribute("dimz", dimz);

            try
            {
                Element el_ess_bois = documentCree.createElement("essence_bois");
                Text eltxt_ess_bois = documentCree.createTextNode(jtfEssBois.getText());
                Element el_tein_bois = documentCree.createElement("teinte_bois");
                Text eltxt_tein_bois = documentCree.createTextNode(jtfTeinMel.getText());



                Text elTexte = null;
                Element racine = (Element) lesNoeuds.get("CustomKitchen");
                racine.setAttribute("kdimx", labelLongueurX.getText());
                racine.setAttribute("kdimy", labelLargeurY.getText());

                // composer dom
                racine.appendChild(el);
                el.appendChild(el_ess_bois);
                el_ess_bois.appendChild(eltxt_ess_bois);
                el.appendChild(el_tein_bois);
                el_tein_bois.appendChild(eltxt_tein_bois);            // .getParentNode()


//                if (lesNoeuds.containsKey(chef))
//                {
//                    System.out.println("Chef trouvé");
//                    Node pere = (Node) lesNoeuds.get(chef);
//                    pere.getParentNode().appendChild(el);
//                }
//                else
//                {
//                    System.out.println("Chef non trouvé");
//                    documentCree.getLastChild().appendChild(el);
//                }
//
//                elTexte = documentCree.createTextNode(nom);
//                el.appendChild(elTexte);
//                System.out.println("Elément ajouté");
//                lesNoeuds.put(nom, elTexte);
            }
            catch (DOMException e)
            {
                System.out.println("oh oh ? " + e.getMessage() + "\nElément non ajouté");
            }
            afficheArbreEnTexte(documentCree, 0);


            // sauvegarde dans xml
            System.out.println("Ecriture de l'arbre DOM dans un fichier DomTree.xml");
            try
            {
                OutputFormat of = new OutputFormat (documentCree, "ISO-8859-1", true);
                FileOutputStream fos = new FileOutputStream (
                        "C:\\Users\\stasy\\Desktop\\XML\\DossierFinal\\Applic_XML\\data\\DomTree.xml");
                BaseMarkupSerializer bms = new XMLSerializer(fos, of);
                bms.serialize(documentCree);
            }
            catch (Exception e)
            {
                System.out.println("Erreur de sérialisation : " + e.getMessage());
            }




        }


    }

    private void HandlerComboBoxTypes() {
        // ici réagir au changement de combobox
    }


    public static void afficheArbreEnTexte (Node noeud, int profondeur)
    {
        if (noeud == null)
            return;
        String nom = noeud.getNodeName();
        String valeur = noeud.getNodeValue();

        if (valeur != null)
            valeur = valeur.trim();
        if (noeud.getNodeType() == Node.TEXT_NODE && valeur.equals("") ) ;
        else println(nom + " : " + (valeur!=null?valeur:"") + " (" +
                typesNoeuds[noeud.getNodeType()] + ")", profondeur);
        NodeList enfants = noeud.getChildNodes();
        for (int i=0; i<enfants.getLength(); i++)
        {
            afficheArbreEnTexte (enfants.item(i), profondeur+1);
        }
    }

    public static void println (String s, int retrait)
    {
        retrait *=2;
        for (int i=0; i<retrait; i++) System.out.print(" ");
        System.out.println(s);
    }

    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Constructeur Cuisines");
        frame.setContentPane(new Applic_XML().defaultPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        // initialisation Document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            documentCree = db.newDocument();
        }
        catch (ParserConfigurationException e)
        {
            System.out.println("Oh oh Problème de configuration du parser ..." +
                    e.getMessage());
        }
        // Creer et ajouter element racine
        Element racine = documentCree.createElement("CustomKitchen");
        documentCree.appendChild(racine);
        afficheArbreEnTexte(documentCree, 0);
        lesNoeuds.put("CustomKitchen", racine);

    }
}




































































































//    // ouvrir nouvelle fenêtre
//    //1. Create the frame and paenl.
//    JFrame frameCK = new JFrame("FrameDemo");
//    JPanel panelCK = new JPanel(new GridLayout(0, 1));
//        panelCK.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//
//    //3. Create components and put them in the panel.
//    JLabel labelTitre = new JLabel("Contructeur cuisines");
//    JLabel labelExplain = new JLabel("Veuillez entrer les dimensions de la cuisine.");
//    JLabel labelDimX = new JLabel("Longueur (X) :");
//    JTextField jtfDimX = new JTextField();
//        jtfDimX.setText("1000");
//    JLabel labelDimY = new JLabel("Largeur (Y) :");
//    JTextField jtfDimY = new JTextField();
//        jtfDimY.setText("1000");
//    JButton btnOK_CK = new JButton();
//        btnOK_CK.addActionListener(e -> HandlerBtnOK_CK(frameCK));
//    JButton btnCancel_CK = new JButton();
//        btnCancel_CK.addActionListener(e -> HandlerBtnCancel_CK(frameCK));
//
//        panelCK.add(labelTitre);
//        panelCK.add(labelExplain);
//        panelCK.add(labelDimX);
//        panelCK.add(jtfDimX);
//        panelCK.add(labelDimY);
//        panelCK.add(jtfDimY);
//        panelCK.add(btnOK_CK);
//        panelCK.add(btnCancel_CK);
//
//    // ajout composants à la fenetre
//        frameCK.setContentPane(panelCK);
//    //2. Optional: What happens when the frame closes?
//    // HIDE_ON_CLOSE
//    // DISPOSE_ON_CLOSE à tester
//        frameCK.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//    //4. Size the frame.
//        frameCK.pack();
//    // Centrer fenetre
//        frameCK.setLocationRelativeTo(null);
//    //5. Show it.
//        frameCK.setVisible(true);