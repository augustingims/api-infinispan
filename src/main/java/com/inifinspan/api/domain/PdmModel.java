package com.inifinspan.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.infinispan.protostream.annotations.ProtoField;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
public class PdmModel implements Serializable, IKeyedObject<String> {
  @ProtoField(
      number = 1,
      required = true
  )
  String parentId;
  @ProtoField(
      number = 2,
      required = true
  )
  String childId;
  @ProtoField(
      number = 3
  )
  String objectVersion;
  @ProtoField(
      number = 4,
      collectionImplementation = HashSet.class
  )
  Set<String> references = new HashSet<>();
  @ProtoField(
      number = 5
  )
  String classId;

  public PdmModel(String parentId, String childId, String classId) {
    this.parentId = parentId;
    this.childId = childId;
    this.classId = classId;
  }

  public PdmModel(String parentId, String childId, Set<String> references, String classId) {
    this.parentId = parentId;
    this.childId = childId;
    this.references = references;
    this.classId = classId;
  }

  public PdmModel(String parentId, String childId, String objectVersion, Set<String> references, String classId) {
    this.parentId = parentId;
    this.childId = childId;
    this.objectVersion = objectVersion;
    this.references = references;
    this.classId = classId;
  }

  public PdmModel() {
  }

  public String getKey() {
    return this.childId;
  }

  public String getParentId() {
    return this.parentId;
  }

  public String getChildId() {
    return this.childId;
  }

  public String getObjectVersion() {
    return this.objectVersion;
  }

  public Set<String> getReferences() {
    return this.references;
  }

  public String getClassId() {
    return this.classId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public void setChildId(String childId) {
    this.childId = childId;
  }

  public void setObjectVersion(String objectVersion) {
    this.objectVersion = objectVersion;
  }

  public void setReferences(Set<String> references) {
    this.references = references;
  }

  public void setClassId(String classId) {
    this.classId = classId;
  }

  public String toString() {
    return "PdmModel(parentId=" + this.getParentId() + ", childId=" + this.getChildId() + ", objectVersion=" + this.getObjectVersion() + ", references=" + this.getReferences() + ", classId=" + this.getClassId() + ")";
  }

}
