package com.francisco.backend.mobiauto.api.enums;

public enum UsuarioPerfil {

    ADMIN("admin"),
    PROPRIETARIO("proprietario"),
    GERENTE("gerente"),
    ASSISTENTE("assistente");

    private String perfil;

    UsuarioPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

}
