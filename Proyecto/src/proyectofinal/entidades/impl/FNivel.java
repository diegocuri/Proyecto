
package proyectofinal.entidades.impl;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import java.io.Serializable;
import java.util.ArrayList;
import accesodatos.Parametro;
import java.sql.SQLException;
import proyectofinal.entidades.Nivel;

/**
 *
 * @author franc
 */
public class FNivel implements Serializable {

    public static boolean Insertar(Nivel nivel) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.finsertar_nivel(?,?,?,?,?,?)";
            
            
            lstP.add(new Parametro(1, nivel.getCodigo()));
            lstP.add(new Parametro(2, nivel.getCodigo_sicoa()));
            lstP.add(new Parametro(3, nivel.getNombre()));
            lstP.add(new Parametro(4, nivel.getParalelo()));
            lstP.add(new Parametro(5, nivel.getModalidad()));
            lstP.add(new Parametro(6, nivel.getCodigo_escuela()));
            
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

    public static ArrayList<Nivel> llenarNiveles(ConjuntoResultado rs) throws Exception {
        ArrayList<Nivel> lst = new ArrayList<Nivel>();
        Nivel nivel = null;
        try {
            while (rs.next()) {
                nivel = new Nivel(rs.getInt("pcodigo"), rs.getInt("pcodigo_sicoa"), rs.getString("pnombre"), rs.getString("pparalelo"), rs.getString("pmodalidad"), rs.getInt("pcodigo_escuela"));
                lst.add(nivel);
            }
        } catch (Exception e) {
            lst.clear();
            throw e;
        }
        return lst;
    }

    public static ArrayList<Nivel> ObtenerNiveles() throws Exception {
        ArrayList<Nivel> lst = new ArrayList<Nivel>();
        try {
            String sql = "select * from actividades.fc_obtener_nivel()";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarNiveles(rs);
            rs = null;

        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static Nivel ObtenerNivelDadoCodigo(int codigo) throws Exception {
        Nivel lst;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.fc_obtener_nivel_dado_codigo(?)";
            lstP.add(new Parametro(1, codigo));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstP);
            lst = new Nivel();
            lst = llenarNiveles(rs).get(0);
            rs = null;
        } catch (SQLException exConec) {
            throw new Exception(exConec.getMessage());
        }
        return lst;
    }

    public static boolean actualizar(Nivel nivel) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.factualiza_nivel(?,?,?,?,?,?)";
            lstP.add(new Parametro(1, nivel.getCodigo()));
            lstP.add(new Parametro(2, nivel.getCodigo_sicoa()));
            lstP.add(new Parametro(3, nivel.getNombre()));
            lstP.add(new Parametro(4, nivel.getParalelo()));
            lstP.add(new Parametro(5, nivel.getModalidad()));
            lstP.add(new Parametro(6, nivel.getCodigo_escuela()));
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

    public static boolean eliminar(Nivel nivel) throws Exception {
        boolean eje = false;
        try {
            ArrayList<Parametro> lstP = new ArrayList<Parametro>();
            String sql = "select * from actividades.felimina_nivel(?)";
            lstP.add(new Parametro(1, nivel.getCodigo()));
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
