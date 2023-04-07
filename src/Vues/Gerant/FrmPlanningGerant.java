package Vues.Gerant;

import Controlers.CtrlUser;
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

public class FrmPlanningGerant extends JFrame{
    private JPanel pnlRoot;
    private JLabel lblTitleDate;
    private JLabel lblDateDebut;
    private JLabel lblDateFin;
    private JPanel pnlDateDebut;
    private JPanel pnlDateFin;
    private JLabel lblTitle;
    private JButton btnRetour;
    private JButton btnPlanning;
    private JScrollPane jsPlanning;
    private JTable tblPlanning;
    private JComboBox cboUser;
    private JComboBox cboStatut;
    private JDateChooser cldDateDebut;
    private JDateChooser cldDateFin;
    CtrlUser ctrlUser;
    private ModelJTable modelJTable;
    public FrmPlanningGerant(Users unUser) {

        ctrlUser = new CtrlUser();
        this.setTitle("Planning Gérent");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
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



        cboStatut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboStatut.getSelectedItem().equals("Moniteur")){
                    cboUser.removeAllItems();
                    for(Users users: ctrlUser.GetAllUser("moniteur")){

                        cboUser.addItem(users.getPrenom() +" "+ users.getNom());
                    }
                }else if (cboStatut.getSelectedItem().equals("Eleve")){
                    cboUser.removeAllItems();
                    for(Users users: ctrlUser.GetAllUser("eleve")){

                        cboUser.addItem(users.getPrenom() +" " +users.getNom());
                    }
                }else {
                    cboUser.removeAllItems();
                }
            }
        });
        btnPlanning.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                modelJTable = new ModelJTable();
                if(cboStatut.getSelectedItem().equals("General")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateDebut = sdf.format(cldDateDebut.getDate());
                    String dateFin = sdf.format(cldDateFin.getDate());
                    modelJTable.loadDatasPlanning(ctrlUser.GetPlanningGeneral(dateDebut,dateFin),ctrlUser.GetUnUserByPrenomNom(unUser.getPrenom(), unUser.getNom()));
                    tblPlanning.setModel(modelJTable);
                }else {


                if(cldDateFin.getDate()==null || cldDateDebut.getDate()==null || cboUser.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null, "Choisissez une date de début,de fin et un utilisateur", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else{
                    String prenomNom = cboUser.getSelectedItem().toString();
                    String[] words = prenomNom.split(" ");
                    String prenom = words[0];
                    String nom = words[1];
                    String codeUser =String.valueOf(ctrlUser.GetCodeByNomPrenom(prenom,nom));
                    modelJTable = new ModelJTable();

                    if (cboStatut.getSelectedItem().equals("General")){

                    }else{
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateDebut = sdf.format(cldDateDebut.getDate());
                        String dateFin = sdf.format(cldDateFin.getDate());
                        modelJTable.loadDatasPlanning(ctrlUser.GetUnPlanning(codeUser,dateDebut,dateFin),ctrlUser.GetUnUserByPrenomNom(prenom,nom));
                        tblPlanning.setModel(modelJTable);
                    }
                }
                }
            }

        });
        btnRetour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmGerant frm = new FrmGerant(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
    }
}
