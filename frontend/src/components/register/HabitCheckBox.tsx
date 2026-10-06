interface Props {
    values: string[];
    onChange: (values: string[]) => void
}

const habits = [
    {value: "1", label: "運動"},
    {value: "2", label: "電影"},
    {value: "3", label: "音樂"},
    {value: "4", label: "旅遊"},
];

export default function HabitCheckBox({values, onChange}: Props) {

    const doChange = (habit:string, checked:boolean) => {
        if (checked){
            onChange([...values, habit]);
        }else{
            onChange(values.filter(value => value !== habit))
        }
    }

    return (
        <div>
            <span>興趣</span>
            {habits.map(habit => (
                <label key={habit.value}>
                    <input
                        type='checkbox'
                        value={habit.value}
                        checked={values.includes(habit.value)}
                        onChange={e =>
                            doChange(habit.value, e.target.checked)}
                    />
                    {habit.label}
                </label>
            ))}
        </div>
    );
}