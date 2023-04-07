package Vues.Gerant;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Year;

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
    private JSpinner txtAnnee;
    private JLabel lblCategorie;
    private JComboBox cboCategorie;
    private JPanel pnlRoot;
    private JButton btnRetour;
    private JSpinner spinner1;
    CtrlVehicule ctrlVehicule;
    CtrlUser ctrlUser;

    public FrmModifierVehicule(String marque,String modele,String annee,String categorie,String immatriculation,Users unUser) {
        ctrlUser = new CtrlUser();
        ctrlVehicule = new CtrlVehicule();
        this.setTitle("Modification de véhicule");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        txtAnnee.setModel(new SpinnerNumberModel(1980,1980, Year.now().getValue(),1));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) txtAnnee.getEditor()).getTextField();
        tf.setEditable(false);

        txtAnnee.setValue((Integer.valueOf(annee)));
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
                    JOptionPane.showMessageDialog(null, "Le champ Modéle est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                } else if (txtAnnee==null) {
                    JOptionPane.showMessageDialog(null, "Le champ Année est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtMarque.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Marque est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtImmatriculation.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Immatriculation est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else{
                    ctrlVehicule.ModifierVehicule(txtMarque.getText(),txtModele.getText(),txtAnnee.getValue().toString(),String.valueOf(ctrlUser.GetCodeByLibelle(cboCategorie.getSelectedItem().toString())),txtImmatriculation.getText());

                    String[] options = {"Modifier à nouveau", "Revenir au menu des véhicule"};
                    int x = JOptionPane.showOptionDialog(null, "Le véhicule a bien été modifié",
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
