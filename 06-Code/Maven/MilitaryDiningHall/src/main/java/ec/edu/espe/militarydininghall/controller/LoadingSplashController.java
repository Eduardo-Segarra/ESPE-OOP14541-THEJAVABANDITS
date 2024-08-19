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

    public void showSplashScreen() {
        SwingUtilities.invokeLater(() -> {
            frmLoadingSplash.setVisible(true);
        });
    }

    public void hideSplashScreen() {
        SwingUtilities.invokeLater(() -> {
            frmLoadingSplash.dispose();
        });
    }

    public static <T> T executeWithLoading(Callable<T> callable) {
        LoadingSplashController loadingSplash = new LoadingSplashController();
        loadingSplash.showSplashScreen();

        final T[] result = (T[]) new Object[1];

        Thread thread = new Thread(() -> {
            try {
                result[0] = callable.call();
            } catch (Exception e) {
                e.printStackTrace();
                result[0] = null;
            } finally {
                loadingSplash.hideSplashScreen();
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }
}
