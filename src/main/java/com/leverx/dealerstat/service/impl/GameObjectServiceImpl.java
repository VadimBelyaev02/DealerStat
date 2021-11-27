package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.GameObject;
import com.leverx.dealerstat.repository.GameObjectsRepository;
import com.leverx.dealerstat.service.GameObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameObjectServiceImpl implements GameObjectService {

    private final GameObjectsRepository repository;

    @Autowired
    public GameObjectServiceImpl(GameObjectsRepository repository) {
        this.repository = repository;
    }

    @Override
    public GameObject findById(Long gameObjectId) {
        return repository.findById(gameObjectId).orElseThrow(() -> {
            throw new NotFoundException("Game object is not found");
        });
    }

    @Override
    public List<GameObject> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(GameObject gameObject) {
        repository.save(gameObject);
    }

    @Override
    public List<GameObject> findAllByAuthorId(Long id) {
        return repository.findAllByAuthorId(id);
    }

    @Override
    public void update(GameObject gameObject, Long id) {
        GameObject gameObjectFromDB = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Game object is not found");
        });
        gameObjectFromDB.setDateOfUpdating(new Date());
        gameObjectFromDB.setPrice(gameObject.getPrice());
        gameObjectFromDB.setDescription(gameObject.getDescription());
        gameObjectFromDB.setTitle(gameObject.getTitle());
    }
}
