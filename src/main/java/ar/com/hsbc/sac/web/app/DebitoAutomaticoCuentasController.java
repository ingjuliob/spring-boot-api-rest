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

@RestController
@RequestMapping("/debitoAutomaticoCuentas")
public class DebitoAutomaticoCuentasController {
    DebitoAutoDTO [] debitoAutoDto= new DebitoAutoDTO[2];
    String clientNumber;
    String [] enteSubEnteString= new String[1];
    DebitoAutoDTO [] enteSubEnt= new DebitoAutoDTO[5];
    Map<String, DebitoAutoDTO []> debitAut= new HashMap<>();
    Map<String, String> getClientNum= new HashMap<>();
    Map<String, DebitoAutoDTO[]> entSbStr= new HashMap<>();
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

        enteSubEnt[0]=DebitoAutoDTO.builder().enteSubEnteDesc("88880017-TOTALHOGAR-COBROCUOTA")
                        .longReference("7").ente("88880").subEnte("0017").build();
        enteSubEnt[1]=DebitoAutoDTO.builder().enteSubEnteDesc("88880018-TOTALHOGAR-COBROOTP03")
                        .longReference("7").ente("88880").subEnte("0018").build();
        enteSubEnt[2]=DebitoAutoDTO.builder().enteSubEnteDesc("88880019-TOTALHOGAR-COBROOTP06")
                        .longReference("7").ente("88880").subEnte("019").build();
        enteSubEnt[3]=DebitoAutoDTO.builder().enteSubEnteDesc("88880020-TOTALHOGAR-COBROOTP12")
                        .longReference("7").ente("88880").subEnte("020").build();
        enteSubEnt[4]=DebitoAutoDTO.builder().enteSubEnteDesc("88880021-TOTALHOGAR-COBROOTP15")
                        .longReference("7").ente("88880").subEnte("021").build();

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
                    transacc = Transaccional.builder().debitToAsociate(Arrays.asList(entSbStr.get(documento)))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }
    
}
