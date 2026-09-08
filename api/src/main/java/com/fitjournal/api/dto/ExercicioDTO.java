package com.fitjournal.api.dto;

public class ExercicioDTO {

    private Integer id;
    private String nome;
    private String grupo;
    private String equipamento;

    public ExercicioDTO() {
    }

    public ExercicioDTO(Integer id, String nome, String grupo, String equipamento) {
        this.id = id;
        this.nome = nome;
        this.grupo = grupo;
        this.equipamento = equipamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public String toString() {
        return "ExercicioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", grupo='" + grupo + '\'' +
                ", equipamento='" + equipamento + '\'' +
                '}';
    }
}
