package Vues.Gerant;

import Entities.Users;
import Vues.User.FrmConnexion;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class FrmGerant extends JFrame{
    private JButton btnVehicule;
    private JButton btnCategorie;
    private JButton btnAjoutMoniteur;
    private JButton btnPlanning;
    private JPanel pnlRoot;
    private JButton btnStats;
    private JButton btnDeconnexion;
    private JLabel lblNom;
    private JLabel lblPrenom;


    public FrmGerant(Users unUser) {
        this.setTitle("Gérent Accueil");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        lblPrenom.setText(unUser.getPrenom());

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
        btnStats.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmGraphique frm = new FrmGraphique(unUser);
                frm.setVisible(true);
                dispose();

            }
        });
        btnDeconnexion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmConnexion frm = null;
                try {
                    frm = new FrmConnexion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                frm.setVisible(true);
                dispose();

            }
        });
    }
}
