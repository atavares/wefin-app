package br.com.wefin.wefinapp.pessoa.api;

import br.com.wefin.wefinapp.pessoa.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PessoaResponse {

    @JsonProperty("nome")
    private String nome;
    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @JsonProperty("identificador")
    private String identificador;

    @JsonProperty("tipo_identificador")
    private String tipoIdentificador;

    @JsonProperty("valor_minimo_parcela")
    private BigDecimal valorMinimoParcela;
    @JsonProperty("valor_maximo_emprestimo")
    private BigDecimal valorMaximoEmprestimo;

    public void converter(Pessoa pessoa) {
        nome = pessoa.getNome();
        dataNascimento = pessoa.getDataNascimento();
        identificador = pessoa.getIdentificador();
        tipoIdentificador = pessoa.getTipoIdentificador().getCodigo();
        valorMinimoParcela = pessoa.getValorMinimoParcela();
        valorMaximoEmprestimo = pessoa.getValorMaximoEmprestimo();
    }
}
