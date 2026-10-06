
interface TextInputProps {
    label: string;
    type: "text" | "password" | "email" | "number";
    value: string;
    onChange: (value: string) => void;

    // 以下為選項
    placeholder?: string;
    required?:boolean;
}

export default function TextInput({label, type, value, onChange}: TextInputProps){
    return (
        <div>
            <label>{label} : </label>
            <input
                type={type}
                value={value}
                onChange={e => onChange(e.target.value)}
            />
        </div>
    );
}
