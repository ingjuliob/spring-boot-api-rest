package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricoSeguimientoDTO {
    private String estadoDescripcion, fechaEjecucion, fechaEstadoSolicitud, motivo, motivoDescripcion, respuesta, usuario;
    
}
