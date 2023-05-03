package no.fintlabs.consumer.model.behandlingsgrunnlag;

import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class BehandlingsgrunnlagService extends CacheService<BehandlingsgrunnlagResource> {

    private final BehandlingsgrunnlagKafkaConsumer fravarKafkaConsumer;

    private final BehandlingsgrunnlagLinker linker;

    public BehandlingsgrunnlagService(
            BehandlingsgrunnlagKafkaConsumer fravarKafkaConsumer,
            BehandlingsgrunnlagLinker linker,
            CacheManager cacheManager,
            BehandlingsgrunnlagConfig config) {
        super(config, cacheManager, fravarKafkaConsumer);
        this.fravarKafkaConsumer = fravarKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<BehandlingsgrunnlagResource> initializeCache(CacheManager cacheManager, ConsumerConfig<BehandlingsgrunnlagResource> consumerConfig, String modelName) {
        return cacheManager.<BehandlingsgrunnlagResource>create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = fravarKafkaConsumer.registerListener(BehandlingsgrunnlagResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, BehandlingsgrunnlagResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        BehandlingsgrunnlagResource resource = consumerRecord.value();
        linker.mapLinks(resource);
        this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
    }

    @Override
    public Optional<BehandlingsgrunnlagResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(BehandlingsgrunnlagResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
