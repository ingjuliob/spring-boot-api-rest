package ar.com.hsbc.sac.web.app;

import java.util.List;

import ar.com.hsbc.sac.web.model.Authority;
import ar.com.hsbc.sac.web.model.BaseResponse;
import ar.com.hsbc.sac.web.model.CardAccessArrangement;
import ar.com.hsbc.sac.web.model.CardDTO;
import ar.com.hsbc.sac.web.model.Branch;
import ar.com.hsbc.sac.web.model.ClienteExtendidoDTO;
import ar.com.hsbc.sac.web.model.DebitCardDTO;
import ar.com.hsbc.sac.web.model.ResponseMDW;
import ar.com.hsbc.sac.web.model.TipoEmbozo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Transaccional {

    private BaseResponse header;
    private String message;
    private Registration registration;
    private ClienteExtendidoDTO client;
    private ResponseMDW debitosCta;
    private List<Authority> authorities;
    private List<CardAccessArrangement> cardAccArr;
    private List<CardDTO> cards;
    private DebitCardDTO detalleTarjeta;
    private List<TipoEmbozo> embozos;
    private List<Branch> branches;
    private ResponseMDW permisosMaxima;
    private String status;
    private boolean adjuntarArchivos;
    private List<RelacionTipoDocumental> relTipoDocumentalCliente;
    private List<RelacionTipoDocumental> relTipoDocumentalProducto;
    private AttachFileDTO attached;
    private PrintDTO print;
    private  HsbcRewardsDTO hsbcRewardsDTO;
    private List<HistoricoSeguimientoDTO> historicoOCA;
    private List<AccionesPendientesOcaDTO> accPendOca;
    private GenerateStateHistPendingActDTO generateStateHistPendingActDTO;
    private List<DomicilioDTO> changeAddresTipDom;
    private List<DomicilioDTO> changeAddresNovDom;
    private List<DomicilioDTO> changeAddresProv;
    private SolicitudCambioDomicilioDTO solicitudParticular;
    private SolicitudCambioDomicilioDTO solicitudCorrespondencia;
    private List<GeograficDTO> geograficDto;
    private List<MotivoDTO> motivoDto;
    private boolean tieneDebitos, tienePlazoFijo,tienePrestamos, tieneTarjetas, visibilidadGrabar;
    private String errorDebitos,estado;
    private List<CardDTO> tarjetasDeCredito;
    private List<PlazoFijoDTO> listaPlazoFijo;
    private List<DebitoDTO> listaDebitos;
    private List<CuentaDTO> cuentaList;
    private List<PrestamosDTO> listaPrestamos;
    private List<FiliatoriosDTO> docTypeList;
    private List<FiliatoriosDTO> maritalStList;
    private List<FiliatoriosDTO> countryList;
    private FiliationInformationDTO filiationInfoDto;
    private List<DebitoAutoDTO> debitoAutoDto;
    private String clientNumber;
    private List<String> enteSubEnteString;
    private List<DebitoAutoDTO> debitToAsociate;


}