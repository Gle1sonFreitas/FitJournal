package com.fitjournal.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class GetRotinaDTO {

    @NotBlank
    private String nomeRotina;
    @NotNull
    private Integer qtdExercicios;
    @JsonFormat(pattern = "dd/MM/yy", timezone = "America/Sao_Paulo")
    private LocalDateTime ultimoTreino;

    public GetRotinaDTO() {
    }

    public GetRotinaDTO(String nomeRotina, Integer qtdExercicios, LocalDateTime ultimoTreino) {
        this.nomeRotina = nomeRotina;
        this.qtdExercicios = qtdExercicios;
        this.ultimoTreino = ultimoTreino;
    }

    public String getNomeRotina() {
        return nomeRotina;
    }

    public void setNomeRotina(String nomeRotina) {
        this.nomeRotina = nomeRotina;
    }

    public Integer getQtdExercicios() {
        return qtdExercicios;
    }

    public void setQtdExercicios(Integer qtdExercicios) {
        this.qtdExercicios = qtdExercicios;
    }

    public LocalDateTime getUltimoTreino() {
        return ultimoTreino;
    }

    public void setUltimoTreino(LocalDateTime ultimoTreino) {
        this.ultimoTreino = ultimoTreino;
    }
}
