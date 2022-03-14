package ar.com.hsbc.sac.web.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.hsbc.sac.web.model.CardDTO;

@RestController
@RequestMapping("/ocaReenvio")
public class OcaController {
    private CardDTO[] creditCards = new CardDTO[4];
    private CardDTO[] debitCards = new CardDTO[2];
    private Map<String, CardDTO[]> enquireCreditCard = new HashMap<>();
    private Map<String, CardDTO[]> enquireDebitCard = new HashMap<>();

    private void setCards() {
        creditCards[0] = CardDTO.builder().numero("4697240000182586").tipoDocumento("DNI")
                            .numeroDocumento("10266305").tipo("Titular").estado("").build();
        creditCards[1] = CardDTO.builder().numero("4697240000182578").nombres("LOPEZ, RAUL ROGER")
                            .tipoDocumento("DNI").numeroDocumento("10330975").bancoOrigen("3")
                            .tipo("Adicional").estado("BAJA").build();
        creditCards[2] = CardDTO.builder().numero("46972400001738964").nombres("LOPEZ, RAUL ROGER")
                            .tipoDocumento("DNI").numeroDocumento("10330975").bancoOrigen("3")
                            .tipo("Adicional").estado("OPERATIVA").build();
        creditCards[3] = CardDTO.builder().numero("4697240001738998").nombres("LACROCE, SEGUNDO ROBERTO")
                            .tipoDocumento("DNI").numeroDocumento("10129290").bancoOrigen("3")
                            .tipo("Adicional").estado("OPERATIVA").build();
            enquireCreditCard.put("DNI10266305", creditCards);

        debitCards[0] = CardDTO.builder().numero("4517610097274025").tipoDocumento("DNI").nombres("BRINGIOTTI, MANUEL")
                            .numeroDocumento("10266305").tipo("Titular").build();
        debitCards[1] = CardDTO.builder().numero("417610097274033").nombres("BRINGIOTTI, MANUEL")
                            .tipoDocumento("DNI").numeroDocumento("10330975")
                            .tipo("Titular").build();
            enquireDebitCard.put("DNI10266305",debitCards);

    }

    @GetMapping("/getCards")
    public ResponseEntity<Transaccional> consultaTarjeta(@RequestParam("operationId") String operationId,
         @RequestParam("productCode")String productCode,@RequestParam("docNum") String docNum,@RequestParam("docType") String docType,
         @RequestParam("productNumber") String productNumber, @RequestParam("nameTitular") String nameTitular) {
            this.setCards();
            String documento = docType + docNum;
            Transaccional transacc = null;
            if("8".equals(productCode) || "B".equals(productCode)){

                    if (enquireDebitCard.containsKey(documento)) {
                            transacc = Transaccional.builder().cards(Arrays.asList(enquireDebitCard.get(documento)))
                                            .header(UtilsController.getSuccessResponse()).build();
                    } else {
                            transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
                    }
                }else{
                    if (enquireCreditCard.containsKey(documento)) {
                        transacc = Transaccional.builder().cards(Arrays.asList(enquireCreditCard.get(documento)))
                                        .header(UtilsController.getSuccessResponse()).build();
                } else {
                        transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
                }
                }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }
}
