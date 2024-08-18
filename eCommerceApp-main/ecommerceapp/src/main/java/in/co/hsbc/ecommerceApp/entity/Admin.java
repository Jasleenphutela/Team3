package in.co.hsbc.ecommerceApp.entity;

public class Admin {
    private static Admin single_instance = null;
    private int id;

    private Admin(int id){
        this.id=id;
    }

    public static synchronized Admin getInstance(int id) {
        if (single_instance == null)
            single_instance = new Admin(id);
        return single_instance;
    }
}
