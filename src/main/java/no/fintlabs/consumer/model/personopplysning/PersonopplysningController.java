package no.fintlabs.consumer.model.personopplysning;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.WriteableConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Personopplysning", value = RestEndpoints.PERSONOPPLYSNING, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class PersonopplysningController extends WriteableConsumerRestController<PersonopplysningResource> {

    public PersonopplysningController(
            CacheService<PersonopplysningResource> cacheService,
            PersonopplysningLinker fintLinker,
            PersonopplysningConfig personopplysningConfig,
            PersonopplysningEventKafkaProducer personopplysningEventKafkaProducer,
            PersonopplysningResponseKafkaConsumer personopplysningResponseKafkaConsumer,
            FintFilterService odataFilterService,
            PersonopplysningRequestKafkaConsumer personopplysningRequestKafkaConsumer) {
        super(cacheService, fintLinker, personopplysningConfig, personopplysningEventKafkaProducer, personopplysningResponseKafkaConsumer, odataFilterService, personopplysningRequestKafkaConsumer);
    }

    @PostConstruct
    private void registerIdentificators() {
        super.registerIdenficatorHandler("systemid", PersonopplysningResource::getSystemId);
    }
}