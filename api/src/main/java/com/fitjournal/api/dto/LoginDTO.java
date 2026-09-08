package com.fitjournal.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank(message = "Por favor, preencha o campo de e-mail.")
    @Email(message = "Endereço de e-mail inválido. Verifique e tente novamente.")
    private String email;

    @NotBlank
    private String senha;

    public LoginDTO() {
    }

    public LoginDTO(String email, String senha) {

        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
