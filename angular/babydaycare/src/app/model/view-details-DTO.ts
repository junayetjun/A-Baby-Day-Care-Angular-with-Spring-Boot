
import { CaregiverDTO } from "./caregiverDTO";


export interface ViewDetailsDTO {
  id: number;
  jobId: number;
  jobTitle: string;
  parentId: number;
  parentName: string;
  caregiverId: number;
  caregiverName: string;
  caregiver?: CaregiverDTO; // optional (matches overloaded constructor in Java)
}
