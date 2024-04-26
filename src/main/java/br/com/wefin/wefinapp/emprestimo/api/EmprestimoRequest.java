package br.com.wefin.wefinapp.emprestimo.api;

import br.com.wefin.wefinapp.emprestimo.entity.Emprestimo;
import br.com.wefin.wefinapp.emprestimo.entity.StatusPagamento;
import br.com.wefin.wefinapp.pessoa.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmprestimoRequest {

    @NotNull(message = "O campo identificador é obrigatório")
    @JsonProperty("identificador")
    private String identificador;

    @NotNull(message = "O campo valor do emprestimo é obrigatório")
    @JsonProperty("valor")
    private BigDecimal valorEmprestimo;

    @NotNull(message = "O campo numero de parcelas é obrigatório")
    @JsonProperty("numero_parcelas")
    private Integer numeroParcelas;

    public Emprestimo converter(Pessoa pessoa) {
        return Emprestimo.builder()
            .valorEmprestimo(valorEmprestimo)
            .numeroParcelas(numeroParcelas)
            .pessoa(pessoa)
            .statusPagamento(StatusPagamento.EM_ABERTO)
            .dataCriacao(LocalDate.now())
            .build();
    }

}
