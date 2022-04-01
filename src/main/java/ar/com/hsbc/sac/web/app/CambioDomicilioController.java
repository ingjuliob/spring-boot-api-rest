package ar.com.hsbc.sac.web.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.hsbc.sac.web.model.CardDTO;

@RestController
@RequestMapping("/cambioDomicilioTC")
public class CambioDomicilioController {
    private CardDTO[] cards = new CardDTO[2];
    private Map<String, CardDTO[]> enquireCard = new HashMap<>();
    private SolicitudCambioDomicilioDTO solicitud = null;
    private List<DomicilioDTO> changeAddresTipDom = new ArrayList<DomicilioDTO>();
    private List<DomicilioDTO> changeAddresNovDom = new ArrayList<DomicilioDTO>();
    private List<DomicilioDTO> changeAddresProv = new ArrayList<DomicilioDTO>();
    private Map<String,SolicitudCambioDomicilioDTO> domiActCorr= new HashMap<>();
    private Map<String,List<DomicilioDTO>> tipoDom= new HashMap<>();
    private Map<String,List<DomicilioDTO>> novDom= new HashMap<>();
    private Map<String,List<DomicilioDTO>> addProv= new HashMap<>();

    private void setCards() {
            cards[0] = CardDTO.builder().numero("4697240000182586").nombres("SSS, RRR")
                            .tipoDocumento("DNI").numeroDocumento("10266305").bancoOrigen("1")
                            .tipo("Titular").codigoAdmin("7").codigoEstado("4").categoria("4").estado("OPERATIVA")
                            .build();
            cards[1] = CardDTO.builder().numero("5360873721102328").nombres("STEFANOLO ESTELA N")
                            .tipoDocumento("DNI").numeroDocumento("10266305").bancoOrigen("HSBC BANK ARGENTINA")
                            .tipo("Titular").codigoAdmin("7").codigoEstado("4").categoria("4").estado("OPERATIVA")
                            .build();
            enquireCard.put("DNI10266305", cards);

            solicitud = SolicitudCambioDomicilioDTO.builder().entidad("HSBC").administradora("VISA").domicilioActualPais("ARG")
                .domicilioActualProvincia("CIUDAD AUTON. DE BUENOS AIRES").domicilioActualCiudad("CAPITAL FEDERAL").domicilioActualCalle("AV NAZCA")
                .domicilioActualAltura("3103").domicilioActualPiso("3").domicilioActualDepto("C").telefonoActualNro("5022420").domicilioActualStatus(true)
                .domicilioActualCodigoPostal("1417").domicilioParticular("AV NAZCA 3103 Piso 3 Depto C C.P. 1417, CAPITAL FEDERAL, CIUDAD AUTON. DE BUENOS AIRES, Tel: 5022420")
                .domicilioCorrespondencia("AV NAZCA 3103 Piso 3 Depto C C.P. 1417, CAPITAL FEDERAL, CIUDAD AUTON. DE BUENOS AIRES, Tel: 5022420")
                .domicilioCorrespondenciaStatus(true).build();
                domiActCorr.put("DNI10266305", solicitud);
            
            changeAddresTipDom.add(DomicilioDTO.builder().staCde("1").ExpirDtText("PARTICULAR").build());
            changeAddresTipDom.add(DomicilioDTO.builder().staCde("6").ExpirDtText("CORRESPONDENCIA").build());
            changeAddresTipDom.add(DomicilioDTO.builder().staCde("7").ExpirDtText("PARTICULAR Y CORRESPONDENCIA").build());
            tipoDom.put("DNI10266305", changeAddresTipDom);

            changeAddresNovDom.add(DomicilioDTO.builder().staCde("***").ExpirDtText("BLANQUEO").build());
            changeAddresNovDom.add(DomicilioDTO.builder().staCde("DTO").ExpirDtText("DEPARTAMENTO").build());
            changeAddresNovDom.add(DomicilioDTO.builder().staCde("LOC").ExpirDtText("LOCAL").build());
            changeAddresNovDom.add(DomicilioDTO.builder().staCde("OFI").ExpirDtText("OFICINA").build());
            novDom.put("DNI10266305", changeAddresNovDom);

            changeAddresProv.add(DomicilioDTO.builder().staCde("2").ExpirDtText("BUENOS AIRES").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("3").ExpirDtText("CATAMARCA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("6").ExpirDtText("CHACO").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("7").ExpirDtText("CHUBUT").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("1").ExpirDtText("CIUDAD AUTONOMA DE BUENOS AIRES").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("4").ExpirDtText("CORDOBA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("5").ExpirDtText("CORRIENTES").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("8").ExpirDtText("ENTRE RIOS").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("9").ExpirDtText("FORMOSA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("10").ExpirDtText("JUJUY").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("11").ExpirDtText("LA PAMPA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("12").ExpirDtText("LA RIOJA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("13").ExpirDtText("MENDOZA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("14").ExpirDtText("MISIONES").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("15").ExpirDtText("NEUQUEN").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("27").ExpirDtText("NEW-ADINTAR").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("25").ExpirDtText("NUEVA PROVINCIA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("16").ExpirDtText("RIO NEGRO").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("17").ExpirDtText("SALTA").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("18").ExpirDtText("SAN JUAN").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("19").ExpirDtText("SAN LUIS").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("20").ExpirDtText("SANTA CRUZ").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("21").ExpirDtText("SANTA FE").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("22").ExpirDtText("SANTIAGO DEL ESTERO").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("24").ExpirDtText("TIERRA DEL FUEGO").build());
            changeAddresProv.add(DomicilioDTO.builder().staCde("23").ExpirDtText("TUCUMAN").build());
            addProv.put("DNI10266305", changeAddresProv);            

            
    }

    @GetMapping("/getCardsHome")
    public ResponseEntity<Transaccional> getCards(@RequestParam("operationId") String operationId,
                    @RequestParam("docType") String docType, @RequestParam("docNum") String docNum,
                    @RequestParam("productCode") String productCode, @RequestParam("productNumber") String productNumber) {
            this.setCards();
            String documento = docType + docNum;
            Transaccional transacc = null;
            if (enquireCard.containsKey(documento)) {
                    transacc = Transaccional.builder().cards(Arrays.asList(enquireCard.get(documento)))
                                    .solicitud(domiActCorr.get(documento)).changeAddresTipDom(tipoDom.get(documento))
                                    .changeAddresNovDom(novDom.get(documento)).changeAddresProv(addProv.get(documento))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }

}
