package bootcamp.transacao.transacao;

import bootcamp.transacao.client.TransacaoClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransacaoController {
    private final TransacaoClient client;
    private final TransacaoRepository transacaoRepository;

    public TransacaoController(TransacaoClient client, TransacaoRepository transacaoRepository) {
        this.client = client;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping("/cartoes/{id}/transacoes")
    public void start(@PathVariable("id") String cartao,
                      @RequestBody @Valid TransacaoRequest request) {
        client.start(request.converte(cartao));
    }

    @DeleteMapping("/cartoes/{id}/transacoes")
    public void stop(@PathVariable("id") String cartao) {
        client.stop(cartao);
    }

    @GetMapping("/cartoes/{id}/transacoes")
    public List<Transacao> list(@PathVariable("id") String cartao,
                                @PageableDefault(size = 10, page = 0, sort = {"efetivadaEm"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return transacaoRepository.findAllByCartao_legacyId(cartao, pageable);
    }
}
