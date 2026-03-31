package com.sliit.item_service;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private List<Map<String, String>> items = new ArrayList<>(List.of(
        Map.of("name", "Book"),
        Map.of("name", "Laptop"),
        Map.of("name", "Phone")
    ));

    @GetMapping
    public List<Map<String, String>> getItems() {
        return items;
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody Map<String, String> item) {
        items.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added: " + item.get("name"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable int id) {
        if (id < 0 || id >= items.size())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(items.get(id));
    }
}