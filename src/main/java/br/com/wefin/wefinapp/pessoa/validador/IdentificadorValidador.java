package br.com.wefin.wefinapp.pessoa.validador;
public interface IdentificadorValidador {
    String IDENTIFICADOR_INVALIDO_MSG = "Identificador %s inválido";

    void validar(String identificador);
}
