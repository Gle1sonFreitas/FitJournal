package com.fitjournal.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastroDTO {

    @NotBlank(message = "Por favor, preencha o campo userName .")
    private String userName;

    @NotBlank(message = "Por favor, preencha o campo e-mail.")
    @Email(message = "Endereço de e-mail inválido. Verifique e tente novamente.")
    private String email;

    @NotNull
    private String password;

    public CadastroDTO(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
