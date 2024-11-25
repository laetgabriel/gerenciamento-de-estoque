package org.acgproject.gerencimentodeestoque.view.controller;

import jakarta.persistence.PersistenceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.controller.MovimentacaoEstoqueController;
import org.acgproject.gerencimentodeestoque.controller.ProdutoController;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.enums.TipoMovimentacao;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.Restricoes;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.validationfornecedor.FornecedorValidator;
import org.acgproject.gerencimentodeestoque.view.controller.validation.validationmovimentacaoestoque.MovimentacaoEstoqueValidator;
import org.acgproject.gerencimentodeestoque.view.observer.MovimentacaoEstoqueObsever;
import org.acgproject.gerencimentodeestoque.view.observer.ProdutoObserver;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroMovimentacaoEstoqueController implements Initializable {

    private MovimentacaoEstoqueDTO movimentacaoEstoqueAtualizar;

    @FXML
    private Label titulo;
    @FXML
    private ComboBox comboBoxProduto;
    @FXML
    private ComboBox comboBoxTipoMovimentacao;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private DatePicker data;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblErroProduto;
    @FXML
    private Label lblErroTipo;
    @FXML
    private Label lblErroQuantidade;
    @FXML
    private Label lblErroData;

    private MovimentacaoEstoqueController movimentacaoEstoqueController = new MovimentacaoEstoqueController();
    private MovimentacaoEstoqueValidator movimentacaoEstoqueValidator = new MovimentacaoEstoqueValidator();
    private ProdutoController produtoController = new ProdutoController();
    private ObservableList<ProdutoDTO> produtoDTOS;
    private ObservableList<TipoMovimentacao> tipoMovimentacoes;

    private List<MovimentacaoEstoqueObsever> observers = new ArrayList<>();

    public void onBtnSalvar() {
        lblErroProduto.setText("");
        lblErroTipo.setText("");
        lblErroQuantidade.setText("");
        lblErroData.setText("");

        ProdutoDTO produto;
        produto = produtoController.buscarProdutoPorNome(
                comboBoxProduto.getSelectionModel().getSelectedItem() != null
                        ? comboBoxProduto.getSelectionModel().getSelectedItem().toString()
                        : ""
        );

        TipoMovimentacao tipo = comboBoxTipoMovimentacao.getSelectionModel().getSelectedIndex() != -1
                ? tipoMovimentacoes.get(comboBoxTipoMovimentacao.getSelectionModel().getSelectedIndex())
                : null;

        Integer quantidade = txtQuantidade.getText().isEmpty() ? 0 : Integer.parseInt(txtQuantidade.getText());
        LocalDate dataSel = data.getValue();

        MovimentacaoEstoqueDTO movimentacaoEstoqueDTO = new MovimentacaoEstoqueDTO(
                null,
                tipo,
                quantidade,
                dataSel,
                produto
        );
        try {

            if (tipo == TipoMovimentacao.ENTRADA){
                produto.setQuantidade(produto.getQuantidade() + quantidade);

            }else if(tipo == TipoMovimentacao.SAIDA) {
                if (quantidade > produto.getQuantidade()) {
                    throw new ValidacaoException("Quantidade acima do disponível em estoque!");
                }else{
                    produto.setQuantidade(produto.getQuantidade() - quantidade);
                }
            }

            movimentacaoEstoqueValidator.validarMovimentacaoEstoque(movimentacaoEstoqueDTO);
            if (movimentacaoEstoqueAtualizar == null) {
                movimentacaoEstoqueController.inserirMovimentacaoEstoque(movimentacaoEstoqueDTO);

                Alertas.mostrarAlerta("Sucesso", "Movimentação salva com sucesso!", Alert.AlertType.INFORMATION);
            }else {
                movimentacaoEstoqueAtualizar.setProdutoDTO(produto);
                movimentacaoEstoqueAtualizar.setTipoMovimentacao(tipo);
                movimentacaoEstoqueAtualizar.setQuantidade(quantidade);
                movimentacaoEstoqueAtualizar.setData(dataSel);
                movimentacaoEstoqueController.alterarMovimentacaoEstoque(movimentacaoEstoqueAtualizar);
                Alertas.mostrarAlerta("Sucesso", "Movimentação atualizada com sucesso!", Alert.AlertType.INFORMATION);
            }

            produtoController.alterarProduto(produto);
            notificarOuvintes();
            Stage palco = (Stage) btnSalvar.getScene().getWindow();
            palco.close();
        } catch (ValidacaoException e) {
            if (e.getMessage().contains("produto")) {
                lblErroProduto.setText(e.getMessage());
            } else if (e.getMessage().contains("tipo")) {
                lblErroTipo.setText(e.getMessage());
            } else if (e.getMessage().contains("quantidade") || e.getMessage().contains("Quantidade")) {
                lblErroQuantidade.setText(e.getMessage());
            }else if (e.getMessage().contains("data")) {
                lblErroData.setText(e.getMessage());
            }
        }
    }

    public void onBtnCancelar() {
        Stage palco = (Stage) btnCancelar.getScene().getWindow();
        palco.close();
    }

    public void atualizarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {
        movimentacaoEstoqueAtualizar = movimentacaoEstoqueDTO;

        titulo.setText("Atualizar Movimentacao Estoque");

        comboBoxProduto.setValue(movimentacaoEstoqueDTO.getProdutoDTO());
        comboBoxTipoMovimentacao.setValue(movimentacaoEstoqueDTO.getTipoMovimentacao());
        txtQuantidade.setText(movimentacaoEstoqueDTO.getQuantidade().toString());
        data.setValue(movimentacaoEstoqueDTO.getData());

        ProdutoDTO prod = movimentacaoEstoqueDTO.getProdutoDTO();

        if(movimentacaoEstoqueDTO.getTipoMovimentacao() == TipoMovimentacao.ENTRADA){
            movimentacaoEstoqueDTO.getProdutoDTO().setQuantidade(prod.getQuantidade() - movimentacaoEstoqueDTO.getQuantidade());
        }else {
            movimentacaoEstoqueDTO.getProdutoDTO().setQuantidade(prod.getQuantidade() + movimentacaoEstoqueDTO.getQuantidade());
        }

        produtoController.alterarProduto(prod);
    }


    public void adicionarObserver(MovimentacaoEstoqueObsever observer) {
        observers.add(observer);
    }

    private void notificarOuvintes(){
        for (MovimentacaoEstoqueObsever observer : observers) {
            observer.atualizarMovimentacaoEstoque();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ProdutoDTO> listaProduto = produtoController.listarProdutos();
        produtoDTOS = FXCollections.observableArrayList(listaProduto);
        comboBoxProduto.setItems(produtoDTOS);

        List<TipoMovimentacao> listaTipoMovimentacao = Arrays.stream(TipoMovimentacao.values()).toList();
        tipoMovimentacoes = FXCollections.observableArrayList(listaTipoMovimentacao);
        comboBoxTipoMovimentacao.setItems(tipoMovimentacoes);

        Restricoes.setTextFieldInteger(txtQuantidade);
        Restricoes.setDatePickerValidation(data);
    }

}
