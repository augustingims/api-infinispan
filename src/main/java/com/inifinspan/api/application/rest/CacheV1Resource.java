package com.inifinspan.api.application.rest;

import com.inifinspan.api.application.service.CacheService;
import com.inifinspan.api.domain.PdmModel;
import com.inifinspan.api.domain.PdmModelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cache")
public class CacheV1Resource {

  private final CacheService cacheService;

  @PostMapping("/v1/add")
  public void addToCache(@RequestParam String childSystem, @RequestBody PdmModelDto pdmModel) {
    cacheService.addToCache(childSystem,pdmModel);
  }

  @GetMapping("/v1/get/{childId}")
  public PdmModel getFromCache(@RequestParam String childSystem, @PathVariable String childId) {
    return cacheService.getFromCache(childSystem,childId);
  }

  @PutMapping("/v1/update/{childId}")
  public void updateCache(@RequestParam String childSystem,@PathVariable String childId, @RequestBody PdmModelDto pdmModel) {
    cacheService.updateCache(childSystem,childId,pdmModel);
  }

  @DeleteMapping("/v1/remove/{childId}")
  public void removeFromCache(@RequestParam String childSystem, @PathVariable String childId) {
    cacheService.removeFromCache(childSystem,childId);
  }

}
