package ar.com.hsbc.sac.web.app;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debitoAutomaticoCuentas")
public class DebitoAutomaticoCuentasController {
    DebitoAutoDTO [] debitoAutoDto= new DebitoAutoDTO[2];
    String clientNumber;
    String [] enteSubEnteString= new String[1];
    String [] enteSubEnt= new String[5];
    Map<String, DebitoAutoDTO []> debitAut= new HashMap<>();
    Map<String, String> getClientNum= new HashMap<>();
    Map<String, String[]> entSbStr= new HashMap<>();
    private void setCards() {
        
        enteSubEnteString[0]="00104000-ARGENCARD-TARJETAS";
        debitoAutoDto[0] = DebitoAutoDTO.builder().claveIdentificacion("30522211563")
                        .numeroReferencia("0310332392808").relacionamiento("057")
                        .esTc(false).enteSubEnteDescripcion(Arrays.asList(enteSubEnteString))
                        .numeroCuentaOrigen("0571167290").ente("00104").subEnte("000")
                        .importeString("00999999999999999").importe("999999990000000").build();
        debitoAutoDto[1] = DebitoAutoDTO.builder().claveIdentificacion("30522211563")
                        .numeroReferencia("0310003323928").relacionamiento("057")
                        .esTc(false).enteSubEnteDescripcion(Arrays.asList(enteSubEnteString))
                        .numeroCuentaOrigen("0571167290").ente("00104").subEnte("000")
                        .importeString("00999999999999999").importe("999999990000000").build();
            debitAut.put("DNI10266305", debitoAutoDto);
            getClientNum.put("DNI10266305", clientNumber);

        enteSubEnt[0]="88880017-TOTALHOGAR-COBROCUOTA";
        enteSubEnt[1]="88880017-TOTALHOGAR-COBROOTP03";
        enteSubEnt[2]="88880017-TOTALHOGAR-COBROOTP06";
        enteSubEnt[3]="88880017-TOTALHOGAR-COBROOTP12";
        enteSubEnt[4]="88880017-TOTALHOGAR-COBROOTP15";

        entSbStr.put("20108496380", enteSubEnt);
        
    }

    @GetMapping("/debitos")
    public ResponseEntity<Transaccional> consultaListaDebitos(@RequestParam("operationId") String operationId,
                    @RequestParam("docType") String docType, @RequestParam("docNum") String docNum,
                    @RequestParam("productCode") String productCode, @RequestParam("productNumber") String productNumber) {
            this.setCards();
            String documento = docType + docNum;
            Transaccional transacc = null;
            if (debitAut.containsKey(documento)) {
                    transacc = Transaccional.builder().debitoAutoDto(Arrays.asList(debitAut.get(documento)))
                                    .clientNumber(getClientNum.get(documento))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }

    @GetMapping("/getEnteSubEnte")
    public ResponseEntity<Transaccional> getEnteSubEnte(@RequestParam("operationId") String operationId,
                    @RequestParam("cuit") String cuit) {
            this.setCards();
            String documento = cuit;
            Transaccional transacc = null;
            if (entSbStr.containsKey(documento)) {
                    transacc = Transaccional.builder().enteSubEnteString(Arrays.asList(entSbStr.get(documento)))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }
    
}
