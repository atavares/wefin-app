package br.com.wefin.wefinapp.pessoa.repository;

import br.com.wefin.wefinapp.pessoa.entity.Pessoa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByIdentificador(String identificador);
}
