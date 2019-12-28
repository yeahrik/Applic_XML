package App_XML_GUI;


import javax.swing.*;
import javax.xml.parsers.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.Hashtable;

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
            {"", "ELEMENT", "ATTRIBUTE", "TEXT", "CDATA_SECTION",
                    "ENTITY_REFERENCE", "ENTITY", "PROCESSING_INSTRUCTION",
                    "COMMENT", "DOCUMENT", "DOCUMENT_TYPE",
                    "DOCUMENT_FRAGMENT", "NOTATION"};

    private int occupNord = 0;
    private int occupEst = 0;
    private int occupSud = 0;
    private int occupOuest = 0;


    private JPanel defaultPanel;
    private JButton buttonCreateKitchen;
    private JLabel labelLongueurX;
    private JLabel labelLargeurY;
    private JButton buttonAjoutElem;
    private JComboBox jcbTypes;
    private JComboBox jcbCote;
    private JButton btnVisualiser;
    private JButton btnSauvegarder;


    // constructeur
    public Applic_XML() {
        buttonCreateKitchen.addActionListener(e -> HandlerBtnCreateKitchen());
        buttonAjoutElem.addActionListener(e -> HandlerBtnAjouterElement());
        btnVisualiser.addActionListener(e -> HandlerBtnVisualiser());
        btnSauvegarder.addActionListener(e -> HandlerBtnSauvegarder());
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
        if (result == JOptionPane.OK_OPTION)
        {
            if (jtfDimX.getText().equals("") || jtfDimY.getText().equals(""))
                infoBox("Veuillez remplir tous les champs", "Erreur");
            else
            {
                // OKK
                labelLongueurX.setText(jtfDimX.getText());
                labelLargeurY.setText(jtfDimY.getText());

                // ajouter attributs de CustomKitchen
                Element racine = (Element) lesNoeuds.get("CustomKitchen");
                racine.setAttribute("kdimx", labelLongueurX.getText());
                racine.setAttribute("kdimy", labelLargeurY.getText());

                // activer choix type et bouton ajouter
                jcbTypes.setEnabled(true);
                jcbCote.setEnabled(true);
                buttonAjoutElem.setEnabled(true);

            }
        }
    }

    // Bouton "Ajouter Element"
    private void HandlerBtnAjouterElement() {
        System.out.println("Ajout d'un élément");

        // type, coté cuisine choisi
        String type_elem = jcbTypes.getSelectedItem().toString();
        String cote_cuisine = jcbCote.getSelectedItem().toString();


        // panel de la nouvelle fenetre
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Ajout d'un element de type '" + jcbTypes.getSelectedItem() + "'"));
        panel.add(new JLabel("Veuillez entrer les dimensions (x,y,z) de l'element :"));


        AnalyseTypeElement(panel, type_elem, cote_cuisine);


//        switch (type_elem)
//        {
//            case "meuble" :
//
//                AnalyseTypeElement(panel, type_elem);
//
//
//
//

//            default :
//                System.out.println("Type element inconnu !");
//        }

    }

    private void AnalyseTypeElement(JPanel panel, String type_elem, String cote_cuisine) {
        // attr : dimx, dimy, dimz (commun a tous les elements)
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

        // autres balises communes
        JTextField jtfEssBois = new JTextField();
        jtfEssBois.setText("chene");
        JTextField jtfTeinMel = new JTextField();
        jtfTeinMel.setText("clair");
        JTextField jtfNbLig = new JTextField();
        jtfNbLig.setText("3");
        JTextField jtfNbCol = new JTextField();
        jtfNbCol.setText("2");
        JTextField jtfDimXpart = new JTextField();
        jtfDimXpart.setText("20");
        JTextField jtfDimYpart = new JTextField();
        jtfDimYpart.setText("30");
        JTextField jtfDimZpart = new JTextField();
        jtfDimZpart.setText("15");
        JTextField jtfHasPorte = new JTextField();
        jtfHasPorte.setText("oui");
        JTextField jtfModelPorte = new JTextField();
        jtfModelPorte.setText("model123");
        JTextField jtfNomNouvComp = new JTextField();
        jtfNomNouvComp.setText("meuble");
        JTextField jtfElecType = new JTextField();
        jtfElecType.setText("frigo");
        JTextField jtfElecMarque = new JTextField();
        jtfElecMarque.setText("samsung");
        JTextField jtfElecModele = new JTextField();
        jtfElecModele.setText("HyperFreezer3000");
        JTextField jtfElecEngClass = new JTextField();
        jtfElecEngClass.setText("B");
        JTextField jtfElecNumSerie = new JTextField();
        jtfElecNumSerie.setText("20-123456-01");

        try
        {
            switch (type_elem)
            {
                case "meuble":

                    panel.add(new JLabel("Essence du bois :"));
                    panel.add(jtfEssBois);
                    panel.add(new JLabel("Teinte bois :"));
                    panel.add(jtfTeinMel);

                    int resultM = JOptionPane.showConfirmDialog(null, panel, "Ajout d'un element",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (resultM == JOptionPane.OK_OPTION)
                    {

                        String dimx = jtfDimX.getText();
                        String dimy = jtfDimY.getText();
                        String dimz = jtfDimZ.getText();

                        // vérifcation depassement mur
                        if(!dimx.equals("") && depasseMurAxeX(dimx, cote_cuisine))
                        {
                            infoBox("Attention ! La longueur depasse le mur (cote " + cote_cuisine + "). Veuillez choisir une taille plus petite", "Erreur");
                            return;
                        }

                        Element el = documentCree.createElement(type_elem);

                        el.setAttribute("dimx", dimx);
                        el.setAttribute("dimy", dimy);
                        el.setAttribute("dimz", dimz);

                        Element el_ess_bois = documentCree.createElement("essence_bois");
                        Text eltxt_ess_bois = documentCree.createTextNode(jtfEssBois.getText());
                        Element el_tein_bois = documentCree.createElement("teinte_bois");
                        Text eltxt_tein_bois = documentCree.createTextNode(jtfTeinMel.getText());


                        // composer dom
                        Element cote = (Element) lesNoeuds.get(cote_cuisine);
                        cote.appendChild(el);
                        el.appendChild(el_ess_bois);
                        el_ess_bois.appendChild(eltxt_ess_bois);
                        el.appendChild(el_tein_bois);
                        el_tein_bois.appendChild(eltxt_tein_bois);

                    }
                    break;

                case "armoire":
                    panel.add(new JLabel("Essence du bois :"));
                    panel.add(jtfEssBois);
                    panel.add(new JLabel("Teinte bois :"));
                    panel.add(jtfTeinMel);
                    panel.add(new JLabel("Nombre lignes :"));
                    panel.add(jtfNbLig);
                    panel.add(new JLabel("Nombre colonnes :"));
                    panel.add(jtfNbCol);
                    panel.add(new JLabel("Dim X etageres :"));
                    panel.add(jtfDimXpart);
                    panel.add(new JLabel("Dim Y etageres :"));
                    panel.add(jtfDimYpart);
                    panel.add(new JLabel("Dim Z etageres :"));
                    panel.add(jtfDimZpart);
                    panel.add(new JLabel("Armoire a-t-elle des portes ? (oui/non) :"));
                    panel.add(jtfHasPorte);
                    panel.add(new JLabel("Si oui, quel est son modele ?"));
                    panel.add(jtfModelPorte);


                    int resultA = JOptionPane.showConfirmDialog(null, panel, "Ajout d'un element",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (resultA == JOptionPane.OK_OPTION)
                    {
                        String dimx = jtfDimX.getText();
                        String dimy = jtfDimY.getText();
                        String dimz = jtfDimZ.getText();

                        // vérifcation depassement mur
                        if(!dimx.equals("") && depasseMurAxeX(dimx, cote_cuisine))
                        {
                            infoBox("Attention ! La longueur depasse le mur (cote " + cote_cuisine + "). Veuillez choisir une taille plus petite", "Erreur");
                            return;
                        }

                        Element el = documentCree.createElement(type_elem);

                        el.setAttribute("dimx", dimx);
                        el.setAttribute("dimy", dimy);
                        el.setAttribute("dimz", dimz);

                        Element el_ess_bois = documentCree.createElement("essence_bois");
                        Text eltxt_ess_bois = documentCree.createTextNode(jtfEssBois.getText());
                        Element el_tein_bois = documentCree.createElement("teinte_bois");
                        Text eltxt_tein_bois = documentCree.createTextNode(jtfTeinMel.getText());
                        Element el_nb_lig = documentCree.createElement("nblig");
                        Text eltxt_nb_lig = documentCree.createTextNode(jtfNbLig.getText());
                        Element el_nb_col = documentCree.createElement("nbcol");
                        Text eltxt_nb_col = documentCree.createTextNode(jtfNbCol.getText());
                        Element el_hasporte = documentCree.createElement("has_porte");
                        Text eltxt_hasporte = documentCree.createTextNode(jtfHasPorte.getText());


                        // On assume que la taille de toutes les parties/tiroirs est la meme ! (simplification)
                        // composer dom
                        Element cote = (Element) lesNoeuds.get(cote_cuisine);
                        cote.appendChild(el);
                        el.appendChild(el_nb_lig);
                        el_nb_lig.appendChild(eltxt_nb_lig);
                        el.appendChild(el_nb_col);
                        el_nb_col.appendChild(eltxt_nb_col);
                        el.appendChild(el_ess_bois);
                        el_ess_bois.appendChild(eltxt_ess_bois);
                        el.appendChild(el_tein_bois);
                        el_tein_bois.appendChild(eltxt_tein_bois);
                        el.appendChild(el_hasporte);
                        el_hasporte.appendChild(eltxt_hasporte);

                        if(jtfHasPorte.getText().equals("oui"))
                        {
                            Element el_modelporte = documentCree.createElement("model_porte");
                            Text eltxt_modelporte = documentCree.createTextNode(jtfModelPorte.getText());
                            el.appendChild(el_modelporte);
                            el_modelporte.appendChild(eltxt_modelporte);
                        }

                        // BOUCLE : pour chaque ligne, chaque colonne
                        for (int i = 0; i < Integer.parseInt(jtfNbLig.getText()); i++)
                        {
                            // ajout <lig>
                            Element lig = documentCree.createElement("lig");
                            el.appendChild(lig);

                            for (int j = 0; j < Integer.parseInt(jtfNbCol.getText()); j++)
                            {
                                //ajout <col>
                                Element col = documentCree.createElement("col");

                                Element el_etagere = documentCree.createElement("etagere");

                                el_etagere.setAttribute("dimx", jtfDimXpart.getText());
                                el_etagere.setAttribute("dimy", jtfDimYpart.getText());
                                el_etagere.setAttribute("dimz", jtfDimZpart.getText());

                                lig.appendChild(col);
                                col.appendChild(el_etagere);

                            }
                        }

                    }

                    break;
                case "meuble_tiroirs":

                    panel.add(new JLabel("Essence du bois :"));
                    panel.add(jtfEssBois);
                    panel.add(new JLabel("Teinte bois :"));
                    panel.add(jtfTeinMel);
                    panel.add(new JLabel("Nombre lignes :"));
                    panel.add(jtfNbLig);
                    panel.add(new JLabel("Nombre colonnes :"));
                    panel.add(jtfNbCol);
                    panel.add(new JLabel("Dim X composant :"));
                    panel.add(jtfDimXpart);
                    panel.add(new JLabel("Dim Y composant :"));
                    panel.add(jtfDimYpart);
                    panel.add(new JLabel("Dim Z composant :"));
                    panel.add(jtfDimZpart);

                    int resultMT = JOptionPane.showConfirmDialog(null, panel, "Ajout d'un element",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (resultMT == JOptionPane.OK_OPTION)
                    {
                        String dimx = jtfDimX.getText();
                        String dimy = jtfDimY.getText();
                        String dimz = jtfDimZ.getText();

                        // vérifcation depassement mur
                        if(!dimx.equals("") && depasseMurAxeX(dimx, cote_cuisine))
                        {
                            infoBox("Attention ! La longueur depasse le mur (cote " + cote_cuisine + "). Veuillez choisir une taille plus petite", "Erreur");
                            return;
                        }


                        Element el = documentCree.createElement(type_elem);

                        el.setAttribute("dimx", dimx);
                        el.setAttribute("dimy", dimy);
                        el.setAttribute("dimz", dimz);

                        Element el_ess_bois = documentCree.createElement("essence_bois");
                        Text eltxt_ess_bois = documentCree.createTextNode(jtfEssBois.getText());
                        Element el_tein_bois = documentCree.createElement("teinte_bois");
                        Text eltxt_tein_bois = documentCree.createTextNode(jtfTeinMel.getText());
                        Element el_nb_lig = documentCree.createElement("nblig");
                        Text eltxt_nb_lig = documentCree.createTextNode(jtfNbLig.getText());
                        Element el_nb_col = documentCree.createElement("nbcol");
                        Text eltxt_nb_col = documentCree.createTextNode(jtfNbCol.getText());

                        // On assume que la taille de toutes les parties/tiroirs est la meme ! (simplification)
                        // composer dom
                        Element cote = (Element) lesNoeuds.get(cote_cuisine);
                        cote.appendChild(el);
                        el.appendChild(el_nb_lig);
                        el_nb_lig.appendChild(eltxt_nb_lig);
                        el.appendChild(el_nb_col);
                        el_nb_col.appendChild(eltxt_nb_col);
                        el.appendChild(el_ess_bois);
                        el_ess_bois.appendChild(eltxt_ess_bois);
                        el.appendChild(el_tein_bois);
                        el_tein_bois.appendChild(eltxt_tein_bois);

                        // BOUCLE : pour chaque ligne, chaque colonne
                        for (int i = 0; i < Integer.parseInt(jtfNbLig.getText()); i++)
                        {
                            // ajout <lig>
                            Element lig = documentCree.createElement("lig");
                            el.appendChild(lig);

                            for (int j = 0; j < Integer.parseInt(jtfNbCol.getText()); j++)
                            {
                                //ajout <col>
                                Element col = documentCree.createElement("col");

                                //Text tiroir = documentCree.createTextNode("tiroir");
                                Element el_tiroir = documentCree.createElement("tiroir");
                                Element el_hasporte = documentCree.createElement("has_porte");
                                Text eltxt_hasporte = documentCree.createTextNode("non");
                                Element el_modelporte = documentCree.createElement("model_porte");
                                Text eltxt_modelporte = documentCree.createTextNode("model123");

                                el_tiroir.setAttribute("dimx", jtfDimXpart.getText());
                                el_tiroir.setAttribute("dimy", jtfDimYpart.getText());
                                el_tiroir.setAttribute("dimz", jtfDimZpart.getText());

                                lig.appendChild(col);
                                col.appendChild(el_tiroir);
                                el_tiroir.appendChild(el_hasporte);


                                int has_porte = JOptionPane.showConfirmDialog(null, "Composante (ligne " + (i + 1) + " et colonne " + (j + 1) + ") possede une porte ?");
                                if (has_porte == JOptionPane.YES_OPTION)
                                {
                                    eltxt_hasporte = documentCree.createTextNode("oui");
                                    el_tiroir.appendChild(el_modelporte);
                                    el_modelporte.appendChild(eltxt_modelporte);

                                }

                                el_hasporte.appendChild(eltxt_hasporte);

                            }
                        }

                    }
                    break;
                case "composite":

                    panel.add(new JLabel("Nombre lignes :"));
                    panel.add(jtfNbLig);
                    panel.add(new JLabel("Nombre colonnes :"));
                    panel.add(jtfNbCol);

                    int resultC = JOptionPane.showConfirmDialog(null, panel, "Ajout d'un element",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (resultC == JOptionPane.OK_OPTION)
                    {
                        String dimx = jtfDimX.getText();
                        String dimy = jtfDimY.getText();
                        String dimz = jtfDimZ.getText();

                        // vérifcation depassement mur
                        if(!dimx.equals("") && depasseMurAxeX(dimx, cote_cuisine))
                        {
                            infoBox("Attention ! La longueur depasse le mur (cote " + cote_cuisine + "). Veuillez choisir une taille plus petite", "Erreur");
                            return;
                        }

                        Element el = documentCree.createElement(type_elem);

                        el.setAttribute("dimx", dimx);
                        el.setAttribute("dimy", dimy);
                        el.setAttribute("dimz", dimz);


                        Element el_nb_lig = documentCree.createElement("nblig");
                        Text eltxt_nb_lig = documentCree.createTextNode(jtfNbLig.getText());
                        Element el_nb_col = documentCree.createElement("nbcol");
                        Text eltxt_nb_col = documentCree.createTextNode(jtfNbCol.getText());

                        // On assume que la taille de toutes les parties/tiroirs est la meme ! (simplification)
                        // composer dom
                        Element cote = (Element) lesNoeuds.get(cote_cuisine);
                        cote.appendChild(el);
                        el.appendChild(el_nb_lig);
                        el_nb_lig.appendChild(eltxt_nb_lig);
                        el.appendChild(el_nb_col);
                        el_nb_col.appendChild(eltxt_nb_col);


                        // BOUCLE : pour chaque ligne, chaque colonne
                        for (int i = 0; i < Integer.parseInt(jtfNbLig.getText()); i++)
                        {
                            // ajout <lig>
                            Element lig = documentCree.createElement("lig");
                            el.appendChild(lig);

                            for (int j = 0; j < Integer.parseInt(jtfNbCol.getText()); j++)
                            {
                                //ajout <col>
                                Element col = documentCree.createElement("col");

                                // panel de la nouvelle fenetre : Demander nom et dimensions element
                                JPanel panelNC = new JPanel(new GridLayout(0, 1));
                                panelNC.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                                panelNC.add(new JLabel("Ajout d'une nouvelle composante (ligne " + (i + 1) + " et colonne " + (j + 1) + ")"));
                                panelNC.add(new JLabel("Intitule composante :"));
                                panelNC.add(jtfNomNouvComp);
                                panelNC.add(new JLabel("Dim X composante :"));
                                panelNC.add(jtfDimXpart);
                                panelNC.add(new JLabel("Dim Y composante :"));
                                panelNC.add(jtfDimYpart);
                                panelNC.add(new JLabel("Dim Z composante :"));
                                panelNC.add(jtfDimZpart);



                                int resultNC = JOptionPane.showConfirmDialog(null, panelNC, "Ajout d'une composante",
                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                                if (resultNC == JOptionPane.OK_OPTION)
                                {
                                    Element el_nouv_compos = documentCree.createElement(jtfNomNouvComp.getText());

                                    el_nouv_compos.setAttribute("dimx", jtfDimXpart.getText());
                                    el_nouv_compos.setAttribute("dimy", jtfDimYpart.getText());
                                    el_nouv_compos.setAttribute("dimz", jtfDimZpart.getText());

                                    lig.appendChild(col);
                                    col.appendChild(el_nouv_compos);
                                }
                                else if(resultNC == JOptionPane.CANCEL_OPTION)
                                {
                                    Element el_nouv_compos = documentCree.createElement("vide");

                                    el_nouv_compos.setAttribute("dimx", jtfDimXpart.getText());
                                    el_nouv_compos.setAttribute("dimy", jtfDimYpart.getText());
                                    el_nouv_compos.setAttribute("dimz", jtfDimZpart.getText());

                                    lig.appendChild(col);
                                    col.appendChild(el_nouv_compos);
                                }

                            }
                        }

                    }

                    break;
                case "electro":


                    panel.add(new JLabel("Type electromenager :"));
                    panel.add(jtfElecType);
                    panel.add(new JLabel("Marque :"));
                    panel.add(jtfElecMarque);
                    panel.add(new JLabel("Modele :"));
                    panel.add(jtfElecModele);
                    panel.add(new JLabel("Energy Class :"));
                    panel.add(jtfElecEngClass);
                    panel.add(new JLabel("Serial Number :"));
                    panel.add(jtfElecNumSerie);

                    int resultE = JOptionPane.showConfirmDialog(null, panel, "Ajout d'un element electromenager",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (resultE == JOptionPane.OK_OPTION)
                    {

                        String dimx = jtfDimX.getText();
                        String dimy = jtfDimY.getText();
                        String dimz = jtfDimZ.getText();

                        // vérifcation depassement mur
                        if(!dimx.equals("") && depasseMurAxeX(dimx, cote_cuisine))
                        {
                            infoBox("Attention ! La longueur depasse le mur (cote " + cote_cuisine + "). Veuillez choisir une taille plus petite", "Erreur");
                            return;
                        }

                        Element el = documentCree.createElement(type_elem);

                        el.setAttribute("dimx", dimx);
                        el.setAttribute("dimy", dimy);
                        el.setAttribute("dimz", dimz);

                        Element el_electr_type = documentCree.createElement("electro_type");
                        Text eltxt_electr_type = documentCree.createTextNode(jtfElecType.getText());
                        Element el_electr_marque = documentCree.createElement("electro_maque");
                        Text eltxt_electr_marque = documentCree.createTextNode(jtfElecMarque.getText());
                        Element el_electr_modele = documentCree.createElement("electro_modele");
                        Text eltxt_electr_modele = documentCree.createTextNode(jtfElecModele.getText());
                        Element el_electr_class_eng = documentCree.createElement("electro_class_eng");
                        Text eltxt_electr_class_eng = documentCree.createTextNode(jtfElecEngClass.getText());
                        Element el_electr_num_ser = documentCree.createElement("electro_num_ser");
                        Text eltxt_electr_num_ser = documentCree.createTextNode(jtfElecNumSerie.getText());


                        // composer dom
                        Element cote = (Element) lesNoeuds.get(cote_cuisine);
                        cote.appendChild(el);
                        el.appendChild(el_electr_type);
                        el_electr_type.appendChild(eltxt_electr_type);
                        el.appendChild(el_electr_marque);
                        el_electr_marque.appendChild(eltxt_electr_marque);
                        el.appendChild(el_electr_modele);
                        el_electr_modele.appendChild(eltxt_electr_modele);
                        el.appendChild(el_electr_class_eng);
                        el_electr_class_eng.appendChild(eltxt_electr_class_eng);
                        el.appendChild(el_electr_num_ser);
                        el_electr_num_ser.appendChild(eltxt_electr_num_ser);

                    }
                    break;
                case "vide":

                    jtfDimY.setEnabled(false);
                    jtfDimZ.setEnabled(false);

                    int resultV = JOptionPane.showConfirmDialog(null, panel, "Ajout d'un element",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (resultV == JOptionPane.OK_OPTION)
                    {
                        String dimx = jtfDimX.getText();

                        // vérifcation depassement mur
                        if(!dimx.equals("") && depasseMurAxeX(dimx, cote_cuisine))
                        {
                            infoBox("Attention ! La longueur depasse le mur (cote " + cote_cuisine + "). Veuillez choisir une taille plus petite", "Erreur");
                            return;
                        }

                        Element el = documentCree.createElement(type_elem);

                        el.setAttribute("dimx", dimx);

                        // composer dom
                        Element cote = (Element) lesNoeuds.get(cote_cuisine);
                        cote.appendChild(el);
                    }

                    break;
                default:
                    System.out.println("Type element inconnu !");

            }

            afficheArbreEnTexte(documentCree, 0);
            System.out.println("Elément créé");
        }
        catch (DOMException e) {
            System.out.println("oh oh ? " + e.getMessage() + "\nElément non ajouté");
        }




    }

    private boolean depasseMurAxeX(String dimx, String cote_cuisine) {
        boolean result = false;

        switch(cote_cuisine)
        {
            case "nord" :
                occupNord += Integer.parseInt(dimx);

                if(occupNord > Integer.parseInt(labelLongueurX.getText()))
                {
                    result = true;
                    occupNord -= Integer.parseInt(dimx);
                }

                break;
            case "sud" :
                occupSud += Integer.parseInt(dimx);

                if(occupSud > Integer.parseInt(labelLongueurX.getText()))
                {
                    result = true;
                    occupSud -= Integer.parseInt(dimx);
                }

                break;
            case "est" :
                occupEst += Integer.parseInt(dimx);

                if(occupEst > Integer.parseInt(labelLargeurY.getText()))
                {
                    result = true;
                    occupEst -= Integer.parseInt(dimx);
                }

                break;
            case "ouest" :
                occupOuest += Integer.parseInt(dimx);

                if(occupOuest > Integer.parseInt(labelLargeurY.getText()))
                {
                    result = true;
                    occupOuest -= Integer.parseInt(dimx);
                }

                break;
        }


        return result;
    }


    // Btn Sauvegarder
    private void HandlerBtnSauvegarder() {
        // sauvegarde dans xml
        System.out.println("Ecriture de l'arbre DOM dans un fichier DomTree.xml");
        try
        {
            OutputFormat of = new OutputFormat(documentCree, "ISO-8859-1", true);
            FileOutputStream fos = new FileOutputStream(
                    "C:\\Users\\stasy\\Desktop\\XML\\DossierFinal\\Applic_XML\\data\\CustomKitchen.xml");
            BaseMarkupSerializer bms = new XMLSerializer(fos, of);
            bms.serialize(documentCree);
        } catch (Exception e)
        {
            System.out.println("Erreur de sérialisation : " + e.getMessage());
        }
    }

    // Btn Visualiser en Texte
    private void HandlerBtnVisualiser() {
        afficheArbreEnTexte(documentCree, 0);
    }


    public static void afficheArbreEnTexte(Node noeud, int profondeur)
    {
        if (noeud == null)
            return;
        String nom = noeud.getNodeName();
        String valeur = noeud.getNodeValue();

        if (valeur != null)
            valeur = valeur.trim();
        if (noeud.getNodeType() == Node.TEXT_NODE && valeur.equals("")) ;
        else println(nom + " : " + (valeur != null ? valeur : "") + " (" +
                typesNoeuds[noeud.getNodeType()] + ")", profondeur);
        NodeList enfants = noeud.getChildNodes();
        for (int i = 0; i < enfants.getLength(); i++)
        {
            afficheArbreEnTexte(enfants.item(i), profondeur + 1);
        }
    }

    public static void println(String s, int retrait)
    {
        retrait *= 2;
        for (int i = 0; i < retrait; i++) System.out.print(" ");
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
        } catch (ParserConfigurationException e)
        {
            System.out.println("Oh oh Problème de configuration du parser ..." +
                    e.getMessage());
        }
        // Creer et ajouter element racine + points cardinaux
        Element racine = documentCree.createElement("CustomKitchen");
        Element nord = documentCree.createElement("nord");
        Element est = documentCree.createElement("est");
        Element ouest = documentCree.createElement("ouest");
        Element sud = documentCree.createElement("sud");

        // Construire DOM de base
        documentCree.appendChild(racine);
        racine.appendChild(nord);
        racine.appendChild(est);
        racine.appendChild(ouest);
        racine.appendChild(sud);


        lesNoeuds.put("CustomKitchen", racine);
        lesNoeuds.put("nord", nord);
        lesNoeuds.put("est", est);
        lesNoeuds.put("ouest", ouest);
        lesNoeuds.put("sud", sud);

        afficheArbreEnTexte(documentCree, 0);
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