import type {Hotels} from "../types/Hotel.ts";

export async function queryHotels(
    page: number,
    rpp: number,
    signal: AbortSignal): Promise<Hotels>{
    const token = localStorage.getItem("token");

    const response = await fetch(
        `http://localhost:8080/hotels?page=${page}&rpp=${rpp}`,
        {
            signal,
            headers: token ? {
                Authorization: `Bearer ${token}`
            } : {}
        });
    if (!response.ok) throw new Error('Query Error');

    return response.json();

}