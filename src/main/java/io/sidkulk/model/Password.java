package io.sidkulk.model;

public class Password {
    private String passwordTitle;
    private String passwordValue;
    private int p_id;

    public Password(String passwordTitle, String passwordValue) {
        super();
        this.passwordTitle = passwordTitle;
        this.passwordValue = passwordValue;
    }

    public Password(int p_id, String passwordTitle, String passwordValue) {
        this.p_id = p_id;
        this.passwordTitle = passwordTitle;
        this.passwordValue = passwordValue;
    }

    public int getP_id() {
        return p_id;
    }

    public String getPasswordTitle() {
        return passwordTitle;
    }

    public void setPasswordTitle(String passwordTitle) {
        this.passwordTitle = passwordTitle;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    @Override
    public String toString() {
        return this.p_id + " \t " + this.passwordTitle + " \t " + this.passwordValue + " \t ";
    }
}
