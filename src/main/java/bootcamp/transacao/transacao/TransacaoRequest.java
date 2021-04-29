package bootcamp.transacao.transacao;

import bootcamp.transacao.client.TransacaoClientRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransacaoRequest {
    @NotBlank
    @Email
    @JsonProperty
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public TransacaoClientRequest converte(String cartao) {
        return new TransacaoClientRequest(cartao, email);
    }
}
