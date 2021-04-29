package bootcamp.transacao.estabelecimento;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageEstabelecimento {
    @JsonProperty private String nome;
    @JsonProperty private String cidade;
    @JsonProperty private String endereco;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public Estabelecimento converte() {
        return new Estabelecimento(nome, cidade, endereco);
    }
}
