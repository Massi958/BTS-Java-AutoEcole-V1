package Vues.Moniteur;

import javax.swing.*;

import Controlers.CtrlUser;
import Entities.Users;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmGraphiqueMoniteur extends JFrame{
    private CtrlUser ctrlUser;
    private JPanel pnlGraph;
    private JButton btnRetour;

    public FrmGraphiqueMoniteur(Users unUser,String dateDebut,String DateFin){
        this.setTitle("Moniteur Statistique");
        this.setContentPane(pnlGraph);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ctrlUser = new CtrlUser();


        DefaultCategoryDataset donnees = new DefaultCategoryDataset();
        donnees = new DefaultCategoryDataset();
        double total;
        String nomPigiste;
        for (String valeur : ctrlUser.GetDatasGraphique(unUser.getCodeUser(),dateDebut,DateFin).keySet())
        {
            total = ctrlUser.GetDatasGraphique(unUser.getCodeUser(),dateDebut,DateFin).get(valeur);
            nomPigiste = valeur;
            donnees.setValue(total,"",nomPigiste);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre d'euro par catégorie",
                "Nom catégorie",
                "Nombre D'euro",
                donnees,
                PlotOrientation.VERTICAL,false, true, false);

        ChartPanel graph = new ChartPanel(chart);
        pnlGraph.add(graph);
        pnlGraph.validate();
        btnRetour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrmConsultationChiffre frm = new FrmConsultationChiffre(unUser);
                frm.setVisible(true);
                dispose();
            }
        });
    }


}
