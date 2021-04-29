package bootcamp.transacao.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageCartao {
    @JsonProperty private String id;
    @JsonProperty private String email;

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao converte() {
        return new Cartao(id, email);
    }
}
