package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MotivoDTO {
    private String codigo, descripcion;
}
