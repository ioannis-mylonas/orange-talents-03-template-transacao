package bootcamp.transacao.cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Cartao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Convert(converter = CartaoIdConverter.class)
    @Column(unique = true)
    private String legacyId;

    @NotBlank
    @Email
    private String email;

    /**
     * @Deprecated Para uso do Hibernate
     */
    public Cartao() {}

    public Cartao(@NotBlank String legacyId, @NotBlank @Email String email) {
        this.legacyId = legacyId;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public String getEmail() {
        return email;
    }
}
