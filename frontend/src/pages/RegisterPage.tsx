import EmailInput  from "../components/register/EmailInput.tsx";
import {useState} from "react";
import type {Gender, RegisterForm} from "../types/RegisterForm.ts";
import PasswdInput from "../components/register/PasswdInput.tsx";
import NameInput from "../components/register/NameInput.tsx";
import GenderInput from "../components/register/GenderInput.tsx";
import AreaSelect from "../components/register/AreaSelect.tsx";
import HabitCheckBox from "../components/register/HabitCheckBox.tsx";
import IconUpload from "../components/register/IconUpload.tsx";

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


    return (
        <main>
            <h1>會員註冊</h1>
            <form>
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

        </main>
    );

}