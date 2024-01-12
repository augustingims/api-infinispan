package com.inifinspan.api.application.rest;

import com.inifinspan.api.application.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cache")
public class CacheResource {

  private final CacheService cacheService;

  @PostMapping("/add/{childKey}")
  public void addToCache(@PathVariable String childKey,@RequestBody Object value) {
    cacheService.addToCache("RC",childKey,value);
  }

  @GetMapping("/get/{childKey}")
  public Object getFromCache(@PathVariable String childKey) {
    return cacheService.getFromCache("RC",childKey);
  }

  @PutMapping("/update/{childKey}")
  public void updateCache(@PathVariable String childKey, @RequestBody Object value) {
    cacheService.updateCache("RC",childKey, value);
  }

  @DeleteMapping("/remove/{childKey}")
  public void removeFromCache(@PathVariable String childKey) {
    cacheService.removeFromCache("RC",childKey);
  }

}
