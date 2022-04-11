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
@RequestMapping("/altaAdicionales")
public class AltaAdicionaleController {
    private CardDTO[] cards = new CardDTO[2];
    private Map<String, CardDTO[]> enquireCard = new HashMap<>();
    private SolicitudCambioDomicilioDTO solicitudParticular = null;
    private DomicilioDTO [] changeAddresTipDom = new DomicilioDTO[3];
    private DomicilioDTO [] changeAddresNovDom = new DomicilioDTO[4];
    private DomicilioDTO [] changeAddresProv = new DomicilioDTO[26];
    private GeograficDTO [] geograficDto = new GeograficDTO[3];
    private GeograficDTO [] geograficDto1 = new GeograficDTO[1];
    private MotivoDTO [] motivoDto = new MotivoDTO[3];
    private Map<String,SolicitudCambioDomicilioDTO> domiActCorr= new HashMap<>();
    private Map<String,DomicilioDTO[]> tipoDom= new HashMap<>();
    private Map<String,DomicilioDTO[]> novDom= new HashMap<>();
    private Map<String,DomicilioDTO[]> addProv= new HashMap<>();
    private Map<String, GeograficDTO[]> geografic= new HashMap<>();
    private Map<String, MotivoDTO[]> motivDto= new HashMap<>();

    private void setCards() {
            cards[0] = CardDTO.builder().numero("4697240000182586").nombres("SSS, RRR")
                            .tipoDocumento("DNI").numeroDocumento("10266305").bancoOrigen("1")
                            .tipo("Titular").codigoAdmin("7").estado("OPERATIVA").categoria("4").codigoEstado("4")
                            .build();
            cards[1] = CardDTO.builder().numero("5360873721102328").nombres("STEFANOLO ESTELA N")
                            .tipoDocumento("DNI").numeroDocumento("10266305").bancoOrigen("HSBC BANK ARGENTINA")
                            .tipo("Titular").codigoAdmin("7").estado("OPERATIVA").categoria("4").codigoEstado("4")
                            .build();
            enquireCard.put("DNI10266305", cards);

            solicitudParticular = SolicitudCambioDomicilioDTO.builder().entidad("HSBC").administradora("VISA").domicilioActualPais("ARG")
                .domicilioActualProvincia("CIUDAD AUTON. DE BUENOS AIRES").domicilioActualCiudad("CAPITAL FEDERAL").domicilioActualCalle("AV NAZCA")
                .domicilioActualAltura("3103").domicilioActualPiso("3").domicilioActualDepto("C").telefonoActualNro("5022420").domicilioActualStatus(true)
                .domicilioActualCodigoPostal("1417").domicilioParticular("AV NAZCA 3103 Piso 3 Depto C C.P. 1417, CAPITAL FEDERAL, CIUDAD AUTON. DE BUENOS AIRES, Tel: 5022420")
                .domicilioCorrespondenciaStatus(true).build();
                domiActCorr.put("DNI10266305", solicitudParticular);
            
            
    }

    @GetMapping("/tarjetas")
    public ResponseEntity<Transaccional> getCards(@RequestParam("operationId") String operationId,
                    @RequestParam("docType") String docType, @RequestParam("docNum") String docNum,
                    @RequestParam("productCode") String productCode, @RequestParam("productNumber") String productNumber) {
            this.setCards();
            String documento = docType + docNum;
            Transaccional transacc = null;
            if (enquireCard.containsKey(documento)) {
                    transacc = Transaccional.builder().cards(Arrays.asList(enquireCard.get(documento)))
                                    .solicitudParticular(domiActCorr.get(documento))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }
    
}
