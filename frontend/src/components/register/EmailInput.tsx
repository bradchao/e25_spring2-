interface Props {
    value: string;
    onChange: (value:string) => void
}

export default function EmailInput({value, onChange}: Props){
    return (
        <div>
            <label>電子郵件</label>
            <input
                type='email'
                value={value}
                onChange={e => onChange(e.target.value)}
                required
            />
        </div>
    );
}