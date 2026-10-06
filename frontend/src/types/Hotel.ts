export type Hotel = {
    id: number;
    name: string;
    addr: string | null;
    tel: string | null;
}

export type Hotels = {
    "data": Hotel[];
    "total":number;
    "totalPage":number;
    "page":number;
    "isLast":boolean;
}