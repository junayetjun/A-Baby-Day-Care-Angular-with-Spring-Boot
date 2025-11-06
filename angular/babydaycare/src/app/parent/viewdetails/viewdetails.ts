import { ChangeDetectorRef, Component } from '@angular/core';
import { ViewDetailsDTO } from '../../model/view-details-DTO';
import { ApplyService } from '../../service/apply.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-viewdetails',
  standalone: false,
  templateUrl: './viewdetails.html',
  styleUrls: ['./viewdetails.css'] // fixed typo: styleUrl -> styleUrls
})
export class Viewdetails {

  caregiverDetails?: ViewDetailsDTO; // single caregiver details
  loading = false;
  errorMessage = '';

  caregiverId!: number;

  constructor(
    private applyService: ApplyService,
    private route: ActivatedRoute,
    private cd: ChangeDetectorRef,
    private router: Router
  ) { }

  ngOnInit(): void {
    const paramCaregiverId = this.route.snapshot.paramMap.get('id'); // expecting URL like /cdetails/:id
    if (paramCaregiverId) {
      this.caregiverId = Number(paramCaregiverId);
      this.fetchCaregiverDetails();
    } else {
      this.errorMessage = 'Caregiver ID not provided';
    }
  }

  fetchCaregiverDetails(): void {
    this.loading = true;

    this.applyService.getViewDetailsForCaregiver(this.caregiverId).subscribe({
      next: (data) => {
        this.caregiverDetails = data;
        console.log('Caregiver details:', this.caregiverDetails);
        this.cd.markForCheck();
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Failed to load caregiver details';
        this.loading = false;
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/parent-applications']); // adjust route as needed
  }
}
