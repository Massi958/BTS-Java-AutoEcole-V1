package Vues.Gerant;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Controlers.CtrlUser;
import Controlers.CtrlVehicule;
import Entities.*;
import Vues.Moniteur.FrmMoniteur;

public class FrmModifierVehicule extends JFrame{
    private JButton btnModifier;
    private JTextField txtMarque;
    private JLabel lblMarque;
    private JLabel lblmodele;
    private JTextField txtModele;
    private JTextField txtImmatriculation;
    private JLabel lblImmatriculation;
    private JTextField txtAnnee;
    private JLabel lblCategorie;
    private JComboBox cboCategorie;
    private JPanel pnlRoot;
    private JButton btnRetour;
    CtrlVehicule ctrlVehicule;
    CtrlUser ctrlUser;

    public FrmModifierVehicule(String marque,String modele,String annee,String categorie,String immatriculation,Users unUser) {
        ctrlUser = new CtrlUser();
        ctrlVehicule = new CtrlVehicule();
        this.setTitle("Modifier Vehicule");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        txtAnnee.setText(annee);
        txtModele.setText(modele);
        txtMarque.setText(marque);
        txtImmatriculation.setText(immatriculation);
        for(Categorie categorie1: ctrlUser.GetLicenceNonPossede(unUser.getCodeUser())){
            cboCategorie.addItem(categorie1.getLibelle());
        }
        cboCategorie.setSelectedItem(categorie);

        btnModifier.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtModele.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Modele est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                } else if (txtAnnee.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Annee est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtMarque.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Marque est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtImmatriculation.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Immatriculation est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else{
                    ctrlVehicule.ModifierVehicule(txtMarque.getText(),txtModele.getText(),txtAnnee.getText(),String.valueOf(ctrlUser.GetCodeByLibelle(cboCategorie.getSelectedItem().toString())),txtImmatriculation.getText());

                    String[] options = {"Modifier a nouveau", "Revenir au menu des vehicule"};
                    int x = JOptionPane.showOptionDialog(null, "Le vehicule a bien été modifier",
                            "Votre Choix",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if ( x == 1){
                        super.mouseClicked(e);
                        FrmVehicule frm = new FrmVehicule(unUser);
                        frm.setVisible(true);
                        dispose();
                    }
                }



                }

        });
        btnRetour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmVehicule frm = new FrmVehicule(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
    }
}
