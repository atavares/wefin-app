package br.com.wefin.wefinapp.emprestimo.api;

import br.com.wefin.wefinapp.emprestimo.entity.Emprestimo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmprestimoResponse {

    @JsonProperty("pessoa")
    private String identificador;

    @JsonProperty("valor")
    private BigDecimal valorEmprestimo;

    @JsonProperty("numero_parcelas")
    private Integer numeroParcelas;
    @JsonProperty("status_pagamento")
    private String statusPagamento;

    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonProperty("data")
    private LocalDate dataCriacao;

    public void converter(Emprestimo emprestimo) {
        identificador = emprestimo.getPessoa().getIdentificador();
        valorEmprestimo = emprestimo.getValorEmprestimo();
        numeroParcelas = emprestimo.getNumeroParcelas();
        statusPagamento = emprestimo.getStatusPagamento().name();
        dataCriacao = emprestimo.getDataCriacao();
    }
}
