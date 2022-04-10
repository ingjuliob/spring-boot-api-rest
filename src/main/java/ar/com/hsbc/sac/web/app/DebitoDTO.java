package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DebitoDTO {
    String enteSubEnteDescripcion,claveIdentificacion,numeroReferencia,relacionamiento, estado;
    boolean esTc;
}
