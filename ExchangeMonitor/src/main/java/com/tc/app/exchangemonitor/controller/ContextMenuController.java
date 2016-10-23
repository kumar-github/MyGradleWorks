package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class ContextMenuController<T> implements Initializable
{
	@FXML  
    private ContextMenu contextMenu ;  
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }
    
    @FXML
    private void deleteTableRow()
    {  
         TableRow<T> row= (TableRow<T>) contextMenu.getOwnerNode();  
         TableView<T> table = row.getTableView();  
         table.getItems().remove(row.getItem());  
    }
}