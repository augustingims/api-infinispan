package com.inifinspan.api;

import com.inifinspan.api.domain.PdmModel;
import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.marshall.MarshallerUtil;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ApiInifinspanApplication implements CommandLineRunner {

  private final RemoteCacheManager cacheManager;

  public static void main(String[] args) {
    SpringApplication.run(ApiInifinspanApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    SerializationContext ctx = MarshallerUtil.getSerializationContext(cacheManager);
    RemoteCache<String, String> protoMetadataCache = cacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
    String msgSchemaFile = null;
    try {
//      ProtoSchemaBuilder protoSchemaBuilder = new ProtoSchemaBuilder();
//      msgSchemaFile = protoSchemaBuilder.fileName("pdmModel.proto").packageName("proto").addClass(PdmModel.class).build(ctx);
//      protoMetadataCache.put("pdmModel.proto", msgSchemaFile);

      ProtoSchemaBuilder protoSchemaBuilder2 = new ProtoSchemaBuilder();
      msgSchemaFile = protoSchemaBuilder2.fileName("pdm.proto").packageName("org.opin.cisl.adapter.v2.ia.epc.pdm.infinispan.repository").addClass(PdmModel.class).build(ctx);
      protoMetadataCache.put("pdm.proto", msgSchemaFile);

//      GeneratedSchema schema = new PdmModelSchemaImpl();
//      protoMetadataCache.put(schema.getProtoFileName(), schema.getProtoFile());

    } catch (Exception e) {
      throw new RuntimeException("Failed to build protobuf definition from 'PdmProto class'", e);
    }

    String errors = protoMetadataCache.get(ProtobufMetadataManagerConstants.ERRORS_KEY_SUFFIX);
    if (errors != null) {
      throw new IllegalStateException("Some Protobuf schema files contain errors: " + errors + "\nSchema :\n" + msgSchemaFile);
    }
  }
}
