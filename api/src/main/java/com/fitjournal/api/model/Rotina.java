package com.fitjournal.api.model;

public class Rotina {

    private Integer id;
    private Integer id_usuario;
    private String nome;

    public Rotina() {

    }

    public Rotina(Integer id, Integer id_usuario, String nome) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
