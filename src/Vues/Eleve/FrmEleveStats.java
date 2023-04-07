package Vues.Eleve;

import Controlers.CtrlUser;
import Entities.Categorie;
import Entities.Users;
import Vues.Eleve.FrmEleve;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.*;
import java.util.Map;

public class FrmEleveStats extends JFrame{

    private JTextField txtLeconsFaites;
    private JLabel lblLeconsFaites;
    private JLabel lblLeconsRestes;
    private JTextField txtLeconsRestes;
    private JTextField txtLeconsPayees;
    private JLabel lblLeconsPayees;
    private JTextField txtLeconsNonPayees;
    private JLabel lblLeconsNonPayees;
    private JButton btnRetour;
    private JLabel lblTitle;
    private JPanel pnlRoot;
    private JComboBox cboTypePermis;
    private JTextField txtPrixPaye;
    private JLabel lblPrixNonPaye;
    private JTextField txtPrixNonPaye;
    private JLabel lblPrixPaye;
    private JButton btnGraphique;
    private JButton btnGraphique2;
    private JButton btnGraphique3;
    private CtrlUser ctrlUser;


    public FrmEleveStats(Users unUser) {
        this.setTitle("Elève Statistique");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ctrlUser = new CtrlUser();
        for(Categorie categorie: ctrlUser.GetLicenceNonPossede(unUser.getCodeUser())){
            cboTypePermis.addItem(categorie.getLibelle());
        }

        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmEleve frm = new FrmEleve(unUser);
                frm.setVisible(true);
                dispose();
            }
        });

        txtLeconsRestes.setEditable(false);

        txtLeconsPayees.setEditable(false);
        txtLeconsFaites.setEditable(false);
        txtLeconsNonPayees.setEditable(false);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e)
            {



            }
        });
        cboTypePermis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem()));

                txtLeconsFaites.setText(String.valueOf(ctrlUser.GetLeconsFaites(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem())))));
                txtLeconsRestes.setText(String.valueOf(ctrlUser.GetLeconsNonFaites(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem())))));
                txtLeconsPayees.setText(String.valueOf(ctrlUser.GetLeconsPayees(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem())))));
                txtLeconsNonPayees.setText(String.valueOf(ctrlUser.GetLeconsNonPayees(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem())))));

                double prixpaye = ctrlUser.GetPrixLibelle(cboTypePermis.getSelectedItem().toString()) * Integer.valueOf(txtLeconsPayees.getText());
                double d = (double) Math.round(prixpaye * 100) / 100;
                double prixNonPaye = ctrlUser.GetPrixLibelle(cboTypePermis.getSelectedItem().toString()) * Integer.valueOf(txtLeconsNonPayees.getText());
                double d2 = (double) Math.round(prixNonPaye * 100) / 100;
                txtPrixPaye.setText(String.valueOf(d));
                txtPrixNonPaye.setText(String.valueOf(d2));


            }
        });
        btnGraphique.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtLeconsFaites.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez Sélectionner un type de permis", "Votre choix", JOptionPane.WARNING_MESSAGE);                }else {
                    DefaultPieDataset donnees = new DefaultPieDataset();
                    for (Map.Entry valeur : ctrlUser.GetDatasGraphiqueEleve2(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem()))).entrySet())
                    {
                        donnees.setValue(valeur.getKey().toString(), Integer.parseInt((valeur.getValue().toString()).trim()));
                    }
                    JFreeChart chart1 = ChartFactory.createRingChart(
                            "Nombre Leçons faites et à venir",
                            donnees,
                            true, // légende
                            true, // info bulle
                            true // url
                    );
                    ChartFrame frame = new ChartFrame("Graphique n°1", chart1);
                    RingPlot plot = (RingPlot) chart1.getPlot();
                    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
                    frame.pack();
                    frame.setVisible(true);
                }




            }
        });
        btnGraphique2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtLeconsFaites.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez Sélectionner un type de permis", "Votre choix", JOptionPane.WARNING_MESSAGE);
                }else {
                    DefaultPieDataset donnees = new DefaultPieDataset();
                    for (Map.Entry valeur : ctrlUser.GetDatasGraphiqueEleve(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem()))).entrySet())
                    {
                        donnees.setValue(valeur.getKey().toString(), Integer.parseInt((valeur.getValue().toString()).trim()));
                    }
                    JFreeChart chart1 = ChartFactory.createRingChart(
                            "Nombre de Leçons Payées et Non Payées",
                            donnees,
                            true, // légende
                            true, // info bulle
                            true // url
                    );
                    ChartFrame frame = new ChartFrame("Graphique n°1", chart1);
                    RingPlot plot = (RingPlot) chart1.getPlot();
                    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
                    frame.pack();
                    frame.setVisible(true);

                }

            }
        });
        btnGraphique3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtLeconsFaites.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez Sélectionner un type de permis", "Votre choix", JOptionPane.WARNING_MESSAGE);                }else {
                    double prixpaye = ctrlUser.GetPrixLibelle(cboTypePermis.getSelectedItem().toString()) * Integer.valueOf(txtLeconsPayees.getText());
                    double d = (double) Math.round(prixpaye * 100) / 100;
                    double prixNonPaye = ctrlUser.GetPrixLibelle(cboTypePermis.getSelectedItem().toString()) * Integer.valueOf(txtLeconsNonPayees.getText());
                    double d2 = (double) Math.round(prixNonPaye * 100) / 100;
                    DefaultPieDataset donnees = new DefaultPieDataset();
                    for (Map.Entry valeur : ctrlUser.GetDatasGraphiqueEleve3(unUser.getCodeUser(),ctrlUser.GetCodeByLibelle(String.valueOf(cboTypePermis.getSelectedItem())),ctrlUser.GetPrixLibelle(cboTypePermis.getSelectedItem().toString())).entrySet())
                    {
                        donnees.setValue(valeur.getKey().toString(), Double.parseDouble((valeur.getValue().toString()).trim()));

                    }
                    JFreeChart chart1 = ChartFactory.createRingChart(
                            "Prix Total des leçons Payées et Non Payées",
                            donnees,
                            true, // légende
                            true, // info bulle
                            true // url
                    );
                    ChartFrame frame = new ChartFrame("Graphique n°1", chart1);
                    RingPlot plot = (RingPlot) chart1.getPlot();
                    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
                    frame.pack();
                    frame.setVisible(true);
                }

            }
        });
    }
}
