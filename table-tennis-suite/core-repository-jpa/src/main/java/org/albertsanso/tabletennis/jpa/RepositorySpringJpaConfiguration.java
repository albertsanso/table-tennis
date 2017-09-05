package org.albertsanso.tabletennis.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@ComponentScan
@EnableJpaRepositories
@EntityScan
public class RepositorySpringJpaConfiguration {
}
