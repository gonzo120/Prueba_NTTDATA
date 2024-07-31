package com.darkcode.spring.app.Controllers;

import java.util.ArrayList;
import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;



// Anotación para indicar que esta clase es un controlador REST
@RestController
public class ClienteController {

    /**
     * Método para manejar solicitudes GET a la ruta "/cliente".
     * @param tipo Tipo de documento (C para cédula, P para pasaporte)
     * @param numero Número de documento del cliente
     * @return Respuesta HTTP con la información del cliente o un mensaje de error
     */
     private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
        // Añadir algunos clientes para pruebas
        clientes.add(new Cliente("Nelson", " ", "Gonzalez", "Contreras", "12345678", "Calle 18B #108 34", "bogota", "23445322"));
        clientes.add(new Cliente("Carlos", "Andrés", "Pérez", "Gómez", "987654321", "Calle Falsa 123", "Medellín", "12345678"));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cliente")
    public ResponseEntity<?> getCliente(@RequestParam String tipo, @RequestParam String numero) {
        try {
            // Verifica si el parámetro 'numero' contiene caracteres no numéricos
            if (numero.matches(".*[^0-9].*")) {
                // Lanza una excepción si el número contiene caracteres especiales
                throw new Exception("El número de documento no es válido");
            }
            
            // Verifica si el tipo de documento es válido (solo 'C' o 'P')
            if (!tipo.equals("C") && !tipo.equals("P")) {
                // Retorna un error 400 (Bad Request) si el tipo de documento no es válido
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de documento no es válido");
            }
    
            // Busca el cliente en la lista de clientes
            for (Cliente cliente : clientes) {
                if (cliente.getCedula().equals(numero)) {
                    return new ResponseEntity<>(cliente, HttpStatus.OK);
                }
            }
            
            // Si no se encuentra el cliente, retorna un error 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no existe");
        } catch (Exception e) {
            // Si ocurre cualquier excepción, retorna un error 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }
    

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/cliente")
    public ResponseEntity<Cliente> updateCliente(@RequestParam String numero, @RequestBody Cliente updatedCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(numero)) {
                if (updatedCliente.getPrimerNombre() != null) {
                    cliente.setPrimerNombre(updatedCliente.getPrimerNombre());
                }
                if (updatedCliente.getSegundoNombre() != null) {
                    cliente.setSegundoNombre(updatedCliente.getSegundoNombre());
                }
                if (updatedCliente.getPrimerApellido() != null) {
                    cliente.setPrimerApellido(updatedCliente.getPrimerApellido());
                }
                if (updatedCliente.getSegundoApellido() != null) {
                    cliente.setSegundoApellido(updatedCliente.getSegundoApellido());
                }
                if (updatedCliente.getDireccion() != null) {
                    cliente.setDireccion(updatedCliente.getDireccion());
                }
                if (updatedCliente.getCiudad() != null) {
                    cliente.setCiudad(updatedCliente.getCiudad());
                }
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

// Clase que representa la entidad 'Cliente'
class Cliente {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String cedula;
    private String direccion;
    private String ciudad;

    // Constructor para inicializar todos los campos del cliente
    public Cliente(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String telefono, String direccion, String ciudad, String cedula) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.cedula = cedula;
    }

    // Métodos getter y setter para acceder y modificar los campos del cliente

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
