package br.com.wefin.wefinapp.pessoa.validador;

import br.com.wefin.wefinapp.config.IdentificadorException;
import org.springframework.stereotype.Service;

@Service
public class EstudanteUniversitarioValidador implements IdentificadorValidador {

    @Override
    public void validar(String identificador) {
        try {
            if(identificador.length()!=8 ||
                identificador.charAt(0)!=identificador.charAt(identificador.length()-1)){
                throw new IdentificadorException("");
            }
        }catch (Exception e){
            throw new IdentificadorException(String.format(IDENTIFICADOR_INVALIDO_MSG, identificador));
        }
    }
}
