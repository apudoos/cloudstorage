package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialsForm {

    Integer credentialId;
    String url;
    String userName;
    String password;


    public CredentialsForm(Integer credentialId, String url, String userName, String password) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CredentialsForm{" +
                "credentialId=" + credentialId +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
