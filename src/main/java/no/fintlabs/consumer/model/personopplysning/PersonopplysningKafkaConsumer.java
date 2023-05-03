package no.fintlabs.consumer.model.personopplysning;

import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class PersonopplysningKafkaConsumer extends EntityKafkaConsumer<PersonopplysningResource> {

    public PersonopplysningKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            PersonopplysningConfig personopplysningConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, personopplysningConfig);
    }
}
