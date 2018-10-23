package model;

public class User {
    private int passwords;


    public User(){
        passwords = 0000;
    }

    // EFFECTS: return passwords
    public int getPasswords(){
        return passwords;
    }

    // EFFECTS: set this.passwords to passwords
    public void setPasswords(int passwords) {
        this.passwords = passwords;
    }

}
