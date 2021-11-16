package br.com.rd.mvpskins;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    static Connection conexao = null;
    static Statement statement = null;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mvpskins?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "admin";

    public static Connection getConnection() {
        try {
            if (conexao == null) {

                Class.forName(DRIVER);
                conexao = DriverManager.getConnection(URL,USER,PASS);
                statement = conexao.createStatement();
            }
            return conexao;

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no driver jdbc!");
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados!");
            ex.printStackTrace();
            return null;
        }
    }
}
