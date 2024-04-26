package br.com.wefin.wefinapp.pessoa.validador;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.wefin.wefinapp.config.IdentificadorException;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaValidador implements IdentificadorValidador {

    @Override
    public void validar(String identificador) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(identificador);
        }catch (Exception e){
            throw new IdentificadorException(String.format(IDENTIFICADOR_INVALIDO_MSG, identificador));
        }

    }
}
