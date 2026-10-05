interface Props {
    value: string;
    onChange: (value:string) => void
}

const areas = [
    {value: "", label: "請選擇地區"},
    {value: "401", label: "北屯區"},
    {value: "402", label: "南屯區"},
    {value: "403", label: "西屯區"},
];

export default function AreaSelect({value, onChange}: Props) {
    return (
        <div>
            <label>地區</label>
            <select value={value}
                    onChange={e => onChange(e.target.value)}>
                {areas.map(area => (
                    <option key={area.value}
                            value={area.value}>
                        {area.label}
                    </option>
                ))}
            </select>

        </div>
    );

}
