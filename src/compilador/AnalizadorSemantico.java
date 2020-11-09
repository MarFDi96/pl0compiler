package compilador;

public class AnalizadorSemantico {
    private final IndicadorDeErrores indicadorDeErrores;
    private final IdentificadorBean[] tabla;
    private int valorVar = 0;

    public AnalizadorSemantico(IndicadorDeErrores indicadorDeErrores) {
        this.indicadorDeErrores = indicadorDeErrores;
        this.tabla = new IdentificadorBean[Constantes.MAX_CANT_IDENT];
    }
    
       
    public int proximoValor() {
        valorVar += 4;
        return valorVar - 4;
    }

    public int getValorVar() {
        return valorVar;
    }
    

    public IdentificadorBean buscar(String nombre, int desde) {
        int i = desde;
        for (i = desde; i >= 0; i--) {
            if (tabla[i].getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                return tabla[i];
            }
        }
        return null;
    }

    public void cargarTabla(int indice, String nombre, Terminal tipo, int valor) {
        IdentificadorBean datosTabla = new IdentificadorBean();
        datosTabla.setNombre(nombre);
        datosTabla.setTipo(tipo);
        datosTabla.setValor(valor);
        this.tabla[indice] = datosTabla;
    }

    public void mostrarTabla(int desplazamiento) {
        for (int i = 0; i <= desplazamiento; i++) {
            System.out.println("nombre: " + this.tabla[i].getNombre());
            System.out.println("tipo: " + this.tabla[i].getTipo());
            System.out.println("valor: " + this.tabla[i].getValor());
            System.out.println("~~~~~");
        }
    }

    public void buscarDuplicado(String nombre, int desde, int hasta) {
        int i = desde;
        for (i = desde; i < hasta; i++) {
            if (nombre.equals(this.tabla[i].getNombre())) {
                indicadorDeErrores.mostrar(16, nombre);
            }
        }
    }

    public void buscarDeclaracion(String nombre, int desde) {
        int i = desde;
        boolean encontro = false;
        for (i = desde; i >= 0; i--) {
            if (nombre.toLowerCase().equals(this.tabla[i].getNombre().toLowerCase())) {
                encontro = true;
            }
        }
        if (encontro == false) {
            indicadorDeErrores.mostrar(17, nombre);
        }
    }

}
