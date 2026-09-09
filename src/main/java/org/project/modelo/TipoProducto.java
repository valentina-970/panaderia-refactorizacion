package org.project.modelo;

public enum TipoProducto {
    PAN("pan"),
    PASTEL("pastel"),
    GALLETA("galleta"),
    POSTRE("postre");

    private final String codigo;

    TipoProducto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoProducto fromCodigo(String codigo) {
        for (TipoProducto tipo : values()) {
            if (tipo.codigo.equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo no válido: " + codigo);
    }
}
