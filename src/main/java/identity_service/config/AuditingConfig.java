package identity_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // Kích hoạt tính năng tự động ghi nhận thời gian
public class AuditingConfig {
}
