package juUnpa.API.Controllers;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.information;
import juUnpa.API.Services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/info")
@CrossOrigin("*")
public class InformationController {

    @Autowired
    InfoService infoService;

    @GetMapping("/list")
    public ResponseEntity<List<?>> listGames(){
        return new ResponseEntity<>(infoService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> gamesOfId(@PathVariable int id){
        Optional<information> info = infoService.listarPorId(id);

        if(info != null) {
            return new ResponseEntity<>(infoService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody information info){
        return new ResponseEntity<>(infoService.guardar(info),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> uapdate(@PathVariable int id, @RequestBody information info){

        Optional<information> infoOptional=infoService.listarPorId(id);

        infoOptional.get().setTitle_info(info.getTitle_info());
        infoOptional.get().setBody_info(info.getBody_info());

        infoService.guardar(infoOptional.get());
        return  ResponseEntity.ok().build();


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        infoService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
