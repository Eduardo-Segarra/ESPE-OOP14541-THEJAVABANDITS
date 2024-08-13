/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.controller;

import ec.edu.espe.militarydininghall.view.FrmLoadingSplash;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.util.concurrent.Callable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.*;

/**
 *
 * @author Ismael Travez, The Java Bandits, DCCO-ESPE
 */
public class LoadingSplashController {

    private FrmLoadingSplash frmLoadingSplash = new FrmLoadingSplash();

    // Método para mostrar la pantalla de carga
    public void showSplashScreen() {
        SwingUtilities.invokeLater(() -> {
            frmLoadingSplash.setVisible(true);
        });
    }

    // Método para ocultar la pantalla de carga
    public void hideSplashScreen() {
        SwingUtilities.invokeLater(() -> {
            frmLoadingSplash.dispose();
        });
    }

    // Método para ejecutar una tarea con la pantalla de carga
    public static <T> T executeWithLoading(Callable<T> callable) {
        LoadingSplashController loadingSplash = new LoadingSplashController(); // Crear una instancia del controlador
        loadingSplash.showSplashScreen(); // Mostrar la pantalla de carga

        final T[] result = (T[]) new Object[1]; // Usar un array para almacenar el resultado

        // Ejecutar la operación en un hilo separado
        Thread thread = new Thread(() -> {
            try {
                result[0] = callable.call(); // Ejecutar la operación
            } catch (Exception e) {
                e.printStackTrace();
                result[0] = null; // Manejar el error
            } finally {
                loadingSplash.hideSplashScreen(); // Ocultar la pantalla de carga
            }
        });
        thread.start();

        // Esperar a que el hilo termine
        try {
            thread.join(); // Esperar a que termine antes de retornar el resultado
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0]; // Retornar el resultado de la operación
    }
}
