package Tools;

import Entities.*;
import Controlers.*;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel {
    private String[] nomsColonnes;
    private Object[][] rows;

    private CtrlUser ctrlUser;
    private CtrlVehicule ctrlVehicule;

    @Override
    public String getColumnName(int column) {
        return nomsColonnes[column];
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return nomsColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }
    public  void loadDatasPlanning(ArrayList<Planning> lePlanning,Users unUser){
        ctrlUser = new CtrlUser();
        if (unUser.getStatut().equals("moniteur")){
            nomsColonnes = new String[]{"Date de la leçon","Heure de la leçon", "Véhicule","Elève"};
            rows = new Object[lePlanning.size()][4];
            int i = 0;
            for (Planning planning: lePlanning){
                rows[i][0] = planning.getDatePlanning();
                rows[i][1] = planning.getHeurePlanning();
                rows[i][2] = ctrlUser.GetVehiculeByCodeLecon(planning.getCodeLecon());
                rows[i][3] = ctrlUser.GetPrenomByCodeLecon(unUser.getCodeUser(),planning.getCodeLecon());

                i++;
            }
        }else if(unUser.getStatut().equals("elève")){
            nomsColonnes = new String[]{"Date de la leçon","Heure de la leçon", "Véhicule","Moniteur"};
            rows = new Object[lePlanning.size()][4];
            int i = 0;
            for (Planning planning: lePlanning){
                rows[i][0] = planning.getDatePlanning();
                rows[i][1] = planning.getHeurePlanning();
                rows[i][2] = ctrlUser.GetVehiculeByCodeLecon(planning.getCodeLecon());
                rows[i][3] = ctrlUser.GetPrenomByCodeLecon(unUser.getCodeUser(),planning.getCodeLecon());

                i++;
            }
        }else{
            nomsColonnes = new String[]{"Date de la leçon","Heure de la leçon", "Véhicule","Participant"};
            rows = new Object[lePlanning.size()][4];
            int i = 0;
            for (Planning planning: lePlanning){
                rows[i][0] = planning.getDatePlanning();
                rows[i][1] = planning.getHeurePlanning();
                rows[i][2] = ctrlUser.GetVehiculeByCodeLecon(planning.getCodeLecon());
                rows[i][3] = ctrlUser.GetLesPrenomByCodeLecon(planning.getCodeLecon());
                i++;
            }
        }


    }

    public  void loadDatasLicence(ArrayList<Licence> lesLicencePossede){
        nomsColonnes = new String[]{"Catégorie","Date d'obtention"};
        rows = new Object[lesLicencePossede.size()][2];
        int i = 0;
        for (Licence licence: lesLicencePossede){
            rows[i][0] = licence.getLibelle();
            rows[i][1] = licence.getDateObtention();
            i++;
        }
    }
    public  void loadDatasCategorie(ArrayList<Categorie> lesCategorie){
        nomsColonnes = new String[]{"Code Catégorie","Libellé","Prix"};
        rows = new Object[lesCategorie.size()][3];
        int i = 0;
        for (Categorie categorie: lesCategorie){
            rows[i][0] = categorie.getCodeCategorie();
            rows[i][1] = categorie.getLibelle();
            rows[i][2] = categorie.getPrix();
            i++;
        }
    }

    public  void loadDatasVehicule(ArrayList<Vehicule> lesVehicule){
        ctrlVehicule = new CtrlVehicule();
        nomsColonnes = new String[]{"Marque","Modéle","Immatriculation","Anneé","Catégorie"};
        rows = new Object[lesVehicule.size()][5];
        int i = 0;
        for (Vehicule vehicule: lesVehicule){
            rows[i][0] = vehicule.getMarque();
            rows[i][1] = vehicule.getModel();
            rows[i][2] = vehicule.getImatriculation();
            rows[i][3] = vehicule.getAnnee();
            rows[i][4] = ctrlVehicule.GetLibelleByCode(vehicule.getCodeCategorie());
            i++;
        }
    }


}
