package Dao;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Connection con;

    //construtor
    public UsuarioDAO() {
        con = ConexaoUtil.getConnection();
    }

    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(\n"
                + "             nome, senha)\n"
                + "    VALUES (?, ?);";

        PreparedStatement preparador = con.prepareStatement(sql);

        preparador.setString(1, usuario.getNome());
        preparador.setString(2, usuario.getSenha());

        preparador.execute();
        preparador.close();

        System.out.println("\n\nSalvou usuario");
    }

    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "select * from usuario";

        PreparedStatement preparador = con.prepareStatement(sql);
        ResultSet consulta = preparador.executeQuery();

        while (consulta.next()) {
            Usuario usu = new Usuario();
            
            usu.setId(consulta.getInt("idusuario"));
            usu.setNome(consulta.getString("nome"));
            usu.setSenha(consulta.getString("senha"));

            usuarios.add(usu);
        }

        return usuarios;
    }

    public Usuario buscarPorId(int id) throws SQLException {
        Usuario usu = new Usuario();

        String sql = "select * from usuario where idusuario=?";
        
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet consulta = pre.executeQuery();

        consulta.next();
        usu.setNome(consulta.getString("nome"));
        usu.setSenha(consulta.getString("senha"));

        return usu;
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM usuario\n"
                + " WHERE idusuario=?;";

        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        pre.execute();
        pre.close();

    }

    public void alterar(Usuario usuario) throws SQLException {

        String sql = "UPDATE usuario\n"
                + "   SET nome=?, senha=?\n"
                + " WHERE idusuario=?;";
           
           PreparedStatement preparador = con.prepareStatement(sql);
          
           
            preparador.setString(1, usuario.getNome());
            preparador.setString(2, usuario.getSenha());
            preparador.setInt(3, usuario.getId());
            
            
            preparador.execute();
            preparador.close();
          
      
    }
    
    public boolean autentica(Usuario usu) throws SQLException{
         boolean pode=false;
         String sql = "select nome, senha from usuario where nome=? and senha=?";
         PreparedStatement pre = con.prepareStatement(sql);
         pre.setString(1, usu.getNome());
         pre.setString(2, usu.getSenha());
        
         ResultSet cons = pre.executeQuery();
         
         if(cons.next()){
             pode = true;
             
         }
         
         return pode;
    }

}
