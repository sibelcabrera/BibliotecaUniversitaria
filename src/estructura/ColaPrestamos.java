package estructura;

import entidad.SolicitudPrestamo;

public class ColaPrestamos {

    // 1. TODO: crear la clase interna Nodo (dato + referencia al siguiente)
    private class Nodo {
        SolicitudPrestamo informacion;
        Nodo siguiente;

        public Nodo(SolicitudPrestamo informacion) {
            this.informacion = informacion;
            this.siguiente = null;
        }
    }

    // 2. TODO: declarar referencias a frente, final y tamaño
    private Nodo frente;
    private Nodo fin;
    private int tamano;

    // 3. TODO: constructor
    public ColaPrestamos() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    // 4. TODO: método estaVacia()
    public boolean estaVacia() {
        return frente == null;
    }

    // 5. TODO: método tamano()
    public int tamano() {
        return tamano;
    }

    // 6. TODO: método encolar(SolicitudPrestamo solicitud)
    public void encolar(SolicitudPrestamo solicitud) {
        Nodo nuevoNodo = new Nodo(solicitud);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        tamano++;
    }

    // 7. TODO: método desencolar() -> debe validar que la cola no esté vacía
    public SolicitudPrestamo desencolar() {
        if (estaVacia()) {
            return null; 
        }
        SolicitudPrestamo informacion = frente.informacion;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null; // Si la cola se quedó vacía, el fin también pasa a ser null
        }
        tamano--;
        return informacion;
    }

    // 8. TODO: método verFrente() -> debe validar que la cola no esté vacía
    public SolicitudPrestamo verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.informacion;
    }

    // 9. TODO: método listarComoTexto() -> recorre la cola y arma un texto
    public String listarComoTexto() {
        if (estaVacia()) {
            return "No hay solicitudes en la cola.";
        }
        StringBuilder sb = new StringBuilder();
        Nodo actual = frente;
        int posicion = 1;
        while (actual != null) {
            sb.append(posicion).append(". ").append(actual.informacion.toString()).append("\n");
            actual = actual.siguiente;
            posicion++;
        }
        return sb.toString();
    }
}
