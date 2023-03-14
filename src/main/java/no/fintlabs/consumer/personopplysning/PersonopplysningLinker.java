package no.fintlabs.consumer.personopplysning;

import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fint.model.resource.personvern.kodeverk.PersonopplysningResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class PersonopplysningLinker extends FintLinker<PersonopplysningResource> {

    public PersonopplysningLinker() {
        super(PersonopplysningResource.class);
    }

    public void mapLinks(PersonopplysningResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public PersonopplysningResources toResources(Collection<PersonopplysningResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public PersonopplysningResources toResources(Stream<PersonopplysningResource> stream, int offset, int size, int totalItems) {
        PersonopplysningResources resources = new PersonopplysningResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(PersonopplysningResource fravar) {
        return getAllSelfHrefs(fravar).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(PersonopplysningResource fravar) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(fravar.getSystemId()) && !StringUtils.isEmpty(fravar.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(fravar.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(PersonopplysningResource fravar) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(fravar.getSystemId()) && !StringUtils.isEmpty(fravar.getSystemId().getIdentifikatorverdi())) {
            builder.add(fravar.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}