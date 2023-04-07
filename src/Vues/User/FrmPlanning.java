package Vues.User;

import Controlers.CtrlUser;
import Entities.Users;
import Tools.ModelJTable;
import Vues.Eleve.FrmEleve;
import Vues.Moniteur.FrmMoniteur;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class FrmPlanning extends JFrame{
    private JPanel pnlRoot;
    private JPanel pnlDateDebut;
    private JDateChooser cldDateDebut;
    private JPanel pnlDateFin;
    private JDateChooser cldDateFin;
    private JLabel lblDateDebut;
    private JLabel lblDateFin;
    private JLabel lblTitleDate;
    private JTable tblPlanning;
    private JLabel lblTitle;
    private JButton btnRetour;
    private JButton btnPlanning;
    private JScrollPane jsPlanning;

    private ModelJTable modelJTable;
    CtrlUser ctrlUser;
    public FrmPlanning(Users UnUser) {
        this.setTitle("Planning");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        //JCalendar DateDebut
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


        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(UnUser.getStatut().compareTo("eleve")==0){
                    FrmEleve frm = new FrmEleve(UnUser);
                    frm.setVisible(true);

                }else if(UnUser.getStatut().compareTo("moniteur")==0){
                    FrmMoniteur frm = new FrmMoniteur(UnUser);
                    frm.setVisible(true);
                }

                dispose();
            }
        });
        btnPlanning.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);


                if(cldDateFin.getDate()==null || cldDateDebut.getDate()==null){
                    JOptionPane.showMessageDialog(null, "Choisissez une date de début et de fin ", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else{
                    modelJTable = new ModelJTable();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateDebut = sdf.format(cldDateDebut.getDate());
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    String dateFin = sdf.format(cldDateFin.getDate());
                    modelJTable = new ModelJTable();
                    modelJTable.loadDatasPlanning(ctrlUser.GetUnPlanning(String.valueOf(UnUser.getCodeUser()),dateDebut,dateFin),UnUser);
                    tblPlanning.setModel(modelJTable);
                }




            }
        });
    }
}
