package org.jvillegas.reactivecruddemo.app.service;

import org.jvillegas.reactivecruddemo.app.data.entity.Producto;
import org.jvillegas.reactivecruddemo.app.dto.ProductoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
    Mono<Producto> getById(String id);

    Flux<Producto> getAll();

    Mono<Producto> save(ProductoDto productoDto);

    Mono<Producto> update(String id, ProductoDto productoDto);

    Mono<Void> deleteById(String id);
}
