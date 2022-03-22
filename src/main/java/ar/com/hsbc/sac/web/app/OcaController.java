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
    private HistoricoSeguimientoDTO[] historicoOCA = new HistoricoSeguimientoDTO[2];
    private AccionesPendientesOcaDTO [] accPendOca= new AccionesPendientesOcaDTO[2];
    private Map<String, CardDTO[]> enquireCreditCard = new HashMap<>();
    private Map<String, CardDTO[]> enquireDebitCard = new HashMap<>();
    private Map<String, HistoricoSeguimientoDTO[]> histSeg= new HashMap<>();
    private Map<String, AccionesPendientesOcaDTO []> accPenOca = new HashMap<>();
    private GenerateStateHistPendingActDTO genStatHistPenDto = null;
    private Map<String, GenerateStateHistPendingActDTO> genStatHisPen= new HashMap<>();

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

            historicoOCA[0] = HistoricoSeguimientoDTO.builder().estadoDescripcion("Este es la descripcion del estado")
                .fechaEjecucion("21/03/2022").fechaEstadoSolicitud("20/03/2022").motivo("Este es el motivo").motivoDescripcion("Descripcion del motivo")
                .respuesta("Esta es la Respuesta").usuario("usuario").build();
            
            historicoOCA[1] = HistoricoSeguimientoDTO.builder().estadoDescripcion("Este es la descripcion del estado2")
            .fechaEjecucion("10/03/2022").fechaEstadoSolicitud("05/03/2022").motivo("Este es el motivo2").motivoDescripcion("Descripcion del motivo2")
            .respuesta("Esta es la Respuesta2").usuario("usuario2").build();

            histSeg.put("12345678901234564697240000182578",historicoOCA);

            accPendOca[0] = AccionesPendientesOcaDTO.builder().descripcionAccion("Descripcion de la Accion")
                .estado("estado").fechaSolicitud("19/03/2022").usuarioSolicitud("usuarioSolicitud").build();

            
            accPendOca[1] = AccionesPendientesOcaDTO.builder().descripcionAccion("Descripcion de la Accion2")
                .estado("estado2").fechaSolicitud("20/03/2022").usuarioSolicitud("usuarioSolicitud2").build();
            
            accPenOca.put("12345678901234564697240000182578", accPendOca);

            genStatHistPenDto =  GenerateStateHistPendingActDTO.builder().providerHist("Proveedor Historico")
                .idPartHist("idPartHist").providerPendAct("providerPendAct").idPartPendAct("idPartPendAct")
                .requestNumber("329847938749328").build();
            
            genStatHisPen.put("12345678901234564697240000182578", genStatHistPenDto);
        

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

    @GetMapping("/getStateHistoryPendingAct")
    public ResponseEntity<Transaccional> getStateHistoryPendingAct(@RequestParam("operationId") String operationId,
         @RequestParam("secuencia")String secuencia,@RequestParam("numero") String numero,@RequestParam("companyCode") String companyCode,
         @RequestParam("causeCode") String causeCode) {
            this.setCards();
            String documento = secuencia + numero;
            Transaccional transacc = null;
                    if (histSeg.containsKey(documento) && accPenOca.containsKey(documento) && genStatHisPen.containsKey(documento)) {
                            transacc = Transaccional.builder().historicoOCA(Arrays.asList(histSeg.get(documento)))
                                            .accPendOca(Arrays.asList(accPenOca.get(documento)))
                                            .generateStateHistPendingActDTO(genStatHisPen.get(documento))
                                            .header(UtilsController.getSuccessResponse()).build();
                    } else {
                            transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
                    }
            
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }
}

