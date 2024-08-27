/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.militarydininghall.factory;

import ec.edu.espe.militarydininghall.model.Dish;

/**
 *
 * @author Ismael Travez, The Java Bandits, DCCO-ESPE
 */
public interface DishFactory {
    Dish createDish(String date, String breakfast, String lunch, String dinner);
}
