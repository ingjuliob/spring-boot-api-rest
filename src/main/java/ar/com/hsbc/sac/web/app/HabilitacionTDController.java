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

import ar.com.hsbc.sac.web.model.DebitCardDTO;

@RestController
@RequestMapping("/habilitacionTD")
public class HabilitacionTDController {
    private DataWindowsDTO [] dataWindowsDTO= new DataWindowsDTO[18];
    private DataWindowsDTO [] dataWindowsDTO2= new DataWindowsDTO[18];
    private Map<String, DataWindowsDTO[]> obtenerDetalles = new HashMap<>(); 
    private DebitCardDTO debitCardDTO=null;
    private void getCardDet(){
        debitCardDTO= DebitCardDTO.builder().banelcoCardNumber("451761009727033").cardType("Banelco").company("2")
        .docNumber("000000010266305").docType("DNI").embozo("122").habilitation("AC_TAR_PRE").reprint("RE_TAR_PRE").build();
        dataWindowsDTO[0] = DataWindowsDTO.builder().name("Categoria").value("0").build();
        dataWindowsDTO[1] = DataWindowsDTO.builder().name("Extraccion").value("50000").build();
        dataWindowsDTO[2] = DataWindowsDTO.builder().name("Transferencia").value("1750000").build();
        dataWindowsDTO[3] = DataWindowsDTO.builder().name("Compras").value("75000/38000").build();
        dataWindowsDTO[4] = DataWindowsDTO.builder().name("Devol.-.-Pos.").value("3000").build();
        dataWindowsDTO[5] = DataWindowsDTO.builder().name("Transf-.-mismo-.-titular").value("100000").build();
        dataWindowsDTO[6] = DataWindowsDTO.builder().name("Categoria").value("5").build();
        dataWindowsDTO[7] = DataWindowsDTO.builder().name("Extraccion").value("30000").build();
        dataWindowsDTO[8] = DataWindowsDTO.builder().name("Transferencia").value("1050000").build();
        dataWindowsDTO[9] = DataWindowsDTO.builder().name("Compras").value("30000/15000").build();
        dataWindowsDTO[10] = DataWindowsDTO.builder().name("Devol.-.-Pos.").value("10000").build();
        dataWindowsDTO[11] = DataWindowsDTO.builder().name("Transf-.-mismo-.-titular").value("100000").build();
        dataWindowsDTO[12] = DataWindowsDTO.builder().name("Categoria").value("3").build();
        dataWindowsDTO[13] = DataWindowsDTO.builder().name("Extraccion").value("40000").build();
        dataWindowsDTO[14] = DataWindowsDTO.builder().name("Transferencia").value("1400000").build();
        dataWindowsDTO[15] = DataWindowsDTO.builder().name("Compras").value("45000/23000").build();
        dataWindowsDTO[16] = DataWindowsDTO.builder().name("Devol.-.-Pos.").value("20000").build();
        dataWindowsDTO[17] = DataWindowsDTO.builder().name("Transf-.-mismo-.-titular").value("100000").build();
        obtenerDetalles.put("ACTIV_TAR", dataWindowsDTO);

        dataWindowsDTO2[0] = DataWindowsDTO.builder().name("Categoria").value("0").build();
        dataWindowsDTO2[1] = DataWindowsDTO.builder().name("Extraccion").value("50000").build();
        dataWindowsDTO2[2] = DataWindowsDTO.builder().name("Transferencia").value("1750000").build();
        dataWindowsDTO2[3] = DataWindowsDTO.builder().name("Compras").value("75000/38000").build();
        dataWindowsDTO2[4] = DataWindowsDTO.builder().name("Devol.-.-Pos.").value("3000").build();
        dataWindowsDTO2[5] = DataWindowsDTO.builder().name("Transf-.-mismo-.-titular").value("100000").build();
        dataWindowsDTO2[6] = DataWindowsDTO.builder().name("Categoria").value("5").build();
        dataWindowsDTO2[7] = DataWindowsDTO.builder().name("Extraccion").value("30000").build();
        dataWindowsDTO2[8] = DataWindowsDTO.builder().name("Transferencia").value("1050000").build();
        dataWindowsDTO2[9] = DataWindowsDTO.builder().name("Compras").value("30000/15000").build();
        dataWindowsDTO2[10] = DataWindowsDTO.builder().name("Devol.-.-Pos.").value("10000").build();
        dataWindowsDTO2[11] = DataWindowsDTO.builder().name("Transf-.-mismo-.-titular").value("100000").build();
        dataWindowsDTO2[12] = DataWindowsDTO.builder().name("Categoria").value("3").build();
        dataWindowsDTO2[13] = DataWindowsDTO.builder().name("Extraccion").value("40000").build();
        dataWindowsDTO2[14] = DataWindowsDTO.builder().name("Transferencia").value("1400000").build();
        dataWindowsDTO2[15] = DataWindowsDTO.builder().name("Compras").value("45000/23000").build();
        dataWindowsDTO2[16] = DataWindowsDTO.builder().name("Devol.-.-Pos.").value("20000").build();
        dataWindowsDTO2[17] = DataWindowsDTO.builder().name("Transf-.-mismo-.-titular").value("100000").build();
        obtenerDetalles.put("ACT_TAR_PRE", dataWindowsDTO2);
    }
    
    
    @GetMapping("/tarjeta/detalle")
    public ResponseEntity<Transaccional> consultaDetalleTarjeta(@RequestParam("operationId") String operationId,
            @RequestParam("numero") String numero, @RequestParam("categoriaCIS") String categoriaCIS) {
        Transaccional transaccional= null;
        if(numero.equals(debitCardDTO.getBanelcoCardNumber())){
            transaccional.setDetalleTarjeta(debitCardDTO);
        }else{
            transaccional.getDetalleTarjeta().setHabilitation("PRK".equals(categoriaCIS)?"ACT_TAR_PRE":"ACTIV_TAR");
        }

        if(obtenerDetalles.containsKey(transaccional.getDetalleTarjeta().getHabilitation())){
            transaccional = Transaccional.builder().dataWindowsDTO(Arrays.asList(obtenerDetalles.get(transaccional.getDetalleTarjeta().getHabilitation())))
                                        .header(UtilsController.getSuccessResponse()).build();
        }else{
            transaccional = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
        }
        return new ResponseEntity<>(transaccional, HttpStatus.OK);
    }
    
}
