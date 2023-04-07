package Vues.Gerant;

import Controlers.CtrlUser;
import Controlers.CtrlVehicule;
import Entities.Users;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class FrmChiffreDaffaire extends JFrame {
    private JPanel pnlGraph;
    private JPanel pnlRoot;
    CtrlVehicule ctrlVehicule;
    CtrlUser ctrlUser;
    public FrmChiffreDaffaire(Users unUser,String dateDebut,String dateFin) {
        this.setTitle("Graphique");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        ctrlVehicule = new CtrlVehicule();
        // Graphique n°4 : Histogramme 1 seule série
        DefaultCategoryDataset donnees = new DefaultCategoryDataset();
        donnees = new DefaultCategoryDataset();
        double total;
        String nomModele;
        for (String valeur : ctrlVehicule.GetDataGraphiqueVehicule2(dateDebut, dateFin).keySet()) {
            total = ctrlVehicule.GetDataGraphiqueVehicule2(dateDebut, dateFin).get(valeur);
            nomModele = valeur;
            donnees.setValue(total, "", nomModele);
        }
        JFreeChart chart1 = ChartFactory.createBarChart(
                "Chiffre d'affaires par Véhicule",
                "Nom des Véhicule",
                "Montant du CA",
                donnees,
                PlotOrientation.VERTICAL,
                false, true, false);
        ChartPanel graph = new ChartPanel(chart1);
        pnlGraph.add(graph);
        pnlGraph.validate();
    }
}