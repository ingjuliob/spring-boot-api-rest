package ar.com.hsbc.sac.web.app;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HsbcRewardsDTO {
    private String respondHsbcRewards, message;
    private boolean hasDebitCard, hasCreditCard;
    
}
