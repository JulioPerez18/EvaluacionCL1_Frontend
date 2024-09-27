package pe.edu.cibertec.evaluacionT1Frontend.response;

import pe.edu.cibertec.evaluacionT1Frontend.clases.Vehiculo;

public record VehiculoResponse(String codigo, String mensaje, Vehiculo vehiculo) {
}
