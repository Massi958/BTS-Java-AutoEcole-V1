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
import java.util.HashMap;
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
    public ArrayList<String> GetDataGraphiqueVehicule(){
        ArrayList<String> lesVehicules = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT Count(lecon.Immatriculation) as Stats,Marque,Modele\n" +
                    "FROM lecon\n" +
                    "inner join vehicule on lecon.Immatriculation= vehicule.Immatriculation\n" +
                    "GROUP BY lecon.Immatriculation\n" +
                    "ORDER BY COUNT(lecon.Immatriculation) DESC\n" +
                    "LIMIT 1;");
            rs = ps.executeQuery();
            while ((rs.next())){
                String vehicule = rs.getString("Stats");
                lesVehicules.add(vehicule);
                String modele = rs.getString("Modele");
                lesVehicules.add(modele);
                String marque = rs.getString("Marque");
                lesVehicules.add(marque);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lesVehicules;
    }
    public HashMap<String,Double> GetDataGraphiqueVehicule2(String DateDebut,String DateFin){
        HashMap<String,Double> lesVehicules = new HashMap();
        try {
            ps = cnx.prepareStatement("SELECT COUNT(lecon.Immatriculation)*categorie.Prix as CA,categorie.Libelle\n" +
                    "FROM lecon\n" +
                    "INNER JOIN vehicule on lecon.Immatriculation=vehicule.Immatriculation\n" +
                    "INNER JOIN categorie on vehicule.CodeCategorie=categorie.CodeCategorie\n" +
                    "WHERE lecon.Reglee=1 and lecon.Date > ? and lecon.Date < ?\n" +
                    "GROUP BY categorie.Libelle\n");
            ps.setString(1,DateDebut);
            ps.setString(2,DateFin);
            rs = ps.executeQuery();
            while ((rs.next())){
                lesVehicules.put(rs.getString("Libelle"), rs.getDouble("CA"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lesVehicules;
    }
    public String GetDataGraphiqueUtiliation(String libelle,int CodeUser,String Marque,String Modele){
        String utilisation="" ;
        try {
            ps = cnx.prepareStatement("SELECT count(lecon.CodeLecon) as util\n" +
                    "FROM lecon\n" +
                    "INNER join vehicule on lecon.Immatriculation=vehicule.Immatriculation\n" +
                    "INNER JOIN categorie on vehicule.CodeCategorie=categorie.CodeCategorie\n" +
                    "INNER JOIN participe on lecon.CodeLecon=participe.CodeLecon\n" +
                    "INNER join users on participe.CodeUser=users.CodeUser\n" +
                    "WHERE categorie.Libelle=? or (vehicule.Marque=? and vehicule.Modele=? )or users.CodeUser=?;");
            ps.setString(1,libelle);
            ps.setString(2,Marque);
            ps.setString(3,Modele);
            ps.setInt(4,CodeUser);
            rs = ps.executeQuery();
            while ((rs.next())){
                utilisation = rs.getString("util");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisation;
    }



}
