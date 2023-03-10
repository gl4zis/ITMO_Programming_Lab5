package org.application.lab5.collection;

import org.application.lab5.general.Main;
import org.application.lab5.exceptions.IncorrectDataException;
import org.application.lab5.parsers.DateParser;
import org.application.lab5.parsers.JsonManager;
import org.application.lab5.dragons.*;
import org.application.lab5.exceptions.IdCollisionException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public abstract class CollectionManager {
    public static void transferCollection(JSONObject json, DragonCollection collection) {
        if (json == null) System.out.println("Не буду работать");
        else if (json.toString().equals("{}")) System.out.println("Создана новая коллекция");
        else {
            try {
                String creationDateString = (String) json.get("creationDate");
                try {
                    Date creationDate = DateParser.stringToDate(creationDateString);
                    collection.setCreationDate(creationDate);
                } catch (NullPointerException e) {
                    System.out.println("Утеряны данные о коллекции (Дата создания)");
                } catch (ParseException e) {
                    System.out.println("Неверный формат даты (Коллекция)");
                    collection.setCreationDate(new Date());
                }
            } catch (ClassCastException e) {
                System.out.println("Утеряны данные о коллекции (Дата создания)");
            }

            try {
                JSONArray dragons = (JSONArray) json.get("dragons");
                int maxId = 0;
                for (Object dragon : dragons) {
                    int id = transferDragon(dragon, collection);
                    if (id > maxId) maxId = id;
                }
                collection.setMaxId(maxId);
            } catch (ClassCastException | NullPointerException e) {
                System.out.println("Утеряны данные о коллекции (Список объектов Dragon)");
            }
            System.out.println("Коллекция загружена из файла");
        }
    }

    public static int transferDragon(Object dr, DragonCollection collection) {
        JSONObject dragon = (JSONObject) dr;
        Dragon dragonObject = null;
        int returningId = 0;
        try {
            int id = ((Long) dragon.get("id")).intValue();
            String name = (String) dragon.get("name");
            double x = (double) ((JSONObject) dragon.get("coordinates")).get("x");
            float y = ((Double) ((JSONObject) dragon.get("coordinates")).get("y")).floatValue();
            Coordinates coordinates = new Coordinates(x, y);
            Date creationDate = DateParser.stringToDate((String) dragon.get("creationDate"));
            long weight = (long) dragon.get("weight");
            Color color = Color.valueOf((String) dragon.get("color"));
            DragonCharacter character = DragonCharacter.valueOf((String) dragon.get("character"));
            float eyesCount = ((Double) ((JSONObject) dragon.get("head")).get("eyesCount")).floatValue();
            DragonHead head = new DragonHead(eyesCount);

            dragonObject = new Dragon(id, name, coordinates, creationDate, weight, color, character, head);
            collection.add(dragonObject);

            returningId = id;
        } catch (ClassCastException e) {
            System.out.println("Утеряны данные об объекте Dragon из-за некорректного файла");
        } catch (IncorrectDataException e) {
            System.out.println(e.getMessage());
        } catch (IdCollisionException e) {
            System.out.println("Объект Dragon утерян: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Неверный формат даты (Dragon)");
        }
        try {
            int age = ((Long) dragon.get("age")).intValue();
            dragonObject.setAge(age);
        } catch (NullPointerException | IncorrectDataException ignored) {
        }
        return returningId;
    }

    public static void saveCollection(JsonManager manager, DragonCollection collection) throws IOException {
        JSONObject json = new JSONObject();
        json.put("creationDate", DateParser.dateToString(collection.getCreationDate()));
        JSONArray dragons = new JSONArray();

        for (Dragon item : collection.getItems()) {
            JSONObject dragon = new JSONObject();
            JSONObject head = new JSONObject();
            head.put("eyesCount", item.getDragonHead().getEyesCount());
            dragon.put("head", head);

            dragon.put("character", item.getDragonCharacter().name());
            dragon.put("color", item.getColor().name());
            dragon.put("weight", item.getWeight());
            try {
                dragon.put("age", item.getAge());
            } catch (NullPointerException e) {
                dragon.put("age", null);
            }
            dragon.put("creationDate", DateParser.dateToString(item.GetCreationDate()));
            JSONObject coordinates = new JSONObject();
            coordinates.put("x", item.getCoordinates().getX());
            coordinates.put("y", item.getCoordinates().getY());
            dragon.put("coordinates", coordinates);
            dragon.put("name", item.getName());
            dragon.put("id", item.getId());

            dragons.add(dragon);
        }

        json.put("dragons", dragons);

        manager.writeJSON(json);
    }
}
