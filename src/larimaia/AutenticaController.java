package larimaia;

import Dao.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AutenticaController implements Initializable {

    @FXML
    private TextField loginT;

    @FXML
    private PasswordField senhaT;

    @FXML
    public void entrar(ActionEvent event) throws SQLException, IOException {
        Usuario usu = new Usuario();
        usu.setNome(loginT.getText());
        usu.setSenha(senhaT.getText());

        UsuarioDAO usuD = new UsuarioDAO();

        boolean teste = usuD.autentica(usu);

        if (teste) {

            Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

        }else{
              JOptionPane.showMessageDialog(null, "login ou senha errado!");
    
    }

    

}

@Override
        public void initialize(URL url, ResourceBundle rb) {
      
        
        
    }    
    
}
