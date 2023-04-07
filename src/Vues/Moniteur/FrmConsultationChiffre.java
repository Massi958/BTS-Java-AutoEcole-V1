package Vues.Moniteur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import Controlers.CtrlUser;
import Entities.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class FrmConsultationChiffre extends JFrame{
    private JButton btnRetour;
    private JPanel pnlRoot;
    private JDateChooser cldDateFin;
    private JDateChooser cldDateDebut;
    private JLabel lblLecons;
    private JTextField txtLecons;


    private JLabel lblTitle;
    private JComboBox cboTypePermis;
    private JTextField txtPrix;

    private JLabel lblPrix;
    private JButton btnGraphique;
    private JPanel pnlDateDebut;
    private JPanel pnlDateFin;
    private JLabel lblIndication;
    private CtrlUser ctrlUser;

    public FrmConsultationChiffre(Users unUser) {
        this.setTitle("Moniteur statistique");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        cldDateDebut = new JDateChooser();
        cldDateDebut.setDateFormatString("dd/MM/yyyy");
        pnlDateDebut.add(cldDateDebut);
        //JCalendar DateFin
        cldDateFin = new JDateChooser();
        cldDateFin.setDateFormatString("dd/MM/yyyy");
        pnlDateFin.add(cldDateFin);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) cldDateDebut.getDateEditor();
        editor.setEditable(false);
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) cldDateFin.getDateEditor();
        editor2.setEditable(false);
        for(Licence licence: ctrlUser.GetLicencePossede(unUser.getCodeUser())){
            cboTypePermis.addItem(licence.getLibelle());
        }
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMoniteur frm = new FrmMoniteur(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
        cboTypePermis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem()));
                int TotalLecon = ctrlUser.GetLeconsNonFaites(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem()))) + ctrlUser.GetLeconsFaites(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem())));

                txtLecons.setText(String.valueOf(TotalLecon));


                double prixpaye = ctrlUser.GetPrixLibelle(cboTypePermis.getSelectedItem().toString()) * Integer.valueOf(txtLecons.getText());
                double d = (double) Math.round(prixpaye * 100) / 100;
                txtPrix.setText(String.valueOf(d));

            }
        });
        btnGraphique.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(cldDateFin.getDate()==null || cldDateDebut.getDate()==null){
                    JOptionPane.showMessageDialog(null, "Choisissez une date de début et de fin ", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateDebut = sdf.format(cldDateDebut.getDate());
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    String dateFin = sdf.format(cldDateFin.getDate());
                    FrmGraphiqueMoniteur frm = new FrmGraphiqueMoniteur(unUser, dateDebut, dateFin);
                    frm.setVisible(true);
                    dispose();
                }
            }
        });
    }
}
