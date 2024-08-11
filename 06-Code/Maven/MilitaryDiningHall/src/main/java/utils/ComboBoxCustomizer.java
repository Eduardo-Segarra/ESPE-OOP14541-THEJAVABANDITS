/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.YearMonth;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Eduardo Segarra, TheJavaBandits, DCCO-ESPE
 */
public class ComboBoxCustomizer {

    private JComboBox<String> cmbMonth;
    private JComboBox<String> cmbDay;

    public ComboBoxCustomizer(JComboBox<String> cmbMonth, JComboBox<String> cmbDay) {
        this.cmbMonth = cmbMonth;
        this.cmbDay = cmbDay;
    }

    public void customizeComboBoxes() {
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbMonth.getModel();
        model.removeAllElements();
        for (int i = currentMonth; i <= 12; i++) {
            model.addElement(String.valueOf(i));
        }
        cmbMonth.addActionListener(new MonthComboBoxListener(this));

        updateDaysComboBox(currentMonth);
    }

    public void updateDaysComboBox(int selectedMonth) {
        cmbDay.removeAllItems();
        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        int daysInMonth = YearMonth.of(today.getYear(), selectedMonth).lengthOfMonth();
        for (int i = 1; i <= daysInMonth; i++) {
            if (selectedMonth == today.getMonthValue() && i < currentDay) {
                continue;
            }
            cmbDay.addItem(String.valueOf(i));
        }
    }

    private class MonthComboBoxListener implements java.awt.event.ActionListener {

        private ComboBoxCustomizer customizer;

        public MonthComboBoxListener(ComboBoxCustomizer customizer) {
            this.customizer = customizer;
        }

        public void actionPerformed(java.awt.event.ActionEvent evt) {
            String selectedItem = (String) cmbMonth.getSelectedItem();
            if (selectedItem != null) {
                int selectedMonth = Integer.parseInt(selectedItem);
                customizer.updateDaysComboBox(selectedMonth);
            }
        }
    }
}
