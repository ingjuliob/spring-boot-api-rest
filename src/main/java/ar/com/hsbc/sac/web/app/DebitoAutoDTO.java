package ar.com.hsbc.sac.web.app;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DebitoAutoDTO {
    private String claveIdentificacion, numeroReferencia,relacionamiento,estado,numeroCuentaOrigen,
    ente, subEnte, importeString, importe,enteSubEnteDesc,longReference;
    private List<String> enteSubEnteDescripcion; 
    private boolean esTc;
    
}
