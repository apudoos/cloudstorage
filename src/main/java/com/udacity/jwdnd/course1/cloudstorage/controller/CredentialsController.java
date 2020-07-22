package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialsController {

    @Autowired
    private CredentialsUploadService credentialsUploadService;

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/uploadCredentials")
    public String uploadNotes(@ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                              Authentication auth, Model model) {

        try {
            credentialsUploadService.storeCredentials(credentialsForm, auth.getName());
        } catch (Exception ex) {
            return "redirect:/result?failure=" + ex.getMessage();
        }

        return "redirect:/result?success";
    }

    @GetMapping("/deleteCredentials/{credentialsId}")
    public String deleteNote(@PathVariable(name="credentialsId") String credentialsId) {

        Long credentialsID = Long.parseLong(credentialsId);
        credentialsUploadService.deleteCredentials(credentialsID);
        return "redirect:/result?deleteSuccess";
    }

    @GetMapping("/getDecryptedCredential")
    @ResponseBody
    public String getDecryptedCredential(@RequestParam(value="credentialId") Integer credentialId) {

        Credentials cred = this.credentialsUploadService.getCredential(credentialId);
        String decryptedPassword = this.encryptionService.decryptValue(cred.getPassword(), cred.getKey());
        return decryptedPassword;
    }

}
