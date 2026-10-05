interface Props {
    value: string;
    onChange: (value:string) => void
}

export default function NameInput({value, onChange}: Props){
    return (
        <div>
            <label>姓名</label>
            <input
                type='text'
                value={value}
                onChange={e => onChange(e.target.value)}
                required
            />
        </div>
    );
}