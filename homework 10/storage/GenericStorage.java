package ru.epam.javacore.lesson_9_exceptions.homework.storage;

import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.common.business.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class GenericStorage<Entity extends BaseEntity> {

    public static EntityCollection entitiesCollection = new EntityCollection();

    public void addEntity(Entity entity) {
        entitiesCollection.addEntity(entity);
    }

    public <Entity> Entity getEntityByID(long id) {
        Entity answer = (Entity) entitiesCollection.searchByIDs(id);
        if (answer != null) {
            return answer;
        } else {
            System.out.println("Object not exists");
            return null;
        }
    }

    public static void deleteCargoByID(long id) {
        entitiesCollection.deleteByID(id);
    }

    public static <Entity> List<Entity> getEntitiesByName(String name) {
        List<Entity> answer = entitiesCollection.searchByName(name);
        return answer;
    }

    public static void displayAllEntities() {
        entitiesCollection.displayEntities();
    }

    private GenericStorage() {
    }


    private static class EntityCollection<Entity extends BaseEntity> {
        List<Entity> entities;

        EntityCollection() {
            this.entities = new ArrayList<>();
        }

        void displayEntities() {
            for (Entity i : entities) {
                if (i != null) {
                    System.out.println(i);
                }
            }
        }

        Entity searchByIDs(long id) {
            for (Entity entity : entities) {
                if (entity != null && id == entity.getId()) {
                    return entity;
                }
            }
            return null;
        }

        List<Entity> searchByName(String name) {
            name = name.toLowerCase();
            List<Entity> answer = new ArrayList<>();
            int counter = 0;
            for (Entity entity : entities) {
                if (entity != null && entity.getName().toLowerCase().equals(name)) {
                    answer.add(entity);
                }
            }
            return answer;
        }


        private void deleteByID(long id) {
            List<Entity> modifier = new ArrayList<>();
            for (Entity entity : entities) {
                if (entity.getId() != Long.valueOf(id)) {
                    modifier.add(entity);
                }
            }
            entities = modifier;
        }

        public void addEntity(Entity entity) {
            entities.add(entity);
        }
    }
}