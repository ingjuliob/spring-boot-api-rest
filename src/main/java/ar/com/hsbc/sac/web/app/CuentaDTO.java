package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuentaDTO {
    String numero, tipo, denominacion, ajuste;
}
