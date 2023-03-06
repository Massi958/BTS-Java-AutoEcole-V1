package Controlers;

import Entities.Categorie;
import Entities.Users;
import Entities.Vehicule;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlVehicule {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public CtrlVehicule() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Vehicule> GetAllVehicule(){
        ArrayList<Vehicule> lesVehicules = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT Immatriculation,Marque,Modele,Annee,vehicule.CodeCategorie\n" +
                    "FROM vehicule");
            rs = ps.executeQuery();
            while ((rs.next())){
                Vehicule vehicule = new Vehicule(rs.getString("CodeCategorie"),rs.getString("Annee"),rs.getString("Immatriculation"),rs.getString("Marque"),rs.getString("Modele"));
                lesVehicules.add(vehicule);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lesVehicules;
    }
    public ArrayList<Categorie> GetAllCat(){
        ArrayList<Categorie> lesCategorie = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT CodeCategorie,Prix,Libelle\n" +
                    "FROM categorie");
            rs = ps.executeQuery();
            while ((rs.next())){
                Categorie categorie = new Categorie(rs.getInt("CodeCategorie"),rs.getString("Libelle"),rs.getDouble("Prix"));
                lesCategorie.add(categorie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lesCategorie;
    }
    public ArrayList<String> GetAllCategorie(){
        ArrayList<String> lesCategories = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT categorie.Libelle\n" +
                    "FROM categorie");
            rs = ps.executeQuery();
            while ((rs.next())){
               String categorie = rs.getString("Libelle");
                lesCategories.add(categorie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lesCategories;
    }
    public String GetLibelleByCode(String Code)
    {
        String libelle = "";
        try {
            ps = cnx.prepareStatement("SELECT categorie.Libelle\n" +
                    "FROM categorie\n" +
                    "WHERE categorie.CodeCategorie=?");
            ps.setString(1, Code);
            rs = ps.executeQuery();
            rs.next();
            libelle = rs.getString("libelle");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return libelle;
    }

    public void AjoutVehicule(String Immatriculation,String Marque,String Modele,String Annee,int CodeCategorie){
        try {
            ps = cnx.prepareStatement("INSERT INTO vehicule(Immatriculation,Marque,Modele,Annee,CodeCategorie)\n" +
                    "VALUES(?,?,?,?,?)");

            ps.setString(1,Immatriculation);
            ps.setString(2,Marque);
            ps.setString(3,Modele);
            ps.setString(4,Annee);
            ps.setInt(5,CodeCategorie);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public void AjoutCategorie(String Libelle,Double Prix,int CodeCategorie){
        try {
            ps = cnx.prepareStatement("INSERT INTO categorie(CodeCategorie,Libelle,Prix)\n" +
                    "VALUES(?,?,?)");

            ps.setInt(1,CodeCategorie);
            ps.setString(2,Libelle);
            ps.setDouble(3,Prix);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public void ModifierVehicule(String marque,String model,String annee,String codecategorie ,String immatriculation){
        try {
            ps = cnx.prepareStatement("UPDATE vehicule SET Marque=?,Modele=?,Annee=?,CodeCategorie=?\n" +
                    "WHERE vehicule.Immatriculation=?");

            ps.setString(1,marque);
            ps.setString(2,model);
            ps.setString(3,annee);
            ps.setString(4,codecategorie);
            ps.setString(5,immatriculation);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public void ModifierCategorie(String Libelle,Double Prix,int CodeCategorie){
        try {
            ps = cnx.prepareStatement("UPDATE categorie SET Prix=?,Libelle=?\n" +
                    "WHERE categorie.CodeCategorie=?");

            ps.setDouble(1,Prix);
            ps.setString(2,Libelle);
            ps.setInt(3,CodeCategorie);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }



}
