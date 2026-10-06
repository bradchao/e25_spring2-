import TextInput from "../components/login/TextInput.tsx";
import {useState} from "react";
import type {LoginRequest} from "../types/Login.ts";


export default function LoginPage() {
    const [form, setForm] = useState<LoginRequest>({
        account: "",
        passwd: ""
    });

    return (
        <main>
            <h1>Login Page</h1>
            <hr />
            <form>
                <TextInput
                    label='Account'
                    type='text'
                    value={form.account}
                    onChange={account =>
                        setForm({...form, account})}
                />
                <TextInput
                    label='Password'
                    type='password'
                    value={form.passwd}
                    onChange={passwd =>
                        setForm({...form, passwd})}
                />
                <button type='submit'>
                    Login
                </button>
            </form>
        </main>
    );
}