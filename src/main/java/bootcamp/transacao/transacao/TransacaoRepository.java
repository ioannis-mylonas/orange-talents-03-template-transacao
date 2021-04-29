package bootcamp.transacao.transacao;

import bootcamp.transacao.cartao.Cartao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findAllByCartao(Cartao cartao, Pageable pageable);
}
