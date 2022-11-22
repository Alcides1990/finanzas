package finanzas.finanzas.controllers;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import finanzas.finanzas.models.PersonaModel;
import finanzas.finanzas.repository.IPersonaRepository;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {
    
    @Autowired
    private IPersonaRepository repo;

    @RequestMapping( value = "/findAll",method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<PersonaModel>> findAll(){
        List<PersonaModel> datos= repo.findAll();
        try{
            if(datos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(datos,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByCodigo/{codigo}")
    public ResponseEntity<PersonaModel> findByCodigo(@PathVariable("codigo") String codigo){
       try{
        PersonaModel datos= repo.findByCodigo(codigo);
        if(datos == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.out.println(datos);
        return new ResponseEntity<>(datos, HttpStatus.OK);
       }catch(Exception ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PersonaModel persona){
        try{
            String retorno= repo.save(persona);
            return new ResponseEntity<>(retorno, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id,@RequestBody PersonaModel persona){
        try {
            return new ResponseEntity<>(repo.update(id,persona), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(repo.delete(id), HttpStatus.OK);
        }catch(Exception ex){   
            return new ResponseEntity<>(ex.getMessage().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
