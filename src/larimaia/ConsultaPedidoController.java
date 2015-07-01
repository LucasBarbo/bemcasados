
package larimaia;

import Dao.PedidoDAO;
import Model.Cliente;
import Model.Pedido;
import Service.ClienteService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;


public class ConsultaPedidoController implements Initializable {
    
    @FXML
    private ComboBox<Cliente> comboClientes;
    
    @FXML
    private TableView tabela;
    
    @FXML
    private TableColumn colunaIdePedido, colunaNomeCliente,
            colunaDataContrato, colunaValor, colunaTipoEvento,
            colunaDataEvento, colunaQuantidade;
    
    @FXML
    private DatePicker dataInicioContratos, dataFimContratos, dataInicioPedidos, dataFimPedidos;
    
     
    @FXML
    public void btnCliente(ActionEvent event) throws SQLException{
    
     PedidoDAO pedido = new PedidoDAO();      
               
    ObservableList<Pedido> relatorio = FXCollections.observableArrayList(pedido.buscaPorCliente(comboClientes.getValue().getId()));
    
    colunaIdePedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
    colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
    colunaTipoEvento.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    colunaValor.setCellValueFactory(new PropertyValueFactory<>("valorTotalPedido"));
    colunaDataContrato.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
    colunaDataEvento.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeItens"));
    
    
    tabela.setItems(relatorio);
    
    }
    
      
    @FXML
    public void btnContratos(ActionEvent event) throws SQLException{
     if(dataInicioContratos.getValue()!=null && dataFimContratos.getValue()!=null){
         
      
       
     PedidoDAO pedido = new PedidoDAO();      
               
    ObservableList<Pedido> relatorio = FXCollections.observableArrayList(
            pedido.buscaPorDataPedido(dataInicioContratos.getValue().toString(), dataFimContratos.getValue().toString()));
    
    colunaIdePedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
    colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
    colunaTipoEvento.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    colunaValor.setCellValueFactory(new PropertyValueFactory<>("valorTotalPedido"));
    colunaDataContrato.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
    colunaDataEvento.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeItens"));
    
    tabela.setItems(relatorio);
    
     }else{
         JOptionPane.showMessageDialog(null,"Datas de inicio e fim obrigatorias!!");
     }
    
    }
    
    
    public void btnEntrega(ActionEvent event) throws SQLException{
        PedidoDAO pedido = new PedidoDAO();      
               
    ObservableList<Pedido> relatorio = FXCollections.observableArrayList(
            pedido.buscarPorDataEntrega(dataInicioPedidos.getValue().toString(), dataFimPedidos.getValue().toString()));
    
    colunaIdePedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
    colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
    colunaTipoEvento.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    colunaValor.setCellValueFactory(new PropertyValueFactory<>("valorTotalPedido"));
    colunaDataContrato.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
    colunaDataEvento.setCellValueFactory(new PropertyValueFactory<>("dataEvento"));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeItens"));
    
    tabela.setItems(relatorio);
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClienteService cliS = new ClienteService();       
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(cliS.buscarTodos());
        comboClientes.setItems(clientes);
        
        
    }    
    
}
