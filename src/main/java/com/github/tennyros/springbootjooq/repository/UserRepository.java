package com.github.tennyros.springbootjooq.repository;

import jooq.tables.records.AppUserRecord;
import lombok.RequiredArgsConstructor;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.Tables.APP_USER;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final DSLContext dsl;

    public AppUserRecord createUser(AppUserRecord userRecord) {
        return dsl
                .insertInto(APP_USER)
                .set(APP_USER.USERNAME, userRecord.getUsername())
                .set(APP_USER.EMAIL, userRecord.getEmail())
                .returning()
                .fetchOne();
    }

    public List<AppUserRecord> getAllUsers() {
        return dsl.selectFrom(APP_USER).fetchInto(AppUserRecord.class);
    }
}
