package com.tc.app.exchangemonitor.controller;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.function.UnaryOperator;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.text.Text;

/** Controls the login screen */
public class LoginController
{
	private static final boolean DEFAULT_BOOLEAN_VALUE = false;
	public static String CONNECTION_URL ="jdbc:jtds:sqlserver://{0};databaseName={1}";

	@FXML private TextField serverNamePortNumTextField;
	@FXML private TextField databaseNameTextField;
	@FXML private TextField usernameTextField;
	@FXML private TextField passwordTextField;
	@FXML private Button loginButton;
	@FXML private Button cancelButton;
	@FXML private Text loginStatusTextField;
	@FXML private ComboBox<String> authenticationTypeComboBox;
	@FXML private CheckBox rememberMeCheckBox;
	private LoginManager loginManager;

	private InvalidationListener authenticationTypeComboBoxSelectedItemListener = observable -> doThisWhenSelectionChanged();
	UnaryOperator<Change> filter = change -> {
		/*String text = change.getText();
		if (text.matches("[0-9]*")) {
			return change;
		}
		return null;*/
		//return change.getText().toUpperCase();
		return null;
	};

	public void initialize()
	{
		serverNamePortNumTextField.textProperty().addListener((a,b,c) -> doThis(a, b, c));
		//serverNamePortNumTextField.setTextFormatter(new TextFormatter<String>(filter));
		/*serverNamePortNumTextField.setTextFormatter(new TextFormatter<>(new StringConverter<String>() {

			@Override
			public String toString(String object) {
				//return null;
				return "a";
			}

			@Override
			public String fromString(String string) {
				//return null;
				return "b";
			}
		}));*/
		//loginStatusTextField.textProperty().bind(Bindings.concat("jdbc:jtds:sqlserver://").concat(serverNamePortNumTextField.textProperty()).concat(";databaseName=").concat(databaseNameTextField.textProperty()));
		//usernameTextField.disableProperty().bind(authenticationTypeComboBox.valueProperty().isEqualTo("Windows Authentication"));
		bind();
		addListeners();
	}

	private void bind()
	{
		loginButton.disableProperty().bind(serverNamePortNumTextField.textProperty().isEmpty()
				.or(databaseNameTextField.textProperty().isEmpty()
						.or(usernameTextField.textProperty().isEmpty()
								.or(passwordTextField.textProperty().isEmpty()))));

		rememberMeCheckBox.disableProperty().bind(serverNamePortNumTextField.textProperty().isEmpty()
				.or(databaseNameTextField.textProperty().isEmpty()
						.or(usernameTextField.textProperty().isEmpty()
								.or(passwordTextField.textProperty().isEmpty()))));
	}

	private void unBind()
	{
		loginButton.disableProperty().unbind();
		rememberMeCheckBox.disableProperty().unbind();
	}

	private void addListeners()
	{
		authenticationTypeComboBox.getSelectionModel().selectedItemProperty().addListener(authenticationTypeComboBoxSelectedItemListener);
	}

	private void removeListeners()
	{
		authenticationTypeComboBox.getSelectionModel().selectedItemProperty().removeListener(authenticationTypeComboBoxSelectedItemListener);
	}

	public void initManager(final LoginManager loginManager)
	{
		this.loginManager = loginManager;
		/*loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				String sessionID = authorize();
				if (sessionID != null) {
					loginManager.authenticated(sessionID);
				}
			}
		});

		cancelButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Platform.runLater(() -> { Platform.exit(); System.exit(0); } );
			}
		});*/
	}

	@FXML
	private void handleLoginButtonClick()
	{
		/*String sessionID = authorize();
		if(sessionID != null)
			loginManager.authenticated(sessionID);*/
		if(authorize())
		{
			loginManager.authenticated();
		}
		else
		{
			//loginStatusTxt.setText("Login Failure");
		}
	}

	@FXML
	private void handleCancelButtonClick()
	{
		Platform.runLater(() -> { Platform.exit(); System.exit(0); } );
	}

	/**
	 * Check authorization credentials.
	 * 
	 * If accepted, return a sessionID for the authorized session
	 * otherwise, return null.
	 */   
	private boolean authorize()
	{
		boolean isFirstTimeLogin = !PreferencesUtil.getUserPreferences().getBoolean(IS_AUTHENTICATED_USER, DEFAULT_BOOLEAN_VALUE);
		boolean isAuthorized = DEFAULT_BOOLEAN_VALUE;

		if(isFirstTimeLogin)
		{
			/* no key in registry. so may be this is the first time login */
			boolean isWindowsAuthentication = authenticationTypeComboBox.getValue().equals("Windows Authentication") ? true : false;
			boolean shouldRemember = rememberMeCheckBox.isSelected();
			String serverName = serverNamePortNumTextField.getText();
			String databaseName = databaseNameTextField.getText();
			String username = isWindowsAuthentication ? null : usernameTextField.getText();
			String password = isWindowsAuthentication ? null : passwordTextField.getText();
			CONNECTION_URL = MessageFormat.format(CONNECTION_URL, serverName, databaseName);

			try
			{
				isAuthorized = DatabaseUtil.makeTestConnection(CONNECTION_URL, username, password);
				if(isAuthorized)
					loginStatusTextField.setText("Login Success...");
				if(isAuthorized && shouldRemember)
					rememberLoginCredentials();
			}
			catch(SQLException exception)
			{
				loginStatusTextField.setText(exception.getMessage());
			}
		}
		else
		{
			try
			{
				isAuthorized = DatabaseUtil.makeTestConnection(PreferencesUtil.getUserPreferences().get(CONNECTION_URL_KEY, ""), null, null);
				if(isAuthorized)
					loginStatusTextField.setText("Login Success...");
			}
			catch (SQLException exception)
			{
				loginStatusTextField.setText(exception.getMessage());
			}
		}
		return isAuthorized;
	}

	private void doThisWhenSelectionChanged()
	{
		if(authenticationTypeComboBox.getSelectionModel().getSelectedIndex() == 0)							//SQL Authentication
		{
			usernameTextField.setText(null);
			passwordTextField.setText(null);
			usernameTextField.setDisable(false);
			passwordTextField.setDisable(false);
			usernameTextField.requestFocus();
		}
		else if(authenticationTypeComboBox.getSelectionModel().getSelectedIndex() == 1)					//Windows Authentication
		{
			usernameTextField.setText(System.getenv("username"));
			passwordTextField.setText(System.getenv("username"));
			usernameTextField.setDisable(true);
			passwordTextField.setDisable(true);
		}
	}

	private void doThis(ObservableValue<? extends String> a, String b, String c)
	{
		serverNamePortNumTextField.setText(c.toUpperCase());
	}

	private static final String IS_AUTHENTICATED_USER = "isAuthenticatedUser";
	private static final String CONNECTION_URL_KEY = "connectionURL";
	private static final String SERVER_NAME = "serverName";
	private static final String DATABASE_NAME = "databaseName";
	private void rememberLoginCredentials()
	{
		PreferencesUtil.getUserPreferences().putBoolean(IS_AUTHENTICATED_USER, true);
		PreferencesUtil.getUserPreferences().put(CONNECTION_URL_KEY, CONNECTION_URL);
		PreferencesUtil.getUserPreferences().put(SERVER_NAME, serverNamePortNumTextField.getText());
		PreferencesUtil.getUserPreferences().put(DATABASE_NAME, databaseNameTextField.getText());
	}
}