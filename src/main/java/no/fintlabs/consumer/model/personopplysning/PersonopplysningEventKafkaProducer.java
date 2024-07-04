package no.fintlabs.consumer.model.personopplysning;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class PersonopplysningEventKafkaProducer extends EventKafkaProducer {
    public PersonopplysningEventKafkaProducer(EventProducerFactory eventProducerFactory, PersonopplysningConfig personopplysningConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, personopplysningConfig, eventTopicService);
    }

}