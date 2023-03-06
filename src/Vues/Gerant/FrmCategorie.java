package Vues.Gerant;

import Controlers.CtrlUser;
import Controlers.CtrlVehicule;
import Tools.ModelJTable;
import Entities.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmCategorie extends  JFrame{
    private JPanel pnlRoot;
    private JScrollPane jsPlanning;
    private JTable tblCategorie;
    private JButton btnModifier;
    private JLabel lblAjouter;
    private JLabel lblCategorie;
    private JTextField txtCodeCategorie;
    private JLabel lblModele;
    private JTextField txtLibelle;
    private JTextField txtPrix;
    private JButton btnAjouterCategorie;
    private JButton btnRetour;
    private JLabel lblPrix;
    private ModelJTable modelJTable;
    CtrlUser ctrlUser;
    CtrlVehicule ctrlVehicule;
    public FrmCategorie(Users unUser) {
        ctrlUser = new CtrlUser();
        ctrlVehicule = new CtrlVehicule();
        modelJTable = new ModelJTable();
        modelJTable.loadDatasCategorie(ctrlVehicule.GetAllCat());
        tblCategorie.setModel(modelJTable);
        txtCodeCategorie.setText(String.valueOf(ctrlUser.GetDerniereCategorie()+1));
        btnAjouterCategorie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtLibelle.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un Libelle", "Votre choix", JOptionPane.WARNING_MESSAGE);
                } else if (txtPrix.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un Prix", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else if (txtCodeCategorie.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez rentrez un numero de Categorie", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else {
                    ctrlVehicule.AjoutCategorie(txtLibelle.getText(),Double.valueOf(txtPrix.getText()),Integer.valueOf(txtCodeCategorie.getText()));
                }
                modelJTable = new ModelJTable();
                modelJTable.loadDatasCategorie(ctrlVehicule.GetAllCat());
                tblCategorie.setModel(modelJTable);
                txtCodeCategorie.setText(String.valueOf(ctrlUser.GetDerniereCategorie()+1));
                txtPrix.setText("");
                txtLibelle.setText("");
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
        this.setTitle("Acceuil Categorie");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        btnAjouterCategorie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        btnModifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Integer row = tblCategorie.getSelectedRow();
                System.out.print(row);
                if(row==-1){
                    JOptionPane.showMessageDialog(null, "Choisissez un Categorie à modifier", "Votre choix", JOptionPane.INFORMATION_MESSAGE);
                }else{

                    String Prix = tblCategorie.getModel().getValueAt(row, 2).toString();
                    String CodeCategorie = tblCategorie.getModel().getValueAt(row, 0).toString();
                    String Libelle = tblCategorie.getModel().getValueAt(row, 1).toString();
                    FrmModifierCategorie frm = new FrmModifierCategorie(unUser,Prix,CodeCategorie,Libelle);
                    frm.setVisible(true);
                    dispose();
                }


            }
        });
    }
}
