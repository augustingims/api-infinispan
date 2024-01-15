package com.inifinspan.api.infrastructure.cache;

import com.inifinspan.api.domain.PdmModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.infinispan.client.hotrod.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CacheRepository {

  private static final String FROM = "from ";

  private final CacheClientResolver cacheClientResolver;

  public PdmModel addOrUpdate(@NonNull String childSystem, @NonNull PdmModel object) {
    if (this.cacheClientResolver.get(childSystem).containsKey(object.getKey())) {
      log.debug("Update object with key {}.", object.getKey());
    } else {
      log.debug("Add object with key {} to remote cache.", object.getKey());
    }

    return this.cacheClientResolver.get(childSystem).put(object.getKey(), object);
  }

  public PdmModel getById(@NonNull String childSystem, @NonNull String key) {
    return this.cacheClientResolver.get(childSystem).get(key);
  }

  public PdmModel deleteById(@NonNull String childSystem, @NonNull String key) {
    log.debug("Remove key {} from remote cache.", key);
    return this.cacheClientResolver.get(childSystem).remove(key);
  }

  public void deleteAll(@NonNull String childSystem) {
    log.debug("Cleared remote cache.");
    this.cacheClientResolver.get(childSystem).clear();
  }

  public List<PdmModel> getAll(@NonNull String childSystem) {
    return new ArrayList<>(this.cacheClientResolver.get(childSystem).values());
  }

  public PdmModel getMappingByChildId(@NonNull String childSystem, String childId) {
    return getById(childSystem, childId);
  }

  public List<PdmModel> getAllByQuery(@NonNull String childSystem) {
    QueryFactory qf = Search.getQueryFactory(cacheClientResolver.get(childSystem));
    Query<PdmModel> query = qf.create("from org.opin.cisl.adapter.v2.ia.epc.pdm.infinispan.repository.PdmModel");
    return query.execute().list();
  }

  public List<PdmModel> getMappingByParentId(@NonNull String childSystem, String parentId){
    QueryFactory qf = Search.getQueryFactory(cacheClientResolver.get(childSystem));
    Query<PdmModel> query = qf.create("from org.opin.cisl.adapter.v2.ia.epc.pdm.infinispan.repository.PdmModel where parentId = :parentId");
    query.setParameter("parentId", parentId);
    return query.execute().list();
  }

  public List<PdmModel> getSuper(@NonNull String childSystem, String childId){
    QueryFactory qf = Search.getQueryFactory(cacheClientResolver.get(childSystem));
    Query<PdmModel> query = qf.create("from org.opin.cisl.adapter.v2.ia.epc.pdm.infinispan.repository.PdmModel m where m.references = :childId");
    query.setParameter("childId", childId);
    return query.execute().list();
  }

  public void removeMappingByChildId(String childSystem, String childId) {
    this.removeReferenceFromSuper(childSystem, childId);
    this.deepRemove(childSystem, childId);
  }

  public void removeMappingByParentId(String childSystem, String parentId) {
    this.getMappingByParentId(childSystem,parentId).forEach((pdm) -> {
      this.removeMappingByChildId(childSystem,pdm.getChildId());
    });
  }

  private void deepRemove(String childSystem, String childId) {
    PdmModel pdm = this.getMappingByChildId(childSystem,childId);
    if (pdm != null && !pdm.getReferences().isEmpty()) {
      pdm.getReferences().forEach(reference -> deepRemove(childSystem, reference));
    }
    deleteById(childSystem,childId);
  }

  private void removeReferenceFromSuper(String childSystem,String childId) {
    this.getSuper(childSystem,childId).forEach((superModel) -> {
      superModel.setReferences(new HashSet(superModel.getReferences()));
      superModel.getReferences().remove(childId);
      addOrUpdate(childSystem,superModel);
    });
  }
}
