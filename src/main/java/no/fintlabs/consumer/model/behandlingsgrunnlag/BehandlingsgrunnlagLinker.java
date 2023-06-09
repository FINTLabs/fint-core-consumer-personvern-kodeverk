package no.fintlabs.consumer.model.behandlingsgrunnlag;

import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class BehandlingsgrunnlagLinker extends FintLinker<BehandlingsgrunnlagResource> {

    public BehandlingsgrunnlagLinker() {
        super(BehandlingsgrunnlagResource.class);
    }

    public void mapLinks(BehandlingsgrunnlagResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BehandlingsgrunnlagResources toResources(Collection<BehandlingsgrunnlagResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BehandlingsgrunnlagResources toResources(Stream<BehandlingsgrunnlagResource> stream, int offset, int size, int totalItems) {
        BehandlingsgrunnlagResources resources = new BehandlingsgrunnlagResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BehandlingsgrunnlagResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BehandlingsgrunnlagResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(BehandlingsgrunnlagResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}
