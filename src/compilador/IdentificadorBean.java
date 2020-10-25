package compilador;

import java.io.Serializable;

public class IdentificadorBean implements Serializable {

    private String nombre;
    private Terminal tipo;
    private int valor;

    public IdentificadorBean() {
    }

    public IdentificadorBean(String nombre, Terminal tipo, int valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Terminal getTipo() {
        return tipo;
    }

    public void setTipo(Terminal tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "{nombre: " + nombre +", tipo: " + tipo + ", valor: " + valor +"}";
    }
    
}
