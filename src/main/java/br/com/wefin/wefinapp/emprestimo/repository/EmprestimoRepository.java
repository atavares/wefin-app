package br.com.wefin.wefinapp.emprestimo.repository;

import br.com.wefin.wefinapp.emprestimo.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
