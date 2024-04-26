package br.com.wefin.wefinapp.pessoa.api;

import br.com.wefin.wefinapp.pessoa.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PessoaRequest {
    @NotBlank(message = "O campo nome é obrigatório")
    @JsonProperty("nome")
    private String nome;

    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "O data de nascimento é obrigatório")
    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "O identificador é obrigatório")
    @JsonProperty("identificador")
    private String identificador;

    public Pessoa converter() {
        return Pessoa.builder()
            .nome(nome)
            .dataNascimento(dataNascimento)
            .identificador(identificador)
            .build();
    }

    public void converter(Pessoa pessoa) {
        pessoa.setNome(nome);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setIdentificador(identificador);
    }

}
