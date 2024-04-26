package br.com.wefin.wefinapp.pessoa.validador;

import br.com.wefin.wefinapp.config.IdentificadorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AposentadoValidador implements IdentificadorValidador{

    @Override
    public void validar(String identificador) {
        try {
            String ultimoCaracter = String.valueOf(identificador.charAt(identificador.length()-1));
            String identificadorAux = StringUtils.chop(identificador);
            if(identificador.length()!=10 ||
               identificadorAux.contains(ultimoCaracter)){
                throw new IdentificadorException("");
            }
        }catch (Exception e){
            throw new IdentificadorException(String.format(IDENTIFICADOR_INVALIDO_MSG, identificador));
        }
    }
}
