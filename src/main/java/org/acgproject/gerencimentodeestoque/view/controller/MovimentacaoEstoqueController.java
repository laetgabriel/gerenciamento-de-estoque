package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.controller.ProdutoController;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.enums.TipoMovimentacao;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.AtualizarVisaoTabelas;
import org.acgproject.gerencimentodeestoque.utils.Viewer;
import org.acgproject.gerencimentodeestoque.view.observer.MovimentacaoEstoqueObsever;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovimentacaoEstoqueController implements Initializable, MovimentacaoEstoqueObsever {

    @FXML
    private MenuItem movimentacaoEstoque;

    @FXML
    private MenuItem produto;

    @FXML
    private MenuItem fornecedor;

    @FXML
    private MenuItem categoria;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnGerarRelatorio;

    @FXML
    private ComboBox<String> comboBoxFiltroData;

    @FXML
    private ComboBox<String> comboBoxFiltroTipo;

    @FXML
    private TextField txtNomeProduto;

    @FXML
    private TableView<MovimentacaoEstoqueDTO> tblMovimentacaoEstoque;

    @FXML
    private TableColumn<MovimentacaoEstoqueDTO, Integer> colId;
    @FXML
    private TableColumn<MovimentacaoEstoqueDTO, ProdutoDTO> colProduto;
    @FXML
    private TableColumn<MovimentacaoEstoqueDTO, Integer> colQuantidade;
    @FXML
    private TableColumn<MovimentacaoEstoqueDTO, TipoMovimentacao> colTipoMovimentacao;
    @FXML
    private TableColumn<MovimentacaoEstoqueDTO, LocalDate> colData;

    private MovimentacaoEstoqueDTO movimentacaoSelecionada;

    private final org.acgproject.gerencimentodeestoque.controller.MovimentacaoEstoqueController controller =
            new org.acgproject.gerencimentodeestoque.controller.MovimentacaoEstoqueController();
    private final ProdutoController produtoController = new ProdutoController();
    private ObservableList<MovimentacaoEstoqueDTO> movimentacaoEstoqueDTOObservableList;
    private ObservableList<MovimentacaoEstoqueDTO> movimentacaoFiltro;

    @FXML
    public void onMenuItemMovimentacaoEstoque(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/MovimentacaoEstoque.fxml");
    }

    @FXML
    public void onMenuItemProduto(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/Produto.fxml");
    }

    @FXML
    public void onMenuItemFornecedor(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/Fornecedor.fxml");
    }

    @FXML
    public void onMenuItemCategoria(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/Categoria.fxml");
    }

    @FXML
    public void onBtnNovo(){
        Viewer.loadViewCadastroMovimentacaoEstoque("/org/acgproject/gerencimentodeestoque/view/CadastroMovimentacaoEstoque.fxml", this, null);
    }

    @FXML
    public void onBtnExcluir(){
        movimentacaoSelecionada = tblMovimentacaoEstoque.getSelectionModel().getSelectedItem();

        if (movimentacaoSelecionada != null) {
            Optional<ButtonType> escolha = Alertas.showConfirmation("Confirmação", "Tem certeza de que deseja excluir" +
                    " a movimentação do produto " + movimentacaoSelecionada.getProdutoDTO().getNome() + " ?");
            if (escolha.get() == ButtonType.OK) {

                ProdutoDTO prod = movimentacaoSelecionada.getProdutoDTO();

                if(movimentacaoSelecionada.getTipoMovimentacao() == TipoMovimentacao.ENTRADA){
                    movimentacaoSelecionada.getProdutoDTO().setQuantidade(prod.getQuantidade() - movimentacaoSelecionada.getQuantidade());
                }else {
                    movimentacaoSelecionada.getProdutoDTO().setQuantidade(prod.getQuantidade() + movimentacaoSelecionada.getQuantidade());
                }

                controller.excluirMovimentacaoEstoque(movimentacaoSelecionada.getId());
                produtoController.alterarProduto(prod);
                atualizarMovimentacaoEstoque();
            }
        } else {
            Alertas.mostrarAlerta("Erro", "Selecione uma movimentação para excluir!", Alert.AlertType.ERROR);
        }
    }

    public void onBtnGerarRelatorio(){
        Stage stage = (Stage) btnGerarRelatorio.getScene().getWindow();
        controller.gerarRelatorio(stage, movimentacaoFiltro);
    }

    public void onBtnAtualizar(){

        movimentacaoSelecionada = tblMovimentacaoEstoque.getSelectionModel().getSelectedItem();
        if (movimentacaoSelecionada != null) {
            Viewer.loadViewCadastroMovimentacaoEstoque("/org/acgproject/gerencimentodeestoque/view/CadastroMovimentacaoEstoque.fxml",
                    this, movimentacaoSelecionada);
        } else {
            Alertas.mostrarAlerta("Erro", "Selecione uma movimentação para atualizar!", Alert.AlertType.ERROR);
        }
    }

    public void atualizarMovimentacaoEstoque() {
        List<MovimentacaoEstoqueDTO> movimentacoes = controller.listarMovimentacaoEstoque();
        movimentacaoEstoqueDTOObservableList = FXCollections.observableArrayList(movimentacoes);
        movimentacaoFiltro  = FXCollections.observableArrayList(movimentacoes);
        tblMovimentacaoEstoque.setItems(movimentacaoEstoqueDTOObservableList);
    }

    private void initializeNodes(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produtoDTO"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colTipoMovimentacao.setCellValueFactory(new PropertyValueFactory<>("tipoMovimentacao"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        formatterColData();
    }

    @FXML
    public void onComboBoxFiltroChanged() {
        String filtroData = comboBoxFiltroData.getValue() != null
                ? comboBoxFiltroData.getValue().toString()
                : "";

        String filtroTipo = comboBoxFiltroTipo.getValue() != null
                ? comboBoxFiltroTipo.getValue().toString()
                : "";

        String filtroNomeProduto = txtNomeProduto.getText() != null
                ? txtNomeProduto.getText()
                : "";

        tabelaFiltrada(filtroData, filtroNomeProduto, filtroTipo);
    }

    private void tabelaFiltrada(String filtroData, String filtroNomeProduto, String filtroTipo) {
        movimentacaoFiltro = AtualizarVisaoTabelas.tabelaFiltradaMovimentacao(filtroData, filtroNomeProduto, filtroTipo,
                movimentacaoEstoqueDTOObservableList, tblMovimentacaoEstoque);
    }

    private void formatterColData(){
        colData.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(date.format(dateFormatter));
                }
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
        atualizarMovimentacaoEstoque();

        List<String> opcoes = new ArrayList<>();
        opcoes.add("Hoje");
        opcoes.add("Semanal");
        opcoes.add("Mês");
        opcoes.add("Todos");
        ObservableList<String> listaOpcoesData = FXCollections.observableList(opcoes);
        comboBoxFiltroData.setItems(listaOpcoesData);

        List<String> tipoMovimentacao = new ArrayList<>();
        tipoMovimentacao.add(TipoMovimentacao.ENTRADA.toString());
        tipoMovimentacao.add(TipoMovimentacao.SAIDA.toString());
        tipoMovimentacao.add("TODOS");
        ObservableList<String> listaOpcoesTipo = FXCollections.observableList(tipoMovimentacao);
        comboBoxFiltroTipo.setItems(listaOpcoesTipo);

        txtNomeProduto.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroData = comboBoxFiltroData.getValue() != null
                    ? comboBoxFiltroData.getValue().toString()
                    : "";
            String filtroTipo = comboBoxFiltroTipo.getValue() != null
                    ? comboBoxFiltroTipo.getValue().toString()
                    : "";

            tabelaFiltrada(filtroData, newValue, filtroTipo);
        });

    }
}
