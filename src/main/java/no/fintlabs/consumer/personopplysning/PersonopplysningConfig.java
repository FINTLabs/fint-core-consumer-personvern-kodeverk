package no.fintlabs.consumer.personopplysning;

import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class PersonopplysningConfig extends ConsumerConfig<PersonopplysningResource> {

    public PersonopplysningConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String domainName() {
        return "utdanning";
    }

    @Override
    protected String packageName() {
        return "vurdering";
    }

    @Override
    protected String resourceName() {
        return "fravar";
    }
}
