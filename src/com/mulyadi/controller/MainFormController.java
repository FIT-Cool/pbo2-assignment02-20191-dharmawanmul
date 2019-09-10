package com.mulyadi.controller;

import com.mulyadi.entity.Category;
import com.mulyadi.entity.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private ComboBox<Category> comboBox;
    @FXML
    private TextField catName;
    @FXML
    private TableView<Item> tableDepartment;
    @FXML
    private TableColumn<Item, String> col01;
    @FXML
    private TableColumn<Item, String> col02;
    @FXML
    private TableColumn<Item, String> col03;
    @FXML
    private Button saveBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button updateBtn;
    private ObservableList<Item> items;
    private ObservableList<Category> categories;

    @FXML
    private void saveBtn(ActionEvent actionEvent) {

    }

    @FXML
    private void resetBtn(ActionEvent actionEvent) {
    }

    @FXML
    private void updateBtn(ActionEvent actionEvent) {
    }

    @FXML
    private void saveCatBtn(ActionEvent actionEvent) {
        Category cat = new Category();
        cat.setName(catName.getText().trim());
        categories.add(cat);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        comboBox.setItems(categories);

    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        Item i = new Item();

    }

    @FXML
    private void BtnSaved(ActionEvent actionEvent) {
        Item item = new Item();
        item.setName(nameTxt.getText());
        item.setPrice(Integer.parseInt(priceTxt.getText()));
        items.add(item);
        tableDepartment.setItems(items);
        col01.setCellValueFactory(data -> {
            Item i = data.getValue();
            return new SimpleStringProperty(i.getName());
        });
        col02.setCellValueFactory(data -> {
            Item j = data.getValue();
            return new SimpleStringProperty(String.valueOf(j.getPrice()));
        });
        col03.setCellValueFactory(data -> {
            Item k = data.getValue();
            return new SimpleStringProperty(k.getCategory().getName());
        });
    }

    @FXML
    private void btnReset(ActionEvent actionEvent) {
    }

    @FXML
    private void btnUpdate(ActionEvent actionEvent) {
    }
}
