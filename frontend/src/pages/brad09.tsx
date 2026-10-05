import "./brad.css"
import {useState} from "react";

type OrderType = {
    id: number;
    name: string;
    cups: number;
    price: number
}
type OrderCardProps = OrderType & {
    onChange: (id: number, amount: number) => void;
}

function OrderCard({id, name, cups, price, onChange}:OrderCardProps){
    return (
        <article className='card'>
            <div>Item: {name}</div>
            <div>Price: {price}</div>
            <button onClick={()=> onChange(id, 1)}>加一</button>
            <button disabled={cups === 0} onClick={()=> onChange(id, -1)}>減一</button>
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

    //const [totalCups, setTotalCups] = useState(0);

    function doChange(id:number, amount: number) {
        setOrders(orderList => orderList.map(
            order => {
                if (order.id === id){
                    return {...order, cups: order.cups + amount}
                }
                return order;
            }
        ))
    }

    const totalCups = orders.reduce(
        (total, order) => {
            return total + order.cups;
        }, 0
    );
    const totalAmount = orders.reduce(
        (total, order) => {
            return total + order.cups * order.price
        }, 0
    );


    return (
        <div className='container'>
            <h1>訂單系統</h1>
            <div className='list'>
                {orders.map(order => (
                   <OrderCard
                       key={order.id}
                       {...order}
                       onChange={doChange}
                   />
                ))

                }
            </div>
            <hr />
            {/* 以下呈現總杯數/金額*/}
            <div> {totalCups} / {totalAmount}</div>
        </div>
    );
}
