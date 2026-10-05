import "./brad.css"

type OrderType = {
    id: number;
    name: string;
    cups: number;
    price: number
}

export default function App() {
    const orders: OrderType[] = [
        {id: 1, name: "name1", price: 100, cups: 2},
        {id: 2, name: "name2", price: 120, cups: 0},
        {id: 3, name: "name3", price: 140, cups: 3},
    ];

    const names = orders.map(order => {
        return order.name;
    });

    const selectedOrders = orders.filter(
        order => {
            return order.cups > 0;
        }
    );

    const totalCups = orders.reduce(
        (total, order) => {
            return total + order.cups
        }, 0
    );

    const totalAmount = orders.reduce(
        (total, order) => {
            return total + order.cups * order.price
        }, 0
    );

    return (
        <div>
            <pre style={{textAlign: 'left'}}>
                {JSON.stringify(orders,null,2)}
            </pre>
            <hr />
            <pre style={{textAlign: 'left'}}>
                {JSON.stringify(names,null,2)}
            </pre>
            <hr />
            <pre style={{textAlign: 'left'}}>
                {JSON.stringify(selectedOrders,null,2)}
            </pre>
            <hr />
            <div>{totalCups} / {totalAmount}</div>
        </div>
    );


}