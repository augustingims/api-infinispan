package com.inifinspan.api.infrastructure.config.cache;

import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Component
@RequiredArgsConstructor
public class CacheClientResolver {

  private final RemoteCacheManager remoteCacheManager;

  @Value("${infinispan.cache.fca-party}")
  public String pdmFcaCache;

  @Value("${infinispan.cache.fcpfa-party}")
  public String pdmFcpfaCache;

  public RemoteCache<String, Object> get(String childSystem){
    return resolve(childSystem);
  }

  private RemoteCache<String, Object> resolve(String childSystem){
    if("RC".equals(childSystem)){
      return remoteCacheManager.getCache(pdmFcaCache);
    } else if("M4".equals(childSystem)){
      return remoteCacheManager.getCache(pdmFcpfaCache);
    } else {
      throw new IllegalStateException("Invalid cache");
    }
  }
}
