import "./brad.css"

function OrderCard({name}: {name: string}){
    return <p className='card'>{name}</p>;
}

export default function App(){
    return <OrderCard name='拿鐵V2' />;
}