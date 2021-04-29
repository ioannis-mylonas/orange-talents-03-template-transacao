package bootcamp.transacao.transacao;

import bootcamp.transacao.cartao.Cartao;
import bootcamp.transacao.cartao.CartaoRepository;
import bootcamp.transacao.client.TransacaoClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransacaoController {
    private final TransacaoClient client;
    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;

    public TransacaoController(TransacaoClient client,
                               TransacaoRepository transacaoRepository,
                               CartaoRepository cartaoRepository) {
        this.client = client;
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
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
    public List<Transacao> list(@PathVariable("id") String cartaoId,
                                                @PageableDefault(size = 10, page = 0, sort = {"efetivadaEm"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Cartao cartao = cartaoRepository
                .findByLegacyId(cartaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return transacaoRepository.findAllByCartao(cartao, pageable);
    }
}
