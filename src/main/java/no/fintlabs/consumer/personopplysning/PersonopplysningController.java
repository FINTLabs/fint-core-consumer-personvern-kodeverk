package no.fintlabs.consumer.personopplysning;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
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
@RequestMapping(name = "Personopplysning", value = RestEndpoints.PERSONOPPLYSNING, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class PersonopplysningController extends ConsumerRestController<PersonopplysningResource> {

    public PersonopplysningController(PersonopplysningService personopplysningService, PersonopplysningLinker linker, FintFilterService oDataFilterService) {
        super(personopplysningService, linker, oDataFilterService);
    }
}

