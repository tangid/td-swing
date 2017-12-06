package com.fredericboisguerin.insa.calculateurprix;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class CalculateurPrixView extends JFrame {

    private final CalculateurPrixPresenter presenter;

    public CalculateurPrixView() throws HeadlessException {
        super("Calculateur de prix");
        this.presenter = new CalculateurPrixPresenter(this);

        JLabel prixArticleLabel = new JLabel("Prix d'un article (€) : ");
        JTextField prixArticleTextField = new JTextField(10);
        prixArticleLabel.setLabelFor(prixArticleTextField);
        prixArticleTextField.setToolTipText("Entrez ici le montant d'un article");

        JLabel quantiteArticle = new JLabel("Quantité ");
        JTextField quantiteArticleField = new JTextField(10);
        prixArticleLabel.setLabelFor(quantiteArticleField);
        prixArticleTextField.setToolTipText("Entrez ici la quantité d'un article");

        String tab[] = {    "Belgique", "Danemark",     "Allemagne" ,   "Grèce" ,
                            "Espagne",  "France",       "Irlande" ,     "Italie" ,
                            "Chypre",   "Luxembourg" ,  "Pays Bas" ,    "Autriche" ,
                            "Portugal", "Suède"};
        String tva[] = {"1.21" ,"1.25", "1.19", "1.23","1.21" ,"1.20" ,"1.23" ,"1.22" ,"1.19", "1.15", "1.21", "1.20", "1.23", "1.25"} ;
        JComboBox comboBox = new JComboBox<String>(tab);
        JLabel cBox = new JLabel("Pays ");
        comboBox.setSelectedIndex(6);

        JLabel montantHTLabel = new JLabel("Montant HT : ");
        JFormattedTextField montantHTTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        montantHTTextField.setValue(0);
        montantHTTextField.setEditable(false);
        montantHTLabel.setLabelFor(montantHTTextField);

        JLabel montantTTCLabel = new JLabel("Montant TTC(france) : ");
        JFormattedTextField montantTTCTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        montantTTCTextField.setValue(0);
        montantTTCTextField.setEditable(false);
        montantTTCLabel.setLabelFor(montantTTCTextField);

        JButton computeButton = new JButton("Calculer");
        computeButton.addActionListener(e ->
        {
            double taxe = new Double(tva[comboBox.getSelectedIndex()]);
            montantHTTextField.setValue(this.presenter.onComputeButtonClicked(prixArticleTextField.getText(), quantiteArticleField.getText()));
            montantTTCTextField.setValue((this.presenter.onComputeButtonClicked(prixArticleTextField.getText(), quantiteArticleField.getText()))*taxe);

        });

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.add(prixArticleTextField);
//crée une grille a deux colonnes pour aligner texte et case
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(prixArticleLabel);
        labelPane.add(quantiteArticle);
        labelPane.add(cBox);
        labelPane.add(montantHTLabel);
        labelPane.add(montantTTCLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(prixArticleTextField);
        fieldPane.add(quantiteArticleField);
        fieldPane.add(comboBox);
        fieldPane.add(montantHTTextField);
        fieldPane.add(montantTTCTextField);

        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, WEST);
        add(fieldPane, EAST);
        add(computeButton, SOUTH);

        prixArticleTextField.requestFocus();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void afficherErreur(String message) {
        showMessageDialog(this, message, "Erreur", ERROR_MESSAGE);
    }

    public void maj()
    {


    }
    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
