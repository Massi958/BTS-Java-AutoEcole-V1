package Vues.Gerant;

import Entities.Users;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FrmGerant extends JFrame{
    private JButton btnVehicule;
    private JButton btnCategorie;
    private JButton btnAjoutMoniteur;
    private JButton btnPlanning;
    private JPanel pnlRoot;


    public FrmGerant(Users unUser) {
        this.setTitle("Moniteur Acceuil");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);


        btnVehicule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmVehicule frm = new FrmVehicule(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
        btnAjoutMoniteur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmAjoutMoniteur frm = new FrmAjoutMoniteur(unUser);
                frm.setVisible(true);
                dispose();

            }
        });
        btnCategorie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmCategorie frm = new FrmCategorie(unUser);
                frm.setVisible(true);
                dispose();

            }
        });
        btnPlanning.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmPlanningGerant frm = new FrmPlanningGerant(unUser);
                frm.setVisible(true);
                dispose();

            }
        });
    }
}
