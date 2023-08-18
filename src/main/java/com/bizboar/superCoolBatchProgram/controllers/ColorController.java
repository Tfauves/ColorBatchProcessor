package com.bizboar.superCoolBatchProgram.controllers;

import com.bizboar.superCoolBatchProgram.domain.RALClassicColors;
import com.bizboar.superCoolBatchProgram.models.ColorDTO;
import com.bizboar.superCoolBatchProgram.repositories.RALClassicColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/public/api/color")
public class ColorController {

    @Autowired
    private RALClassicColorsRepository colorsRepository;

    @GetMapping
    public @ResponseBody List<RALClassicColors> getAllColors() {
        return colorsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getColorById(@PathVariable Long id) {
        Optional<RALClassicColors> colorOptional = colorsRepository.findById(id);

        if (colorOptional.isPresent()) {
            RALClassicColors colorEntity = colorOptional.get();
            ColorDTO colorDTO = new ColorDTO();
            colorDTO.setName(colorEntity.getEnglish());
            colorDTO.setHex(colorEntity.getHex());
            colorDTO.setLrv(colorEntity.getLrvCategory());
            return ResponseEntity.ok(colorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
