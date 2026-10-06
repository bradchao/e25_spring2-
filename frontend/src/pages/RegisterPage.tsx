import EmailInput  from "../components/register/EmailInput.tsx";
import {type SyntheticEvent, useState} from "react";
import type {Gender, RegisterForm} from "../types/RegisterForm.ts";
import PasswdInput from "../components/register/PasswdInput.tsx";
import NameInput from "../components/register/NameInput.tsx";
import GenderInput from "../components/register/GenderInput.tsx";
import AreaSelect from "../components/register/AreaSelect.tsx";
import HabitCheckBox from "../components/register/HabitCheckBox.tsx";
import IconUpload from "../components/register/IconUpload.tsx";
import {registerMember} from "../services/MemberService.ts";

export default function RegisterPage() {
    const [form, setForm] = useState<RegisterForm>({
        email: "",
        passwd: "",
        name: "",
        gender: "MALE",
        area: "",
        habits: [],
        icon: null
    })

    const [message, setMessage] = useState("");

    const doSubmit = async (
        e: SyntheticEvent<HTMLFormElement, SubmitEvent>) => {
        e.preventDefault();
        try{
            const result = await registerMember(form);
            console.log(result);
            if (result.success){
                window.location.href = "/login";
            }else{
                //
                setMessage("註冊失敗(1)")
            }

        }catch (e) {
            console.log(e)
            setMessage("註冊失敗(2)")
        }

    }



    return (
        <main>
            <h1>會員註冊</h1>
            <form onSubmit={doSubmit}>
                <EmailInput
                    value={form.email}
                    onChange={email => setForm({...form, email})}
                />
                <PasswdInput
                    value={form.passwd}
                    onChange={passwd => setForm({...form, passwd})}
                />
                <NameInput
                    value={form.name}
                    onChange={name => setForm({...form, name})}
                />
                <GenderInput
                    value={form.gender}
                    onChange={gender => setForm({...form, gender})}
                />
                <AreaSelect
                    value={form.area}
                    onChange={area => setForm({...form, area})}
                />
                <HabitCheckBox
                    values={form.habits}
                    onChange={habits => setForm({...form, habits})}
                />
                <IconUpload
                    value={form.icon}
                    onChange={icon => setForm({...form, icon})}
                />

                <button type="submit">註冊</button>
            </form>
            {message && (<p>{message}</p>) }
        </main>
    );

}