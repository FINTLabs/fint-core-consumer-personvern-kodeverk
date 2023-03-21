package no.fintlabs.core.consumer.behandlingsgrunnlag;

import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class BehandlingsgrunnlagConfig extends ConsumerConfig<BehandlingsgrunnlagResource> {

    public BehandlingsgrunnlagConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String domainName() {
        return "personvern";
    }

    @Override
    protected String packageName() {
        return "kodeverk";
    }

    @Override
    protected String resourceName() {
        return "behandlingsgrunnlag";
    }
}
