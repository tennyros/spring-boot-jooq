package com.github.tennyros.springbootjooq.repository;

import com.example.generated.Tables;
import com.example.generated.tables.records.UserRecord;
import lombok.RequiredArgsConstructor;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final DSLContext dsl;

    public UserRecord createUser(UserRecord userRecord) {
        return dsl
                .insertInto(Tables.USER)
                .set(Tables.USER.USERNAME, userRecord.getUsername())
                .set(Tables.USER.EMAIL, userRecord.getEmail())
                .returning()
                .fetchOne();
    }

    public List<UserRecord> getAllUsers() {
        return dsl.selectFrom(Tables.USER).fetchInto(UserRecord.class);
    }
}
