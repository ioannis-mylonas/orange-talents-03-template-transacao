package bootcamp.transacao.transacao;

import bootcamp.transacao.cartao.Cartao;
import bootcamp.transacao.estabelecimento.Estabelecimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoBuilder {
    private String id;
    private BigDecimal valor;
    private Cartao cartao;
    private Estabelecimento estabelecimento;
    private LocalDateTime efetivadaEm;

    public TransacaoBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public TransacaoBuilder setValor(double valor) {
        this.valor = BigDecimal.valueOf(valor);
        return this;
    }

    public TransacaoBuilder setCartao(Cartao cartao) {
        this.cartao = cartao;
        return this;
    }

    public TransacaoBuilder setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
        return this;
    }

    public TransacaoBuilder setEfetivadaEm(LocalDateTime efetivadaEm) {
        this.efetivadaEm = efetivadaEm;
        return this;
    }

    public Transacao build() {
        return new Transacao(id, valor, cartao, estabelecimento, efetivadaEm);
    }
}
