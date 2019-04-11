package share.jsonLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import share.dataObject.*;
import share.enumUtils.EnumEnseigne;
import share.enumUtils.EnumUser;
import share.manager.DataManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonLoaderImpl implements JsonLoader {

    /// -- Définition des chemins --- ///

    private final static String ROOT_FOLDER = "C:\\Users\\Neveux_du_Geniebre\\IdeaProjects\\PersistanceServer\\src\\datas";
    private final static String MAGASIN_FILE = ROOT_FOLDER + "\\magasin.json";
    private final static String USER_FILE = ROOT_FOLDER + "\\user.json";
    private final static String VISITE_FILE = ROOT_FOLDER + "\\visite.json";

    JsonLoaderImpl() {
    }

    @Override
    public void loadAllDatas() {
        loadMagasinInfos(loadFileFromPath(MAGASIN_FILE));
        loadUserInfos(loadFileFromPath(USER_FILE));
        loadVisitesInfos(loadFileFromPath(VISITE_FILE));
    }


    @Override
    public void saveAllDatas() {
    }

    @Override
    public void saveAllVisites() {

    }

    @Override
    public void updateVisite(int idVisite) {

    }

    private void loadMagasinInfos(String file) {
        JSONObject obj = new JSONObject(file);
        JSONArray arr = obj.getJSONArray("magasins");
        JSONObject o;
        ArrayList<Magasin> magasins = new ArrayList<>();
        Magasin m;
        for (int i = 0; i < arr.length(); i++) {
            o = arr.getJSONObject(i);
            m = new Magasin();
            m.setId(o.getInt("id"));
            m.setVille(o.getInt("villeId"));
            m.setEnseigne(EnumEnseigne.getEnumFromString(o.getString("enseigne")));
            magasins.add(m);
        }
        DataManager.INSTANCE.setMagasins(magasins);
    }

    private void loadUserInfos(String file) {
        JSONObject obj = new JSONObject(file);
        JSONArray arr = obj.getJSONArray("users");
        JSONObject o;
        ArrayList<User> users = new ArrayList<>();
        User u;
        for (int i = 0; i < arr.length(); i++) {
            o = arr.getJSONObject(i);
            EnumUser enumUser = EnumUser.getEnumFromString(o.getString("function"));
            if (enumUser != null)
                switch (enumUser) {
                    case MANAGER:
                        u = new Manager();
                        u.setId(o.getInt("id"));
                        u.setFirstName(o.getString("firstname"));
                        u.setName(o.getString("name"));
                        users.add(u);
                        break;
                    case SELLER:
                        u = new Seller();
                        u.setId(o.getInt("id"));
                        u.setFirstName(o.getString("firstname"));
                        u.setName(o.getString("name"));
                        ((Seller) u).setIdManager(o.getInt("managerid"));
                        users.add(u);
                        break;
                }
        }
        DataManager.INSTANCE.setUsers(users);
    }

    private void loadVisitesInfos(String file) {
        JSONObject obj = new JSONObject(file);
        JSONArray arr = obj.getJSONArray("visites");
        JSONObject o;
        ArrayList<Visite> visites = new ArrayList<>();
        Visite v;

        for (int i = 0; i < arr.length(); i++) {
            o = arr.getJSONObject(i);
            v = new Visite();
            v.setId(o.getInt("id"));
            v.setIdMagasin(o.getInt("idMagasin"));
            v.setIdVisitor(o.getInt("idVisitor"));
            v.setVisiteDone(o.getBoolean("isVisiteDone"));
            v.setDateVisite(o.getString("dateVisite"));
            v.setComment(o.getString("comment"));
            visites.add(v);
        }
        DataManager.INSTANCE.setVisites(visites);
    }

    private String loadFileFromPath(String filePath) {
        File file = new File(filePath);
        String output = "";
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc != null && sc.hasNextLine())
            output = output.concat(sc.nextLine());
        return output;
    }

}