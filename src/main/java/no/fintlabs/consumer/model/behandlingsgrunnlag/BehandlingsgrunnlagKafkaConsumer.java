package no.fintlabs.consumer.model.behandlingsgrunnlag;

import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingsgrunnlagKafkaConsumer extends EntityKafkaConsumer<BehandlingsgrunnlagResource> {

    public BehandlingsgrunnlagKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            BehandlingsgrunnlagConfig consumerConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, consumerConfig);
    }
}
