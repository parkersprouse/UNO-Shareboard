package com.bayou.ras.impl;

import com.bayou.domains.User;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Service    //registers this java class as a Service bean so that the container is aware of it for injection
public class UserResourceAccessor implements IResourceAccessor<User> {
    @Autowired
    IUserRepository repo;

    @Override
    public User find(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Iterable<User> findAll() {
        return repo.findAll();
    }

    public User findByEmail(String email) {
        return repo.findByEmailIgnoreCase(email);
    }

    public User findByAccountName(String accountName) {
        return repo.findByAccountNameIgnoreCase(accountName);
    }

    @Override
    public Long add(User entity) {
        return repo.save(entity).getId();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}