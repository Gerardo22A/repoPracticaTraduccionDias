package com.example.traducciondia;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.spring.guides.gs_producing_web_service.Dia;
import jakarta.annotation.PostConstruct;

@Component
public class TraduccionRepository {

    private static final Map<String, Dia> listaDias = new HashMap<>();

    @PostConstruct
    public void initData() {

        // Crear objetos Dia sin el constructor
        Dia dia1 = new Dia();
        dia1.setNombre("Lunes");
        dia1.setTraduccion("Monday");

        Dia dia2 = new Dia();
        dia2.setNombre("Martes");
        dia2.setTraduccion("Tuesday");

        Dia dia3 = new Dia();
        dia3.setNombre("Miércoles");
        dia3.setTraduccion("Wednesday");

        Dia dia4 = new Dia();
        dia4.setNombre("Jueves");
        dia4.setTraduccion("Thursday");

        Dia dia5 = new Dia();
        dia5.setNombre("Viernes");
        dia5.setTraduccion("Friday");

        Dia dia6 = new Dia();
        dia6.setNombre("Sábado");
        dia6.setTraduccion("Saturday");

        Dia dia7 = new Dia();
        dia7.setNombre("Domingo");
        dia7.setTraduccion("Sunday");

        // Agregar objetos Dia a la lista
        listaDias.put(dia1.getNombre(), dia1);
        listaDias.put(dia2.getNombre(), dia2);
        listaDias.put(dia3.getNombre(), dia3);
        listaDias.put(dia4.getNombre(), dia4);
        listaDias.put(dia5.getNombre(), dia5);
        listaDias.put(dia6.getNombre(), dia6);
        listaDias.put(dia7.getNombre(), dia7);
    }

    public Dia findDia(String name) {
        Assert.notNull(name, "The dia's name must not be null");
        return listaDias.get(name);
    }
}