package bootcamp.transacao.cartao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartaoRepositoryTest {
    @Autowired private CartaoRepository repository;
    private final CartaoBuilder builder = new CartaoBuilder();

    @BeforeEach
    public void setup() {
        builder.setEmail("test@email.com");

        repository.saveAll(List.of(
                builder.setId("ID 1").build(),
                builder.setId("ID 2").build(),
                builder.setId("ID 3").build(),
                builder.setId("ID 4").build(),
                builder.setId("ID 5").build(),
                builder.setId("ID 6").build()
        ));
    }

    @ParameterizedTest
    @ValueSource(strings = { "ID 1", "ID 2", "ID 3", "ID 4", "ID 5", "ID 6" })
    public void testaFindByLegacyIdSucesso(String id) {
        Optional<Cartao> cartao = repository.findByLegacyId(id);
        assertTrue(cartao.isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = { "ID 7", "ID 8", "ID 9", "ID 10", "ID 11", "ID 12", "", "I" })
    public void testaFindByLegacyIdFalha(String id) {
        Optional<Cartao> cartao = repository.findByLegacyId(id);
        assertTrue(cartao.isEmpty());
    }
}