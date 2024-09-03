package no.fintlabs.consumer.model.personopplysning;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PersonopplysningService extends CacheService<PersonopplysningResource> {

    private final PersonopplysningKafkaConsumer personopplysningKafkaConsumer;

    private final PersonopplysningLinker linker;

    public PersonopplysningService(
            PersonopplysningKafkaConsumer personopplysningKafkaConsumer,
            PersonopplysningLinker linker,
            CacheManager cacheManager,
            PersonopplysningConfig personopplysningConfig) {
        super(personopplysningConfig, cacheManager, personopplysningKafkaConsumer);
        this.personopplysningKafkaConsumer = personopplysningKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<PersonopplysningResource> initializeCache(CacheManager cacheManager, ConsumerConfig<PersonopplysningResource> consumerConfig, String modelName) {
        return cacheManager.<PersonopplysningResource>create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        personopplysningKafkaConsumer.registerListener(PersonopplysningResource.class, this::addResourceToCache);
    }

    private void addResourceToCache(ConsumerRecord<String, PersonopplysningResource> consumerRecord) {
        updateRetensionTime(consumerRecord.headers().lastHeader("topic-retension-time"));
        this.eventLogger.logDataRecieved();
        PersonopplysningResource fravarResource = consumerRecord.value();
        linker.mapLinks(fravarResource);
        this.getCache().put(consumerRecord.key(), fravarResource, linker.hashCodes(fravarResource));
    }

    @Override
    public Optional<PersonopplysningResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(PersonopplysningResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
