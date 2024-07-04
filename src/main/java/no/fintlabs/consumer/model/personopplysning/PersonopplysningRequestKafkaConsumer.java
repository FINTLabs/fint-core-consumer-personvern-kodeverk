package no.fintlabs.consumer.model.personopplysning;

import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class PersonopplysningRequestKafkaConsumer extends EventRequestKafkaConsumer<PersonopplysningResource> {
    public PersonopplysningRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PersonopplysningConfig personopplysningConfig) {
        super(eventConsumerFactoryService, personopplysningConfig);
    }
}
