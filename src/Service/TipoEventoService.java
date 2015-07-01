/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.TipoEventoDAO;
import Model.TipoEvento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TipoEventoService {
    
    private TipoEventoDAO tipoeventoDAO;

    public TipoEventoService() {
        tipoeventoDAO = new TipoEventoDAO();
    }

    public void salvar(TipoEvento tipoevento) throws ServiceException {

        if (tipoevento.getDescricao().isEmpty()) {
            throw new ServiceException(" Campo descricao é obrigatorio ");
        }
        tipoeventoDAO.salvar(tipoevento);
    }

    public void excluir(TipoEvento id) {
        tipoeventoDAO.excluir(id);
    }

    public List<TipoEvento> buscarTodos() {
        return tipoeventoDAO.buscarTodos();
    }

    public TipoEvento buscarPorId(Integer id) {
        return tipoeventoDAO.buscarPorId(id);
    }

    public void alterar(TipoEvento tipoevento) {
        tipoeventoDAO.alterar(tipoevento);
    }

}
