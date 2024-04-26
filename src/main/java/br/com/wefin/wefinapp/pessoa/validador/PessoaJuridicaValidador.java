package br.com.wefin.wefinapp.pessoa.validador;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.wefin.wefinapp.config.IdentificadorException;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaValidador implements IdentificadorValidador {

    @Override
    public void validar(String identificador) {
        try {
            CNPJValidator cnpjValidator = new CNPJValidator();
            cnpjValidator.assertValid(identificador);
        }catch (Exception e){
            throw new IdentificadorException(String.format(IDENTIFICADOR_INVALIDO_MSG, identificador));
        }
    }
}
