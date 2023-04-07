package Vues.Gerant;

import Controlers.CtrlUser;
import Controlers.CtrlVehicule;
import Entities.Users;
import Entities.Vehicule;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class FrmGraphique extends JFrame {
    private JPanel pnlRoot;
    private JLabel lblVehicule;
    private JTextField txtNombre;
    private JLabel lblMarque;
    private JTextField txtMarque;
    private JLabel lblStats;
    private JTextField txtStats;
    private JLabel lblUtilse;
    private JPanel pnlDateDebut;
    private JPanel pnlDateFin;
    private JPanel pnlGraph;
    private JButton voirLeChiffreDButton;
    private JLabel lblDateDebut;
    private JLabel lblDateFin;
    private JTextField txtMoniteur;
    private JTextField txtUtilisation;
    private JComboBox cboStatut;
    private JButton voirLeNombreDeButton;
    private JComboBox cboUser;
    private JButton btnRetour;
    private JLabel lblNombreDeLecon;
    private JLabel lblChiffre;
    private JLabel lblNombre;
    private JLabel lblMarque1;
    private JLabel lblUtilisation1;
    private JLabel lblMoniteur;
    private JLabel lblUtilisation;
    CtrlVehicule ctrlVehicule;
    CtrlUser ctrlUser;
    private JDateChooser cldDateDebut;
    private JDateChooser cldDateFin;
    public FrmGraphique(Users unUser) {
        this.setTitle("Statistique");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlVehicule = new CtrlVehicule();
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
        lblNombre.setText(ctrlVehicule.GetDataGraphiqueVehicule().get(1));
        lblUtilisation1.setText(ctrlVehicule.GetDataGraphiqueVehicule().get(0));
        lblMarque1.setText(ctrlVehicule.GetDataGraphiqueVehicule().get(2));
        lblMoniteur.setText(ctrlUser.GetDataGraphiqueMoniteur().get(2) + " "+ctrlUser.GetDataGraphiqueMoniteur().get(1));
        lblUtilisation.setText(ctrlUser.GetDataGraphiqueMoniteur().get(0));

        lblNombre.setFont(lblChiffre.getFont().deriveFont(Font.BOLD));
        lblUtilisation1.setFont(lblChiffre.getFont().deriveFont(Font.BOLD));
        lblMarque1.setFont(lblChiffre.getFont().deriveFont(Font.BOLD));
        lblMoniteur.setFont(lblChiffre.getFont().deriveFont(Font.BOLD));
        lblUtilisation.setFont(lblChiffre.getFont().deriveFont(Font.BOLD));
        lblChiffre.setFont(lblChiffre.getFont().deriveFont(Font.BOLD));




        voirLeChiffreDButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(cldDateFin.getDate()==null || cldDateDebut.getDate()==null ){
                    JOptionPane.showMessageDialog(null, "Choisissez une date de début et de fin ", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateDebut = sdf.format(cldDateDebut.getDate());
                    String dateFin = sdf.format(cldDateFin.getDate());

                    FrmChiffreDaffaire frm = new FrmChiffreDaffaire(unUser,dateDebut,dateFin);
                    frm.setVisible(true);

                }



            }
        });

        cboStatut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboStatut.getSelectedItem().equals("Moniteur")){
                    cboUser.removeAllItems();
                    for(Users users: ctrlUser.GetAllUser("moniteur")){

                        cboUser.addItem(users.getPrenom() +" "+ users.getNom());
                    }
                }else if (cboStatut.getSelectedItem().equals("Véhicule")){
                    cboUser.removeAllItems();
                    for(Vehicule vehicules: ctrlVehicule.GetAllVehicule()){

                        cboUser.addItem(vehicules.getMarque() +" "+ vehicules.getModel());
                    }
                }else{
                    cboUser.removeAllItems();
                    for(String categ: ctrlVehicule.GetAllCategorie()){

                        cboUser.addItem(categ);
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
       /* cboUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cboStatut.getSelectedItem().equals("Moniteur")){
                    String prenomNom = cboUser.getSelectedItem().toString();
                    String[] words = prenomNom.split(" ");
                    String prenom = words[0];
                    String nom = words[1];

                    lblNombreDeLecon.setText(ctrlVehicule.GetDataGraphiqueUtiliation("",ctrlUser.GetCodeByNomPrenom(prenom,nom),"",""));
                }else if (cboStatut.getSelectedItem().equals("Vehicule")){
                    String MarqueModele = cboUser.getSelectedItem().toString();
                    String[] words = MarqueModele.split(" ");
                    String marque = words[0];
                    String modele = words[1];
                    lblNombreDeLecon.setText(ctrlVehicule.GetDataGraphiqueUtiliation("",0,"marque","modele"));
                }else{
                    lblNombreDeLecon.setText(ctrlVehicule.GetDataGraphiqueUtiliation(cboUser.getSelectedItem().toString(),0,"",""));
                }
            }
        });*/
        /*cboUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(cboStatut.getSelectedItem().equals("Moniteur")){
                    String prenomNom = cboUser.getSelectedItem().toString();
                    String[] words = prenomNom.split(" ");
                    String prenom = words[0];
                    String nom = words[1];

                    lblNombreDeLecon.setText(ctrlVehicule.GetDataGraphiqueUtiliation("",ctrlUser.GetCodeByNomPrenom(prenom,nom),"",""));
                }else if (cboStatut.getSelectedItem().equals("Vehicule")){
                    String MarqueModele = cboUser.getSelectedItem().toString();
                    String[] words = MarqueModele.split(" ");
                    String marque = words[0];
                    String modele = words[1];
                    lblNombreDeLecon.setText(ctrlVehicule.GetDataGraphiqueUtiliation("",0,"marque","modele"));
                }else{
                    lblNombreDeLecon.setText(ctrlVehicule.GetDataGraphiqueUtiliation(cboUser.getSelectedItem().toString(),0,"",""));
                }
            }
        });*/
        voirLeNombreDeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (cboUser.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null, "Veuillez Selectionner un Choix", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else {
                    if(cboStatut.getSelectedItem().equals("Moniteur")){
                        String prenomNom = cboUser.getSelectedItem().toString();
                        String[] words = prenomNom.split(" ");
                        String prenom = words[0];
                        String nom = words[1];

                        lblChiffre.setText(ctrlVehicule.GetDataGraphiqueUtiliation("",ctrlUser.GetCodeByNomPrenom(prenom,nom),"",""));
                    }else if (cboStatut.getSelectedItem().equals("Véhicule")){
                        String MarqueModele = cboUser.getSelectedItem().toString();
                        String[] words = MarqueModele.split(" ");
                        String marque = words[0];
                        String modele = words[1];
                        lblChiffre.setText(ctrlVehicule.GetDataGraphiqueUtiliation("",0,marque,modele));
                    }else{
                        lblChiffre.setText(ctrlVehicule.GetDataGraphiqueUtiliation(cboUser.getSelectedItem().toString(),0,"",""));
                    }
                }

            }
        });

    }
}
