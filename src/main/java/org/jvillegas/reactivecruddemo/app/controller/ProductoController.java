package org.jvillegas.reactivecruddemo.app.controller;

import lombok.RequiredArgsConstructor;
import org.jvillegas.reactivecruddemo.app.data.entity.Producto;
import org.jvillegas.reactivecruddemo.app.dto.ProductoDto;
import org.jvillegas.reactivecruddemo.app.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/productos")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<Producto>> getById(@PathVariable String id) {
        return productoService.getById(id).map(producto -> ResponseEntity.ok(producto))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Producto>>> getAll() {
        return Mono.just(ResponseEntity.ok(productoService.getAll()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Producto>> save(@RequestBody ProductoDto productoDto) {
        return productoService.save(productoDto).map(producto ->
                ResponseEntity.status(HttpStatus.CREATED).body(producto));
    }

    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<Producto>> update(@PathVariable String id,@RequestBody ProductoDto productoDto){
        return productoService.update(id,productoDto).map(producto ->
                ResponseEntity.status(HttpStatus.ACCEPTED).body(producto))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> deleteById(String id){
        return productoService.deleteById(id).map(unused ->
                        new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
