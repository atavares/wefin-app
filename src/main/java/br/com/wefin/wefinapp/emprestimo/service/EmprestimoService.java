package br.com.wefin.wefinapp.emprestimo.service;

import br.com.wefin.wefinapp.config.IdentificadorException;
import br.com.wefin.wefinapp.emprestimo.api.EmprestimoRequest;
import br.com.wefin.wefinapp.emprestimo.api.EmprestimoResponse;
import br.com.wefin.wefinapp.emprestimo.entity.Emprestimo;
import br.com.wefin.wefinapp.emprestimo.repository.EmprestimoRepository;
import br.com.wefin.wefinapp.pagamento.service.PagamentoService;
import br.com.wefin.wefinapp.pessoa.entity.Pessoa;
import br.com.wefin.wefinapp.pessoa.service.PessoaService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmprestimoService {

    private static final String VALOR_LIMITIE_EXCEDIDO_MSG = "O Valor ultrapassa o limite para empréstimo";
    private static final String VALOR_MINIMO_EXCEDIDO_MSG = "O Valor das parcelas não pode ser inferior ao valor minimo permitido";
    private static final String NUMERO_PARCELAS_EXCEDIDO_MSG = "A quantidade de parcelas nao pode ser superior a 24";

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PagamentoService pagamentoService;

    @Transactional
    public EmprestimoResponse efetuarEmprestimo(EmprestimoRequest emprestimoRequest){
        log.info("Inicio efetua emprestimo {}", emprestimoRequest);
        Emprestimo emprestimo = retornaDadosParaEmprestimo(emprestimoRequest);

        emprestimo = this.emprestimoRepository.save(emprestimo);

        EmprestimoResponse emprestimoResponse = new EmprestimoResponse();
        emprestimoResponse.converter(emprestimo);
        log.info("Finaliza efetua emprestimo {}", emprestimoResponse);
        return emprestimoResponse;
    }

    private Emprestimo retornaDadosParaEmprestimo(EmprestimoRequest emprestimoRequest) {
        Pessoa pessoa = this.pessoaService.validaPessoaExistentePorIdentificador(emprestimoRequest.getIdentificador());
        Emprestimo emprestimo = emprestimoRequest.converter(pessoa);
        this.pessoaService.validaIdentificador(pessoa);
        this.validaLimiteMaximo(emprestimo);
        this.validaLimiteMinimo(emprestimo);
        this.validaQuantidadeParcela(emprestimo);

        return emprestimo;
    }

    private void validaLimiteMaximo(Emprestimo emprestimo) {
        if(emprestimo.getValorEmprestimo().compareTo(emprestimo.getPessoa().getValorMaximoEmprestimo())==1) {
            throw new IdentificadorException(VALOR_LIMITIE_EXCEDIDO_MSG);
        }
    }

    private void validaLimiteMinimo(Emprestimo emprestimo) {
        if(emprestimo.getValorEmprestimo()
            .divide(BigDecimal.valueOf(emprestimo.getNumeroParcelas()),2, RoundingMode.HALF_UP)
            .compareTo(emprestimo.getPessoa().getValorMinimoParcela())==-1) {
            throw new IdentificadorException(VALOR_MINIMO_EXCEDIDO_MSG);
        }
    }

    private void validaQuantidadeParcela(Emprestimo emprestimo) {
        if(emprestimo.getNumeroParcelas()>24) {
            throw new IdentificadorException(NUMERO_PARCELAS_EXCEDIDO_MSG);
        }
    }
}
