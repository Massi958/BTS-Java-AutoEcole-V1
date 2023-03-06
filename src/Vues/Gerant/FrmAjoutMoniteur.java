package Vues.Gerant;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Controlers.CtrlUser;
import Entities.*;

public class FrmAjoutMoniteur extends JFrame{
    private JButton btnRetour;
    private JPanel pnlRoot;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblTitre;
    private JPanel pnlDate;
    private JTextField txtDate;
    private JLabel lblDate;
    private JTextField txtAdresse;
    private JLabel lblAdresse;
    private JLabel lblVille;
    private JTextField txtVille;
    private JTextField txtCodePostal;
    private JLabel lblCodePostal;
    private JTextField txtMdp;
    private JLabel lblMdp;
    private JTextField txtNum;
    private JLabel lblNum;
    private JButton btnValider;
    private JComboBox cboSexe;
    private JTextField txtEmail;
    private JLabel lblEmail;
    CtrlUser ctrlUser;

    public FrmAjoutMoniteur(Users unUser) {
        this.setTitle("Moniteur Acceuil");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        btnRetour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmGerant frm = new FrmGerant(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
        btnValider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtNom.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ nom est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                } else if (txtPrenom.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ prénom est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtMdp.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ mot de passe est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtAdresse.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ adresse est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtCodePostal.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ code postal est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtVille.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ ville est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtNum.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ numéro de téléphone est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtDate.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ date de naissance est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else {
                    ctrlUser.AjoutMoniteur(txtNom.getText(),txtPrenom.getText(),txtEmail.getText(),txtMdp.getText(),cboSexe.getSelectedItem().toString(),txtDate.getText(),txtAdresse.getText(),txtCodePostal.getText(),txtVille.getText(),txtNum.getText());
                    JOptionPane.showMessageDialog(null, "Votre compte a bien été créer", "Super !", JOptionPane.WARNING_MESSAGE);
                }
                }
        });
    }
}
