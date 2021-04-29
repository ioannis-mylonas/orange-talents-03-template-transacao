package bootcamp.transacao.estabelecimento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    Optional<Estabelecimento> findByNomeAndCidade(String nome, String cidade);
}
