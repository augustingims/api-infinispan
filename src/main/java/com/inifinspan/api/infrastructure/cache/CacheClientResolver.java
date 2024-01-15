package com.inifinspan.api.infrastructure.cache;

import com.inifinspan.api.domain.PdmModel;
import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.stereotype.Component;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Component
@RequiredArgsConstructor
public class CacheClientResolver {

  private final RemoteCacheManager remoteCacheManager;

  private final CacheProperties cacheProperties;

  public RemoteCache<String, PdmModel> get(String childSystem){
    return resolve(childSystem);
  }

  private RemoteCache<String, PdmModel> resolve(String childSystem){
    if("RC".equals(childSystem)){
      return remoteCacheManager.getCache(cacheProperties.getFcaCacheParty());
    } else if("M4".equals(childSystem)){
      return remoteCacheManager.getCache(cacheProperties.getFcpfaCacheParty());
    } else {
      throw new IllegalStateException("Invalid cache");
    }
  }
}
