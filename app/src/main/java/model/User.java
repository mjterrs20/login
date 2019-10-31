package model;

public class User {
    public String id;
    public String userName;
    public String noHp;
    public String noPorsi;
    public String password;

    public User(String id, String userName, String noHp,String noPorsi, String password) {
        this.id = id;
        this.userName = userName;
        this.noHp = noHp;
        this.noPorsi = noPorsi;
        this.password = password;
    }

}
