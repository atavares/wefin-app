package br.com.wefin.wefinapp.pessoa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import br.com.wefin.wefinapp.pessoa.api.PessoaRequest;
import br.com.wefin.wefinapp.pessoa.api.PessoaResponse;
import br.com.wefin.wefinapp.pessoa.entity.Pessoa;
import br.com.wefin.wefinapp.pessoa.entity.TipoIdentificador;
import br.com.wefin.wefinapp.pessoa.repository.PessoaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private PessoaRequest pessoaRequest;

    @BeforeEach
    void setup(){
        this.pessoaRequest = new PessoaRequest();
    }

    @Test
    void deveAdicionarNovaPessoa() {
        Pessoa novaPessoa = getPessoa();
        this.pessoaRequest.setIdentificador("33333333333");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(novaPessoa);

        PessoaResponse response = pessoaService.adicionar(this.pessoaRequest);

        assertEquals("João da Silva", response.getNome());
        assertEquals(LocalDate.of(2000, 2, 1), response.getDataNascimento());
        assertEquals(TipoIdentificador.PESSOA_FISICA.getCodigo(), response.getTipoIdentificador());
        assertEquals(new BigDecimal("300"), response.getValorMinimoParcela());
        assertEquals(new BigDecimal("10000"), response.getValorMaximoEmprestimo());
    }

    @Test
    void deveAlterarPessoa() {
        Long id = 1L;
        Pessoa pessoa = getPessoa();
        pessoa.setTipoIdentificador(TipoIdentificador.PESSOA_JURIDICA);
        this.pessoaRequest.setIdentificador("11222333000155");
        this.pessoaRequest.setNome("André Silva");
        this.pessoaRequest.setDataNascimento(LocalDate.of(2000, 2, 1));

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaResponse response = pessoaService.alterar(1L, this.pessoaRequest);

        assertEquals("André Silva", response.getNome());
        assertEquals(LocalDate.of(2000, 2, 1), response.getDataNascimento());
        assertEquals(TipoIdentificador.PESSOA_JURIDICA.getCodigo(), response.getTipoIdentificador());
        assertEquals(new BigDecimal("1000"), response.getValorMinimoParcela());
        assertEquals(new BigDecimal("100000"), response.getValorMaximoEmprestimo());
    }

    @Test
    void defineFaixaDeValoresPessoaFisica() {
        Pessoa pessoa = getPessoa();
        pessoa.setTipoIdentificador(TipoIdentificador.PESSOA_FISICA);

        pessoaService.defineFaixaValores(pessoa);

        assertEquals(new BigDecimal("300"), pessoa.getValorMinimoParcela());
        assertEquals(new BigDecimal("10000"), pessoa.getValorMaximoEmprestimo());
    }

    @Test
    void defineFaixaDeValoresPessoaJuridica() {
        Pessoa pessoa = getPessoa();
        pessoa.setTipoIdentificador(TipoIdentificador.PESSOA_JURIDICA);

        pessoaService.defineFaixaValores(pessoa);

        assertEquals(new BigDecimal("1000"), pessoa.getValorMinimoParcela());
        assertEquals(new BigDecimal("100000"), pessoa.getValorMaximoEmprestimo());
    }

    @Test
    void defineFaixaDeValoresEstudanteUniversitario() {
        Pessoa pessoa = getPessoa();
        pessoa.setTipoIdentificador(TipoIdentificador.ESTUDANTE_UNIVERSITARIO);

        pessoaService.defineFaixaValores(pessoa);

        assertEquals(new BigDecimal("100"), pessoa.getValorMinimoParcela());
        assertEquals(new BigDecimal("10000"), pessoa.getValorMaximoEmprestimo());
    }

    @Test
    void defineFaixaDeValoresAposentadp() {
        Pessoa pessoa = getPessoa();
        pessoa.setTipoIdentificador(TipoIdentificador.APOSENTADO);

        pessoaService.defineFaixaValores(pessoa);

        assertEquals(new BigDecimal("400"), pessoa.getValorMinimoParcela());
        assertEquals(new BigDecimal("25000"), pessoa.getValorMaximoEmprestimo());
    }

    @Test
    void deveRetornarTipoIdentificadorPessoaFisica() {
        Pessoa pessoa = getPessoa();
        String identificador = "33333333333";

        pessoaService.defineTipoIdentificador(identificador, pessoa);

        assertEquals(TipoIdentificador.PESSOA_FISICA.getCodigo(), pessoa.getTipoIdentificador().getCodigo());
    }

    @Test
    void deveRetornarTipoIdentificadorPessoaJuridica() {
        Pessoa pessoa = getPessoa();
        String identificador = "11222888000122";

        pessoaService.defineTipoIdentificador(identificador, pessoa);

        assertEquals(TipoIdentificador.PESSOA_JURIDICA.getCodigo(), pessoa.getTipoIdentificador().getCodigo());
    }

    @Test
    void deveRetornarTipoIdentificadorEstudanteUniversitario() {
        Pessoa pessoa = getPessoa();
        String identificador = "12345678";

        pessoaService.defineTipoIdentificador(identificador, pessoa);

        assertEquals(TipoIdentificador.ESTUDANTE_UNIVERSITARIO.getCodigo(), pessoa.getTipoIdentificador().getCodigo());
    }

    @Test
    void deveRetornarTipoIdentificadorAposentado() {
        Pessoa pessoa = getPessoa();
        String identificador = "1234567890";

        pessoaService.defineTipoIdentificador(identificador, pessoa);

        assertEquals(TipoIdentificador.APOSENTADO.getCodigo(), pessoa.getTipoIdentificador().getCodigo());
    }
    private Pessoa getPessoa(){
        return Pessoa.builder()
            .nome("João da Silva")
            .dataNascimento(LocalDate.of(2000, 2, 1))
            .tipoIdentificador(TipoIdentificador.PESSOA_FISICA)
            .valorMinimoParcela(new BigDecimal("300"))
            .valorMaximoEmprestimo(new BigDecimal("10000"))
            .build();
    }
}
