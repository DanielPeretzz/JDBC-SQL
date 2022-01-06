package com.homework.daniel.dal;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAL <ID, Entity> {
    ID create(final Entity entity) throws Exception;
    Entity read(final ID id) throws Exception;
    void update(final Entity entity);
    void delete(final ID id) throws Exception;
    List<Entity> readAll();
}
