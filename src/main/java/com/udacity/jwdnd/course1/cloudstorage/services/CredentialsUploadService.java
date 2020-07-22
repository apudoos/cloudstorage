package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialsUploadService implements CredentialsService {

    private EncryptionService encryptionService;
    private final CredentialMapper credentialMapper;
    private final UserMapper userMapper;

    @Autowired
    public CredentialsUploadService(EncryptionService encryptionService,
                                    CredentialMapper credentialMapper,
                                    UserMapper userMapper) {
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void storeCredentials(CredentialsForm credentialsForm, String userName) {

        Credentials credentials = new Credentials();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = encryptionService.encryptValue(credentialsForm.getPassword(), encodedSalt);

        credentials.setUrl(credentialsForm.getUrl());
        credentials.setUserName(credentialsForm.getUserName());
        credentials.setPassword(hashedPassword);
        credentials.setKey(encodedSalt);
        User user = this.userMapper.getUser(userName);
        credentials.setUserId(user.getUserid());

        if(credentialsForm.getCredentialId() != null) {
            credentials.setCredentialId(credentialsForm.getCredentialId());
            int rows = credentialMapper.updateCredentials(credentials);
        } else {
            int rows = credentialMapper.insertNewCredentials(credentials);
        }
    }

    @Override
    public void deleteCredentials(Long credentialId) {
        this.credentialMapper.deleteSpecificCredential(credentialId);
    }


    @Override
    public List<CredentialsForm> listAllCredentials(String userName) {
        //get UserId from username
        User user = this.userMapper.getUser(userName);
        return this.credentialMapper.selectAllCredentials(user.getUserid())
                .stream().map(p -> new CredentialsForm(p.getCredentialId(),
                        p.getUrl(), p.getUserName(), p.getPassword()))
                .collect(Collectors.toList());

    }

    @Override
    public Credentials getCredential(Integer id) {
        return this.credentialMapper.selectCredentialsById(id);
    }

    @Override
    public String getDecryptedPassword(Integer id) {
        System.out.println(id);
        Credentials cred =  this.credentialMapper.selectCredentialsById(id);
        System.out.println("Cred details: " + cred.toString());
        String decryptedPassword = this.encryptionService.decryptValue(cred.getPassword(), cred.getKey());
        return decryptedPassword;
    }
}
