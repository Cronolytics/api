package com.cronolytics.api.dto.req;

public class AtualizarCadastroMobileDTO {
    private String email;
    private String senha;
    private String tel;

    public AtualizarCadastroMobileDTO(String email, String senha, String tel) {
        this.email = email;
        this.senha = senha;
        this.tel = tel;
    }

    public AtualizarCadastroMobileDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
