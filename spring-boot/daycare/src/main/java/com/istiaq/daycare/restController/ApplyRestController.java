package com.istiaq.daycare.restController;

import com.istiaq.daycare.dto.ApplyDTO;
import com.istiaq.daycare.dto.ViewDetailsDTO;
import com.istiaq.daycare.entity.Apply;
import com.istiaq.daycare.entity.Caregiver;
import com.istiaq.daycare.entity.Parent;
import com.istiaq.daycare.repository.ICaregiverRepo;
import com.istiaq.daycare.repository.IParentRepo;
import com.istiaq.daycare.repository.IApplyRepo;
import com.istiaq.daycare.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplyRestController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ICaregiverRepo caregiverRepo;

    @Autowired
    private IParentRepo parentRepo;

    @Autowired
    private IApplyRepo applyRepo;

    // ---------------- Create a new application ----------------
    @PostMapping
    public ResponseEntity<ViewDetailsDTO> createApplication(@RequestBody Apply apply, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        String caregiverEmail = authentication.getName();
        Caregiver caregiver = caregiverRepo.findByUserEmail(caregiverEmail)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        Apply createdApply = applyService.createApplication(apply, caregiver);
        ViewDetailsDTO dto = applyService.mapToDTO(createdApply);

        return ResponseEntity.ok(dto);
    }

    // ---------------- Get all applications ----------------
    @GetMapping
    public ResponseEntity<List<Apply>> getAllApplications() {
        return ResponseEntity.ok(applyService.getAllApplications());
    }

    // ---------------- Get application by ID ----------------
    @GetMapping("{id}")
    public ResponseEntity<Apply> getApplicationById(@PathVariable Long id) {
        Optional<Apply> apply = applyService.getApplicationById(id);
        return apply.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ---------------- Update an application ----------------
    @PutMapping("{id}")
    public ResponseEntity<Apply> updateApplication(@PathVariable Long id, @RequestBody Apply updatedApply) {
        try {
            Apply updated = applyService.updateApplication(id, updatedApply);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ---------------- Delete an application ----------------
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applyService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- Get logged-in caregiver's applications ----------------
    @GetMapping("/my")
    public ResponseEntity<List<ViewDetailsDTO>> getMyApplications(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        String username = authentication.getName();
        Caregiver caregiver = caregiverRepo.findByUserEmail(username)
                .orElseThrow(() -> new RuntimeException("Caregiver not found"));

        List<ViewDetailsDTO> applications = applyService.getAppliesByCaregiver(caregiver.getId());
        return ResponseEntity.ok(applications);
    }

    // ---------------- Get all applications for a job (Parent) ----------------
    @GetMapping("/applicant/{jobId}")
    public ResponseEntity<List<ViewDetailsDTO>> getApplicationsForJob(
            @PathVariable Long jobId,
            Authentication authentication
    ) {
        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        String email = authentication.getName();
        Parent parent = parentRepo.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        List<ViewDetailsDTO> applications = applyService.getApplicationsByJob(parent.getId(), jobId);
        return ResponseEntity.ok(applications);
    }

    // ---------------- Get details of a single caregiver ----------------
    @GetMapping("/caregiver/{caregiverId}")
    public ResponseEntity<ViewDetailsDTO> getCaregiverDetails(@PathVariable Long caregiverId) {
        Apply apply = applyRepo.findFirstByCaregiver_Id(caregiverId)
                .orElseThrow(() -> new RuntimeException("Application not found for caregiver ID " + caregiverId));

        ViewDetailsDTO dto = applyService.mapToDTO(apply);
        return ResponseEntity.ok(dto);
    }
}
