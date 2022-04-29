package org.palestiner.keys.security;

import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.palestiner.keys.entity.Dictionary;

@RowLevelRole(
        name = "Can see only Dictionaries created by themselves",
        code = "dictionaries-created-by-themselves")
public interface CreatedByMeDictionaryRole {

    @JpqlRowLevelPolicy(
            entityClass = Dictionary.class,
            where = "{E}.createdBy = :current_user_username")
    void dictionary();
}
