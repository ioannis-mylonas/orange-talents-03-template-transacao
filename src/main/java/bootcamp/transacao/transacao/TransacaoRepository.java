package bootcamp.transacao.transacao;

//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
//    List<Transacao> findAllByCartao_legacyId(String cartao, Pageable pageable);
}
