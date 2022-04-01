package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DomicilioDTO {
    private String staCde, ExpirDtText;
    
}
