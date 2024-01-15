package com.inifinspan.api.application.rest;

import com.inifinspan.api.domain.PdmModel;
import com.inifinspan.api.infrastructure.cache.CacheRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CacheV2Resource {

  private final CacheRepository cacheRepository;

  @PostMapping("/v2/addOrUpdate")
  public PdmModel addOrUpdate(@RequestParam String childSystem, @RequestBody PdmModel pdmModel) {
    return cacheRepository.addOrUpdate(childSystem,pdmModel);
  }
  @GetMapping("/v2/get/child/{childId}")
  public PdmModel getById(@RequestParam String childSystem, @PathVariable String childId) {
    return cacheRepository.getById(childSystem,childId);
  }

  @GetMapping("/v2/get/parent/{parentId}")
  public List<PdmModel> getMappingByParentId(@RequestParam String childSystem, @PathVariable String parentId) {
    return cacheRepository.getMappingByParentId(childSystem,parentId);
  }

  @GetMapping("/v2/get/reference/{childId}")
  public List<PdmModel> getByReference(@RequestParam String childSystem, @PathVariable String childId) {
    return cacheRepository.getSuper(childSystem,childId);
  }
  @GetMapping("/v2/getAll")
  public List<PdmModel> getAll(@RequestParam String childSystem) {
    return cacheRepository.getAll(childSystem);
  }
  @GetMapping("/v2/getAllByQuery")
  public List<PdmModel> getAllByQuery(@RequestParam String childSystem) {
    return cacheRepository.getAllByQuery(childSystem);
  }
  @DeleteMapping("/v2/remove/{childId}")
  public PdmModel deleteById(@RequestParam String childSystem, @PathVariable String childId) {
    return cacheRepository.deleteById(childSystem,childId);
  }
  @DeleteMapping("/v2/remove/child/{childId}")
  public void removeMappingByChildIdWithReference(@RequestParam String childSystem, @PathVariable String childId) {
    cacheRepository.removeMappingByChildId(childSystem,childId);
  }

  @DeleteMapping("/v2/remove/parent/{parentId}")
  public void removeMappingByParentIdWithAllReferences(@RequestParam String childSystem, @PathVariable String parentId) {
    cacheRepository.removeMappingByParentId(childSystem,parentId);
  }
  @DeleteMapping("/v2/removeAll")
  public void deleteAll(@RequestParam String childSystem) {
    cacheRepository.deleteAll(childSystem);
  }

}
