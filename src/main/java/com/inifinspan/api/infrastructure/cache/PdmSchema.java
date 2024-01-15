package com.inifinspan.api.infrastructure.cache;

import com.inifinspan.api.domain.PdmModel;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

/**
 * @author TECHAP KEMADJEU Augustin Marius <externe.augustin.kemadjeu@allianz.fr>
 */
@AutoProtoSchemaBuilder(
    includeClasses = {PdmModel.class},
    schemaFileName = "pdmModel.proto",
    schemaFilePath = "proto/",
    schemaPackageName = "com.inifinspan.api.infrastructure.cache"
)
public interface PdmSchema extends GeneratedSchema {}
