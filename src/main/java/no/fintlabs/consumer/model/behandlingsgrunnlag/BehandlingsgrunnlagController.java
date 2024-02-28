package no.fintlabs.consumer.model.behandlingsgrunnlag;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.ConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Behandlingsgrunnlag", value = RestEndpoints.BEHANDLINGSGRUNNLAG, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class BehandlingsgrunnlagController extends ConsumerRestController<BehandlingsgrunnlagResource> {

    public BehandlingsgrunnlagController(
            BehandlingsgrunnlagService service,
            BehandlingsgrunnlagLinker linker,
            FintFilterService oDataFilterService) {
        super(service, linker, oDataFilterService);
    }

    @PostConstruct
    private void registerIdentificators() {
        super.registerIdenficatorHandler("systemid", BehandlingsgrunnlagResource::getSystemId);
    }

}

