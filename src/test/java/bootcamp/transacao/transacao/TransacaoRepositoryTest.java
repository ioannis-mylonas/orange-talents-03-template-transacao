package bootcamp.transacao.transacao;

import bootcamp.transacao.cartao.Cartao;
import bootcamp.transacao.cartao.CartaoBuilder;
import bootcamp.transacao.cartao.CartaoRepository;
import bootcamp.transacao.estabelecimento.Estabelecimento;
import bootcamp.transacao.estabelecimento.EstabelecimentoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransacaoRepositoryTest {
    @Autowired private TransacaoRepository transacaoRepository;
    @Autowired private CartaoRepository cartaoRepository;

    private final CartaoBuilder cartaoBuilder = new CartaoBuilder();
    private final EstabelecimentoBuilder estabelecimentoBuilder = new EstabelecimentoBuilder();
    private final TransacaoBuilder transacaoBuilder = new TransacaoBuilder();

    private Cartao cartaoDefault;

    private final PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "efetivadaEm"));

    @BeforeEach
    public void setup() {
        cartaoDefault = cartaoBuilder
                .setEmail("teste@email.com")
                .setId("ID 1")
                .build();

        Estabelecimento estabelecimentoDefault = estabelecimentoBuilder
                .setEndereco("Endereco A")
                .setCidade("Cidade A")
                .setNome("Nome A")
                .build();

        transacaoBuilder
                .setId("ID DEFAULT")
                .setEfetivadaEm(LocalDateTime.now())
                .setValor(25.03)
                .setEstabelecimento(estabelecimentoDefault);
    }

    @Test
    public void testaEncontraTransacaoPorCartaoSucesso() {
        Transacao transacao = transacaoBuilder
                .setCartao(cartaoDefault)
                .build();

        transacaoRepository.save(transacao);

        List<Transacao> resultado = transacaoRepository.findAllByCartao(cartaoDefault, pageable);
        assertEquals(1, resultado.size());
    }

    @Test
    public void testaEncontraTransacaoPorCartaoVarios() {
        transacaoBuilder.setCartao(cartaoDefault);

        List<Transacao> transacoes = List.of(
                transacaoBuilder.build(),
                transacaoBuilder.build(),
                transacaoBuilder.build(),
                transacaoBuilder.build(),
                transacaoBuilder.build()
        );

        transacaoRepository.saveAll(transacoes);

        List<Transacao> resultado = transacaoRepository.findAllByCartao(cartaoDefault, pageable);
        assertEquals(5, resultado.size());
    }

    @Test
    public void testaEncontraTransacaoPorCartaoVazio() {
        cartaoRepository.save(cartaoDefault);
        List<Transacao> resultado = transacaoRepository.findAllByCartao(cartaoDefault, pageable);
        assertEquals(0, resultado.size());
    }
}