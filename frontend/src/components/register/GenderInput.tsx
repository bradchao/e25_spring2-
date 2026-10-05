import type {Gender} from "../../types/RegisterForm.ts";

interface Props {
    value: Gender;
    onChange: (value:Gender) => void
}

export default function GenderInput({value, onChange}: Props){
    return (
        <div>
            <span>性別</span>
            <label>
                <input
                    type="radio"
                    name="gender"
                    value="MALE"
                    checked={value === "MALE"}
                    onChange={() => onChange("MALE")}
                />男
            </label>
            <label>
                <input
                    type="radio"
                    name="gender"
                    value="FEMALE"
                    checked={value === "FEMALE"}
                    onChange={() => onChange("FEMALE")}
                />女
            </label>
        </div>
    );
}