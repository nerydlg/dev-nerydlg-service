package dev.nerydlg.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "dev.nerydlg.repository")
@EntityScan("dev.nerydlg.entity")
public class RepositoryConfiguration {
}
