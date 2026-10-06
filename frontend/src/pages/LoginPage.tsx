import TextInput from "../components/login/TextInput.tsx";
import {type SyntheticEvent, useState} from "react";
import type {LoginRequest} from "../types/Login.ts";
import {login} from "../services/AuthService.ts";


export default function LoginPage() {
    const [form, setForm] = useState<LoginRequest>({
        account: "",
        passwd: ""
    });

    const [message, setMessage] = useState("");

    const doSubmit = async (
        e: SyntheticEvent<HTMLFormElement, SubmitEvent>){
        e.preventDefault();

        try {
            const result = await login(form);
            if (result.success && result.member && result.token) {
                localStorage.setItem("token", result.token);
                localStorage.setItem("member", JSON.stringify(result.member));
                console.log("OK");
            } else {
                setMessage("Login Failure");
            }
        }catch (e) {
            console.log(e);
            setMessage("System Busy");
        }
    }


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
            {message && (<p>{message}</p>)}
        </main>
    );
}