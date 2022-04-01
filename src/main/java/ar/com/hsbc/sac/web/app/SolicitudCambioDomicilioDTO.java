package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitudCambioDomicilioDTO {
    private String entidad, administradora, domicilioActualPais, domicilioActualProvincia, domicilioActualCiudad
        , domicilioActualCalle, domicilioActualAltura, domicilioActualPiso, domicilioActualDepto, telefonoActualNro
        , domicilioActualCodigoPostal, domicilioParticular, domicilioCorrespondencia;
    
    private boolean domicilioActualStatus, domicilioCorrespondenciaStatus;
}
