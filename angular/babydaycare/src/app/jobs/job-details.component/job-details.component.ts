import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobDTO } from '../../model/jobDTO';
import { JobService } from '../../service/job.service';
import { ApplyService } from '../../service/apply.service';

@Component({
  selector: 'app-job-details',
  standalone: false,
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {

  job: JobDTO | null = null;

  constructor(
    private route: ActivatedRoute,
    private jobService: JobService,
    private cd: ChangeDetectorRef,
    private applyService: ApplyService
  ) {}

  ngOnInit(): void {
    const jobId = Number(this.route.snapshot.paramMap.get('id'));
    if (isNaN(jobId)) {
      console.error('Invalid job ID');
      return;
    }

    this.jobService.getJobById(jobId).subscribe({
      next: (data) => {
        this.job = data;
        console.log('Loaded job details:', data);
        this.cd.markForCheck();
      },
      error: (err) => {
        console.error('Error loading job:', err);
      }
    });
  }

  onLogoError(event: any): void {
    event.target.src = 'assets/default-parent.png';
  }

  applyJob(): void {
    if (!this.job?.id || !this.job?.parentId) {
      alert('Job or parent information missing.');
      return;
    }

    const applyPayload = {
      job: { id: this.job.id },
      parent: { id: this.job.parentId }
    };

    this.applyService.applyForJob(applyPayload).subscribe({
      next: (res) => {
        console.log('Application successful:', res);
        alert('You have successfully applied for this service!');
      },
      error: (err) => {
        console.error('Application failed:', err);
        alert('Failed to apply. Please login first.');
      }
    });
  }
}

