interface Props {
    value: string;
    onChange: (value:string) => void
}

export default function PasswdInput({value, onChange}: Props){
    return (
        <div>
            <label>密碼</label>
            <input
                type='password'
                value={value}
                onChange={e => onChange(e.target.value)}
                required
            />
        </div>
    );
}