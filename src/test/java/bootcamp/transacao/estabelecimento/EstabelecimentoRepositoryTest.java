package bootcamp.transacao.estabelecimento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EstabelecimentoRepositoryTest {
    @Autowired private EstabelecimentoRepository repository;
    private final EstabelecimentoBuilder builder = new EstabelecimentoBuilder();

    @BeforeEach
    public void setup() {
        builder.setNome("Estabelecimento A").setCidade("Cidade A").setEndereco("Endereco A");

        repository.saveAll(List.of(
                builder.build(),
                builder.setNome("Estabelecimento B").build(),
                builder.setNome("Estabelecimento C").build(),

                builder.setCidade("Cidade B").setNome("Estabelecimento A").build(),
                builder.setNome("Estabelecimento B").build(),
                builder.setNome("Estabelecimento C").build()
        ));
    }

    @ParameterizedTest
    @CsvSource(value = {"Estabelecimento A,Cidade A", "Estabelecimento B,Cidade B",
    "Estabelecimento B,Cidade A", "Estabelecimento A,Cidade B"})
    public void testaFindByNomeAndCidadeSucesso(String nome, String cidade) {
        Optional<Estabelecimento> estabelecimento = repository.findByNomeAndCidade(nome, cidade);
        assertTrue(estabelecimento.isPresent());
    }

    @ParameterizedTest
    @CsvSource(value = {"Estabelecimento D,Cidade A", "Estabelecimento B,Cidade D",
            "Estabelecimento D,Cidade D", "Estabelecimento E,Cidade E"})
    public void testaFindByNomeAndCidadeFalha(String nome, String cidade) {
        Optional<Estabelecimento> estabelecimento = repository.findByNomeAndCidade(nome, cidade);
        assertTrue(estabelecimento.isEmpty());
    }
}