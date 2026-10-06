/*
    "icon": "data:image/*; base64, xxxxxx..."
*/

interface Props {
    value: string | null;
    onChange: (value: string | null) => void
}

export default function IconUpload({value, onChange}: Props) {

    const doFile = (
        e : React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (!file){
            onChange(null);
            return;
        }

        const  reader = new FileReader();
        reader.onload = () => {
            onChange(reader.result as string);
        }

        reader.readAsDataURL(file);

    }

    return (
        <div>
            <label>頭像</label>
            <input
                type="file"
                accept="image/*"
                onChange={doFile}
            />
            {value && (
                <div>
                    <img
                        src={value}
                        width="64"
                    />
                </div>
            )}
        </div>
    );
}