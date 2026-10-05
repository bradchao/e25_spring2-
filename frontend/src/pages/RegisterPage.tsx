import EmailInput  from "../components/register/EmailInput.tsx";
import {useState} from "react";
import type {RegisterForm} from "../types/RegisterForm.ts";

export default function RegisterPage() {
    const [form, setForm] = useState<RegisterForm>()


    return (
        <main>
            <h1>會員註冊</h1>
            <form>
                <EmailInput
                    value={form?.email}
                    onChange={}
                />
                <label>Email</label>: <input />
                <label>Email</label>: <input />
                <button type="submit">註冊</button>
            </form>

        </main>
    );

}