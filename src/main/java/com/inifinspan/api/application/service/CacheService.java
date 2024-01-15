package com.inifinspan.api.application.service;

import com.inifinspan.api.domain.PdmModel;
import com.inifinspan.api.domain.PdmModelDto;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
public interface CacheService {

  void addToCache(String childSystem, PdmModelDto pdmModel);

  PdmModel getFromCache(String childSystem, String childId);

  void updateCache(String childSystem, String childId, PdmModelDto pdmModel);

  void removeFromCache(String childSystem, String childId);
}
