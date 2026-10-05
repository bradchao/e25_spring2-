export type Gender = "MALE" | "FEMALE"

export interface RegisterForm {
    email: string;
    passwd: string;
    name: string;
    gender: Gender;
    area: string;
    habits: string[];
    icon: string | null;
}