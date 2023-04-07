package Vues.Eleve;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import Entities.*;
import Vues.User.FrmConnexion;
import Vues.User.FrmPlanning;
import Vues.User.FrmUsersUpdate;

public class FrmEleve extends JFrame {
    private JPanel pnlRoot;
    private JButton btnLecon;
    private JButton btnCour;
    private JButton btnModifierUser;
    private JButton btnPlanning;

    private JButton btnDeconnexion;
    private JLabel lblPrenom;
    private JLabel lblCompte;


    public FrmEleve(Users unUser) {
        this.setTitle("Elève Accueil");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        lblPrenom.setText(unUser.getPrenom());
        btnModifierUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                FrmUsersUpdate frm = new FrmUsersUpdate(unUser);
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
        btnCour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmEleveCours frm = new FrmEleveCours(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
        btnPlanning.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmPlanning frm = new FrmPlanning(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
        btnLecon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmEleveStats frm = new FrmEleveStats(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
    }
}
