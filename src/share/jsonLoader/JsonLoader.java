package share.jsonLoader;

public interface JsonLoader {

    JsonLoaderImpl INSTANCE = new JsonLoaderImpl();

    void loadAllDatas();

    void saveAllDatas();

    void saveAllVisites();

    void updateVisite(int idVisite);

}
