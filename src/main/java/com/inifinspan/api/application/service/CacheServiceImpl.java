package com.inifinspan.api.application.service;

import com.inifinspan.api.domain.PdmModel;
import com.inifinspan.api.domain.PdmModelDto;
import com.inifinspan.api.infrastructure.cache.CacheClientResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {

  private final CacheClientResolver cacheClientResolver;

  @Override
  public void addToCache(String childSystem, PdmModelDto pdmModel) {
    log.info("Method addToCache");
    log.info("childSystem: {}", childSystem);
    cacheClientResolver.get(childSystem).put(pdmModel.getChildId(), new PdmModel(pdmModel.getParentId(), pdmModel.getChildId(), pdmModel.getReferences(), pdmModel.getClassId()));
  }

  @Override
  public PdmModel getFromCache(String childSystem, String childId) {
    log.info("Method getFromCache");
    log.info("childSystem: {}", childSystem);
    log.info("childId: {}", childId);
    return cacheClientResolver.get(childSystem).get(childId);
  }

  @Override
  public void updateCache(String childSystem, String childId, PdmModelDto pdmModel) {
    log.info("Method updateCache");
    log.info("childSystem: {}", childSystem);
    log.info("childId: {}", childId);
    PdmModel pdmModelExist = getFromCache(childSystem, childId);
    if(pdmModelExist != null){
      pdmModelExist.setReferences(pdmModel.getReferences());
      cacheClientResolver.get(childSystem).replace(childId, pdmModelExist);
    }
  }

  @Override
  public void removeFromCache(String childSystem, String childId) {
    cacheClientResolver.get(childSystem).remove(childId);
  }
}
