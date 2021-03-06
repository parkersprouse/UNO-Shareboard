package com.bayou.views;

/**
 * Created by joshuaeaton on 2/17/17.
 */
public class LoginView extends BaseEntityView {
    private String email;
    private String accountName;
    private String passwordSalt;
    private String errorMessage;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String account_name) {
        this.accountName = account_name;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LoginView loginView = (LoginView) o;

        if (email != null ? !email.equals(loginView.email) : loginView.email != null) return false;
        if (accountName != null ? !accountName.equals(loginView.accountName) : loginView.accountName != null)
            return false;
        if (passwordSalt != null ? !passwordSalt.equals(loginView.passwordSalt) : loginView.passwordSalt != null)
            return false;
        return errorMessage != null ? errorMessage.equals(loginView.errorMessage) : loginView.errorMessage == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (accountName != null ? accountName.hashCode() : 0);
        result = 31 * result + (passwordSalt != null ? passwordSalt.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }
}
