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
@RequestMapping("/cierreCuenta")
public class CierreCuentaController {
    private DebitoDTO[] listaDebitos = new DebitoDTO[2];
    private Map<String, DebitoDTO[]> obtenerDebitos = new HashMap<>();
    private PrestamosDTO [] listaPrestamos = new PrestamosDTO[2];
    private CardDTO [] tarjetasDeCredito = new CardDTO[2];
    private PlazoFijoDTO [] listaPlazoFijo = new PlazoFijoDTO[2];
    private CuentaDTO [] cuentaList = new CuentaDTO[2];
    private Map<String,CuentaDTO[]> obtenerCuenta= new HashMap<>();
    private Map<String,PrestamosDTO[]> obtenerPrestamos= new HashMap<>();
    private Map<String,CardDTO[]> obtenerTC= new HashMap<>();
    private Map<String,PlazoFijoDTO[]> obtenerPlazofijo= new HashMap<>();
    private boolean tieneDebitos = false;
    private boolean tienePlazoFijo = false;
    private boolean tienePrestamos = false;
    private boolean tieneTarjetas = false;
    private String errorDebitos = "0";
    private boolean visibilidadGrabar = false;
    private String estado ="Estado de la cuenta: NORMAL";
    

    private void setCards() {
        if(tieneTarjetas){
            tarjetasDeCredito[0] = CardDTO.builder().numero("4697240000182586").tipo("6").categoria("Visa")
                                .cuentAsociadaNumero("324242342342324423").build();
            tarjetasDeCredito[1] = CardDTO.builder().numero("5360873721102328").tipo("7").categoria("4")
                                .cuentAsociadaNumero("4534534534543534543").build();
        }
        obtenerTC.put("DNI10266305", tarjetasDeCredito);
        if(tienePlazoFijo){
            listaPlazoFijo[0] = PlazoFijoDTO.builder().numeroCertificado("2344982094820394").tasa("20")
                .montoCobrar("21323213213").fechaVencimiento("2023/02/08").fechaComposicion("2020/03/19")
                .monto("2343243443").build();
            listaPlazoFijo[1] = PlazoFijoDTO.builder().numeroCertificado("2344982094820394").tasa("20")
                .montoCobrar("21323213213").fechaVencimiento("2023/02/08").fechaComposicion("2020/03/19")
                .monto("2343243443").build();
        }
        obtenerPlazofijo.put("DNI10266305", listaPlazoFijo);   
        if(tienePrestamos){
            listaPrestamos[0] = PrestamosDTO.builder().tipo("tipo prestamo").numero("12312321323")
                .montoOtorgado("232343243243").estado("Relacionar a Cta").fechaVencimiento("2023/10/11")
                .build();
        }
        obtenerPrestamos.put("DNI10266305", listaPrestamos);
        if(tieneDebitos){
            listaDebitos[0] = DebitoDTO.builder().enteSubEnteDescripcion("Ente subEnte descripcion")
                .claveIdentificacion("Numero Cuit").numeroReferencia("12313132131").relacionamiento("Baja")
                .estado("0").esTc(false).build();
        }
        obtenerDebitos.put("DNI10266305", listaDebitos);

        cuentaList[0] = CuentaDTO.builder().numero("3003339788").tipo("0").denominacion("CUENTA CORRIENTE $")
            .ajuste("CC $").build();
        cuentaList[1] = CuentaDTO.builder().numero("3006507476").tipo("2").denominacion("CAJA AHORROS $")
            .ajuste("CA $").build();
        obtenerCuenta.put("DNI10266305", cuentaList);
            
    }

    @GetMapping("/obtenerCuentas")
    public ResponseEntity<Transaccional> obtenerCuentas(@RequestParam("operationId") String operationId,
                    @RequestParam("docType") String docType, @RequestParam("docNum") String docNum,
                    @RequestParam("productCode") String productCode, @RequestParam("productNumber") String productNumber) {
            this.setCards();
            String documento = docType + docNum;
            Transaccional transacc = null;
            System.out.println("inicia el servicio");
            if (obtenerCuenta.containsKey(documento)) {
                    transacc = Transaccional.builder().tieneDebitos(tieneDebitos).tienePlazoFijo(tienePlazoFijo).tienePrestamos(tienePrestamos)
                                    .tieneTarjetas(tieneTarjetas).visibilidadGrabar(visibilidadGrabar).errorDebitos(errorDebitos).estado(estado)
                                    .tarjetasDeCredito(Arrays.asList(obtenerTC.get(documento))).listaPlazoFijo(Arrays.asList(obtenerPlazofijo.get(documento)))
                                    .listaDebitos(Arrays.asList(obtenerDebitos.get(documento))).cuentaList(Arrays.asList(obtenerCuenta.get(documento)))
                                    .header(UtilsController.getSuccessResponse()).build();

            } else {
                    transacc = Transaccional.builder().header(UtilsController.getNotFoundResponse()).build();
            }
            return new ResponseEntity<>(transacc, HttpStatus.OK);
    }

       
}
