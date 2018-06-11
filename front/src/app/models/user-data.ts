import {Role} from './role';

export interface UserData {

  id: number;
  username: string;
  email: string;
  role: Role;
}
