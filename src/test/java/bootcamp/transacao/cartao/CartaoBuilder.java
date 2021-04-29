package bootcamp.transacao.cartao;

public class CartaoBuilder {
    private String id, email;

    public CartaoBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CartaoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Cartao build() {
        return new Cartao(id, email);
    }
}
