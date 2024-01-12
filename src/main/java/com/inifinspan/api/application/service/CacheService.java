package com.inifinspan.api.application.service;

import com.inifinspan.api.infrastructure.config.cache.CacheClientResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService {

  private final CacheClientResolver cacheClientResolver;

  public void addToCache(String childSystem, String childKey,Object value) {
    cacheClientResolver.get(childSystem).put(childKey, value);
  }

  public Object getFromCache(String childSystem,String childKey) {
    return cacheClientResolver.get(childSystem).get(childKey);
  }

  public void updateCache(String childSystem,String childKey, Object value) {
    cacheClientResolver.get(childSystem).replace(childKey, value);
  }

  public void removeFromCache(String childSystem,String childKey) {
    cacheClientResolver.get(childSystem).remove(childKey);
  }

}
