/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.ClienteDAO;
import Model.Cliente;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ClienteService {
     private ClienteDAO clienteDAO;

    //Contrutor
    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    public void salvar(Cliente cliente) throws ServiceException {

        if (cliente.getNome().isEmpty()) {
            throw new ServiceException(" Campo nome é obrigatorio ");
        }
        if (cliente.getTelefone().isEmpty()) {
            throw new ServiceException(" Campo telefone é obrigatorio ");
        }
         if (cliente.getEmail().isEmpty()) {
            throw new ServiceException(" Campo Email é obrigatorio ");
        }
         
        clienteDAO.salvar(cliente);
    }

      public Cliente buscarPorId(Integer id) {
        return clienteDAO.buscarPorId(id);
    }

    public void excluir(int id) {
       clienteDAO.excluir(id);
    }

     public List<Cliente> buscarTodos() {
        return (List<Cliente>) clienteDAO.buscarTodos();
    }
}
