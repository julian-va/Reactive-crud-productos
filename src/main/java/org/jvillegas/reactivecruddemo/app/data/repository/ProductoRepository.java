package org.jvillegas.reactivecruddemo.app.data.repository;

import org.jvillegas.reactivecruddemo.app.data.entity.Producto;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<Producto,String> {
}
