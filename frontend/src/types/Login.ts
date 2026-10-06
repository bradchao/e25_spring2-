import type {Member} from "./Member.ts";

export interface LoginRequest {
    account: string;
    passwd: string;
}

export interface LoginResponse {
    success: boolean;
    member?: Member;
    token?: string;
}