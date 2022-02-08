package ar.com.hsbc.sac.web.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.hsbc.sac.web.model.CardDTO;

@RestController
@RequestMapping("/bajaTC")
public class BajaTcController {
        private CardDTO[] cards = new CardDTO[2];
        private Map<String, CardDTO[]> enquireCard = new HashMap<>();

        private void setCards() {
                cards[0] = CardDTO.builder().numero("4697240000182586").nombres("SSSS           , RRR")
                                .tipoDocumento("DNI").numeroDocumento("10266305").bancoOrigen("3")
                                .tipo("Titular").estado("OPERATIVA")
                                .build();
                cards[1] = CardDTO.builder().numero("4697240001738964").nombres("LOPEZ, RAUL ROGER")
                                .tipoDocumento("DNI").numeroDocumento("10330975").bancoOrigen("3")
                                .tipo("Adicional").estado("OPERATIVA")
                                .build();
                cards[2] = CardDTO.builder().numero("469724000178998").nombres("LACROCE, SEGUNDO ROBERTO")
                                .tipoDocumento("DNI").numeroDocumento("10129290").bancoOrigen("3")
                                .tipo("Adicional").estado("OPERATIVA")
                                .build();
                enquireCard.put("DNI1026630560010001693", cards);
        }

        @GetMapping("/getCards")
        public ResponseEntity<Transaccional> obtenerDetallesTarjeta(@RequestParam("operationId") String operationId,
                        @RequestParam("docType") String docType, @RequestParam("docNum") String docNum,
                        @RequestParam("productCode") String productCode, @RequestParam("productNumber") String productNumber) {
                this.setCards();
                String documento = docType + docNum+ productCode+ productNumber;
                Transaccional transacc = null;
                if (enquireCard.containsKey(documento)) {
                        transacc = Transaccional.builder().cards(Arrays.asList(enquireCard.get(documento)))
                                        .header(UtilsController.getSuccessResponse()).build();
                } else {
                        transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
                }
                return new ResponseEntity<>(transacc, HttpStatus.OK);
        }

}
