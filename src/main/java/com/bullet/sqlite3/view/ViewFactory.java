package com.bullet.sqlite3.view;

import com.bullet.sqlite3.controller.MainController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private final ObjectProperty<MenuOption> selectedMenuOption;

    private AnchorPane employeeView;
    private AnchorPane employeePaymentDetailsView;

    /***********    CONSTRUCTOR SECTION     */

    public ViewFactory() {
        this.selectedMenuOption = new SimpleObjectProperty<>();
    }
    /***********  END OF CONSTRUCTOR SECTION     */

    /***********    GETTER AND SETTER SECTION     */
    public MenuOption getSelectedMenuOption() {
        return selectedMenuOption.get();
    }
    public void setSelectedMenuOption(MenuOption menuOption) {
        this.selectedMenuOption.set(menuOption);
    }

    public ObjectProperty<MenuOption> selectedMenuOptionProperty() {
        return selectedMenuOption;
    }

    /***********    END GETTER AND SETTER SECTION     */

    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        MainController controller = new MainController();
        loader.setController(controller);
        createStage(loader);
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        createStage(loader);
    }

    public AnchorPane getEmployeeView() {
        if (employeeView == null) {
            try {
                employeeView = new FXMLLoader(getClass().getResource("/fxml/employee.fxml")).load();
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return employeeView;
    }

    public AnchorPane getEmployeePaymentDetailsView() {
        if (employeePaymentDetailsView == null) {
            try {
                employeePaymentDetailsView = new FXMLLoader(getClass().getResource("/fxml/employeePaymentDetails.fxml")).load();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return employeePaymentDetailsView;
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        Stage stage = new Stage();
        stage.setTitle("PAYROLL APP");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
