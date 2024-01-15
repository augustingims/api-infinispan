package com.inifinspan.api.infrastructure.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Component
@ConfigurationProperties(prefix = "infinispan")
@Data
public class CacheProperties {
  private String fcaCacheParty;
  private String fcpfaCacheParty;
}
