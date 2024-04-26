package br.com.wefin.wefinapp.pagamento.api;


import br.com.wefin.wefinapp.pagamento.service.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/pagar/{id}")
    public ResponseEntity pagar(@PathVariable Long id) {
        log.info("Inicia pagamento emprestimo {}", id);
        this.pagamentoService.pagar(id);
        log.info("Finaliza pagamento emprestimo {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
