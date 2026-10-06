import type {RegisterForm} from "../types/RegisterForm.ts";

const API_URL = "http://localhost:8080/api/members";

export async function registerMember(form: RegisterForm) {
    console.log(JSON.stringify(form))

    const response = await fetch(API_URL,{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(form)
    });

    if (!response.ok){
        throw new Error("註冊失敗")
    }
    return response.json();
}