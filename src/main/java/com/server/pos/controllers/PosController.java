package com.server.pos.controllers;

import com.server.pos.dtos.PosDto;
import com.server.pos.models.Pos;
import com.server.pos.services.PosService;
import com.server.pos.util.Response;
import com.server.pos.util.ResponseBuild;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pos")
public class PosController {
    private final PosService posService;
    private  final ResponseBuild<Pos> posResponseBuild;

    @GetMapping("/")
    public ResponseEntity<Response> getAllPos(){
        return new ResponseEntity<>(posResponseBuild.listResponseFunction
                .apply(posService.getPos()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getOnePos(@PathVariable Integer id){
        return new ResponseEntity<>(posResponseBuild.responseFunction
                .apply(posService.getOnePos(id)),HttpStatus.OK);
    }
    @PostMapping("/save-Pos")
    public ResponseEntity<Response> createPos(@RequestBody PosDto posDto){
        return new ResponseEntity<>(posResponseBuild.responseFunction
                .apply(posService.createPos(posDto)),HttpStatus.OK);
    }
    @PutMapping("/update-pos/{id}")
    public ResponseEntity<Response> updatePos(@PathVariable("id") Integer id,@RequestBody PosDto posDto){
        return new ResponseEntity<>(posResponseBuild.responseFunction
                .apply(posService.updatePos(id,posDto)),HttpStatus.OK);
    }
    @DeleteMapping("/delete-pos/{id}")
    public ResponseEntity <Boolean> deletePos(@PathVariable ("id")Integer id){
        return new ResponseEntity<>(posService.deletePos(id),HttpStatus.OK);
    }
}
