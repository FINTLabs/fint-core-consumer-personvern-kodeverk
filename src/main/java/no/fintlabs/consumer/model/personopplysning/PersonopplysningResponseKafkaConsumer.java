package no.fintlabs.consumer.model.personopplysning;

import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class PersonopplysningResponseKafkaConsumer extends EventResponseKafkaConsumer<PersonopplysningResource> {

    public PersonopplysningResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, PersonopplysningConfig personopplysningConfig, PersonopplysningLinker personopplysningLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, personopplysningConfig, personopplysningLinker, eventTopicService);
    }
}
