package kata.preproj.service;

import kata.preproj.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void delete(Long id);
}