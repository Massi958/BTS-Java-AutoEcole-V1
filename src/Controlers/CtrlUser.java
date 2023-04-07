package Controlers;

import Entities.*;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlUser {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public CtrlUser() {
        cnx = ConnexionBDD.getCnx();
    }
    public Users GetUnUser(String email, String motDePasse){
        Users unUser = null;
        try {
            ps = cnx.prepareStatement("SELECT CodeUser,Nom,Prenom,Email,MotdePasse,Statut,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone FROM users WHERE Email=? and MotdePasse=?");


            ps.setString(1, email);
            ps.setString(2, motDePasse);
            rs = ps.executeQuery();
            if(rs.next()){
                unUser = new Users(rs.getString("CodeUser"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Email"),rs.getString("MotdePasse"),rs.getString("Statut"),rs.getString("Sexe"),rs.getString("DateDeNaissance"),rs.getString("Adresse1"),rs.getString("CodePostal"),rs.getString("Ville"),rs.getString("Telephone"));
            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unUser;

    }
    public Users GetUnUserByPrenomNom(String prenom, String nom){
        Users unUser = null;
        try {
            ps = cnx.prepareStatement("SELECT CodeUser,Nom,Prenom,Email,MotdePasse,Statut,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone FROM users WHERE Prenom=? and Nom=?");


            ps.setString(1, prenom);
            ps.setString(2, nom);
            rs = ps.executeQuery();
            if(rs.next()){
                unUser = new Users(rs.getString("CodeUser"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Email"),rs.getString("MotdePasse"),rs.getString("Statut"),rs.getString("Sexe"),rs.getString("DateDeNaissance"),rs.getString("Adresse1"),rs.getString("CodePostal"),rs.getString("Ville"),rs.getString("Telephone"));
            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unUser;

    }

    public int GetCodeByLibelle(String libelle)
    {
        int Code = 0;
        try {
            ps = cnx.prepareStatement("SELECT categorie.CodeCategorie\n" +
                    "FROM categorie\n" +
                    "WHERE categorie.Libelle=?");
            ps.setString(1, libelle);
            rs = ps.executeQuery();
            rs.next();
            Code = rs.getInt("CodeCategorie");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return Code;
    }
    public int GetCodeByNomPrenom(String prenom,String nom)
    {
        int Code = 0;
        try {
            ps = cnx.prepareStatement("SELECT users.CodeUser\n" +
                    "FROM users\n" +
                    "WHERE users.Prenom=? and users.Nom=?");
            ps.setString(1, prenom);
            ps.setString(2, nom);
            rs = ps.executeQuery();
            rs.next();
            Code = rs.getInt("CodeUser");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return Code;
    }

    public String GetCodeByPrenom(String prenom)
    {
        String Code = "";
        try {
            ps = cnx.prepareStatement("SELECT users.CodeUser\n" +
                    "FROM users\n" +
                    "WHERE users.Prenom=?;");
            ps.setString(1, prenom);
            rs = ps.executeQuery();
            rs.next();
            Code = rs.getString("CodeUser");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return Code;
    }
    public String GetImmatriculationByModele(String modele)
    {
       String Immatriculation = "";
        try {
            ps = cnx.prepareStatement("SELECT vehicule.Immatriculation FROM vehicule WHERE Modele=?;");
            ps.setString(1, modele);
            rs = ps.executeQuery();
            rs.next();
            Immatriculation = rs.getString("Immatriculation");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return Immatriculation;
    }
    public String GetPrenomByCodeLecon(String codeUser,String codeLecon)
    {
        String Prenom = "";
        try {
            ps = cnx.prepareStatement("SELECT users.Prenom\n" +
                    "FROM users\n" +
                    "WHERE users.CodeUser=\n" +
                    "(SELECT participe.CodeUser\n" +
                    "FROM participe\n" +
                    "WHERE participe.CodeLecon=? and participe.CodeUser not in (SELECT users.CodeUser FROM users WHERE users.CodeUser = ?))");
            ps.setString(1, codeLecon);
            ps.setString(2, codeUser);
            rs = ps.executeQuery();
            rs.next();
            Prenom = rs.getString("Prenom");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return Prenom;
    }
    public String GetLesPrenomByCodeLecon(String codeLecon)
    {
        String Prenom = "";
        try {
            ps = cnx.prepareStatement("SELECT users.Prenom\n" +
                    "FROM users\n" +
                    "WHERE users.CodeUser IN\n" +
                    "(SELECT participe.CodeUser\n" +
                    "FROM participe\n" +
                    "WHERE participe.CodeLecon=?)");
            ps.setString(1, codeLecon);
            rs = ps.executeQuery();
            while (rs.next()){
                Prenom = Prenom +rs.getString("Prenom")+ " et " ;
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

        return Prenom.substring(0, Prenom.length() - 3);
    }
    public String GetVehiculeByCodeLecon(String codeLecon)
    {
        String Vehicule = "";
        String Immatriculation = "";
        try {
            ps = cnx.prepareStatement("SELECT vehicule.Modele,vehicule.Marque\n" +
                    "FROM vehicule\n" +
                    "INNER join lecon on vehicule.Immatriculation=lecon.Immatriculation\n" +
                    "WHERE lecon.CodeLecon=?");
            ps.setString(1, codeLecon);

            rs = ps.executeQuery();
            rs.next();
            Vehicule = rs.getString("Marque") +" " +rs.getString("Modele");

            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return Vehicule;

    }
    public String GetDerniereLecon()
    {
        String dernierCode = "";
        try {
            ps = cnx.prepareStatement("SELECT MAX(lecon.CodeLecon) as max\n" +
                    "FROM lecon");
            rs = ps.executeQuery();
            rs.next();
            dernierCode = rs.getString("max");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return dernierCode;
    }
    public int GetDerniereCategorie()
    {
        int dernierCode = 0;
        try {
            ps = cnx.prepareStatement("SELECT MAX(categorie.CodeCategorie) as max\n" +
                    "FROM categorie");
            rs = ps.executeQuery();
            rs.next();
            dernierCode = rs.getInt("max");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return dernierCode;
    }
    public Double GetPrixLibelle(String libelle)
    {
        Double Prix = 0.0;
        try {
            ps = cnx.prepareStatement("SELECT categorie.Prix\n" +
                    "FROM categorie\n" +
                    "WHERE categorie.Libelle=?");
            ps.setString(1, libelle);
            rs = ps.executeQuery();
            rs.next();
            Prix = rs.getDouble("Prix");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return Prix;
    }
    public int GetLeconsFaites(String CodeUser,int CodeCategorie)
    {
        int LeconsFaites = 0;
        try {
            ps = cnx.prepareStatement("SELECT COUNT(participe.CodeLecon) as LeconsFaites \n" +
                                        "FROM participe\n" +
                                        "INNER JOIN lecon ON participe.CodeLecon = lecon.CodeLecon\n" +
                                        "INNER JOIN vehicule on lecon.Immatriculation = vehicule.Immatriculation\n" +
                                        "WHERE participe.CodeUser=? and vehicule.CodeCategorie=? and lecon.Date<NOW()");
            ps.setString(1, CodeUser);
            ps.setInt(2,CodeCategorie);
            rs = ps.executeQuery();
            rs.next();
            LeconsFaites = rs.getInt("LeconsFaites");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return LeconsFaites;
    }
    public int GetLeconsNonFaites(String CodeUser,int CodeCategorie)
    {
        int LeconPasFaite = 0;
        try {
            ps = cnx.prepareStatement("SELECT COUNT(participe.CodeLecon) as LeconPasFaite\n" +
                    "FROM participe\n" +
                    "INNER JOIN lecon ON participe.CodeLecon = lecon.CodeLecon\n" +
                    "INNER JOIN vehicule on lecon.Immatriculation = vehicule.Immatriculation\n" +
                    "WHERE participe.CodeUser=? and vehicule.CodeCategorie=? and lecon.Date>NOW()");
            ps.setString(1, CodeUser);
            ps.setInt(2,CodeCategorie);
            rs = ps.executeQuery();
            rs.next();
            LeconPasFaite = rs.getInt("LeconPasFaite");
            ps.close();
            rs.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return LeconPasFaite;
    }

    public int GetLeconsPrises(String leconprises)
    {
        int GetLeconsPrises = 0;
        try {
            ps = cnx.prepareStatement( "SELECT COUNT(participe.CodeLecon)\n" +
                    "FROM participe\n" +
                    "WHERE participe.CodeUser = ? ");
            ps.setString(1, leconprises);
            rs = ps.executeQuery();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return GetLeconsPrises;
    }

    public int GetLeconsPayees(String CodeUser,int CodeCategorie)
    {
        int LeconsPayees = 0;
        try {
            ps = cnx.prepareStatement(  "SELECT COUNT(participe.CodeLecon) as lecon\n" +
                    "FROM participe\n" +
                    "INNER JOIN lecon ON participe.CodeLecon = lecon.CodeLecon\n" +
                    "INNER JOIN vehicule on lecon.Immatriculation = vehicule.Immatriculation\n" +
                    "WHERE participe.CodeUser=? AND lecon.Reglee=1 and vehicule.CodeCategorie=?");
            ps.setString(1, CodeUser);
            ps.setInt(2,CodeCategorie);
            rs = ps.executeQuery();
            rs.next();
            LeconsPayees = rs.getInt("lecon");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return LeconsPayees;
    }


    public HashMap<String,Integer> GetDatasGraphiqueEleve(String CodeUser,int CodeCategorie)
    {
        HashMap<String,Integer> datas = new HashMap<>();

        int nbLeconsPayees = GetLeconsPayees(CodeUser,CodeCategorie);
        int nbLeconsNonPayees = GetLeconsNonPayees(CodeUser,CodeCategorie);

        datas.put("Payées",nbLeconsPayees);
        datas.put("Non Payées",nbLeconsNonPayees);

        return datas;
    }
    public HashMap<String,Integer> GetDatasGraphiqueEleve2(String CodeUser,int CodeCategorie)
    {
        HashMap<String,Integer> datas = new HashMap<>();

        int nbLeconsFait = GetLeconsFaites(CodeUser,CodeCategorie);
        int nbLeconsPasEncoreFait = GetLeconsNonFaites(CodeUser,CodeCategorie);

        datas.put("Lecon qui sont Passé",nbLeconsFait);
        datas.put("Lecon à venir",nbLeconsPasEncoreFait);

        return datas;
    }
    public HashMap<String,Double> GetDatasGraphiqueEleve3(String CodeUser,int CodeCategorie,double prixCateg)
    {
        HashMap<String,Double> datas = new HashMap<>();

        double prixPayee = GetLeconsPayees(CodeUser,CodeCategorie)*prixCateg;
        double prixPasPayee = GetLeconsNonPayees(CodeUser,CodeCategorie)*prixCateg;

        datas.put("Prix Total des Lecon Payées",prixPayee);
        datas.put("Prix Total des Lecon Non Payées",prixPasPayee);

        return datas;
    }


    public int GetLeconsNonPayees(String CodeUser,int CodeCategorie)
    {
        int LeconsNonPayees = 0;
        try {
            ps = cnx.prepareStatement(  "SELECT COUNT(participe.CodeLecon) as lecon\n" +
                    "FROM participe\n" +
                    "INNER JOIN lecon ON participe.CodeLecon = lecon.CodeLecon\n" +
                    "INNER JOIN vehicule on lecon.Immatriculation = vehicule.Immatriculation\n" +
                    "WHERE participe.CodeUser=? AND lecon.Reglee=0 and vehicule.CodeCategorie=?");
            ps.setString(1, CodeUser);
            ps.setInt(2,CodeCategorie);
            rs = ps.executeQuery();
            rs.next();
            LeconsNonPayees = rs.getInt("lecon");
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return LeconsNonPayees;
    }
    public  ArrayList<String> GetEleveDispo(String CodeUser,String Date,String Heure){
        ArrayList<String> GetEleveDispo = new ArrayList<>();
        try {
            ps = cnx.prepareStatement(  "SELECT lecon.codeLecon\n" +
                    "FROM lecon\n" +
                    "INNER JOIN participe on lecon.CodeLecon=participe.CodeLecon\n" +
                    "WHERE participe.CodeUser=? and lecon.Date=? and lecon.Heure=?");
            ps.setString(1, CodeUser);
            ps.setString(3, Heure);
            ps.setString(2, Date);
            rs = ps.executeQuery();

            if (rs.next() == true) {

                String eleve = rs.getString("codeLecon");
                GetEleveDispo.add(eleve);

            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return GetEleveDispo;


    }





    public ArrayList<Planning> GetUnPlanning(String code, String dateDebut, String dateFin){
        ArrayList<Planning> lePlanning = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT lecon.Heure,lecon.Date,lecon.CodeLecon\n" +
                    "        FROM lecon\n" +
                    "        INNER join participe on lecon.CodeLecon = participe.CodeLecon\n" +
                    "        WHERE participe.codeUser = ? and lecon.Date BETWEEN ? AND ?" +
                    "        ORDER by lecon.Date DESC;");


            ps.setString(1, code);
            ps.setString(2, dateDebut);
            ps.setString(3, dateFin);

            rs = ps.executeQuery();
            while (rs.next()){
                Planning planning = new Planning(rs.getString("Heure"), rs.getString("Date"),rs.getString("CodeLecon"));
                lePlanning.add(planning);
                 }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lePlanning;

    }
    public ArrayList<Planning> GetPlanningGeneral( String dateDebut, String dateFin){
        ArrayList<Planning> lePlanning = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT DISTINCT lecon.Heure,lecon.Date,lecon.CodeLecon\n" +
                    "        FROM lecon\n" +
                    "        INNER join participe on lecon.CodeLecon = participe.CodeLecon\n" +
                    "        WHERE lecon.Date BETWEEN ? AND ?" +
                    "        ORDER by lecon.Date DESC;");


            ps.setString(1, dateDebut);
            ps.setString(2, dateFin);

            rs = ps.executeQuery();
            while (rs.next()){
                Planning planning = new Planning(rs.getString("Heure"), rs.getString("Date"),rs.getString("CodeLecon"));
                lePlanning.add(planning);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lePlanning;

    }
    public  ArrayList<Users> GetMoniteurDisponible(String Permis,String Date,String Heure){
        ArrayList<Users> MoniteurDispo = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT users.CodeUser,Nom,Prenom,Email,MotdePasse,Statut,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone\n" +
                    "FROM users\n" +
                    "INNER JOIN licence on users.CodeUser=licence.CodeUser\n" +
                    "WHERE users.CodeUser not in (SELECT users.CodeUser FROM users INNER JOIN participe on users.CodeUser=participe.CodeUser\n" +
                    "INNER JOIN lecon on participe.CodeLecon=lecon.CodeLecon \n" +
                    "WHERE lecon.Date=? AND lecon.Heure=? ) and Statut=\"moniteur\" AND licence.CodeCategorie=?");
            ps.setString(3,Permis);
            ps.setString(1,Date);
            ps.setString(2,Heure);
            rs = ps.executeQuery();
            while(rs.next()){

                Users moniteur = new Users(rs.getString("CodeUser"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Email"),rs.getString("MotdePasse"),rs.getString("Statut"),rs.getString("Sexe"),rs.getString("DateDeNaissance"),rs.getString("Adresse1"),rs.getString("CodePostal"),rs.getString("Ville"),rs.getString("Telephone"));
                MoniteurDispo.add(moniteur);
                      }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return MoniteurDispo;


    }
    public  ArrayList<Vehicule> GetVehiculeDisponible(String Permis, String Date, String Heure){
        ArrayList<Vehicule> VehiculeDispo = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT DISTINCT vehicule.Immatriculation,vehicule.Marque,vehicule.Modele,vehicule.Annee,vehicule.CodeCategorie\n" +
                    "FROM vehicule\n" +
                    "INNER JOIN lecon on vehicule.Immatriculation=vehicule.Immatriculation\n" +
                    "INNER JOIN participe on lecon.CodeLecon= participe.CodeLecon\n" +
                    "INNER JOIN users on participe.CodeUser= users.CodeUser\n" +
                    "WHERE vehicule.Immatriculation not in (SELECT  DISTINCT vehicule.Immatriculation\n" +
                    "FROM vehicule \n" +
                    "INNER JOIN categorie on vehicule.CodeCategorie=categorie.CodeCategorie \n" +
                    "INNER JOIN lecon on vehicule.Immatriculation=lecon.Immatriculation\n" +
                    "INNER JOIN licence on categorie.CodeCategorie=licence.CodeCategorie\n" +
                    "WHERE lecon.Date=? AND lecon.Heure=? AND licence.CodeCategorie=?) and CodeCategorie=?;");
            ps.setString(3,Permis);
            ps.setString(1,Date);
            ps.setString(2,Heure);
            ps.setString(4,Permis);

            rs = ps.executeQuery();
            while(rs.next()){

                Vehicule vehicule = new Vehicule(rs.getString("CodeCategorie"),rs.getString("Annee"),rs.getString("Immatriculation"),rs.getString("Marque"),rs.getString("Modele"));
                VehiculeDispo.add(vehicule);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return VehiculeDispo;


    }
    public ArrayList<Licence> GetLicencePossede(String codeUser){
        ArrayList<Licence> lesLicencePossede = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT categorie.Libelle,licence.DateObtention,licence.CodeLicence,licence.CodeUser,licence.CodeCategorie\n" +
                    "FROM licence\n" +
                    "inner join categorie on licence.CodeCategorie= categorie.CodeCategorie\n" +
                    "INNER JOIN users on licence.CodeUser=users.CodeUser\n" +
                    "WHERE users.CodeUser=?;");


            ps.setString(1, codeUser);


            rs = ps.executeQuery();
            while (rs.next()){
                Licence licence = new Licence(rs.getString("CodeUser"),rs.getString("Libelle"),rs.getString("CodeCategorie"),rs.getString("CodeLicence"),rs.getString("DateObtention"));
                lesLicencePossede.add(licence);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesLicencePossede;

    }

    public ArrayList<Categorie> GetLicenceNonPossede(String codeUser){
        ArrayList<Categorie> lesLicenceNonPossede = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT categorie.Libelle,categorie.CodeCategorie,categorie.Prix\n" +
                    "FROM categorie \n" +
                    "WHERE categorie.CodeCategorie not IN(SELECT licence.CodeCategorie FROM licence WHERE CodeUser=?)");


            ps.setString(1, codeUser);


            rs = ps.executeQuery();
            while (rs.next()){
                Categorie categorie = new Categorie(rs.getInt("CodeCategorie"),rs.getString("Libelle"),rs.getDouble("Prix"));
                lesLicenceNonPossede.add(categorie);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesLicenceNonPossede;

    }

     public void ModifierProfil(int codeUser,String nom,String prenom,String email,String motDePasse,String statut,String sexe,String dateDeNaissance,String adresse1,String codePostal,String ville,String telephone){
         try {
             ps = cnx.prepareStatement("UPDATE users SET Nom=?,Prenom=?,Email=?,MotdePasse=?,Sexe=?,DateDeNaissance=?,Adresse1=?,CodePostal=?,Ville=?,Telephone=? WHERE CodeUser=?");
             ps.setInt(11,codeUser);
             ps.setString(1,nom);
             ps.setString(2,prenom);
             ps.setString(3,email);
             ps.setString(4,motDePasse);
             ps.setString(5,sexe);
             ps.setString(6,dateDeNaissance);
             ps.setString(7,adresse1);
             ps.setString(8,codePostal);
             ps.setString(9,ville);
             ps.setString(10,telephone);
             ps.executeUpdate();
             ps.close();

         } catch (SQLException e) {
             Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
         }

     }
    public void AjoutLicence(int codeCategorie,String CodeUser,String dateObtention){
        try {
            ps = cnx.prepareStatement("INSERT INTO licence(CodeCategorie, CodeLicence, CodeUser, DateObtention)\n" +
                    "VALUES (?, ?, ?, ?)");

            ps.setInt(1,codeCategorie);
            ps.setString(2,null);
            ps.setString(3,CodeUser);
            ps.setString(4,dateObtention);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void AjoutLecon(String Immatriculation, String Date, String Heure){
        try {
            ps = cnx.prepareStatement("INSERT INTO lecon(CodeLecon,Date,Heure,Immatriculation,Reglee)\n" +
                    "VALUES (null,? ,?,?,0)");

            ps.setString(1,Date);
            ps.setString(2,Heure);
            ps.setString(3,Immatriculation);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void AjoutParticipe(String CodeUser, String Codelecon){
        try {
            ps = cnx.prepareStatement("INSERT INTO participe(CodeLecon,CodeUser)\n" +
                    "VALUES (?,?)");

            ps.setString(2,CodeUser);
            ps.setString(1,Codelecon);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void AjoutUser(String nom,String prenom,String email,String motDePasse,String sexe,String dateDeNaissance,String adresse1,String codePostal,String ville,String telephone){
        try {
            ps = cnx.prepareStatement("INSERT INTO users (Nom, Prenom, Email, MotDePasse, Sexe, DateDeNaissance, Adresse1, CodePostal, Ville, Telephone, Statut)\n" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'eleve')");
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setString(3,email);
            ps.setString(4,motDePasse);
            ps.setString(5,sexe);
            ps.setString(6,dateDeNaissance);
            ps.setString(7,adresse1);
            ps.setString(8,codePostal);
            ps.setString(9,ville);
            ps.setString(10,telephone);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    public void AjoutMoniteur(String nom,String prenom,String email,String motDePasse,String sexe,String dateDeNaissance,String adresse1,String codePostal,String ville,String telephone){
        try {
            ps = cnx.prepareStatement("INSERT INTO users (Nom, Prenom, Email, MotDePasse, Sexe, DateDeNaissance, Adresse1, CodePostal, Ville, Telephone, Statut)\n" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'moniteur')");
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setString(3,email);
            ps.setString(4,motDePasse);
            ps.setString(5,sexe);
            ps.setString(6,dateDeNaissance);
            ps.setString(7,adresse1);
            ps.setString(8,codePostal);
            ps.setString(9,ville);
            ps.setString(10,telephone);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public ArrayList<Users> GetAllUser(String statut){
        ArrayList<Users> lesUsers = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT CodeUser,Nom,Prenom,Email,MotdePasse,Statut,Sexe,DateDeNaissance,Adresse1,CodePostal,Ville,Telephone\n" +
                    "FROM users\n" +
                    "WHERE Statut=?");
            ps.setString(1,statut);
            rs = ps.executeQuery();
            while ((rs.next())){
                Users user = new Users(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
                lesUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lesUsers;
    }
    public HashMap<String,Double> GetDatasGraphique(String codeUser,String DateDebut,String dateFin)
    {
        HashMap<String, Double> datas = new HashMap();
        try {
            ps = cnx.prepareStatement("SELECT COUNT(lecon.CodeLecon)*categorie.Prix as Total,categorie.Libelle\n" +
                    "FROM lecon\n" +
                    "INNER JOIN participe on lecon.CodeLecon=participe.CodeLecon\n" +
                    "INNER JOIN vehicule on lecon.Immatriculation=vehicule.Immatriculation\n" +
                    "INNER JOIN categorie on vehicule.CodeCategorie=categorie.CodeCategorie\n" +
                    "WHERE participe.CodeUser=? and lecon.Date BETWEEN ? and ?\n" +
                    "GROUP BY Libelle");
            ps.setString(1,codeUser);
            ps.setString(2,DateDebut);
            ps.setString(3,dateFin);
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString("Libelle"), rs.getDouble("total"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return datas;
    }
    public ArrayList<String> GetDataGraphiqueMoniteur(){
        ArrayList<String> leMoniteur = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT Count(lecon.Immatriculation) as Stats,users.Nom,users.Prenom\n" +
                    "FROM lecon\n" +
                    "INNER JOIN participe on lecon.CodeLecon=participe.CodeLecon\n" +
                    "INNER JOIN users on participe.CodeUser=users.CodeUser\n" +
                    "GROUP BY participe.CodeUser\n" +
                    "ORDER BY COUNT(participe.CodeUser) DESC\n" +
                    "LIMIT 1;");
            rs = ps.executeQuery();
            while ((rs.next())){
                String Stats = rs.getString("Stats");
                leMoniteur.add(Stats);
                String Nom = rs.getString("Nom");
                leMoniteur.add(Nom);
                String Prenom = rs.getString("Prenom");
                leMoniteur.add(Prenom);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return leMoniteur;
    }




}
