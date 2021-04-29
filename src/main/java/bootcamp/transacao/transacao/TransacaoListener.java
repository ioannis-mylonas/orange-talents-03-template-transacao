package bootcamp.transacao.transacao;

import bootcamp.transacao.cartao.Cartao;
import bootcamp.transacao.cartao.CartaoRepository;
import bootcamp.transacao.estabelecimento.Estabelecimento;
import bootcamp.transacao.estabelecimento.EstabelecimentoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TransacaoListener {
    private final CartaoRepository cartaoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final TransacaoRepository transacaoRepository;

    public TransacaoListener(CartaoRepository cartaoRepository,
                             EstabelecimentoRepository estabelecimentoRepository,
                             TransacaoRepository transacaoRepository) {

        this.cartaoRepository = cartaoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @KafkaListener(topics = {"transacoes"}, containerFactory = "kafkaJsonListenerContainerFactory")
    @Transactional
    public void listen(MessageTransacao message) {
        Cartao cartao = cartaoRepository
                .findByLegacyId(message.getCartao().getId())
                .orElse(message.getCartao().converte());

        Estabelecimento estabelecimento = estabelecimentoRepository
                .findByNomeAndCidade(
                        message.getEstabelecimento().getNome(),
                        message.getEstabelecimento().getCidade()
                ).orElse(message.getEstabelecimento().converte());

        Transacao transacao = message.converte(cartao, estabelecimento);
        transacaoRepository.save(transacao);
    }
}
