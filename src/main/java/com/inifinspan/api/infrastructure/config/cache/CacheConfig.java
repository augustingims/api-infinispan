package com.inifinspan.api.infrastructure.config.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Configuration
@RequiredArgsConstructor
public class CacheConfig {
  

//  @Bean
//  public RemoteCacheManager remoteCacheManager() {
//    ConfigurationBuilder builder = new ConfigurationBuilder();
//    // Configure the builder with your Infinispan server details
//    return new RemoteCacheManager(builder.build());
//  }
//
//  @Bean
//  public RemoteCache<String, Object> remoteCache(RemoteCacheManager remoteCacheManager) {
//    // Assuming you want to create a RemoteCache bean for use in your application
//    return remoteCacheManager.getCache(); // You may need to specify the cache name
//  }
}
