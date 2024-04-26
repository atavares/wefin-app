package br.com.wefin.wefinapp.pagamento.service;

import br.com.wefin.wefinapp.config.ResourceNotFoundException;
import br.com.wefin.wefinapp.emprestimo.entity.Emprestimo;
import br.com.wefin.wefinapp.emprestimo.entity.StatusPagamento;
import br.com.wefin.wefinapp.emprestimo.repository.EmprestimoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PagamentoService {

    private static final String EMPRESTIMO_IDENTIFICADOR_NAO_ENCONTRADA_MSG = "Emprestimo com id %s não encontrado";

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Transactional
    public void pagar(Long idEmprestimo){
        log.info("Realiza pagamento do emprestimo {}", idEmprestimo);
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
            .orElseThrow(()-> new ResourceNotFoundException(String.format(EMPRESTIMO_IDENTIFICADOR_NAO_ENCONTRADA_MSG, idEmprestimo)));
        emprestimo.setStatusPagamento(StatusPagamento.PAGO);
        this.emprestimoRepository.save(emprestimo);
        log.info("Finaliza pagamento do emprestimo {}", idEmprestimo);
    }
}
