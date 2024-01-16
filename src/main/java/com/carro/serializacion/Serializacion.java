package com.carro.serializacion;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializacion {
    public static void main(String[] args) {
        // Crear una lista de personas
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Juan", 25));
        personas.add(new Persona("María", 30));
        personas.add(new Persona("Carlos", 22));

        // Serializar la lista de personas
        serializarObjetos(personas, "personas.ser");

        // Deserializar la lista de personas
        List<Persona> personasDeserializadas = deserializarObjetos("personas.ser");

        // Mostrar las personas deserializadas
        for (Persona persona : personasDeserializadas) {
            System.out.println(persona);
        }
    }

    // Método para serializar objetos
    private static void serializarObjetos(List<?> objetos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(objetos);
            System.out.println("Objetos serializados correctamente en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para deserializar objetos
    private static List<Persona> deserializarObjetos(String archivo) {
        List<Persona> personas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object objeto = ois.readObject();
            if (objeto instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<?> objetos = (List<?>) objeto;
                for (Object obj : objetos) {
                    if (obj instanceof Persona) {
                        personas.add((Persona) obj);
                    }
                }
            }
            System.out.println("Objetos deserializados correctamente desde " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return personas;
    }
}

