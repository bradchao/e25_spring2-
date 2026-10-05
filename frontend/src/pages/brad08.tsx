import "./brad.css"
import {useState} from "react";

type OrderType = {
    id: number;
    name: string;
    cups: number;
    price: number
}
type OrderCardProps = OrderType & {
    onAdd: (id: number) => void;
}

function OrderCard({id, name, cups, price, onAdd}:OrderCardProps){
    return (
        <article className='card'>
            <div>Item: {name}</div>
            <div>Price: {price}</div>
            <button onClick={()=> onAdd(id)}>加一</button>
            <div>{cups} 杯</div>
            <div>{cups * price} 元</div>
        </article>
    );
}
//   dftghdfgh
/*
cdfgrfdhjfdy
 */
export default function App() {
    const [orders, setOrders] = useState<OrderType[]>([
        {id: 1, name: "拿鐵", price: 100, cups: 0},
        {id: 2, name: "美式", price: 120, cups: 0},
        {id: 3, name: "摩卡", price: 140, cups: 0},
    ]);

    function addOne(id: number) {
        const newOrders = orders.map(
            order => {
                if (order.id === id){
                    return {...order, cups: order.cups + 1}
                }
                return order;
            }
        );
        setOrders(newOrders);
    }


    return (
        <div className='container'>
            <h1>訂單系統</h1>
            <div className='list'>
                {orders.map(order => (
                   <OrderCard
                       key={order.id}
                       {...order}
                       onAdd={addOne}
                   />
                ))

                }
            </div>
            <hr />
            {/* 以下呈現總杯數/金額*/}
            <div> / </div>
        </div>
    );
}
