package bootcamp.transacao.transacao;

import bootcamp.transacao.cartao.Cartao;
import bootcamp.transacao.estabelecimento.Estabelecimento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String legacyId;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal valor;

    @NotNull
    @Valid
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Cartao cartao;

    @NotNull
    @Valid
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Estabelecimento estabelecimento;

    @PastOrPresent
    @NotNull
    private LocalDateTime efetivadaEm;

    /**
     * @Deprecated Para uso do Hibernate
     */
    public Transacao() {}

    public Transacao(String legacyId, BigDecimal valor,
                     Cartao cartao, Estabelecimento estabelecimento,
                     LocalDateTime efetivadaEm) {

        this.legacyId = legacyId;
        this.valor = valor;
        this.cartao = cartao;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

    public Long getId() {
        return id;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
