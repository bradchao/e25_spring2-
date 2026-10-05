import "./brad.css"
import {useState} from "react";

type Order = {
    id: number;
    name: string;
    cups: number
}

export default function () {
    const [orders, setOrders] = useState<Order[]>([
        {id:1, name:'拿鐵', cups:0},
        {id:2, name:'摩卡', cups:0},
        {id:3, name:'曼特寧', cups:0}
    ]);

    function addCup(id: number){
        setOrders(orderList => orderList.map(
            order =>
                order.id === id?
                    {...order, cups : order.cups + 1}
                    : order
        ))
    }

    return orders.map(order => (
        <button key={order.id} onClick={() => addCup(order.id)}>
            {order.name} : {order.cups} 杯
        </button>
    ));
}
