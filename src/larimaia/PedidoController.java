
package larimaia;

import Dao.ItemPedidoDAO;
import Dao.PedidoDAO;
import Model.Cliente;
import Model.ItemPedido;
import Model.Pedido;
import Model.Produto;
import Model.TipoEvento;
import Service.ClienteService;
import Service.ProdutoService;
import Service.TipoEventoService;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class PedidoController implements Initializable {
    
   private static final List<ItemPedido> produtos = new ArrayList<>();
    double s=0;
    private  ObservableList<ItemPedido> infoItens;
    
    @FXML
    private TextField localContratadoT, cerimonialT, horarioT, qtdProduto;
    
    @FXML
    private TextField valorTotalProduto;
    
    @FXML
    private DatePicker dataPedidoT, dataEventoT;
    
    @FXML
    private TableView tabela;
    
    @FXML
    private TableColumn colunaProduto;
    
    @FXML
    private TableColumn colunaQtd;
    
    @FXML
    private TableColumn colunaValor;
    
    @FXML
    private TextField valorTotalLista;
     
        
    @FXML
    private ComboBox<Cliente> clientesCombo;
    
    @FXML
    private ComboBox<TipoEvento> tipoCombo;
    
    @FXML
    private ComboBox<Produto> produtoCombo;
    
    
   
    
    
    @FXML
    private void produtoEvento(ActionEvent event){
       double valor =  Double.parseDouble(produtoCombo.getValue().getValor());
       int qtd = Integer.parseInt(qtdProduto.getText());
       
       double result = valor * qtd;
       
       // transformo o valor da operação em string para setar no textfild da tela
       String resultString = String.valueOf(result);
       
       
       valorTotalProduto.setText(resultString);
    
    }
    
    
    @FXML
    private void adicionar(ActionEvent event) {
        ItemPedido item = new ItemPedido();
       
        double valor =  Double.parseDouble(produtoCombo.getValue().getValor());
        
       
       item.setProduto(produtoCombo.getValue());
       item.setQuantidade(Integer.parseInt(qtdProduto.getText()));
       item.setValor(valor * item.getQuantidade());
       
       
       
       produtos.add(item);
       
        colunaProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
       
       ObservableList<ItemPedido> infoItens = FXCollections.observableArrayList(produtos);
       
       tabela.setItems(infoItens);
       
       s = 0;
       for(ItemPedido i : produtos){
               s = s + i.getValor();
            }
            
            String soma = String.valueOf(s);
            valorTotalLista.setText(soma);
       
       
       
       
    }
    
    
    
   @FXML
   private void excluir(ActionEvent e){
       

   
       tabela.getSelectionModel().getSelectedItem();
       
       int idTab = tabela.getSelectionModel().getFocusedIndex();
       infoItens.remove(idTab);
       tabela.getSelectionModel().clearSelection(idTab, colunaValor);
       tabela.getItems().remove(idTab);
       tabela.setItems(infoItens);
   }
    
    @FXML
    private void salvarPedido(ActionEvent event) throws SQLException, ParseException {
           Pedido pedido = new Pedido();
           
           pedido.setLocalContratado(localContratadoT.getText());
           pedido.setDataPedido(dataPedidoT.getValue().toString());
           pedido.setCliente(clientesCombo.getValue());
           pedido.setCerimonial(cerimonialT.getText());
           pedido.setDataEvento(dataEventoT.getValue().toString());
           pedido.setTipoEvento(tipoCombo.getValue());
           pedido.setHorario(horarioT.getText());
           pedido.setValorTotalPedido(Double.parseDouble(valorTotalLista.getText()));
           
           PedidoDAO pedDAO = new PedidoDAO();
           
           pedDAO.salvar(pedido);
           
           
           Integer id = pedDAO.buscarId();
           ItemPedidoDAO itemDAO = new ItemPedidoDAO();
           for(ItemPedido item : produtos){
               itemDAO.salvar(item, id);
           }           
           
           produtos.removeAll(produtos);
           
           
           
           
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //colocando no combo os clientes
        ClienteService cliS = new ClienteService();       
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(cliS.buscarTodos());
        clientesCombo.setItems(clientes);
        
        //colocando no combo os tipos de eventos
        TipoEventoService tipoE = new TipoEventoService();       
        ObservableList<TipoEvento> eventos = FXCollections.observableArrayList(tipoE.buscarTodos());
        tipoCombo.setItems(eventos);
        
         //colocando no combo os produtos
        ProdutoService prod = new ProdutoService();       
        ObservableList<Produto> pro = FXCollections.observableArrayList(prod.buscarTodos());
        produtoCombo.setItems(pro);
        
        
     
        
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
         
            
    
        
    }       
    
}
