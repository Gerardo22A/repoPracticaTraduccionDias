package com.example.idatdsw1accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/mascotas")
public class MainController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewMascota(@RequestParam String nombre, @RequestParam String raza,
            @RequestParam String propietario) {
        Mascota mascota = new Mascota(nombre, raza, propietario);
        mascotaRepository.save(mascota);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Mascota> getAllMascotas() {
        System.out.println("llego");
        return mascotaRepository.findAll();
    }

    @PutMapping(path = "/edit")
    public @ResponseBody String editMascota(@RequestParam String nombre, @RequestParam String raza,
            @RequestParam String propietario, @RequestParam Integer id) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        if (mascota != null) {
            mascota.setNombre(nombre);
            mascota.setRaza(raza);
            mascota.setPropietario(propietario);
            mascotaRepository.save(mascota);
            return "Edited";
        } else {
            return "Mascota not found";
        }
    }

    @GetMapping(path = "/ver/{id}")
    public @ResponseBody Mascota getMascota(@PathVariable Integer id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @DeleteMapping(path = "/del")
    public @ResponseBody String deleteMascota(@RequestParam Integer id) {
        Mascota mascota = mascotaRepository.findById(id).orElse(null);
        if (mascota != null) {
            mascotaRepository.delete(mascota);
            return "Deleted";
        } else {
            return "Mascota not found";
        }
    }

    @GetMapping(path = "/get/report")
    public @ResponseBody List<Map<String, Object>> getReport() {
        String sql = "SELECT CONCAT(nombre, ' (nombre) ==> (propietario: ', propietario, ')') as MIS_MASCOTAS FROM mascota;";
        return jdbcTemplate.queryForList(sql);
    }
}
