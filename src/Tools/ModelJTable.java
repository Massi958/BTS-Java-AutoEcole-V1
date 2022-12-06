package Tools;

import Entities.*;
import Controlers.*;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel {
    private String[] nomsColonnes;
    private Object[][] rows;
    private CtrlUser ctrlUser;
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
            nomsColonnes = new String[]{"Date de la lecon","Heure de la lecon", "code Lecon","Eleve"};
        }else{
            nomsColonnes = new String[]{"Date de la lecon","Heure de la lecon", "code Lecon","Moniteur"};
        }

        rows = new Object[lePlanning.size()][4];
        int i = 0;
        for (Planning planning: lePlanning){
            rows[i][0] = planning.getDatePlanning();
            rows[i][1] = planning.getHeurePlanning();
            rows[i][2] = planning.getCodeLecon();
            rows[i][3] = ctrlUser.GetPrenomByCodeLecon(unUser.getCodeUser(),planning.getCodeLecon());

            i++;
        }
    }

    public  void loadDatasLicence(ArrayList<Licence> lesLicencePossede){
        nomsColonnes = new String[]{"Categorie","Date d'obtention"};
        rows = new Object[lesLicencePossede.size()][2];
        int i = 0;
        for (Licence licence: lesLicencePossede){
            rows[i][0] = licence.getLibelle();
            rows[i][1] = licence.getDateObtention();
            i++;
        }
    }



}
