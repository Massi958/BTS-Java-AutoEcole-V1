package Vues.Gerant;
import Controlers.CtrlVehicule;
import Entities.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmModifierCategorie extends JFrame{
    private JPanel pnlRoot;
    private JButton btnModifier;
    private JLabel lblCodeCategorie;
    private JTextField txtCodeCategorie;
    private JLabel lblLibelle;
    private JTextField txtLibelle;
    private JLabel lblPrix;
    private JTextField txtPrix;
    private JButton btnRetour;
    CtrlVehicule ctrlVehicule;


    public FrmModifierCategorie(Users unUser,String Prix,String CodeCategorie,String Libelle) {
        ctrlVehicule = new CtrlVehicule();
        this.setTitle("Modifier Categorie");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        txtPrix.setText(Prix);
        txtCodeCategorie.setText(CodeCategorie);
        txtLibelle.setText(Libelle);
        btnRetour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmCategorie frm = new FrmCategorie(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
        btnModifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtPrix.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Prix est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                } else if (txtLibelle.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Libelle est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else if (txtCodeCategorie.getText().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "Le champ Code Categorie est vide", "Attention !", JOptionPane.WARNING_MESSAGE);
                }else{
                        ctrlVehicule.ModifierCategorie(txtLibelle.getText(),Double.valueOf(txtPrix.getText()),Integer.valueOf(txtCodeCategorie.getText()));
                    String[] options = {"Modifier a nouveau", "Revenir au menu des Categories"};
                    int x = JOptionPane.showOptionDialog(null, "Le Categorie a bien été modifier",
                            "Votre Choix",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if ( x == 1){
                        super.mouseClicked(e);
                        FrmCategorie frm = new FrmCategorie(unUser);
                        frm.setVisible(true);
                        dispose();
                    }
                }

            }
        });
    }
}
