package ar.com.hsbc.sac.web.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hsbcRewards")
public class HsbcRewardsController {
    HsbcRewardsDTO hsbcRewDto = null;
    Map <String,HsbcRewardsDTO> hsbcRewMap = new HashMap<>();
    private void setRewards(){
        this.hsbcRewDto= HsbcRewardsDTO.builder().respondHsbcRewards("RETAD08 ").hasCreditCard(true)
            .hasDebitCard(false).message("El cliente posee tarjeta de debito").build();
        hsbcRewMap.put("DNI10266305",hsbcRewDto);
    }

    @GetMapping("/getRewardsInfo")
    public ResponseEntity<Transaccional> getRewardsInformation(@RequestParam("operationId") String operationId, @RequestParam("documentNumber") String documentNumber,
    @RequestParam("documentType") String documentType){
        this.setRewards();
        String documento = documentType + documentNumber;
        Transaccional transacc = null;
        if (hsbcRewMap.containsKey(documento)) {
                transacc = Transaccional.builder().hsbcRewardsDTO(hsbcRewMap.get(documento))
                                .header(UtilsController.getSuccessResponse()).build();
        } else {
                transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
        }
        return new ResponseEntity<>(transacc, HttpStatus.OK);

    } 
    
}
