package model;

import java.util.Objects;

public class Password {

    private User user;
    private int pw;

    public Password(int password){
        this.pw = password;
    }

    public void setUser(User u){
        if(!(this.user == u)){
            this.user = u;
            u.setPasswords(this);
        }
    }

    public int getPw() {
        return pw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return pw == password.pw &&
                Objects.equals(user, password.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, pw);
    }
}
