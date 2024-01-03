package com.demo.application.dao;

import com.demo.application.entity.User;
import com.demo.application.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOImpl implements UserDAO {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Ahmet", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Carl", LocalDate.now().minusYears(45)));
        users.add(new User(++usersCount, "Ana", LocalDate.now().minusYears(15)));
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    @Override
    public User findSingleUserById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals((id));

        // return users.stream().filter(predicate).findFirst().get();

        /*
            Yukarıda yoruma aldığım get() metodu "Optional" ın içindeki değeri alır
            Fakat "findFirst" boş bir Optional dönerse "NoSuchElementException" hatasını fırlatır
            Bu durumu önlemek için alttaki gibi "orElse" veya "orElseThrow" kullanılması önerilir
        */

        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    @Override
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    @Override
    public void deleteUserById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}

/*
    We communicate with the database by using JPA/Hibernate
    At first we used Static List. Later on we'll pass to JPA/Hibernate
*/