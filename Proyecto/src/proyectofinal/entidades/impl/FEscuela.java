
package proyectofinal.entidades.impl;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import java.io.Serializable;
import java.util.ArrayList;
import accesodatos.Parametro;
import java.sql.SQLException;
import proyectofinal.entidades.Escuela;

/**
 *
 * @author franc
 */
public class FEscuela implements Serializable {

    public static boolean Insertar(Escuela escuela) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.finsertar_escuela(?,?,?,?,?)";
            lstP.add(new Parametro(1, escuela.getCodigo()));
            lstP.add(new Parametro(2, escuela.getCodigo_facultad()));
            lstP.add(new Parametro(3, escuela.getNombre()));
            lstP.add(new Parametro(4, escuela.getDescripcion()));
            lstP.add(new Parametro(5, escuela.getCodigo_sicoa()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                eje = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return eje;
    }

    public static ArrayList<Escuela> llenarEscuelaes(ConjuntoResultado rs) throws Exception {
        ArrayList<Escuela> lst = new ArrayList<Escuela>();
        Escuela escuela = null;
        try {
            while (rs.next()) {
                escuela = new Escuela(rs.getInt("pcodigo"), rs.getInt("pcodigofacultad"), rs.getString("pnombre"), rs.getString("pdescripcion"), rs.getInt("pcodigo_sicoa"));
                lst.add(escuela);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<Escuela> ObtenerEscuelaes() throws Exception {
        ArrayList<Escuela> lst = new ArrayList<Escuela>();
        try {
            String sql = "select * from actividades.fc_obtener_escuela()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarEscuelaes(rs);
            rs = null;

        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static Escuela ObtenerEscuelaDadoCodigo(int codigo) throws Exception {
        Escuela lst;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.fc_obtener_escuela_dado_codigo(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = new Escuela();
            lst = llenarEscuelaes(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static boolean actualizar(Escuela escuela) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.factualiza_escuela(?,?,?,?,?)";
            lstP.add(new Parametro(1, escuela.getCodigo()));
            lstP.add(new Parametro(2, escuela.getCodigo_facultad()));
            lstP.add(new Parametro(3, escuela.getNombre()));
            lstP.add(new Parametro(4, escuela.getDescripcion()));
            lstP.add(new Parametro(5, escuela.getCodigo_sicoa()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                eje = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return eje;
    }

    public static boolean eliminar(Escuela escuela) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.felimina_escuela(?)";
            lstP.add(new Parametro(1, escuela.getCodigo()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            while (rs.next()) {
                if (rs.getString(0).equals("true"));
                eje = true;
            }
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return eje;
    }
}
