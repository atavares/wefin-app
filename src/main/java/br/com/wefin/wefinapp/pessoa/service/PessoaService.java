package br.com.wefin.wefinapp.pessoa.service;

import br.com.wefin.wefinapp.config.ResourceNotFoundException;
import br.com.wefin.wefinapp.pessoa.api.PessoaRequest;
import br.com.wefin.wefinapp.pessoa.api.PessoaResponse;
import br.com.wefin.wefinapp.pessoa.entity.Pessoa;
import br.com.wefin.wefinapp.pessoa.entity.TipoIdentificador;
import br.com.wefin.wefinapp.pessoa.repository.PessoaRepository;
import br.com.wefin.wefinapp.pessoa.validador.IdentificadorValidador;
import java.math.BigDecimal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PessoaService {

    private static final String PESSOA_ID_NAO_ENCONTRADA_MSG = "Pessoa com id %s não encontrado";
    private static final String PESSOA_IDENTIFICADOR_NAO_ENCONTRADA_MSG = "Pessoa com identificador %s não encontrado";
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private Map<TipoIdentificador,IdentificadorValidador> mapaIdentificadorValidador;

    @Transactional
    public PessoaResponse adicionar(PessoaRequest pessoaRequest) {
        log.info("Inicia adição de uma nova pessoa {}", pessoaRequest);
        Pessoa novaPessoa = defineNovaPessoa(pessoaRequest);
        novaPessoa = pessoaRepository.save(novaPessoa);
        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.converter(novaPessoa);
        log.info("Finaliza adição de uma nova pessoa {}", pessoaResponse);
        return pessoaResponse;
    }

    @Transactional
    public PessoaResponse alterar(Long id, PessoaRequest pessoaRequest) {
        log.info("Inicia alteração da pessoa {}", id);
        PessoaResponse pessoaResponse = new PessoaResponse();
        Pessoa pessoaExistente = validaPessoaExistentePorId(id);
        definePessoaExistente(pessoaExistente, pessoaRequest);
        pessoaRepository.save(pessoaExistente);
        pessoaResponse.converter(pessoaExistente);
        log.info("Finaliza alteração de uma nova pessoa {}", pessoaResponse);
        return pessoaResponse;
    }

    @Transactional
    public void remover(Long id) {
        log.info("Inicia remoção da pessoa {}", id);
        Pessoa pessoaExistente = validaPessoaExistentePorId(id);
        this.pessoaRepository.delete(pessoaExistente);
        log.info("Finaliza remoção da pessoa {}", id);
    }

    public PessoaResponse buscar(Long id) {
        PessoaResponse pessoaResponse = new PessoaResponse();
        log.info("Inicia busca da pessoa {}", id);
        Pessoa pessoaExistente = validaPessoaExistentePorId(id);
        pessoaResponse.converter(pessoaExistente);
        log.info("Finaliza busca da pessoa {}", pessoaResponse);
        return pessoaResponse;
    }

    public Pessoa defineNovaPessoa(PessoaRequest pessoaRequest) {
        Pessoa novaPessoa =  pessoaRequest.converter();
        defineTipoIdentificador(pessoaRequest.getIdentificador(), novaPessoa);
        defineFaixaValores(novaPessoa);
        return novaPessoa;
    }

    public void definePessoaExistente(Pessoa pessoaExistente, PessoaRequest pessoaRequest) {
        pessoaRequest.converter(pessoaExistente);
        defineTipoIdentificador(pessoaRequest.getIdentificador(), pessoaExistente);
        defineFaixaValores(pessoaExistente);
    }

    public void defineTipoIdentificador(String identificador, Pessoa pessoa) {
        if(identificador.length()==11) {
            pessoa.setTipoIdentificador(TipoIdentificador.PESSOA_FISICA);
            return;
        }

        if(identificador.length()==14) {
            pessoa.setTipoIdentificador(TipoIdentificador.PESSOA_JURIDICA);
            return;
        }

        if(identificador.length()==8) {
            pessoa.setTipoIdentificador(TipoIdentificador.ESTUDANTE_UNIVERSITARIO);
            return;
        }

        if(identificador.length()==10) {
            pessoa.setTipoIdentificador(TipoIdentificador.APOSENTADO);
        }
    }

    public void defineFaixaValores(Pessoa pessoa) {
        TipoIdentificador tipoIdentificador = pessoa.getTipoIdentificador();
        if(tipoIdentificador.equals(TipoIdentificador.PESSOA_FISICA)) {
            pessoa.setValorMinimoParcela(new BigDecimal("300"));
            pessoa.setValorMaximoEmprestimo(new BigDecimal("10000"));
            return;
        }

        if(tipoIdentificador.equals(TipoIdentificador.PESSOA_JURIDICA)) {
            pessoa.setValorMinimoParcela(new BigDecimal("1000"));
            pessoa.setValorMaximoEmprestimo(new BigDecimal("100000"));
            return;
        }

        if(tipoIdentificador.equals(TipoIdentificador.ESTUDANTE_UNIVERSITARIO)) {
            pessoa.setValorMinimoParcela(new BigDecimal("100"));
            pessoa.setValorMaximoEmprestimo(new BigDecimal("10000"));
            return;
        }

        if(tipoIdentificador.equals(TipoIdentificador.APOSENTADO)) {
            pessoa.setValorMinimoParcela(new BigDecimal("400"));
            pessoa.setValorMaximoEmprestimo(new BigDecimal("25000"));
        }
    }
    public Pessoa validaPessoaExistentePorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(PESSOA_ID_NAO_ENCONTRADA_MSG, id)));
    }

    public Pessoa validaPessoaExistentePorIdentificador(String identificador) {
        return pessoaRepository.findByIdentificador(identificador)
            .orElseThrow(()-> new ResourceNotFoundException(String.format(PESSOA_IDENTIFICADOR_NAO_ENCONTRADA_MSG, identificador)));
    }

    public void validaIdentificador(Pessoa pessoa) {
        mapaIdentificadorValidador.get(pessoa.getTipoIdentificador()).validar(pessoa.getIdentificador());
    }
}
