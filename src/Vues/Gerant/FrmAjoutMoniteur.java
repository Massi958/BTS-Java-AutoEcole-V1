package Vues.Gerant;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import Controlers.CtrlUser;
import Entities.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class FrmAjoutMoniteur extends JFrame{
    private JButton btnRetour;
    private JPanel pnlRoot;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblTitre;

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
    private JPanel pnldate;
    private JPanel pnlDate;
    private JTextField txtMotDePasseC;
    private JTextField textField1;
    private JDateChooser cldDate;
    CtrlUser ctrlUser;

    public FrmAjoutMoniteur(Users unUser) {
        this.setTitle("Ajout moniteur");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        cldDate = new JDateChooser();
        cldDate.setDateFormatString("dd-MM-yyyy");
        pnldate.add(cldDate);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) cldDate.getDateEditor();
        editor.setEditable(false);
        cldDate.setMaxSelectableDate(java.sql.Date.valueOf(LocalDate.now().toString()));


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
                }else if (cldDate.getDate()==null) {
                    JOptionPane.showMessageDialog(null, "Le champ date de naissance est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if(txtMdp.getText().compareTo(txtMotDePasseC.getText())!=0){
                    JOptionPane.showMessageDialog(null, "Les mots de passe doivent être identiques", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(cldDate.getDate());
                    ctrlUser.AjoutMoniteur(txtNom.getText(),txtPrenom.getText(),txtEmail.getText(),txtMdp.getText(),cboSexe.getSelectedItem().toString(),date,txtAdresse.getText(),txtCodePostal.getText(),txtVille.getText(),txtNum.getText());
                    JOptionPane.showMessageDialog(null, "Le compte a bien été créé.", "Super !", JOptionPane.WARNING_MESSAGE);
                }
                cldDate.setDate(null);
                txtNum.setText("");
                txtAdresse.setText("");
                txtMdp.setText("");
                txtVille.setText("");
                txtEmail.setText("");
                txtCodePostal.setText("");
                txtPrenom.setText("");
                txtNom.setText("");


                }
        });
    }
}
