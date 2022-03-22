package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccionesPendientesOcaDTO {
    private String descripcionAccion, estado, fechaSolicitud, usuarioSolicitud;
}
