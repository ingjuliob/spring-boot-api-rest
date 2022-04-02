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
    private DomicilioDTO [] changeAddresTipDom = new DomicilioDTO[3];
    private DomicilioDTO [] changeAddresNovDom = new DomicilioDTO[4];
    private DomicilioDTO [] changeAddresProv = new DomicilioDTO[26];
    private GeograficDTO [] geograficDto = new GeograficDTO[3];
    private Map<String,SolicitudCambioDomicilioDTO> domiActCorr= new HashMap<>();
    private Map<String,DomicilioDTO[]> tipoDom= new HashMap<>();
    private Map<String,DomicilioDTO[]> novDom= new HashMap<>();
    private Map<String,DomicilioDTO[]> addProv= new HashMap<>();
    private Map<String, GeograficDTO[]> geografic= new HashMap<>();

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
            
            changeAddresTipDom[0]=DomicilioDTO.builder().staCde("1").ExpirDtText("PARTICULAR").build();
            changeAddresTipDom[1]=DomicilioDTO.builder().staCde("6").ExpirDtText("CORRESPONDENCIA").build();
            changeAddresTipDom[2]=DomicilioDTO.builder().staCde("7").ExpirDtText("PARTICULAR Y CORRESPONDENCIA").build();
            tipoDom.put("DNI10266305", changeAddresTipDom);

            changeAddresNovDom[0]=DomicilioDTO.builder().staCde("***").ExpirDtText("BLANQUEO").build();
            changeAddresNovDom[1]=DomicilioDTO.builder().staCde("DTO").ExpirDtText("DEPARTAMENTO").build();
            changeAddresNovDom[2]=DomicilioDTO.builder().staCde("LOC").ExpirDtText("LOCAL").build();
            changeAddresNovDom[3]=DomicilioDTO.builder().staCde("OFI").ExpirDtText("OFICINA").build();
            novDom.put("DNI10266305", changeAddresNovDom);

            changeAddresProv[0]=DomicilioDTO.builder().staCde("2").ExpirDtText("BUENOS AIRES").build();
            changeAddresProv[1]=DomicilioDTO.builder().staCde("3").ExpirDtText("CATAMARCA").build();
            changeAddresProv[2]=DomicilioDTO.builder().staCde("6").ExpirDtText("CHACO").build();
            changeAddresProv[3]=DomicilioDTO.builder().staCde("7").ExpirDtText("CHUBUT").build();
            changeAddresProv[4]=DomicilioDTO.builder().staCde("1").ExpirDtText("CIUDAD AUTONOMA DE BUENOS AIRES").build();
            changeAddresProv[5]=DomicilioDTO.builder().staCde("4").ExpirDtText("CORDOBA").build();
            changeAddresProv[6]=DomicilioDTO.builder().staCde("5").ExpirDtText("CORRIENTES").build();
            changeAddresProv[7]=DomicilioDTO.builder().staCde("8").ExpirDtText("ENTRE RIOS").build();
            changeAddresProv[8]=DomicilioDTO.builder().staCde("9").ExpirDtText("FORMOSA").build();
            changeAddresProv[9]=DomicilioDTO.builder().staCde("10").ExpirDtText("JUJUY").build();
            changeAddresProv[10]=DomicilioDTO.builder().staCde("11").ExpirDtText("LA PAMPA").build();
            changeAddresProv[11]=DomicilioDTO.builder().staCde("12").ExpirDtText("LA RIOJA").build();
            changeAddresProv[12]=DomicilioDTO.builder().staCde("13").ExpirDtText("MENDOZA").build();
            changeAddresProv[13]=DomicilioDTO.builder().staCde("14").ExpirDtText("MISIONES").build();
            changeAddresProv[14]=DomicilioDTO.builder().staCde("15").ExpirDtText("NEUQUEN").build();
            changeAddresProv[15]=DomicilioDTO.builder().staCde("27").ExpirDtText("NEW-ADINTAR").build();
            changeAddresProv[16]=DomicilioDTO.builder().staCde("25").ExpirDtText("NUEVA PROVINCIA").build();
            changeAddresProv[17]=DomicilioDTO.builder().staCde("16").ExpirDtText("RIO NEGRO").build();
            changeAddresProv[18]=DomicilioDTO.builder().staCde("17").ExpirDtText("SALTA").build();
            changeAddresProv[19]=DomicilioDTO.builder().staCde("18").ExpirDtText("SAN JUAN").build();
            changeAddresProv[20]=DomicilioDTO.builder().staCde("19").ExpirDtText("SAN LUIS").build();
            changeAddresProv[21]=DomicilioDTO.builder().staCde("20").ExpirDtText("SANTA CRUZ").build();
            changeAddresProv[22]=DomicilioDTO.builder().staCde("21").ExpirDtText("SANTA FE").build();
            changeAddresProv[23]=DomicilioDTO.builder().staCde("22").ExpirDtText("SANTIAGO DEL ESTERO").build();
            changeAddresProv[24]=DomicilioDTO.builder().staCde("24").ExpirDtText("TIERRA DEL FUEGO").build();
            changeAddresProv[25]=DomicilioDTO.builder().staCde("23").ExpirDtText("TUCUMAN").build();
            addProv.put("DNI10266305", changeAddresProv);  
            
            geograficDto[0]= GeograficDTO.builder().code("B2170").description("GERLI - BS. AS.").build();
            geograficDto[1]= GeograficDTO.builder().code("B2240").description("LANUS ESTE - BS. AS.").build();
            geograficDto[2]= GeograficDTO.builder().code("B2241").description("LANUS OESTE - BS. AS.").build();
                geografic.put("1824", geograficDto);

            
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
                                    .solicitud(domiActCorr.get(documento)).changeAddresTipDom(Arrays.asList(tipoDom.get(documento)))
                                    .changeAddresNovDom(Arrays.asList(novDom.get(documento))).changeAddresProv(Arrays.asList(addProv.get(documento)))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }

    @GetMapping("/getGeograficCode")
    public ResponseEntity<Transaccional> getGeograficCode(@RequestParam("operationId") String operationId,
                    @RequestParam("postalCode") String postalCode) {
            this.setCards();
            String documento = postalCode;
            Transaccional transacc = null;
            if (geografic.containsKey(documento)) {
                    transacc = Transaccional.builder().geograficDto(Arrays.asList(geografic.get(documento)))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }

}
