package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrestamosDTO {
    private String tipo, numero, montoOtorgado, fechaVencimiento, estado;
    
}
