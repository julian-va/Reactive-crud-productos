package org.jvillegas.reactivecruddemo.app;

import org.jvillegas.reactivecruddemo.app.data.entity.Producto;
import org.jvillegas.reactivecruddemo.app.data.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class CrudProductoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudProductoApplication.class, args);
    }

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        reactiveMongoTemplate.dropCollection("productos").subscribe();

        var productos = List.of(Producto.builder().name("Producto 1").stock(10).price(1000).description("description 1").build(), Producto.builder().name("Producto 2").stock(100).price(100).description("description 2").build(), Producto.builder().name("Producto 3").stock(60).price(5000).description("description 3").build(), Producto.builder().name("Producto 4").stock(28).price(51000).description("description 4").build(), Producto.builder().name("Producto 5").stock(90).price(91000).description("description 5").build());
        repository.saveAll(productos).subscribe();
    }
}
