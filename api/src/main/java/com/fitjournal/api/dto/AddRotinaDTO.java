package com.fitjournal.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AddRotinaDTO {

    private Integer id;
    @NotNull
    private Integer idUsuario;
    @NotBlank
    private String nome;
    @NotEmpty(message = "Você deve cadastrar exercicios a sua rotina")
    private List<ExercicioDTO> exercicios;

    public AddRotinaDTO() {
    }

    public AddRotinaDTO(Integer id, Integer idUsuario, String nome) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ExercicioDTO> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<ExercicioDTO> exercicios) {
        this.exercicios = exercicios;
    }

    @Override
    public String toString() {
        return "AddRotinaDTO{" +
                "exercicios=" + exercicios.toString() +
                '}';
    }
}
