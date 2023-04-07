package Vues.Gerant;

import Entities.Users;

import javax.swing.*;
import Controlers.*;
import Entities.Vehicule;
import Tools.ModelJTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Year;
import java.util.regex.Pattern;

public class FrmVehicule extends JFrame {

    private JPanel pnlRoot;
    private JScrollPane jsPlanning;
    private JTable tblVehicule;
    private JButton btnModifier;
    private JLabel lblAjouter;
    private JLabel lblMarque;
    private JTextField txtMarque;
    private JLabel lblModele;
    private JTextField txtModele;
    private JTextField txtImmatriculation;
    private JComboBox cboCategorie;
    private JButton btnAjouterVehicule;
    private JButton btnRetour;
    private JSpinner txtAnnee;
    private JSpinner spinner1;
    CtrlVehicule ctrlVehicule;
    CtrlUser ctrlUser;
    private ModelJTable modelJTable;
    public FrmVehicule(Users unUser) {
        this.setTitle("Ajout/Modification de véhicule");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlVehicule = new CtrlVehicule();
        ctrlUser = new CtrlUser();


        txtAnnee.setModel(new SpinnerNumberModel(1980,1980,Year.now().getValue(),1));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) txtAnnee.getEditor()).getTextField();
        tf.setEditable(false);

        for(String categorie: ctrlVehicule.GetAllCategorie()){
            cboCategorie.addItem(categorie);

        }


        modelJTable = new ModelJTable();
        modelJTable.loadDatasVehicule(ctrlVehicule.GetAllVehicule());
        tblVehicule.setModel(modelJTable);

        btnAjouterVehicule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i=0;
                super.mouseClicked(e);
                if (txtImmatriculation.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez une Immatriculation", "Votre choix", JOptionPane.WARNING_MESSAGE);
                } else if (txtAnnee==null ) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez une Année en chiffre et non en lettre", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtMarque.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez une marque", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtModele.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un Modéle", "Votre choix", JOptionPane.WARNING_MESSAGE);
                } else if (txtImmatriculation.getText()!=null) {


                    for (Vehicule immatriculation : ctrlVehicule.GetAllVehicule()){
                        if (immatriculation.getImatriculation().compareTo(txtImmatriculation.getText()) == 0 ){
                            JOptionPane.showMessageDialog(null, "Il existe déjà une voiture avec cette immatriculation", "Votre choix", JOptionPane.WARNING_MESSAGE);
                            i=1;
                        }

                    }if (i==0){
                        ctrlVehicule.AjoutVehicule(txtImmatriculation.getText(),txtMarque.getText(),txtModele.getText(),txtAnnee.getValue().toString(), ctrlUser.GetCodeByLibelle(cboCategorie.getSelectedItem().toString()));
                        JOptionPane.showMessageDialog(null, "Le Vehicule a bien été Ajouté", "Votre choix", JOptionPane.INFORMATION_MESSAGE);
                        modelJTable = new ModelJTable();
                        modelJTable.loadDatasVehicule(ctrlVehicule.GetAllVehicule());
                        tblVehicule.setModel(modelJTable);
                    }



                }
            }
        });
        btnModifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Integer row = tblVehicule.getSelectedRow();
                System.out.print(row);
                if(row==-1){
                    JOptionPane.showMessageDialog(null, "Choisissez un vehicule à modifié", "Votre choix", JOptionPane.INFORMATION_MESSAGE);
                }else{

                    String marque = tblVehicule.getModel().getValueAt(row, 0).toString();
                    String modele = tblVehicule.getModel().getValueAt(row, 1).toString();
                    String annee = tblVehicule.getModel().getValueAt(row, 3).toString();
                    String categorie = tblVehicule.getModel().getValueAt(row, 4).toString();
                    String immatriculation = tblVehicule.getModel().getValueAt(row, 2).toString();
                    FrmModifierVehicule frm = new FrmModifierVehicule(marque,modele,annee,categorie,immatriculation,unUser);
                    frm.setVisible(true);
                    dispose();
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
