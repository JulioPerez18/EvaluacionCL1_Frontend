package pe.edu.cibertec.evaluacionT1Frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.evaluacionT1Frontend.request.placaRequest;
import pe.edu.cibertec.evaluacionT1Frontend.response.VehiculoResponse;
import pe.edu.cibertec.evaluacionT1Frontend.viewmodel.VehiculoModel;

@Controller
@RequestMapping("/ruedita")
public class RueditaController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/inicio")
    public String inicio(Model model){
        VehiculoModel modelV =new VehiculoModel("00","","","","","","");
        model.addAttribute("vehiculoModel",modelV);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("placa") String placa, Model model){
        if (placa.isEmpty() || !placa.matches("[A-Z]{3}-[0-9]{3}")|| placa.length() != 7) {
            VehiculoModel modelV = new VehiculoModel("99", "Debe ingresar una placa correcta", "", "", "", "", "");
            model.addAttribute("vehiculoModel", modelV);
            return "inicio";
        }

        try {
            String endpoint = "http://localhost:8081/vehiculo/searchVehiculo";
            placaRequest request = new placaRequest(placa);
            VehiculoResponse vR =restTemplate.postForObject(endpoint,request, VehiculoResponse.class);
            assert vR != null;
            if (vR.codigo().equals("00")){
                VehiculoModel modelV =new VehiculoModel("00",placa,vR.vehiculo().getMarca(),vR.vehiculo().getModelo(),String.valueOf(vR.vehiculo().getNroAsientos()),String.valueOf(vR.vehiculo().getNroAsientos()),vR.vehiculo().getColor());
                model.addAttribute("vehiculoModel",modelV);
                return "vehiculo";
            }
            else{
                VehiculoModel modelV =new VehiculoModel("99", vR.mensaje(),"","","","","");
                model.addAttribute("vehiculoModel",modelV);
                return "inicio";
            }
        }catch (Exception ex){
            VehiculoModel modelV =new VehiculoModel("99","Ocurrio un problema inesperado: " + ex.getMessage(),"","","","","");
            model.addAttribute("vehiculoModel",modelV);
            return "inicio";
        }
    }
}
