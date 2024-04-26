package br.com.wefin.wefinapp.pessoa.entity;

public enum TipoIdentificador {

    PESSOA_FISICA ("PF"),
    PESSOA_JURIDICA ("PJ"),
    ESTUDANTE_UNIVERSITARIO("EU"),
    APOSENTADO("AP");
    private String codigo;

    TipoIdentificador(String codigo) {
        this.codigo=codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
