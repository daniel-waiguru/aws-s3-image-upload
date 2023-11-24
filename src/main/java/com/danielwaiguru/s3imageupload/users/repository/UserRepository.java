package com.danielwaiguru.s3imageupload.users.repository;

import com.danielwaiguru.s3imageupload.users.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private static final List<User> profiles = new ArrayList<>();

    static {
        profiles.add(new User(UUID.randomUUID(), "autuno", null));
        profiles.add(new User(UUID.randomUUID(), "_daniel", null));
    }

    public List<User> getUsers() {
        return profiles;
    }
}
