package bootcamp.transacao.transacao;

import bootcamp.transacao.cartao.Cartao;
import bootcamp.transacao.cartao.MessageCartao;
import bootcamp.transacao.estabelecimento.Estabelecimento;
import bootcamp.transacao.estabelecimento.MessageEstabelecimento;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MessageTransacao {
    @JsonProperty private String id;
    @JsonProperty private BigDecimal valor;
    @JsonProperty private MessageEstabelecimento estabelecimento;
    @JsonProperty private MessageCartao cartao;
    @JsonProperty private LocalDateTime efetivadaEm;

    public void setId(String id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setEstabelecimento(MessageEstabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public void setCartao(MessageCartao cartao) {
        this.cartao = cartao;
    }

    public void setEfetivadaEm(LocalDateTime efetivadaEm) {
        this.efetivadaEm = efetivadaEm;
    }

    public MessageEstabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public MessageCartao getCartao() {
        return cartao;
    }

    public Transacao converte(Cartao cartao, Estabelecimento estabelecimento) {
        return new Transacao(this.id, this.valor, cartao, estabelecimento, this.efetivadaEm);
    }
}
