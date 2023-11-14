package no.fintlabs.consumer.model.behandlingsgrunnlag;

import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class BehandlingsgrunnlagConfig extends ConsumerConfig<BehandlingsgrunnlagResource> {

    public BehandlingsgrunnlagConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "behandlingsgrunnlag";
    }
}
