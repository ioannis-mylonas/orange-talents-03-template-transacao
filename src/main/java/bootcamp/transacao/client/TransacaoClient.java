package bootcamp.transacao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "TransacaoClient", url = "${bootcamp.transacoes-url}")
public interface TransacaoClient {
    @PostMapping("/api/cartoes")
    void start(@RequestBody @Valid TransacaoClientRequest request);

    @DeleteMapping("/api/cartoes/{id}")
    void stop(@PathVariable(name = "id") String id);
}
