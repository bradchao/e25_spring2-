import type {LoginRequest, LoginResponse} from "../types/Login.ts";

const API_URL = "http://localhost:8080/members/login";

export async function login(request: LoginRequest): Promise<LoginResponse> {
    const  response =await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(request)
    });

    if (!response.ok) throw new Error("Login Failure");

    return response.json();

}