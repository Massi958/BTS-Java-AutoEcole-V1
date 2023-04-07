package Vues.Moniteur;

import Controlers.CtrlUser;
import Entities.Categorie;
import Entities.Users;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FrmAjoutLicence extends JFrame{
    private JButton btnRetour;
    private JComboBox cboLicence;
    private JPanel pnlRoot;
    private JPanel pnlObtention;
    private JButton btnAjouter;
    private JScrollPane jsPlanning;
    private JTable tblLicence;
    private CtrlUser ctrlUser;
    private JDateChooser cldObtention ;
    private ModelJTable modelJTable;

    public FrmAjoutLicence(Users unUser) {
        this.setTitle("Ajout Licence");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        //JCalendar DateDebut
        cldObtention = new JDateChooser();
        cldObtention.setDateFormatString("dd/MM/yyyy");
        pnlObtention.add(cldObtention);
        modelJTable = new ModelJTable();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) cldObtention.getDateEditor();
        editor.setEditable(false);
        cldObtention.setMaxSelectableDate(java.sql.Date.valueOf(LocalDate.now().toString()));


        modelJTable = new ModelJTable();
        modelJTable.loadDatasLicence(ctrlUser.GetLicencePossede(String.valueOf(unUser.getCodeUser())));
        tblLicence.setModel(modelJTable);

        for(Categorie categorie: ctrlUser.GetLicenceNonPossede(unUser.getCodeUser())){
            cboLicence.addItem(categorie.getLibelle());

        }


        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMoniteur frm = new FrmMoniteur(unUser);
                frm.setVisible(true);
                dispose();
            }
        });


        btnAjouter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if ((cboLicence.getSelectedItem())==null){
                    String[] options = {"Voir vos Licences Possedées", "Revenir au menu Principal"};

                    int x = JOptionPane.showOptionDialog(null, "Vous possedées déjà toutes les licences",
                            "Votre Choix",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if ( x == 1){
                        super.mouseClicked(e);
                        FrmMoniteur frm = new FrmMoniteur(unUser);
                        frm.setVisible(true);
                        dispose();
                    }
                } else if (cldObtention.getDate() == null  ) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez une Date", "Votre choix", JOptionPane.WARNING_MESSAGE);
                } else {
                    modelJTable = new ModelJTable();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateObtention = sdf.format(cldObtention.getDate());
                    ctrlUser.AjoutLicence(ctrlUser.GetCodeByLibelle(cboLicence.getSelectedItem().toString()), unUser.getCodeUser(),dateObtention);
                    String[] options = {"Voir vos Licences Possedées", "Revenir au menu Principal"};

                    int x = JOptionPane.showOptionDialog(null, "Votre Licence a été ajouteée",
                            "Votre Choix",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if ( x == 1){
                        super.mouseClicked(e);
                        FrmMoniteur frm = new FrmMoniteur(unUser);
                        frm.setVisible(true);
                        dispose();
                    }else{
                        modelJTable.loadDatasLicence(ctrlUser.GetLicencePossede(String.valueOf(unUser.getCodeUser())));
                        tblLicence.setModel(modelJTable);
                        cboLicence.removeAllItems();
                        for(Categorie categorie: ctrlUser.GetLicenceNonPossede(unUser.getCodeUser())){
                            cboLicence.addItem(categorie.getLibelle());
                    }


                    }

                }


            }
        });
    }

    private void modelJTable(String valueOf) {
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
