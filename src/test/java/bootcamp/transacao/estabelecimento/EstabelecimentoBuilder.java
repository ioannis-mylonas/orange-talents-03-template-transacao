package bootcamp.transacao.estabelecimento;

public class EstabelecimentoBuilder {
    private String nome, cidade, endereco;

    public EstabelecimentoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public EstabelecimentoBuilder setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EstabelecimentoBuilder setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public Estabelecimento build() {
        return new Estabelecimento(nome, cidade, endereco);
    }
}
