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
    private FiliatoriosDTO [] docTypeList = new FiliatoriosDTO[5];
    private FiliatoriosDTO [] maritalStList = new FiliatoriosDTO[6];
    private FiliatoriosDTO [] countryList = new FiliatoriosDTO[5];
    private Map<String, FiliatoriosDTO []> mapDoTyL= new HashMap<>();
    private Map<String, FiliatoriosDTO []> mapMarStL= new HashMap<>();
    private Map<String, FiliatoriosDTO []> mapCounL= new HashMap<>();

    private Map<String, CardDTO[]> enquireCard = new HashMap<>();
    private SolicitudCambioDomicilioDTO solicitudParticular = null;
    private Map<String,SolicitudCambioDomicilioDTO> domiActCorr= new HashMap<>();
    private FiliationInformationDTO filiationInformationDTO=null;
    private Map<String, FiliationInformationDTO> mapFiliation= new HashMap<>();
    

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

        docTypeList[0]=FiliatoriosDTO.builder().codigo("DNI").descripcion("DOCUMENTO NACIONAL DE INDENTIDAD").build();
        docTypeList[1]=FiliatoriosDTO.builder().codigo("LC").descripcion("LIBRETA CIVICA").build();
        docTypeList[2]=FiliatoriosDTO.builder().codigo("LE").descripcion("LIBRETA DE ENROLAMIENTO").build();
        docTypeList[3]=FiliatoriosDTO.builder().codigo("NT").descripcion("NEW ADINTAR II").build();
        docTypeList[4]=FiliatoriosDTO.builder().codigo("PA").descripcion("PASAPORTE").build();
        mapDoTyL.put("DNI10266305", docTypeList);

        maritalStList[0]=FiliatoriosDTO.builder().codigo("0").descripcion("OTRO").build();
        maritalStList[1]=FiliatoriosDTO.builder().codigo("1").descripcion("SOLTERO/A").build();
        maritalStList[2]=FiliatoriosDTO.builder().codigo("2").descripcion("CASADO/A").build();
        maritalStList[3]=FiliatoriosDTO.builder().codigo("3").descripcion("VIUDO/A").build();
        maritalStList[4]=FiliatoriosDTO.builder().codigo("4").descripcion("SEPARADO/A").build();
        maritalStList[5]=FiliatoriosDTO.builder().codigo("5").descripcion("DIVORCIADO").build();
        mapMarStL.put("DNI10266305", maritalStList);

        countryList[0]=FiliatoriosDTO.builder().codigo("ALA").descripcion("Alan Islands").build();
        countryList[1]=FiliatoriosDTO.builder().codigo("AND").descripcion("ANDORRA").build();
        countryList[2]=FiliatoriosDTO.builder().codigo("AGO").descripcion("ANGOLA").build();
        countryList[3]=FiliatoriosDTO.builder().codigo("AIA").descripcion("ANGUILLA").build();
        countryList[4]=FiliatoriosDTO.builder().codigo("ATA").descripcion("ANTARTIDA").build();
        mapCounL.put("DNI10266305", countryList);

        filiationInformationDTO=FiliationInformationDTO.builder().nombre("DOMINGO EDUARDO")
                .apellido("ORTIZ").fechaNacimiento("16/02/1952").cuit("20102663056")
                .sexoDescripcion("MASCULINO").build();
        mapFiliation.put("DNI10266305", filiationInformationDTO);
            
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
                                    .docTypeList(Arrays.asList(mapDoTyL.get(documento)))
                                    .maritalStList(Arrays.asList(mapMarStL.get(documento)))
                                    .countryList(Arrays.asList(mapCounL.get(documento)))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }

    @GetMapping("/cargDatosAdicionales")
    public ResponseEntity<Transaccional> cargDatosAdicionales(@RequestParam("operationId") String operationId,
                    @RequestParam("docType") String docType, @RequestParam("docNum") String docNum,
                    @RequestParam("productCode") String productCode, @RequestParam("productNumber") String productNumber) {
            this.setCards();
            String documento = docType + docNum;
            Transaccional transacc = null;
            if (enquireCard.containsKey(documento)) {
                    transacc = Transaccional.builder().filiationInfoDto(mapFiliation.get(documento))
                                    .header(UtilsController.getSuccessResponse()).build();
            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }
    
}
