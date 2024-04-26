package br.com.wefin.wefinapp.config;

import br.com.wefin.wefinapp.pessoa.entity.TipoIdentificador;
import br.com.wefin.wefinapp.pessoa.validador.AposentadoValidador;
import br.com.wefin.wefinapp.pessoa.validador.EstudanteUniversitarioValidador;
import br.com.wefin.wefinapp.pessoa.validador.IdentificadorValidador;
import br.com.wefin.wefinapp.pessoa.validador.PessoaFisicaValidador;
import br.com.wefin.wefinapp.pessoa.validador.PessoaJuridicaValidador;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private PessoaFisicaValidador pessoaFisicaValidador;

    @Autowired
    private PessoaJuridicaValidador pessoaJuridicaValidador;

    @Autowired
    private EstudanteUniversitarioValidador estudanteUniversitarioValidador;

    @Autowired
    private AposentadoValidador aposentadoValidador;

    @Bean
    public Map<TipoIdentificador, IdentificadorValidador> mapaIdentificadorValidador(){
        Map<TipoIdentificador, IdentificadorValidador> mapa = new HashMap<>();
        mapa.put(TipoIdentificador.PESSOA_FISICA, pessoaFisicaValidador);
        mapa.put(TipoIdentificador.PESSOA_JURIDICA, pessoaJuridicaValidador);
        mapa.put(TipoIdentificador.ESTUDANTE_UNIVERSITARIO, estudanteUniversitarioValidador);
        mapa.put(TipoIdentificador.APOSENTADO, aposentadoValidador);

        return mapa;
    }
}
