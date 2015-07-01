
package Model;




public class Pedido {
    private Integer idpedido;
    private String localContratado, Cerimonial, horario;
    private String dataPedido, dataEvento;
    private Cliente cliente;
    private TipoEvento tipoEvento;
    private String descricao, nomeCliente;
    private double valorTotalPedido;
    private int quantidadeItens;
 
    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }
  
    
    public String getLocalContratado() {
        return localContratado;
    }

    public void setLocalContratado(String localContratado) {
        this.localContratado = localContratado;
    }

    public String getCerimonial() {
        return Cerimonial;
    }

    public void setCerimonial(String Cerimonial) {
        this.Cerimonial = Cerimonial;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    
}
