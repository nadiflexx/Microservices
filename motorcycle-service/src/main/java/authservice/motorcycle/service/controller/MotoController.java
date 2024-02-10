package authservice.motorcycle.service.controller;

import authservice.motorcycle.service.entity.Moto;
import authservice.motorcycle.service.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("moto")
public class MotoController {

    @Autowired
    private MotoService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveMoto(moto));
    }

    @GetMapping
    public ResponseEntity<List<Moto>> getAllMotos() {
        return ResponseEntity.ok(service.getAllMotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMotoById(@PathVariable String id) {
        return service.getMotoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Moto> updateMoto(@PathVariable String id, @RequestBody Moto moto) {
        return service.getMotoById(id)
                .map(motoDesired -> {
                    motoDesired.setId(id);
                    motoDesired.setBrand(moto.getBrand());
                    motoDesired.setModel(moto.getModel());

                    Moto userUpdate = service.updateMoto(motoDesired);
                    return new ResponseEntity<>(userUpdate, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMotoById(@PathVariable String id) {
        service.deleteMoto(id);
        return new ResponseEntity<>("Moto deleted succesfully", HttpStatus.OK);
    }
}
