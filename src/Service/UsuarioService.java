
package Service;

import Dao.UsuarioDAO;
import Model.Cliente;
import Model.Usuario;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class UsuarioService {
    public void salvar(Usuario usuario) throws ServiceException, SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if (usuario.getNome().isEmpty()) {
            throw new ServiceException(" Campo nome é obrigatorio ");
           
        }
        if (usuario.getSenha().isEmpty()) {
            throw new ServiceException(" Campo senha é obrigatorio ");
        }
        
         
        usuarioDAO.salvar(usuario);
    }
    
    public void alterar(Usuario usuario) throws ServiceException, SQLException{
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if (usuario.getNome().isEmpty()) {
            throw new ServiceException(" Campo nome é obrigatorio ");
        }
        if (usuario.getSenha().isEmpty()) {
            throw new ServiceException(" Campo senha é obrigatorio ");
        }
        
         
        usuarioDAO.alterar(usuario);
               
    }
    
    
}
