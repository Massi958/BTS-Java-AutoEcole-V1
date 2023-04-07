package Vues.User;

import Controlers.CtrlUser;
import Entities.Users;
import Vues.Eleve.FrmEleve;
import Vues.Moniteur.FrmMoniteur;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FrmUsersUpdate extends JFrame {
    private JPanel pnlRoot;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblTitre;
    private JPanel pnlDate;
    private JTextField txtAdresse;
    private JLabel lblDate;
    private JLabel lblAdresse;
    private JTextField txtVille;
    private JLabel lblVille;
    private JTextField txtCodePostal;
    private JLabel lblCodePostal;

    private JTextField txtMdp;
    private JTextField txtNum;
    private JLabel lblMdp;
    private JLabel lblNum;
    private JButton btnValider;
    private JButton btnRetour;
    private JComboBox cboSexe;
    private JTextField txtDate;
    private JTextField txtCodeUser;
    private JTextField txtEmail;
    private JLabel lblEmail;
    private JPanel pnldate;
    private CtrlUser ctrlUser;
    private JDateChooser cldDate;
    public FrmUsersUpdate(Users unUser) {
        this.setTitle("Modifications de l'utilisateur");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        cldDate = new JDateChooser();
        cldDate.setDateFormatString("dd-MM-yyyy");
        pnldate.add(cldDate);
        ctrlUser = new CtrlUser();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) cldDate.getDateEditor();
        editor.setEditable(false);
        cldDate.setMaxSelectableDate(java.sql.Date.valueOf(LocalDate.now().toString()));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                txtNom.setText(unUser.getNom());
                java.util.Date date2 = null;
                try {
                    date2 = new SimpleDateFormat("yyyy-MM-dd").parse(unUser.getDateDeNaissance());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                cldDate.setDate(date2);

                txtAdresse.setText(unUser.getAdresse1());
                txtCodeUser.setText(String.valueOf(unUser.getCodeUser()));
                txtCodePostal.setText(unUser.getCodePostal());
                txtVille.setText(unUser.getVille());
                txtMdp.setText(unUser.getMotDePasse());
                txtPrenom.setText(unUser.getPrenom());
                txtNum.setText(unUser.getTelephone());
                txtEmail.setText(unUser.getEmail());
               if(unUser.getSexe().equals("Homme")){
                   cboSexe.setSelectedIndex(0);
               } else if (unUser.getSexe().equals("Femme")) {
                   cboSexe.setSelectedIndex(1);
               }else {
                   cboSexe.setSelectedIndex(2);
               }



            }
        });
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(unUser.getStatut().compareTo("eleve")==0){
                    FrmEleve frm = new FrmEleve(unUser);
                    frm.setVisible(true);

                }else if(unUser.getStatut().compareTo("moniteur")==0){
                    FrmMoniteur frm = new FrmMoniteur(unUser);
                    frm.setVisible(true);
                }
                dispose();
            }
        });
        btnValider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtNom.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un Nom", "Votre choix", JOptionPane.WARNING_MESSAGE);
                } else if (txtPrenom.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un Prénom", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtMdp.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un mot de passe", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtAdresse.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez une adresse", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtCodePostal.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un code postal", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtVille.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez une ville", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtNum.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un Numero de telephone", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (cldDate==null) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez une Date", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(cldDate.getDate());
                 ctrlUser.ModifierProfil(Integer.valueOf(txtCodeUser.getText()),txtNom.getText(),txtPrenom.getText(),txtEmail.getText(),txtMdp.getText(),unUser.getStatut(),cboSexe.getSelectedItem().toString(),date,txtAdresse.getText(),txtCodePostal.getText(),txtVille.getText(),txtNum.getText());
                 String[] options = {"Modifier une Nouvelle fois votre Profil", "Revenir au menu Principal"};
                 int x = JOptionPane.showOptionDialog(null, "Votre Profil a bien été Modifié",
                            "Votre Choix",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if ( x == 1){
                        super.mouseClicked(e);
                        if (unUser.getStatut().equals("moniteur")){
                            FrmMoniteur frm = new FrmMoniteur(unUser);
                            frm.setVisible(true);
                            dispose();
                        }else{
                            FrmEleve frm = new FrmEleve(unUser);
                            frm.setVisible(true);
                            dispose();
                        }

                    }
                }

            }
        });
    }
}
