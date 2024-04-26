package br.com.wefin.wefinapp.pessoa.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String identificador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificador")
    private TipoIdentificador tipoIdentificador;

    @Column(name = "valor_min_mensal")
    private BigDecimal valorMinimoParcela;

    @Column(name = "valor_max_emprestimo")
    private BigDecimal valorMaximoEmprestimo;
}
