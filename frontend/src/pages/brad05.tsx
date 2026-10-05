import "./brad.css"
import {useState} from "react";

type OrderCardProps = {
    name: string,
    price: number
}

// private
function OrderCard({name, price}: OrderCardProps){
    const [cups, setCups] = useState(0);
    function addOne(){
        setCups(cups + 1);
    }
    function addTwo(){
        setCups(cups + 2);
    }
    function toZero() {
        setCups(0);
    }

    return  <article className='card'>
                <div>品項: {name}</div>
                <div>售價: {price}</div>
                <button onClick={addOne}>買一杯</button>
                <button onClick={addTwo}>買兩杯</button>
                <button onClick={toZero}>歸零</button>
                <div>共: {cups} 杯</div>
            </article>;
}

export default function App(){
    return <OrderCard name='拿鐵V4' price={100} />;
}