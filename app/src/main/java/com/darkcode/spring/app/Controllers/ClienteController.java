package com.darkcode.spring.app.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Anotación para indicar que esta clase es un controlador REST
@RestController
public class ClienteController {

    /**
     * Método para manejar solicitudes GET a la ruta "/cliente".
     * @param tipo Tipo de documento (C para cédula, P para pasaporte)
     * @param numero Número de documento del cliente
     * @return Respuesta HTTP con la información del cliente o un mensaje de error
     */
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
    
            // Verifica si el número de documento es el esperado
            if (!numero.equals("23445322")) {
                // Retorna un error 404 (Not Found) si el número de documento no coincide
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no existe");
            }   
            
            // Si el tipo y número son válidos, crea un objeto 'Cliente' con datos de ejemplo
            Cliente cliente = new Cliente("Nelson", "SegundoNombre", "PrimerApellido", "SegundoApellido", "Telefono", "Direccion", "Ciudad");
            // Retorna la información del cliente con un estado HTTP 200 (OK)
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            // Si ocurre cualquier excepción, retorna un error 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }
}

// Clase que representa la entidad 'Cliente'
class Cliente {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String direccion;
    private String ciudad;

    // Constructor para inicializar todos los campos del cliente
    public Cliente(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String telefono, String direccion, String ciudad) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
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
