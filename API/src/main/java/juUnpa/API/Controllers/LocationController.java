package juUnpa.API.Controllers;
import juUnpa.API.Entities.Location;
import juUnpa.API.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController()
@RequestMapping("/location")
@CrossOrigin("*")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping("/list")
    public ResponseEntity<List<?>> listGames(){
        return new ResponseEntity<>(locationService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> gamesOfId(@PathVariable int id){
        Optional<Location> location = locationService.listarPorId(id);

        if(location != null) {
            return new ResponseEntity<>(locationService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Location location){
        return new ResponseEntity<>(locationService.guardar(location),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> uapdate(@PathVariable int id, @RequestBody Location location){

        Optional<Location> infoOptional=locationService.listarPorId(id);

        infoOptional.get().setName(location.getName());
        infoOptional.get().setLatitude(location.getLatitude());
        infoOptional.get().setLongitude(location.getLongitude());



        locationService.guardar(infoOptional.get());
        return  ResponseEntity.ok().build();


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        locationService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
