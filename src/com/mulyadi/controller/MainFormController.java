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
    Alert alert = new Alert(Alert.AlertType.ERROR);

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
        if (cat.getName().equals("")) {
            alert.setContentText("Please fill category name");
            alert.showAndWait();
        }
        else {
            boolean found = false;
            for(Category i : categories) {
                if (i.getName().equals(cat.getName())) {
                    found = true;
                    alert.setContentText("Duplicate category name");
                    alert.showAndWait();
                    break;
                }
            }
            if (!found) {
                categories.add(cat);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateBtn.setDisable(true);
        items = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        comboBox.setItems(categories);
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
    private void tableClicked(MouseEvent mouseEvent) {
        Item c = tableDepartment.getSelectionModel().getSelectedItem();
        nameTxt.setText(c.getName());
        priceTxt.setText(String.valueOf(c.getPrice()));
        comboBox.setValue(c.getCategory());
        updateBtn.setDisable(false);
    }

    @FXML
    private void BtnSaved(ActionEvent actionEvent) {
        Item item = new Item();
        if (nameTxt.getText().isEmpty() || priceTxt.getText().isEmpty() || comboBox.getValue() == null) {
            alert.setContentText("Please fill name/ price/ category");
            alert.showAndWait();
        }
        else {
            item.setName(nameTxt.getText());
            item.setPrice(Double.parseDouble(priceTxt.getText()));
            item.setCategory(comboBox.getValue());
            items.add(item);
        }
    }


    @FXML
    private void btnReset(ActionEvent actionEvent) {
        nameTxt.setText("");
        priceTxt.setText("");
        comboBox.setValue(null);
        catName.setText("");
        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
    }

    @FXML
    private void btnUpdate(ActionEvent actionEvent) {
        saveBtn.setDisable(true);
        updateBtn.setDisable(false);
        Item items = tableDepartment.getSelectionModel().getSelectedItem();
        items.setName(nameTxt.getText());
        items.setPrice(Double.valueOf(priceTxt.getText()));
        items.setCategory(comboBox.getValue());
        tableDepartment.refresh();
    }
}
