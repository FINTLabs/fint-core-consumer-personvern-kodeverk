package no.fintlabs.core.consumer.config;

import com.google.common.collect.ImmutableMap;
import no.fint.model.personvern.kodeverk.Behandlingsgrunnlag;
import no.fint.model.personvern.kodeverk.Personopplysning;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
                .put(Behandlingsgrunnlag.class.getName(), contextPath + RestEndpoints.BEHANDLINGSGRUNNLAG)
                .put(Personopplysning.class.getName(), contextPath + RestEndpoints.PERSONOPPLYSNING)
            /* .put(TODO,TODO) */
            .build();
    }

}
