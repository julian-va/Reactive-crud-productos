package org.jvillegas.reactivecruddemo.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jvillegas.reactivecruddemo.app.data.entity.Producto;
import org.jvillegas.reactivecruddemo.app.data.repository.ProductoRepository;
import org.jvillegas.reactivecruddemo.app.dto.ProductoDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductoServiceImpl implements ProductoService{
    private final ProductoRepository productoRepository;

    public Mono<Producto> getById(String id) {
        log.info("Id a buscar {}.", id);
        return productoRepository.findById(id);
    }

    public Flux<Producto> getAll() {
        log.info("Listando todos los productos");
        return productoRepository.findAll();
    }

    public Mono<Producto> save(ProductoDto productoDto) {
        log.info("Producto a guardar {}.", productoDto);
        return productoRepository.save(Producto
                .builder()
                .description(productoDto.getDescription())
                .price(productoDto.getPrice())
                .name(productoDto.getName())
                .stock(productoDto.getStock())
                .build());
    }

    public Mono<Producto> update(String id, ProductoDto productoDto) {
        return productoRepository.findById(id).flatMap(producto1 -> {
            producto1.setDescription(productoDto.getDescription());
            producto1.setName(productoDto.getName());
            producto1.setStock(productoDto.getStock());
            producto1.setPrice(productoDto.getPrice());
            return productoRepository.save(producto1);
        });
    }

    public Mono<Void> deleteById(String id){
        log.info("Producto id a eliminar {}.", id);
        return productoRepository.deleteById(id);
    }
}
