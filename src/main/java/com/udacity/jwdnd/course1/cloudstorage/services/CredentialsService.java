package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;

import java.util.List;

public interface CredentialsService {

    void storeCredentials(CredentialsForm credentialsForm, String userName);
    void deleteCredentials(Long credentialId);
    //Notes editCredentials(Long credentialId);
    List<CredentialsForm> listAllCredentials(String userName);
    Credentials getCredential(Integer id);
    String getDecryptedPassword(Integer id);

}
