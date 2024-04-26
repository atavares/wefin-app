package br.com.wefin.wefinapp.emprestimo.api;


import br.com.wefin.wefinapp.emprestimo.service.EmprestimoService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/realizar")
    public ResponseEntity<EmprestimoResponse> realizar(@Valid @RequestBody EmprestimoRequest emprestimoRequest) {
        log.info("Inicia realizar emprestimo {}", emprestimoRequest);
        EmprestimoResponse response = this.emprestimoService.efetuarEmprestimo(emprestimoRequest);
        log.info("Finaliza realizar emprestimo {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
