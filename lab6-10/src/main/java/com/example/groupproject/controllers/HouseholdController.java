package com.example.groupproject.controllers;

import com.example.groupproject.dto.HouseholdDTO;
import com.example.groupproject.services.HouseholdService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/households")
public class HouseholdController {

  private final HouseholdService householdService;

  public HouseholdController(HouseholdService householdService) {
    this.householdService = householdService;
  }

  @GetMapping
  public List<HouseholdDTO> getAllHouseholds() {
    return householdService.getAllHouseholds();
  }

  @GetMapping("/{eircode}")
  public HouseholdDTO getHousehold(@PathVariable String eircode) {
    return householdService.getHouseholdByEircode(eircode);
  }

  @GetMapping("/no-pets")
  public List<HouseholdDTO> getHouseholdsWithNoPets() {
    return householdService.getHouseholdsWithNoPets();
  }

  @PostMapping
  @Secured("ROLE_ADMIN")
  public HouseholdDTO createHousehold(@RequestBody @Valid HouseholdDTO householdDTO) {
    return householdService.createHousehold(householdDTO);
  }

  @DeleteMapping("/{eircode}")
  @Secured("ROLE_ADMIN")
  public void deleteHousehold(@PathVariable String eircode) {
    householdService.deleteHousehold(eircode);
  }
}