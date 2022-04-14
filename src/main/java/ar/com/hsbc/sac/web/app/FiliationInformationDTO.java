package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FiliationInformationDTO {
    private String nombre,apellido, fechaNacimiento, cuit, sexoDescripcion;
}
