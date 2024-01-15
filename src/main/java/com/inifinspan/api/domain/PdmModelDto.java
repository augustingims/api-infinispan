package com.inifinspan.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@Getter
@Setter
public class PdmModelDto implements Serializable {
 
  String parentId;
  
  String childId;
  
  String objectVersion;
  
  Set<String> references = new HashSet<>();
  
  String classId;
}
