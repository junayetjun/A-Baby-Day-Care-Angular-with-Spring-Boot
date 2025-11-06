import { ChangeDetectorRef, Component } from '@angular/core';
import { ApplyDTO } from '../../model/applyDTO';
import { ApplyService } from '../../service/apply.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-parentjobapplication',
  standalone: false,
  templateUrl: './parentjobapplication.html',
  styleUrls: ['./parentjobapplication.css'] // fixed typo
})
export class Parentjobapplication {

  applications: ApplyDTO[] = [];
  loading = false;
  errorMessage = '';
  jobId!: number;

  constructor(
    private applyService: ApplyService,
    private route: ActivatedRoute,
    private cd: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {
    const paramJobId = this.route.snapshot.paramMap.get('id');
    if (paramJobId) {
      this.jobId = Number(paramJobId);
      this.fetchApplications();
    } else {
      this.errorMessage = 'Job ID not provided';
    }
  }

  fetchApplications(): void {
    this.loading = true;
    this.applyService.getApplicationsForJob(this.jobId).subscribe({
      next: (data) => {
        this.applications = data;
        this.cd.markForCheck();
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Failed to load applications';
        this.loading = false;
      }
    });
  }

  // navigate to caregiver detail view
  viewDetails(caregiverId: number) {
    this.router.navigate(['/cdetails', caregiverId]);
  }

  // ✅ Delete application
  deleteApplication(applicationId: number) {
    if (confirm('Are you sure you want to delete this application?')) {
      this.applyService.deleteApplication(applicationId).subscribe({
        next: () => {
          this.applications = this.applications.filter(app => app.id !== applicationId);
          this.cd.markForCheck();
        },
        error: (err) => {
          console.error(err);
          alert('Failed to delete application');
        }
      });
    }
  }
}
