package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateStateHistPendingActDTO {
    private String providerHist, idPartHist, providerPendAct, idPartPendAct, requestNumber;
}
