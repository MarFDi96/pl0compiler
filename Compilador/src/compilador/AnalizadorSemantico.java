package compilador;

public class AnalizadorSemantico {

    private final IndicadorDeErrores indicadorDeErrores;
    private final IdentificadorBean[] tabla;

    public AnalizadorSemantico(IndicadorDeErrores indicadorDeErrores) {
        this.indicadorDeErrores = indicadorDeErrores;
        this.tabla = new IdentificadorBean[Constantes.MAX_CANT_IDENT];
    }

    public IdentificadorBean[] getTabla() {
        return tabla;
    }

    public void insertarEnTabla(IdentificadorBean bean, int base, int desplazamiento) {
        /*
        Para insertar en tabla, buscar que NO esté entre base y base+desplazamiento-1 (Scope local)
        */
        if(bean.getTipo().equals(Terminal.CONST) && existeEnTablaInterno(bean, base, base+desplazamiento-1)){
            indicadorDeErrores.mostrar(Errores.CONSTANTE_DUPLICADA, bean.getNombre());
        }else{
            System.out.println("Poniendo en tabla: " + bean);
            tabla[base + desplazamiento] = bean;
        }
    }
    
    private boolean existeEnTablaInterno(IdentificadorBean bean, int inicio, int terminacion){
        int i = terminacion;
        boolean existe = false;
        while(i > inicio){
            IdentificadorBean ib = tabla[i--];
            existe = ib.getNombre().equals(bean.getNombre()) && ib.getTipo().equals(bean.getTipo());
        }
        return existe;
    }
    
    public IdentificadorBean getIdentificador(int posicion){
        IdentificadorBean ib = tabla[posicion];
        return ib;
    }    
    
    public int getIdentificador(String nombre, int base, int desplazamiento){
        IdentificadorBean ib = new IdentificadorBean(nombre, null, 0);
        return existeEnTabla(ib, base, desplazamiento);
    }
    
    public int existeEnTabla(IdentificadorBean bean, int base, int desplazamiento){
        int i = base+desplazamiento-1;
        int posicion = -1;
        while(i >= 0){
            IdentificadorBean ib = tabla[i];
            if(ib != null && ib.getNombre().equals(bean.getNombre())){
                posicion = i;
                break;
            }
            i--;
        }
        return posicion;
    }
    
}
