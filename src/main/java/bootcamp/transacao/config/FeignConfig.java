package bootcamp.transacao.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"bootcamp.transacao"})
public class FeignConfig {
}
