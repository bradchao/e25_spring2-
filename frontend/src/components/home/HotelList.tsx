import {useState} from "react";
import type {Hotels} from "../../types/Hotel.ts";

export default function HotelList(){
    const [page, setPage] = useState(0);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [retry, setRetry] = useState(0);
    const [result, setResult] = useState<Hotels | null>(null);
    const rpp = 10;


    return (
        <div>
            <h3>Hotel Table</h3>
            <hr />
            {result?.data.length === 0 ? (<p>No Data</p>) : (
                <div>
                    <div>
                        <p>TotalItem: {result?.total}</p>
                        <p>{result?.page} / {result?.totalPage} </p>
                    </div>
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th>No</th>
                                <th>Name</th>
                                <th>Addr</th>
                                <th>Tel</th>
                            </tr>
                            </thead>
                            <tbody>
                            {result?.data.map(hotel => (
                                <tr key={hotel.id}>
                                    <td>{hotel.id}</td>
                                    <td>{hotel.name}</td>
                                    <td>{hotel.addr}</td>
                                    <td>{hotel.tel}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            )}

        </div>
    );

}