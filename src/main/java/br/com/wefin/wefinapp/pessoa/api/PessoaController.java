package br.com.wefin.wefinapp.pessoa.api;


import br.com.wefin.wefinapp.pessoa.service.PessoaService;
import java.lang.reflect.InvocationTargetException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> inserir(@Valid @RequestBody PessoaRequest pessoaRequest) {
        log.info("Inicia inserir pessoa {}", pessoaRequest);
        PessoaResponse response = this.pessoaService.adicionar(pessoaRequest);
        log.info("Finaliza inserir pessoa {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaRequest pessoaRequest) {
        log.info("Inicia atualizar pessoa {}", pessoaRequest);
        PessoaResponse response = this.pessoaService.alterar(id, pessoaRequest);
        log.info("Finaliza atualizar pessoa {}", response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity remover(@PathVariable Long id)  {
        log.info("Inicia remover pessoa {}", id);
        this.pessoaService.remover(id);
        log.info("Finaliza remover pessoa {}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPessoa(@PathVariable Long id) {
        log.info("Inicia busca pessoa {}", id);
        PessoaResponse response = this.pessoaService.buscar(id);
        log.info("Finaliza busca pessoa {}", response);
        return ResponseEntity.ok(response);
    }
}
