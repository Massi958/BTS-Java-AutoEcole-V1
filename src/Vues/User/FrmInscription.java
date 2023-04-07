package Vues.User;

import Controlers.CtrlUser;
import Vues.User.FrmAcceuil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FrmInscription extends JFrame {

    private JPanel pnlRoot;
    private JLabel lblNom;
    private JTextField txtNom;
    private JLabel lblPrenom;
    private JTextField txtPrenom;
    private JLabel lblSexe;
    private JComboBox cboSexe;
    private JLabel lblDate;
    private JTextField txtDate;
    private JTextField txtEmail;
    private JLabel lblEmail;
    private JLabel lblMotDePasse;
    private JPasswordField pwdMdp;
    private JLabel lblNum;
    private JTextField txtNum;
    private JLabel lblVille;
    private JTextField txtVille;
    private JLabel lblAdresse;
    private JTextField txtAdresse;
    private JLabel lblTitle;
    private JLabel lblCodePostal;
    private JTextField txtCodePostal;
    private JButton btnValider;
    private JButton btnRetour;
    private JTextField txtMdp;
    private JPanel pnlDate;
    private JTextField txtMotDePasseC;


    private CtrlUser ctrlUser;
    private JDateChooser cldDate;
    public FrmInscription() {
        this.setTitle("Inscription");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        cldDate = new JDateChooser();
        cldDate.setDateFormatString("dd-MM-yyyy");
        pnlDate.add(cldDate);
        ctrlUser = new CtrlUser();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) cldDate.getDateEditor();
        editor.setEditable(false);
        cldDate.setMaxSelectableDate(java.sql.Date.valueOf(LocalDate.now().toString()));

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAcceuil frm = null;
                try {
                    frm = new FrmAcceuil();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
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
                }else if (cldDate.getDate()==null) {
                    JOptionPane.showMessageDialog(null, "Le champ date de naissance est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtMdp.getText().compareTo(txtMotDePasseC.getText())!=0){
                    JOptionPane.showMessageDialog(null, "Les mots de passe doivent être identiques", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(cldDate.getDate());
                    ctrlUser.AjoutUser(txtNom.getText(),txtPrenom.getText(),txtEmail.getText(),txtMdp.getText(),cboSexe.getSelectedItem().toString(),date,txtAdresse.getText(),txtCodePostal.getText(),txtVille.getText(),txtNum.getText());
                    JOptionPane.showMessageDialog(null, "Votre compte a bien été créé", "Super !", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
